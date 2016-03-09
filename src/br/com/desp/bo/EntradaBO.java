package br.com.desp.bo;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import br.com.desp.beans.Entrada;
import br.com.desp.dao.EntradaDAO;

public abstract class EntradaBO {
	
	public static void cadastrar(Entrada ent, Connection c)throws Exception{
		if(ent.getDescricao().length() < 3){
			throw new Exception("Caractéres insuficientes para descrição");
		}
		if(ent.getValor() <= 0){
			throw new Exception("O valor de saida não pode ser 0 ou negativo");
		}
		new EntradaDAO().cadastrar(ent, c);
	}
	
	public static void editar(Entrada ent, Connection c)throws Exception{
		if(ent.getDescricao().length() < 3){
			throw new Exception("Caractéres insuficientes para descrição");
		}
		if(ent.getValor() <= 0){
			throw new Exception("O valor de saida não pode ser 0 ou negativo");
		}
		new EntradaDAO().editar(ent, c);
	}
	
	public static void deletar(int codigo, Connection c)throws Exception{
		new EntradaDAO().deletar(codigo, c);
	}
	
	public static List<Entrada> listar(int cdFilial, Connection c)throws Exception{
		return new EntradaDAO().listar(cdFilial, c);
	}
	
	public static List<Entrada> pesqData(int cdFilial, Calendar data1, Calendar data2, Connection c)throws Exception{
		return new EntradaDAO().pesqData(cdFilial, data1, data2, c);
	}
	
	public static Entrada pesqCodigo(int codigo, Connection c)throws Exception{
		return new EntradaDAO().pesqCodigo(codigo, c);
	}

}
