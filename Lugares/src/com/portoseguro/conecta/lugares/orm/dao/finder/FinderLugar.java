package com.portoseguro.conecta.lugares.orm.dao.finder;

import java.util.ArrayList;
import java.util.List;

import com.portoseguro.conecta.lugares.abstratas.Contexto;
import com.portoseguro.conecta.lugares.excecoes.SysErr;
import com.portoseguro.conecta.lugares.orm.modelos.Lugar;
import com.portoseguro.conecta.lugares.utils.GeradorSQLBean;

public class FinderLugar extends Finder<Lugar> {
	
	public FinderLugar(Contexto contexto) {
		super(contexto);
	}

	String entidade = GeradorSQLBean.getInstancia(Lugar.class).getNomeTabela();
	
	public List<Lugar> listar () throws SysErr {
		List<Lugar> lugares = new ArrayList<Lugar>();
		
		String sql = "select * from "+entidade;
		String[] selectionArgs = new String[]{};
		try {
			cursor = getBD().rawQuery(sql, selectionArgs);

			if (cursor.getCount() > 0 && cursor.getColumnCount() == 6 && cursor.moveToFirst()) {
				while (!cursor.isAfterLast()) {
					Lugar l = new Lugar();
					preencheVO(l);
					lugares.add(l);				
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			finalizar();
		}
		
		return lugares;
	}

	@Override
	void preencheVO(Lugar o) throws Exception {
		boolean tudoOK = verificaVOECursor(o);
		if (tudoOK) {
			o.setId(cursor.getInt(cursor.getColumnIndex("id")));
			o.setNome(cursor.getString(cursor.getColumnIndex("nome")));
			o.setTelefone(cursor.getLong(cursor.getColumnIndex("telefone")));
			o.setLocalproximaViagem(cursor.getString(cursor.getColumnIndex("localProximaViagem")));
			o.setDataProximaViagem(cursor.getString(cursor.getColumnIndex("dataProximaViagem")));
			o.setHashTags(cursor.getString(cursor.getColumnIndex("hashTags")));
		}
	}

}
