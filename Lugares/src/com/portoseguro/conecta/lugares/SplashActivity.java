package com.portoseguro.conecta.lugares;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.portoseguro.conecta.lugares.abstratas.ClasseActivity;
import com.portoseguro.conecta.lugares.utils.UtilsImagem;

public class SplashActivity extends ClasseActivity {

	int tempoDeAtraso = 5; //Tempo de Atraso em Segundos
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		idTela = R.layout.activity_splash;
		super.onCreate(savedInstanceState);
		ocultarBarraDeAcoes();
		ImageView img = mapear(R.id.splash_img);
		UtilsImagem.loadBitmap(contexto, R.drawable.splash, img);
		executarAnimacao(R.anim.fade_in);
		img.setAnimation(getAnimacao(R.anim.fade_in));
		img.animate().setDuration(2000).start();
		
        new Handler().postDelayed(new Runnable() {
              
            // Using handler with postDelayed called runnable run method
  
            @Override
            public void run() {
                irPara(MainActivity.class);
  
                // close this activity
                finish();
            }
        }, tempoDeAtraso*1000); 
        
        View tela = mapear(R.id.conteiner);
    	tela.animate().alpha(0).setDuration(2000).setStartDelay(3000).start();        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
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
