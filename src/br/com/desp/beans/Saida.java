package br.com.desp.beans;

import java.util.Calendar;

public class Saida {
	
	private int codigo;
	private String descricao;
	private double valor;
	private Calendar dtSaida;
	private Filial filial;
	public Saida(int codigo, String descricao, double valor, Calendar dtSaida,
			Filial filial) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
		this.dtSaida = dtSaida;
		this.filial = filial;
	}
	public Saida() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Calendar getDtSaida() {
		return dtSaida;
	}
	public void setDtSaida(Calendar dtSaida) {
		this.dtSaida = dtSaida;
	}
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}

}
