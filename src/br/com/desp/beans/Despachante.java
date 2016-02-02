package br.com.desp.beans;

public class Despachante {
	
	private int codigo;
	private String razaoSociao;
	private Long ssp;
	
	public Despachante(int codigo, String razaoSociao, Long ssp) {
		super();
		this.codigo = codigo;
		this.razaoSociao = razaoSociao;
		this.ssp = ssp;
	}

	public Despachante() {
		super();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getRazaoSociao() {
		return razaoSociao;
	}

	public void setRazaoSociao(String razaoSociao) {
		this.razaoSociao = razaoSociao;
	}

	public Long getSsp() {
		return ssp;
	}

	public void setSsp(Long ssp) {
		this.ssp = ssp;
	}
	

}
