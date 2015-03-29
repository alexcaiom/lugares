package com.portoseguro.conecta.lugares.orm.dao.finder;

import android.database.sqlite.SQLiteDatabase;

import com.portoseguro.conecta.lugares.abstratas.Contexto;
import com.portoseguro.conecta.lugares.excecoes.SysErr;
import com.portoseguro.conecta.lugares.orm.dao.DAO;

public abstract class Finder<T> extends DAO<T> {

	public Finder(Contexto contexto) {
		this.contexto = contexto;
	}
	
	protected String getNomeEntidade(){
		return CLASSE_NOME.substring(CLASSE_NOME.lastIndexOf("Finder"));
	}
	
	abstract void preencheVO(T o) throws Exception;
	public boolean verificaVOECursor(T o){
		if (naoExiste(cursor) || cursor.isClosed() || naoExiste(o)) {
			return false;
		}
		return true;
	}
	
	protected SQLiteDatabase getBD() throws SysErr{
		return getBD(TIPO_BD_LEITURA);
	}
	
}
