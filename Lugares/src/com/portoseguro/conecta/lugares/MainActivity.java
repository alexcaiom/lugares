package com.portoseguro.conecta.lugares;

import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.widget.ListView;

import com.portoseguro.conecta.lugares.abstratas.ClasseActivity;
import com.portoseguro.conecta.lugares.abstratas.Lugares;
import com.portoseguro.conecta.lugares.adaptador.AdaptadorLugar;
import com.portoseguro.conecta.lugares.excecoes.Erro;
import com.portoseguro.conecta.lugares.orm.bo.BOLugar;
import com.portoseguro.conecta.lugares.orm.modelos.Lugar;
import com.portoseguro.conecta.lugares.utils.Dialogos;


public class MainActivity extends ClasseActivity {

	ListView lista;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	idTela = R.layout.activity_main;
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
		executarAnimacao(R.anim.slide_down);
        carregarTela();
    }


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_info) {
			imprimirLugares();
		} else if (id == R.id.action_add) {
			irPara(LugarEditarActivity.class);
		}
        return super.onOptionsItemSelected(item);
    }
    
    private void imprimirLugares() {
		try {
			List<Lugar> lugares = new BOLugar(getContexto()).listar();
			StringBuilder texto = new StringBuilder();
			boolean primeiroItem = true;
			if (existe(lugares)) {
				for (Lugar lugar : lugares) {
					if (!primeiroItem) {
						texto.append(", ");
					}
					texto.append(lugar);
					primeiroItem = false;
				}				
			}
			
			if (!texto.toString().isEmpty()) {
				Dialogos.Alerta.exibirMensagemInformacao(getContexto(), false, texto.toString(), null);
			} else {
				Dialogos.Alerta.exibirMensagemInformacao(getContexto(), false, "Nenhum lugar cadastrado!", null);
			}
		} catch (Erro e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void carregarTela() {
    	lista = (ListView) findViewById(R.id.lugares_lista);
//    	List<Lugar> lugares = ;
		lista.setAdapter(new AdaptadorLugar(getContexto(), Arrays.asList(Lugares.values())));
    }
    
    public void carregarEventos(){
    	
    }
}

