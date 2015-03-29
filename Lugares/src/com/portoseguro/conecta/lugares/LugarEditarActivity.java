package com.portoseguro.conecta.lugares;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.portoseguro.conecta.lugares.abstratas.ClasseActivity;
import com.portoseguro.conecta.lugares.excecoes.Erro;
import com.portoseguro.conecta.lugares.orm.bo.BOLugar;
import com.portoseguro.conecta.lugares.orm.modelos.Lugar;
import com.portoseguro.conecta.lugares.utils.Dialogos;
import com.portoseguro.conecta.lugares.utils.Sessao;
import com.portoseguro.conecta.lugares.utils.UtilsData;

public class LugarEditarActivity extends ClasseActivity {

	EditText 	txtNome, txtTelefone, txtMetaProximaViagem, txtDataProximaViagem, txtHashtag;
	Button 		btnLimpar, btnSalvar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		idTela = R.layout.activity_lugar_editar;
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_lugar_editar);
		executarAnimacao(R.anim.slide_down);
		mostrarBotaoHome();
		carregarTela();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lugar_editar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void carregarTela() {
		txtNome 				= mapear(R.id.lugar_txtNome);
		txtTelefone 			= mapear(R.id.lugar_txtTelefone);
		txtMetaProximaViagem 	= mapear(R.id.lugar_txtMetaProximaViagem);
		txtDataProximaViagem 	= mapear(R.id.lugar_txtDataProximaViagem);
		txtHashtag 				= mapear(R.id.lugar_txtHashtag);
		
		btnLimpar 				= mapear(R.id.lugar_btnLimpar);
		btnSalvar 				= mapear(R.id.lugar_btnSalvar);
		carregarEventos();
	}

	@Override
	public void carregarEventos() {
		txtDataProximaViagem.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					OnDateSetListener resposta = new OnDateSetListener() {
						@Override
						public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
							String data = 
									((dayOfMonth < 10 ) ? "0" + dayOfMonth : dayOfMonth) + "/" + 
									(((monthOfYear+1) < 10) ? "0" + (monthOfYear + 1) : monthOfYear + 1) + "/" + 
									year;
							txtDataProximaViagem.setText(data);
							
						}
					};
					String dataTxt = txtDataProximaViagem.getText().toString();
					Calendar data = null;
					if (dataTxt.isEmpty()) {
						data = GregorianCalendar.getInstance();
					} else {
						data = UtilsData.strToCalendar(dataTxt);
					}
					int ano = data.get(GregorianCalendar.YEAR);
					int mes = data.get(GregorianCalendar.MONTH);
					int dia = data.get(GregorianCalendar.DAY_OF_MONTH);
					DatePickerDialog dialogo = new DatePickerDialog(LugarEditarActivity.this, resposta, ano, mes, dia);
					dialogo.show();
				}
			}
		});
		
		btnLimpar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				limparFormulario();
			}
		});
		
		btnSalvar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean formularioEstaPreenchido = verificarPreenchimentoDoFormulario();
				if (formularioEstaPreenchido) {
					try {
						Lugar lugar = salvar();
						if (existe(lugar)) {
							avisar("Lugar salvo com sucesso", Toast.LENGTH_SHORT);
							Sessao.addParametro("lugar", lugar);
							onBackPressed();
						}
					} catch (Erro e) {
						e.printStackTrace();
						Dialogos.Alerta.exibirMensagemErro(e, getContexto(), null);
					}
				}
			}
		});
		
	}

	protected boolean verificarPreenchimentoDoFormulario() {
		if (txtNome.getText().toString().isEmpty()) {
			informarCampoNaoPreenchido("Nome", txtNome); 									return false;
		} else if (txtTelefone.getText().toString().isEmpty()) {
			informarCampoNaoPreenchido("Telefone", txtTelefone); 							return false;
		} else if (txtMetaProximaViagem.getText().toString().isEmpty()) {
			informarCampoNaoPreenchido("Meta da Proxima Viagem", txtMetaProximaViagem); 	return false;
		} else if (txtDataProximaViagem.getText().toString().isEmpty()) {
			informarCampoNaoPreenchido("Data da Proxima Viagem", txtDataProximaViagem); 	return false;
		} else if (txtHashtag.getText().toString().isEmpty()) {
			informarCampoNaoPreenchido("Hashtag", txtHashtag); 								return false;
		}
		
		return true;
	}

	private void informarCampoNaoPreenchido(String campo, View campoASerDadoFoco) {
		Dialogos.Alerta.exibirMensagemInformacao(getContexto(), false, "Preencha o campo "+campo, null);
		campoASerDadoFoco.requestFocus();
	}

	protected Lugar salvar() throws Erro {
		String nome 				= txtNome.getText().toString();
		Long telefone 				= Long.parseLong(txtTelefone.getText().toString());
		String localproximaViagem 	= txtMetaProximaViagem.getText().toString();
		String dataProximaViagem 	= txtDataProximaViagem.getText().toString();
		String hashTags 			= txtHashtag.getText().toString();
		Lugar lugar = new Lugar()
							.setNome(nome)
							.setTelefone(telefone)
							.setLocalproximaViagem(localproximaViagem)
							.setDataProximaViagem(dataProximaViagem)
							.setHashTags(hashTags);
		lugar = new BOLugar(getContexto()).salvar(lugar);
		return lugar;
	}

	protected void limparFormulario() {
		txtNome.setText("");
		txtTelefone.setText("");
		txtMetaProximaViagem.setText("");
		txtDataProximaViagem.setText("");
		txtHashtag.setText("");
	}
}
