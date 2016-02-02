package br.com.desp.bo;

import java.sql.Connection;

import br.com.desp.beans.Despachante;
import br.com.desp.dao.DespachanteDAO;

public abstract class DespachanteBO {
	
	public static Despachante pesqCodigo(int codigo, Connection c)throws Exception{
		return new DespachanteDAO().pesqCodigo(codigo, c);
	}
	
	public static Despachante pesqSsp(int ssp, Connection c)throws Exception{
		return new DespachanteDAO().pesqSssp(ssp, c);
	}
	
	public static int cadastrar(Despachante desp, Connection c)throws Exception{
		if(Long.toString(desp.getSsp()).length() < 4){
			throw new Exception("SSP deve ter 5 caractérs");
		}
		return new DespachanteDAO().cadastrar(desp, c);
	}

}
