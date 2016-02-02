package br.com.desp.beans;

public class Pagamento {
	
	private int codigo;
	private FormaPagamento forPagamento;
	private OrdemServico ordemServico;
	private double vlPagao;
	private String numero;
	private String observacoes;
	private int status;
	public Pagamento(int codigo, FormaPagamento forPagamento, String numero,
			String observacoes) {
		super();
		this.codigo = codigo;
		this.forPagamento = forPagamento;
		this.numero = numero;
		this.observacoes = observacoes;
	}
	public Pagamento() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public FormaPagamento getForPagamento() {
		return forPagamento;
	}
	public void setForPagamento(FormaPagamento forPagamento) {
		this.forPagamento = forPagamento;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getVlPagao() {
		return vlPagao;
	}
	public void setVlPagao(double vlPagao) {
		this.vlPagao = vlPagao;
	}
	
	

}
