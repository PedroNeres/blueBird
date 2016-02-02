package br.com.desp.beans;

public class Cargo {
	
	private int codigo;
	private String descricao;
	private int acesso;
	
	public Cargo(int codigo, String descricao, int acesso) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.acesso = acesso;
	}

	public Cargo() {
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

	public int getAcesso() {
		return acesso;
	}

	public void setAcesso(int acesso) {
		this.acesso = acesso;
	}

}
