package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.FormaPagamento;
import br.com.desp.dao.FormaPagamentoDAO;

public abstract class FormaPagamentoBO {

	public static void cadastrar(FormaPagamento fp, Connection c)throws Exception{
		if(fp.getDescricao().length() <3){
			throw new Exception("Digite uma forma de pagamento com no mínimo 3 caractérs");
		}
		new FormaPagamentoDAO().cadastrar(fp, c);
	}
	
	public static void deletar(FormaPagamento fp, Connection c)throws Exception{
		if(fp.getCodigo() < 1){
			throw new Exception("Digite um código válido");
		}
		new FormaPagamentoDAO().deletar(fp, c);
	}
	
	public static void editar(FormaPagamento fp, Connection c)throws Exception{
		if(fp.getCodigo() < 1){
			throw new Exception("Digite um código válido");
		}
		if(fp.getDescricao().length() <3){
			throw new Exception("Digite uma forma de pagamento com no mínimo 3 caractérs");
		}
		new FormaPagamentoDAO().editar(fp, c);
	}
	
	public static List<FormaPagamento> listar(Connection c)throws Exception{
		return new FormaPagamentoDAO().listar(c);
	}
	
	public static FormaPagamento pesqCodigo(int codigo, Connection c)throws Exception{
		if(codigo < 1){
			throw new Exception("Digite um código válido");
		}
		return new FormaPagamentoDAO().pesCodigo(codigo, c);
	}
	
}
