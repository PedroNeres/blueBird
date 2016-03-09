package br.com.desp.beans;

import java.util.Calendar;

public class Entrada {
	
	private int codigo;
	private String descricao;
	private double valor;
	private Calendar dtEntrada;
	private Filial filial;
	public Entrada(int codigo, String descricao, double valor,
			Calendar dtEntrada, Filial filial) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
		this.dtEntrada = dtEntrada;
		this.filial = filial;
	}
	public Entrada() {
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
	public Calendar getDtEntrada() {
		return dtEntrada;
	}
	public void setDtEntrada(Calendar dtEntrada) {
		this.dtEntrada = dtEntrada;
	}
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}

}
