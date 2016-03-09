package br.com.desp.beans;

import java.util.Calendar;

public class Contas {
	
	private int codigo;
	private String descricao;
	private double valor;
	private Calendar proxVenc;
	private int diaVencimento;
	private Filial filial;
	private String observacoes;
	private int qtParcelas;
	private int status;
	public Contas(int codigo, String descricao, double valor,
			Calendar proxVenc, int diaVencimento, int qtParcelas, int status) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
		this.proxVenc = proxVenc;
		this.diaVencimento = diaVencimento;
		this.qtParcelas = qtParcelas;
		this.status = status;
	}
	public Contas() {
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
	public Calendar getProxVenc() {
		return proxVenc;
	}
	public void setProxVenc(Calendar proxVenc) {
		this.proxVenc = proxVenc;
	}
	public int getDiaVencimento() {
		return diaVencimento;
	}
	public void setDiaVencimento(int diaVencimento) {
		this.diaVencimento = diaVencimento;
	}
	public int getQtParcelas() {
		return qtParcelas;
	}
	public void setQtParcelas(int qtParcelas) {
		this.qtParcelas = qtParcelas;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

}
