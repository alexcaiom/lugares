package com.portoseguro.conecta.lugares.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.portoseguro.conecta.lugares.abstratas.Classe;
import com.portoseguro.conecta.lugares.abstratas.Contexto;
import com.portoseguro.conecta.lugares.escutadores.EscutadorLigacaoFim;

public class UtilsTelefone extends Classe{

	public static String formatarNumeroComCodigoNacionalidadeEAreaCom2Digitos(String telefone) {
		boolean telefoneTem13Digitos = existe(telefone) && telefone.length() == 13;
		if (telefoneTem13Digitos) {
			String codigoDoPais = telefone.substring(0, 2);
			String codigoDeArea = telefone.substring(2, 4);
			telefone = codigoDoPais + " " + codigoDeArea + " " + telefone.substring(4);
		}
		return telefone;
	}
	
	public static void realizarLigacaoTelefonica(Contexto contexto, String numero){
		String url = "tel:"+numero;
		Intent intencao = new Intent(Intent.ACTION_CALL, Uri.parse(url));
		contexto.getContexto().startActivity(intencao);
		
		EscutadorLigacaoFim escutador = new EscutadorLigacaoFim(contexto.getContexto());
		TelephonyManager gerenciador = (TelephonyManager) contexto.getContexto().getSystemService(Context.TELEPHONY_SERVICE);
		gerenciador.listen(escutador, PhoneStateListener.LISTEN_CALL_STATE);
	}

	public static boolean validarNumeroTelefone(String numeroTelefone) {
		return existe(numeroTelefone) && numeroTelefone.length() == 13;
	}

}
