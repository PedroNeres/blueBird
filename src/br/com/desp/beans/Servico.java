package br.com.desp.beans;

import java.util.List;

public class Servico {
	
	private int codigo;
	private String descricao;
	private double vlrTaxa;
	private double vlrHono;
	private double vlrTotal;
	private List<TipoVeiculo> tipoVeiculo;
	private List<TipoOrdemServico> tipoOrdemServico;
	public Servico(int codigo, String descricao, double vlrTaxa,
			double vlrHono, double vlrTotal, List<TipoVeiculo> tipoVeiculo,
			List<TipoOrdemServico> tipoOrdemServico) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.vlrTaxa = vlrTaxa;
		this.vlrHono = vlrHono;
		this.vlrTotal = vlrTotal;
		this.tipoVeiculo = tipoVeiculo;
		this.tipoOrdemServico = tipoOrdemServico;
	}
	public Servico() {
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
	public double getVlrTaxa() {
		return vlrTaxa;
	}
	public void setVlrTaxa(double vlrTaxa) {
		this.vlrTaxa = vlrTaxa;
	}
	public double getVlrHono() {
		return vlrHono;
	}
	public void setVlrHono(double vlrHono) {
		this.vlrHono = vlrHono;
	}
	public double getVlrTotal() {
		return vlrTotal;
	}
	public void setVlrTotal(double vlrTotal) {
		this.vlrTotal = vlrTotal;
	}
	public List<TipoVeiculo> getTipoVeiculo() {
		return tipoVeiculo;
	}
	public void setTipoVeiculo(List<TipoVeiculo> tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	public List<TipoOrdemServico> getTipoOrdemServico() {
		return tipoOrdemServico;
	}
	public void setTipoOrdemServico(List<TipoOrdemServico> tipoOrdemServico) {
		this.tipoOrdemServico = tipoOrdemServico;
	}

}
