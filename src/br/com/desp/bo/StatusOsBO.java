package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.StatusOs;
import br.com.desp.dao.StatusOsDAO;

public abstract class StatusOsBO {
	
	public static void cadastrar(StatusOs status, Connection c)throws Exception{
		if(status.getDescricao().length() < 3){
			throw new Exception("A descri��o do status deve ter no m�nimo 3 caract�rs");
		}
		new StatusOsDAO().cadastrar(status, c);
	}
	
	public static void editar(StatusOs status, Connection c)throws Exception{
		if(status.getDescricao().length() < 3){
			throw new Exception("A descri��o do status deve ter no m�nimo 3 caract�rs");
		}
		new StatusOsDAO().editar(status, c);
	}
	
	public static void deletar(StatusOs status, Connection c)throws Exception{
		if(StatusOsBO.pesqCodigo(status.getCodigo(), c).getDescricao() == null){
			throw new Exception("Digite um c�digo v�lido");
		}
		new StatusOsDAO().deletar(status, c);
	}
	
	public static List<StatusOs> listar(Connection c)throws Exception{
		return new StatusOsDAO().listar(c);
	}
	
	public static StatusOs pesqCodigo(int codigo, Connection c)throws Exception{
		if(new StatusOsDAO().pesqCodigo(codigo, c).getDescricao() == null){
			throw new Exception("C�digo inv�lido");
		}
		return new StatusOsDAO().pesqCodigo(codigo, c);
	}
	
	public static StatusOs pesqDescricao(String descricao, Connection c)throws Exception{
		if(new StatusOsDAO().pesqDescricao(descricao, c).getDescricao() == null){
			throw new Exception("Descri��o n�o encontrada");
		}
		return new StatusOsDAO().pesqDescricao(descricao, c);
	}

}
