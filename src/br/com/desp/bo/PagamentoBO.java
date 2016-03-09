package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.Pagamento;
import br.com.desp.dao.PagamentoDAO;

public abstract class PagamentoBO {
	
	public static void cadastrar(Pagamento pag, Connection c)throws Exception{
		if(FormaPagamentoBO.pesqCodigo(pag.getForPagamento().getCodigo(), c).getDescricao() == null){
			throw new Exception("Escolha uma foma de pagamento");
		}
		if(pag.getVlPagao() <= 0){
			throw new Exception("O valor do pagamento não pode ser zerado ou negativo");
		}
		new PagamentoDAO().cadastrar(pag, c);
	}
	
	public static void editar(Pagamento pag, Connection c)throws Exception{
		if(FormaPagamentoBO.pesqCodigo(pag.getForPagamento().getCodigo(), c).getDescricao() == null){
			throw new Exception("Escolha uma foma de pagamento");
		}
		if(OrdemServicoBO.pesqNumeroOs(pag.getOrdemServico().getNumero(), c).getNumero() == 0){
			throw new Exception("Ordem de serviço inválida para lançamento de pagamento");
		}
		if(pag.getVlPagao() <= 0){
			throw new Exception("O valor do pagamento não pode ser zerado ou negativo");
		}
		new PagamentoDAO().editar(pag, c);
	}
	
	public static void deletar(int cdPagamento, Connection c)throws Exception{
		if(PagamentoBO.pesqCodigo(cdPagamento, c).getForPagamento().getDescricao() == null){
			throw new Exception("Digite um código de pagamento válido");
		}
		new PagamentoDAO().deletar(cdPagamento, c);
	}
	
	public static void aprovar(int cdPagamento, Connection c)throws Exception{
		if(PagamentoBO.pesqCodigo(cdPagamento, c).getForPagamento().getDescricao() == null){
			throw new Exception("Digite um código de pagamento válido");
		}
		new PagamentoDAO().aprovar(cdPagamento, c);
	}
	
	public static void aprovarTodos(Connection c)throws Exception{
		new PagamentoDAO().aprovarTodos(c);
	}
	
	public static List<Pagamento> listarOrdem(int nrOrdem, Connection c)throws Exception{
		if(nrOrdem == 0){
			throw new Exception("Digite um número de os para listar pagamentos");
		}
		return new PagamentoDAO().listarPagOs(nrOrdem, c);
	}
	
	public static List<Pagamento> listarPagAberto(int cdFilial, Connection c)throws Exception{
		return new PagamentoDAO().listarPagAber(cdFilial, c);
	}
	
	public static Pagamento pesqCodigo(int cdPagamento, Connection c)throws Exception{
		if(cdPagamento == 0){
			throw new Exception("Digite um código para pesquisa");
		}
		return new PagamentoDAO().pesqCodigo(cdPagamento, c);
	}
	
	

}
