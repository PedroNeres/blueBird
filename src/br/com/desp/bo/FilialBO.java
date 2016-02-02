package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.Filial;
import br.com.desp.dao.FilialDAO;

public abstract class FilialBO {

	public static void cadastrar(Filial fil, Connection c)throws Exception{
		new FilialDAO().cadastrar(fil, c);
	}
	
	public static void desativar(Filial fil, Connection c)throws Exception{
		new FilialDAO().excluir(fil, c);
	}
	
	public static List<Filial> listar(Connection c)throws Exception{
		return new FilialDAO().listar(c);
	}
	
	public static Filial pesqCodigo(int codigo, Connection c)throws Exception{
		if(new FilialDAO().pesqCodigo(codigo, c) == null){
			throw new Exception("Digite um código válido");
		}
		return new FilialDAO().pesqCodigo(codigo, c);
	}
	
	public static List<Filial> listFilDesp(int cdDespachante, Connection c)throws Exception{
		if(DespachanteBO.pesqCodigo(cdDespachante, c) == null){
			throw new Exception("Digite um código de despachante válido");
		}
		return new FilialDAO().listarFilDesp(cdDespachante, c);
	}
	
}
