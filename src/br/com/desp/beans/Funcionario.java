package br.com.desp.beans;

import java.util.Calendar;

public class Funcionario extends PFisica {
	
	private Cargo cargo;
	private double salario;
	private Calendar dtAdmissao;
	private Calendar dtRecisao;
	public Funcionario(Cargo cargo, double salario, Calendar dtAdmissao,
			Calendar dtRecisao) {
		super();
		this.cargo = cargo;
		this.salario = salario;
		this.dtAdmissao = dtAdmissao;
		this.dtRecisao = dtRecisao;
	}
	public Funcionario() {
		super();
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public Calendar getDtAdmissao() {
		return dtAdmissao;
	}
	public void setDtAdmissao(Calendar dtAdmissao) {
		this.dtAdmissao = dtAdmissao;
	}
	public Calendar getDtRecisao() {
		return dtRecisao;
	}
	public void setDtRecisao(Calendar dtRecisao) {
		this.dtRecisao = dtRecisao;
	}

}
