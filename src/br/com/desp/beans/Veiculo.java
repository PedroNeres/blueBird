package br.com.desp.beans;

public class Veiculo {
	
	private int codigo;
	private String placa;
	private long renavam;
	private String modelo;
	private TipoVeiculo tipo;
	public Veiculo(int codigo, String placa, int renavam, TipoVeiculo tipo) {
		super();
		this.codigo = codigo;
		this.placa = placa;
		this.renavam = renavam;
		this.tipo = tipo;
	}
	public Veiculo() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public long getRenavam() {
		return renavam;
	}
	public void setRenavam(long renavam) {
		this.renavam = renavam;
	}
	public TipoVeiculo getTipo() {
		return tipo;
	}
	public void setTipo(TipoVeiculo tipo) {
		this.tipo = tipo;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

}
