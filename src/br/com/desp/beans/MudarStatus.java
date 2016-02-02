package br.com.desp.beans;

import java.util.Calendar;

public class MudarStatus {
	
	private int codigo;
	private OrdemServico ordemServico;
	private StatusOs status;
	private Calendar dtMudanca;
	private String observacao;
	private Funcionario funcionario;
	public MudarStatus(int codigo, OrdemServico ordemServico, StatusOs status,
			Calendar dtMudanca, String observacao) {
		super();
		this.codigo = codigo;
		this.ordemServico = ordemServico;
		this.status = status;
		this.dtMudanca = dtMudanca;
		this.observacao = observacao;
	}
	public MudarStatus() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	public StatusOs getStatus() {
		return status;
	}
	public void setStatus(StatusOs status) {
		this.status = status;
	}
	public Calendar getDtMudanca() {
		return dtMudanca;
	}
	public void setDtMudanca(Calendar dtMudanca) {
		this.dtMudanca = dtMudanca;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	

}
