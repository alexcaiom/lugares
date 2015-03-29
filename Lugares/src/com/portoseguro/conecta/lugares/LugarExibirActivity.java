package com.portoseguro.conecta.lugares;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.portoseguro.conecta.lugares.abstratas.ClasseActivity;

public class LugarExibirActivity extends ClasseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		idTela = R.layout.activity_lugar_exibir;
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_lugar_exibir);
		executarAnimacao(R.anim.slide_down);
		mostrarBotaoHome();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void carregarEventos() {
		// TODO Auto-generated method stub
		
	}
}
