package com.portoseguro.conecta.lugares.orm.modelos;

public class Lugar {

	private int id;
	private String nome;
	private Long telefone;
	private String localproximaViagem;
	private String dataProximaViagem;
	private String hashTags;
	
	public Lugar() {}

	public Lugar(int id, String nome, Long telefone, String localproximaViagem, String dataProximaViagem,
			String hashTags) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.localproximaViagem = localproximaViagem;
		this.dataProximaViagem = dataProximaViagem;
		this.hashTags = hashTags;
	}

	public int getId() {
		return id;
	}

	public Lugar setId(int id) {
		this.id = id;
		return this;
	}

	public String getNome() {
		return nome;
	}

	public Lugar setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public Long getTelefone() {
		return telefone;
	}

	public Lugar setTelefone(Long telefone) {
		this.telefone = telefone;
		return this;
	}

	public String getLocalproximaViagem() {
		return localproximaViagem;
	}

	public Lugar setLocalproximaViagem(String localproximaViagem) {
		this.localproximaViagem = localproximaViagem;
		return this;
	}

	public String getDataProximaViagem() {
		return dataProximaViagem;
	}

	public Lugar setDataProximaViagem(String dataProximaViagem) {
		this.dataProximaViagem = dataProximaViagem;
		return this;
	}

	public String getHashTags() {
		return hashTags;
	}

	public Lugar setHashTags(String hashTags) {
		this.hashTags = hashTags;
		return this;
	}

	@Override
	public String toString() {
		return "Lugar [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", localproximaViagem="
				+ localproximaViagem + ", dataProximaViagem=" + dataProximaViagem + ", hashTags=" + hashTags + "]";
	}
	
}
