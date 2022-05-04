package com.farmaciaL.farmacia.model;

import java.math.BigInteger; //cada produto é um id. O bigInteger é só para ter mais "ids". Poder navegar por mais

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull //essa variável não pode ser nula
	@Size (min = 3, max = 50) //Limite de caracteres
	private String nome;
	
	@NotNull
	@Size (min = 1, max = 5)
	private String preco;
	
	public void setPreco(String preco) {
		this.preco = preco;
	}

	@NotNull
	@Size (min = 10, max = 255)
	private String bula;
	
	@NotNull
	@Size (max = 3)
	private String disponivel;
	
	@NotNull
	@Size (min = 5, max = 255)
	private String localidade;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private categoria categoria;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBula() {
		return bula;
	}

	public void setBula(String bula) {
		this.bula = bula;
	}

	public String getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(categoria categoria) {
		this.categoria = categoria;
	}

	public String getPreco() {
		return preco;
	}
	
	

}