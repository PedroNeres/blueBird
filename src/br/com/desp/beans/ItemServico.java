package br.com.desp.beans;

public class ItemServico {
	
	private int codigo;
	private Servico servico;
	private OrdemServico os;
	private int quantidade;
	private double vlrTotal;
	public ItemServico(int codigo, Servico servico, int quantidade,
			double vlrTotal) {
		super();
		this.codigo = codigo;
		this.servico = servico;
		this.quantidade = quantidade;
		this.vlrTotal = vlrTotal;
	}
	public ItemServico() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public OrdemServico getOs() {
		return os;
	}
	public void setOs(OrdemServico os) {
		this.os = os;
	}
	public double getVlrTotal() {
		return vlrTotal;
	}
	public void setVlrTotal(double vlrTotal) {
		this.vlrTotal = vlrTotal;
	}
	

}
