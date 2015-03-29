package com.portoseguro.conecta.lugares.orm.dao;

import android.content.ContentValues;

import com.portoseguro.conecta.lugares.abstratas.Contexto;
import com.portoseguro.conecta.lugares.excecoes.SysErr;
import com.portoseguro.conecta.lugares.orm.interfaces.GenericDAO;
import com.portoseguro.conecta.lugares.orm.modelos.Lugar;

public class DAOLugar extends GenericDAO<Lugar> {

	public DAOLugar(Contexto contexto) {
		this.contexto = contexto;
	}
	
	@Override
	public Lugar incluir(Lugar orm) throws SysErr {
		ContentValues values = new ContentValues();
		values.put("nome", orm.getNome());
		values.put("telefone", orm.getTelefone());
		values.put("localProximaViagem", orm.getLocalproximaViagem());
		values.put("dataProximaViagem", orm.getDataProximaViagem());
		values.put("hashtags", orm.getHashTags());
		
		Long id = incluir(orm, values);
		if (id > -1) {
			orm.setId(id.intValue());
		} else{
			orm = null;
		}
		
		return orm;
	}

	@Override
	public void atualizar(Lugar orm) throws SysErr {
		ContentValues values = new ContentValues();
		values.put("nome", orm.getNome());
		values.put("telefone", orm.getTelefone());
		values.put("localProximaViagem", orm.getLocalproximaViagem());
		values.put("dataProximaViagem", orm.getDataProximaViagem());
		values.put("hashtags", orm.getHashTags());
		String whereClause = "id=?";
		String[] whereArgs = new String[]{
				orm.getId().toString()
		};
		
		atualizar(values, whereClause, whereArgs);
	}

	@Override
	public void excluir(Lugar orm) throws SysErr {
		// TODO Auto-generated method stub
		
	}

	
}
