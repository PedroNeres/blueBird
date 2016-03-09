package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.Contas;
import br.com.desp.dao.ContasDAO;

public abstract class ContasBO {
	
	public static void cadastrar(Contas cont, Connection c)throws Exception{
		if(cont.getDescricao().length() < 3){
			throw new Exception("A descrição da conta não pode ter menos que 3 caracters");
		}
		if(cont.getDiaVencimento() <1 || cont.getDiaVencimento() > 31){
			throw new Exception("O dia de vencimento é inválido");
		}
		if(cont.getQtParcelas() < 1){
			throw new Exception("A quantidade de parcelas não pode ser menor que 1");
		}
		if(cont.getValor() < 5){
			throw new Exception("O valor da conta não pode ser menor que 5 reais");
		}
		new ContasDAO().cadastrar(cont, c);
	}
	
	public static void pagar(Contas cont, Connection c)throws Exception{
		new ContasDAO().pagar(cont, c);
	}
	
	public static void vencendo(Contas cont, Connection c)throws Exception{
		new ContasDAO().vencendo(cont, c);
	}
	
	public static void deletar(int codigo, Connection c)throws Exception{
		new ContasDAO().deletar(codigo, c);
	}
	
	public static void alterarValor(Contas cont, Connection c)throws Exception{
		if(cont.getValor() < 5){
			throw new Exception("O valor da conta não pode ser menor que 5 reais");
		}
		new ContasDAO().alterarValor(cont, c);
	}
	
	public static void alterarDiaVenc(Contas cont, Connection c)throws Exception{
		if(cont.getDiaVencimento() <1 || cont.getDiaVencimento() > 31){
			throw new Exception("O dia de vencimento é inválido");
		}
		new ContasDAO().alterarDiaVenc(cont, c);
	}
	
	public static List<Contas> listar(int cdFilial, Connection c)throws Exception{
		return new ContasDAO().listar(cdFilial, c);
	}
	
	public static Contas pesqCodigo(int codigo, int cdFilial, Connection c)throws Exception{
		return new ContasDAO().pesqCodigo(codigo, cdFilial,  c);
	}
	
	public static Contas pesqDescricao(String descricao, int cdFilial, Connection c)throws Exception{
		return new ContasDAO().pesqDescricao(descricao, cdFilial, c);
	}
	
	public static List<Contas> pesqStatus(int nrStatus, int cdFilial, Connection c)throws Exception{
		return new ContasDAO().pesqStatus(nrStatus, cdFilial, c);
	}

}
