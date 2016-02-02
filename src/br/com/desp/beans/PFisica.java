package br.com.desp.beans;

import java.util.List;

public class PFisica extends Pessoa {
	
	private Filial filial;
	private long cpf;
	public PFisica(int codigo, String nome, Usuario usuario,
			List<Telefone> telefone, Endereco endereco, int status,
			Usuario usuario2, Filial filial, long cpf) {
		super(codigo, nome, usuario, telefone, endereco, status);
		usuario = usuario2;
		this.filial = filial;
		this.cpf = cpf;
	}
	public PFisica(int codigo, String nome, Usuario usuario,
			List<Telefone> telefone, Endereco endereco, int status) {
		super(codigo, nome, usuario, telefone, endereco, status);
	}
	public PFisica() {
		super();
	}
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

}
