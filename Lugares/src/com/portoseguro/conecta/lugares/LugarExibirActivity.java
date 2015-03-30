package com.portoseguro.conecta.lugares;

import java.util.List;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.portoseguro.conecta.lugares.abstratas.ClasseActivity;
import com.portoseguro.conecta.lugares.excecoes.Erro;
import com.portoseguro.conecta.lugares.orm.bo.BOLugar;
import com.portoseguro.conecta.lugares.orm.modelos.Lugar;
import com.portoseguro.conecta.lugares.utils.Constantes;
import com.portoseguro.conecta.lugares.utils.Dialogos;
import com.portoseguro.conecta.lugares.utils.Sessao;
import com.portoseguro.conecta.lugares.utils.UtilsTelefone;

public class LugarExibirActivity extends ClasseActivity {
	
	TextView lblNome, lblTelefone, lblMetaProximaViagem, lblDataProximaViagem, lblHashTag, lblTelefoneInstrucoes;
	Button btnAtualizar;
	Lugar lugar = null;
	static final int CODIGO_REQUISICAO_EDICAO_LUGAR = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		idTela = R.layout.activity_lugar_exibir;
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_lugar_exibir);
		executarAnimacao(R.anim.slide_down);
		mostrarBotaoHome();
		carregarTela();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lugar_exibir, menu);
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
		lblNome 				= mapear(R.id.lugar_lblNome);
		lblTelefone 			= mapear(R.id.lugar_lblTelefone);
		lblMetaProximaViagem 	= mapear(R.id.lugar_lblMetaProximaViagem);
		lblDataProximaViagem 	= mapear(R.id.lugar_lblDataProximaViagem);
		lblHashTag 				= mapear(R.id.lugar_lblHashtag);
		lblTelefoneInstrucoes   = mapear(R.id.lugar_lblTelefoneInstrucoes);
		
		btnAtualizar = mapear(R.id.lugar_btnAtualizar);
		try {
			boolean acabeiDeEditarOLugar = Sessao.temParametro(Constantes.LUGAR);
			if (acabeiDeEditarOLugar) {
				lugar = (Lugar) Sessao.getParametro(Constantes.LUGAR);
				lblNome.setText(lugar.getNome());
				lblTelefone.setText(lugar.getTelefone().toString());
				lblMetaProximaViagem.setText(lugar.getLocalproximaViagem());
				lblDataProximaViagem.setText(lugar.getDataProximaViagem());
				lblHashTag.setText(lugar.getHashTags());
			} else {
				List<Lugar> lugares = new BOLugar(contexto).listar();
				if (!lugares.isEmpty()) {
					lugar = lugares.get(0);
					lblNome.setText(lugar.getNome());
					String telefone = lugar.getTelefone().toString();
					telefone = UtilsTelefone.formatarNumeroComCodigoNacionalidadeEAreaCom2Digitos(telefone);
					lblTelefone.setText(telefone);
					lblMetaProximaViagem.setText(lugar.getLocalproximaViagem());
					lblDataProximaViagem.setText(lugar.getDataProximaViagem());
					lblHashTag.setText(lugar.getHashTags());
				} 
				//Codigo apenas para efeito de testes de conformidade com os prints do prototipo
				else {
					lugar = new Lugar();
					lugar.setNome("Getulio Lima")
						 .setTelefone(5511944222276l)
						 .setLocalproximaViagem("India")
						 .setDataProximaViagem("10/06/2015")
						 .setHashTags("#India #Rave #Shiva");
					
					new BOLugar(contexto).inserir(lugar);
					
					lugares = new BOLugar(contexto).listar();
					if (!lugares.isEmpty()) {
						lugar = lugares.get(0);
						lblNome.setText(lugar.getNome());
						String telefone = lugar.getTelefone().toString();
						telefone = UtilsTelefone.formatarNumeroComCodigoNacionalidadeEAreaCom2Digitos(telefone);
						lblTelefone.setText(telefone);
						lblMetaProximaViagem.setText(lugar.getLocalproximaViagem());
						lblDataProximaViagem.setText(lugar.getDataProximaViagem());
						lblHashTag.setText(lugar.getHashTags());
					}
				}
			}
			
			boolean telefoneEstaPreenchido = !lblTelefone.getText().toString().isEmpty();
			if (telefoneEstaPreenchido) {
				lblTelefone.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						UtilsTelefone.realizarLigacaoTelefonica(contexto, lblTelefone.getText().toString());
					}
				});
			}
			
			Sessao.removerParametro(Constantes.LUGAR);
		} catch (Erro e) {
			e.printStackTrace();
			Dialogos.Alerta.exibirMensagemErro(e, contexto, null);
		}
		
		carregarEventos();
	}

	@Override
	public void carregarEventos() {
		btnAtualizar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle parametros = new Bundle();
				parametros.putSerializable(Constantes.LUGAR, lugar);
				irPara(LugarEditarActivity.class, parametros);
			}
		});
		
		lblTelefoneInstrucoes.animate()
							.setDuration(5000)
							.alphaBy(1.0f)
							.alpha(0.0f)
							.setListener(new AnimatorListener() {
								@Override
								public void onAnimationStart(Animator animation) {}
								@Override
								public void onAnimationRepeat(Animator animation) {}
								@Override
								public void onAnimationEnd(Animator animation) {
									lblTelefoneInstrucoes.setVisibility(View.GONE);
								}
								@Override
								public void onAnimationCancel(Animator animation) {}
							})
							.start();
	}
}
