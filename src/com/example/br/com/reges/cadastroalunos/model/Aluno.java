package com.example.br.com.reges.cadastroalunos.model;

import java.io.Serializable;

public class Aluno implements Serializable {

	private static final long serialVersionUID = 4306907577099415244L;
	
	private Integer id;
	private String nome;
	private String endereco;
	private String telefone;
	private Float nota;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Float getNota() {
		return nota;
	}

	public void setNota(Float nota) {
		this.nota = nota;
	}

	public String toString() {
		return id + " - " + nome;
	}
}
