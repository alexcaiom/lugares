/**
 * 
 */
package com.portoseguro.conecta.lugares.abstratas;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * @author Waiso
 *
 */
public class Contexto {
	
	ClasseActivity classeContexto;
	Activity contexto;
	public final String CLASSE_NOME = getClass().getSimpleName();
	
	public Contexto(ClasseActivity contexto){
		if (contexto instanceof ClasseActivity) {
			this.classeContexto = contexto;
		}
		this.contexto = contexto;
	}

	public ClasseActivity getContexto() {
		return classeContexto;
	}

	public void setContexto(ClasseActivity classeContexto) {
		this.classeContexto = classeContexto;
	}
	
	
	public void exibirMensagemDeProcessamento(){
		
	}
	
	protected void log(Object textoParaLog) {
		Log.i(CLASSE_NOME, textoParaLog.toString());
	}
	
	public boolean temInternet(){
		ConnectivityManager connectivityManager = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	public void avisar(String aviso){
		if (this.contexto instanceof ClasseActivity) {
			((ClasseActivity) contexto).getContexto().avisar(aviso);
		}
	}

}
