package com.portoseguro.conecta.lugares.orm.interfaces;

import java.util.List;

import com.portoseguro.conecta.lugares.excecoes.Erro;


public interface IBO<T> {

	public T inserir(T o) throws Erro;
	public void alterar(T o) throws Erro;
	public void excluir(T o) throws Erro;
	public List<T> listar() throws Erro;
	
}
