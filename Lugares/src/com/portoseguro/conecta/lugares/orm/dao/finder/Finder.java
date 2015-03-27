package com.portoseguro.conecta.lugares.orm.dao.finder;

import com.portoseguro.conecta.lugares.excecoes.SysErr;
import com.portoseguro.conecta.lugares.orm.dao.DAO;

import android.database.sqlite.SQLiteDatabase;

public abstract class Finder<T> extends DAO<T> {

	protected String getNomeEntidade(){
		return CLASSE_NOME.substring(CLASSE_NOME.lastIndexOf("Finder"));
	}
	
	abstract void preencheVO(T o) throws Exception;
	
	protected SQLiteDatabase getBD() throws SysErr{
		return getBD(TIPO_BD_LEITURA);
	}
	
}
