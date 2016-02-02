package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.StatusOs;
import br.com.desp.dao.StatusOsDAO;

public abstract class StatusOsBO {
	
	public static void cadastrar(StatusOs status, Connection c)throws Exception{
		if(status.getDescricao().length() < 3){
			throw new Exception("A descrição do status deve ter no mínimo 3 caractérs");
		}
		new StatusOsDAO().cadastrar(status, c);
	}
	
	public static void editar(StatusOs status, Connection c)throws Exception{
		if(status.getDescricao().length() < 3){
			throw new Exception("A descrição do status deve ter no mínimo 3 caractérs");
		}
		new StatusOsDAO().editar(status, c);
	}
	
	public static void deletar(StatusOs status, Connection c)throws Exception{
		if(StatusOsBO.pesqCodigo(status.getCodigo(), c).getDescricao() == null){
			throw new Exception("Digite um código válido");
		}
		new StatusOsDAO().deletar(status, c);
	}
	
	public static List<StatusOs> listar(Connection c)throws Exception{
		return new StatusOsDAO().listar(c);
	}
	
	public static StatusOs pesqCodigo(int codigo, Connection c)throws Exception{
		if(new StatusOsDAO().pesqCodigo(codigo, c).getDescricao() == null){
			throw new Exception("Código inválido");
		}
		return new StatusOsDAO().pesqCodigo(codigo, c);
	}
	
	public static StatusOs pesqDescricao(String descricao, Connection c)throws Exception{
		if(new StatusOsDAO().pesqDescricao(descricao, c).getDescricao() == null){
			throw new Exception("Descrição não encontrada");
		}
		return new StatusOsDAO().pesqDescricao(descricao, c);
	}

}
