package com.portoseguro.conecta.lugares.abstratas;

import com.portoseguro.conecta.lugares.R;


public enum Lugares {

	PUERTO_MADERO	(R.drawable.foto1),
	IPANEMA			(R.drawable.foto2),
	EGITO			(R.drawable.foto3),
	BURNING_MAN		(R.drawable.foto4),
	INTERCAMBIO		(R.drawable.foto5);
	
	int img;
	
	private Lugares(int img) {
		this.img = img;
	}

	public Integer getImg() {
		return img;
	}
	
}
