package com.portoseguro.conecta.lugares;

import java.util.List;

import com.portoseguro.conecta.lugares.adaptador.AdaptadorLugar;
import com.portoseguro.conecta.lugares.orm.modelos.Lugar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class MainActivity extends Activity {

	ListView lista;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
			
		}
        return super.onOptionsItemSelected(item);
    }
    
    private void carregarTela() {
    	lista = (ListView) findViewById(R.id.lugares_lista);
//    	List<Lugar> lugares = ;
//		lista.setAdapter(new AdaptadorLugar(MainActivity.this, lugares));
    }
}

