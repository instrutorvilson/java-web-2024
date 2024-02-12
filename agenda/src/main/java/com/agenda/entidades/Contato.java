package com.agenda.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_contatos")
public class Contato {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Size(min = 5, message = "O campo deve ter no mínimo 5 caracteres.")
	private String nome;
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "O email não está no formato correto.")
	private String email;
	@Pattern(regexp = "^\\(?(\\d{2})\\)?[-.\\s]?([9]{0,1}\\d{4})[-.\\s]?(\\d{4})$", message = "O número de telefone não está no formato correto.")
	private String fone;
	
	@ManyToOne
	private Endereco endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
