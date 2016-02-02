package br.com.desp.beans;

public class Filial extends Pessoa{
	
	private int codigo;
	private Despachante despachante;
	public Filial(int codigo, Despachante despachante) {
		super();
		this.codigo = codigo;
		this.despachante = despachante;
	}
	public Filial() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Despachante getDespachante() {
		return despachante;
	}
	public void setDespachante(Despachante despachante) {
		this.despachante = despachante;
	}

}
