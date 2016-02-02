package br.com.desp.beans;

public class TipoVeiculo {
	
	private int codigo;
	private String descricao;
	public TipoVeiculo(int codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
	}
	public TipoVeiculo() {
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
	

}
