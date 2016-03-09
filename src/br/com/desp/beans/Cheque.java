package br.com.desp.beans;

import java.util.Calendar;

public class Cheque {
	
	private int codigo;
	private int numero;
	private String emitente;
	private Calendar dtRecebimento;
	private Calendar dtDeposito;
	private int status;
	private double valor;
	private int cdFilial;
	public Cheque(int codigo, int numero, String emitente,
			Calendar dtRecebimento, Calendar dtDeposito, double valor) {
		super();
		this.codigo = codigo;
		this.numero = numero;
		this.emitente = emitente;
		this.dtRecebimento = dtRecebimento;
		this.dtDeposito = dtDeposito;
		this.valor = valor;
	}
	public Cheque() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getEmitente() {
		return emitente;
	}
	public void setEmitente(String emitente) {
		this.emitente = emitente;
	}
	public Calendar getDtRecebimento() {
		return dtRecebimento;
	}
	public void setDtRecebimento(Calendar dtRecebimento) {
		this.dtRecebimento = dtRecebimento;
	}
	public Calendar getDtDeposito() {
		return dtDeposito;
	}
	public void setDtDeposito(Calendar dtDeposito) {
		this.dtDeposito = dtDeposito;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCdFilial() {
		return cdFilial;
	}
	public void setCdFilial(int cdFilial) {
		this.cdFilial = cdFilial;
	}

}
