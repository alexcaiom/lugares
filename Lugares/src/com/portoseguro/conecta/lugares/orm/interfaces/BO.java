package com.portoseguro.conecta.lugares.orm.interfaces;

import com.portoseguro.conecta.lugares.abstratas.Contexto;

public abstract class BO<T> implements IBO<T> {

	protected Contexto contexto;
	
	public BO(Contexto contexto) {
		this.setContexto(contexto);
	}

	public Contexto getContexto() {
		return contexto;
	}

	public void setContexto(Contexto contexto) {
		this.contexto = contexto;
	}
	
}
