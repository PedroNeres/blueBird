package br.com.desp.beans;

public class Telefone {

	private int codigo;
	private int ddd;
	private int numero;
	
	public Telefone(int ddd, int numero) {
		super();
		this.ddd = ddd;
		this.numero = numero;
	}

	public Telefone() {
		super();
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
}
