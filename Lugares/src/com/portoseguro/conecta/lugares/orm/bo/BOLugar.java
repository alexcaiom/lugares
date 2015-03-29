package com.portoseguro.conecta.lugares.orm.bo;

import java.util.List;

import com.portoseguro.conecta.lugares.abstratas.Contexto;
import com.portoseguro.conecta.lugares.excecoes.Erro;
import com.portoseguro.conecta.lugares.orm.dao.DAOLugar;
import com.portoseguro.conecta.lugares.orm.dao.finder.FinderLugar;
import com.portoseguro.conecta.lugares.orm.interfaces.BO;
import com.portoseguro.conecta.lugares.orm.modelos.Lugar;

public class BOLugar extends BO<Lugar> {
	
	public BOLugar(Contexto contexto) {
		super(contexto);
	}

	@Override
	public Lugar inserir(Lugar o) throws Erro {
		return new DAOLugar(contexto).incluir(o);
	}

	@Override
	public void alterar(Lugar o) throws Erro {
		
	}

	@Override
	public void excluir(Lugar o) throws Erro {
		
	}

	@Override
	public List<Lugar> listar() throws Erro {
		return new FinderLugar(contexto).listar();
	}

	public Lugar salvar(Lugar lugar) throws Erro {
		boolean deveIncluir = lugar.getId() == 0;
		if (deveIncluir) {
			return inserir(lugar);
		} else {
			alterar(lugar);
		}
		return lugar;
	}

}
