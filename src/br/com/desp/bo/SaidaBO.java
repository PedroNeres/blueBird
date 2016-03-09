package br.com.desp.bo;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import br.com.desp.beans.Saida;
import br.com.desp.dao.SaidaDAO;

public abstract class SaidaBO {
	
	public static void cadastrar(Saida sai, Connection c)throws Exception{
		if(sai.getDescricao().length() < 3){
			throw new Exception("Caractéres insuficientes para descrição");
		}
		if(sai.getValor() <= 0){
			throw new Exception("O valor de saida não pode ser 0 ou negativo");
		}
		new SaidaDAO().cadastrar(sai, c);
	}
	
	public static void editar(Saida sai, Connection c)throws Exception{
		if(sai.getDescricao().length() < 3){
			throw new Exception("Caractéres insuficientes para descrição");
		}
		if(sai.getValor() <= 0){
			throw new Exception("O valor de saida não pode ser 0 ou negativo");
		}
		new SaidaDAO().editar(sai, c);
	}
	
	public static void deletar(int codigo, Connection c)throws Exception{
		new SaidaDAO().deletar(codigo, c);
	}
	
	public static List<Saida> listar(int cdFilial, Connection c)throws Exception{
		return new SaidaDAO().listar(cdFilial, c);
	}
	
	public static List<Saida> pesqData(int cdFilial, Calendar data1, Calendar data2, Connection c)throws Exception{
		return new SaidaDAO().pesqData(cdFilial, data1, data2, c);
	}
	
	public static Saida pesqCodigo(int codigo, Connection c)throws Exception{
		return new SaidaDAO().pesqCodigo(codigo, c);
	}

}
