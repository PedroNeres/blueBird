package br.com.desp.beans;

import java.util.Calendar;

public class Mensagem {
	
	private int codigo;
	private String mensagem;
	private String emailRemetente;
	private String nomeRemetente;
	private String emailDestinatario;
	private String nomeDestinatario;
	private Calendar dtEnvio;
	private String assunto;
	private int status;
	public Mensagem(int codigo, String mensagem, String emailRemetente,
			String nomeRemetente, String emailDestinatario,
			String nomeDestinatario, Calendar dtEnvio, String assunto) {
		super();
		this.codigo = codigo;
		this.mensagem = mensagem;
		this.emailRemetente = emailRemetente;
		this.nomeRemetente = nomeRemetente;
		this.emailDestinatario = emailDestinatario;
		this.nomeDestinatario = nomeDestinatario;
		this.dtEnvio = dtEnvio;
		this.assunto = assunto;
	}
	public Mensagem() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getEmailRemetente() {
		return emailRemetente;
	}
	public void setEmailRemetente(String emailRemetente) {
		this.emailRemetente = emailRemetente;
	}
	public String getNomeRemetente() {
		return nomeRemetente;
	}
	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}
	public String getEmailDestinatario() {
		return emailDestinatario;
	}
	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}
	public String getNomeDestinatario() {
		return nomeDestinatario;
	}
	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}
	public Calendar getDtEnvio() {
		return dtEnvio;
	}
	public void setDtEnvio(Calendar dtEnvio) {
		this.dtEnvio = dtEnvio;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
