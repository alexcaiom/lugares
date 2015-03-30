/**
 * 
 */
package com.portoseguro.conecta.lugares.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex
 *
 */
public class Sessao {

	private static final Map<String, Object> parametros = new HashMap<String, Object>();
	
	public static final Object getParametro(String chave){
		return parametros.get(chave);
	}
	
	public static final void addParametro(String chave, Object valor){
		parametros.put(chave, valor);
	}
	
	public static final boolean temParametro(String chave){
		return parametros.containsKey(chave);
	}
	
	public static final void removerParametro(String chave){
		if (temParametro(chave)) {
			parametros.remove(chave);
		}
	}
	
}
