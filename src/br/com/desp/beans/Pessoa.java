package br.com.desp.beans;

import java.util.List;

public class Pessoa {
	
	private int codigo;
	private String nome;
	private Usuario usuario;
	private List<Telefone> telefone;
	private Endereco endereco;
	private int status;
	public Pessoa(int codigo, String nome, Usuario usuario,
			List<Telefone> telefone, Endereco endereco, int status) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.usuario = usuario;
		this.telefone = telefone;
		this.endereco = endereco;
		this.status = status;
	}
	public Pessoa() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Telefone> getTelefone() {
		return telefone;
	}
	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
