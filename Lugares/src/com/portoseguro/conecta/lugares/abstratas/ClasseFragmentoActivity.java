/**
 * 
 */
package com.portoseguro.conecta.lugares.abstratas;

import com.portoseguro.conecta.lugares.interfaces.ClasseActivityInterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Alex
 *
 */
public abstract class ClasseFragmentoActivity extends FragmentActivity implements ClasseActivityInterface{

	public final String CLASSE_NOME = getClass().getSimpleName();
	public Contexto contexto;
	
	public void irPara(Class tela, Bundle parametros){
		Intent intencao = new Intent(this, tela);
		if(parametros != null && !parametros.isEmpty()){
			intencao.putExtras(parametros);
		}
		startActivity(intencao);
	}
	
	public void irPara(Class tela){
		irPara(tela, null);
	}
	
	/**
	 * Metodo que lanca um aviso do tipo Toast ao usuario<br/>
	 * Aviso padrao: Longo
	 * @param aviso
	 */
	public void avisar(String aviso, Integer duracao) {
		if(duracao == null){
			Toast.makeText(getApplicationContext(), aviso, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getApplicationContext(), aviso, duracao).show();
		}
	}

	/**
	 * Metodo que lanca um aviso do tipo Toast ao usuario<br/>
	 * Aviso padrao: Longo
	 * @param aviso
	 */
	public void avisar(String aviso) {
		avisar(aviso, null);
	}
	
	public void exibirMensagemDeProcessamento(){
		
	}
	
	protected void log(Object textoParaLog) {
		Log.i(CLASSE_NOME, textoParaLog.toString());
	}
	
	/**
	 * Metodo que verifica se o objeto eh nulo
	 * @param o
	 * @return
	 */
	public static boolean naoExiste(Object o){
		boolean naoExiste = Classe.naoExiste(o);
		return naoExiste;
	}
	
	/**
	 * Metodo que verifica se o objeto nao eh nulo
	 * @param o
	 * @return
	 */
	public static boolean existe(Object o){
		boolean existe = !naoExiste(o);
		return existe;
	}
}
