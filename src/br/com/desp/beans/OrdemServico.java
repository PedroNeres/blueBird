package br.com.desp.beans;

import java.util.Calendar;
import java.util.List;

public class OrdemServico {
	
	private int numero;
	private Cliente cliente;
	private Funcionario atendente;
	private Calendar dtEntrada;
	private TipoOrdemServico tipo;
	private List<ItemServico> servicos;
	private double total;
	private double desconto;
	private double vlrPago;
	private MudarStatus status;
	private Veiculo veiculo;
	private List<Pagamento> pagamento;
	public OrdemServico(int numero, Cliente cliente, Funcionario atendente,
			Calendar dtEntrada, TipoOrdemServico tipo,
			List<ItemServico> servicos, double total, double desconto,
			double vlrPago, MudarStatus status, Veiculo veiculo,
			List<Pagamento> pagamento) {
		super();
		this.numero = numero;
		this.cliente = cliente;
		this.atendente = atendente;
		this.dtEntrada = dtEntrada;
		this.tipo = tipo;
		this.servicos = servicos;
		this.total = total;
		this.desconto = desconto;
		this.vlrPago = vlrPago;
		this.status = status;
		this.veiculo = veiculo;
		this.pagamento = pagamento;
	}
	public OrdemServico() {
		super();
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Funcionario getAtendente() {
		return atendente;
	}
	public void setAtendente(Funcionario atendente) {
		this.atendente = atendente;
	}
	public Calendar getDtEntrada() {
		return dtEntrada;
	}
	public void setDtEntrada(Calendar dtEntrada) {
		this.dtEntrada = dtEntrada;
	}
	public TipoOrdemServico getTipo() {
		return tipo;
	}
	public void setTipo(TipoOrdemServico tipo) {
		this.tipo = tipo;
	}
	public List<ItemServico> getServicos() {
		return servicos;
	}
	public void setServicos(List<ItemServico> servicos) {
		this.servicos = servicos;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public double getVlrPago() {
		return vlrPago;
	}
	public void setVlrPago(double vlrPago) {
		this.vlrPago = vlrPago;
	}
	public MudarStatus getStatus() {
		return status;
	}
	public void setStatus(MudarStatus status) {
		this.status = status;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public List<Pagamento> getPagamento() {
		return pagamento;
	}
	public void setPagamento(List<Pagamento> pagamento) {
		this.pagamento = pagamento;
	}

}
