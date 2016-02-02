package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.TipoOrdemServico;
import br.com.desp.dao.TipoOrdemDAO;

public abstract class TipoOrdemBO {

	public static void cadastrar(TipoOrdemServico tipoOrdem, Connection c)throws Exception{
		if(tipoOrdem.getDescricao().length() < 4){
			throw new Exception("A descri��o do tipo de Ordem de servi�o deve ter no m�nimo 4 digitos");
		}
		new TipoOrdemDAO().cadastrar(tipoOrdem, c);
	}
	
	public static void editar(TipoOrdemServico tipoOrdem, Connection c)throws Exception{
		if(tipoOrdem.getDescricao().length() < 4){
			throw new Exception("A descri��o do tipo de Ordem de servi�o deve ter no m�nimo 4 digitos");
		}
		new TipoOrdemDAO().editar(tipoOrdem, c);
	}
	
	public static void deletar(int codigo, Connection c)throws Exception{
		if(codigo == 0){
			throw new Exception("Digite um c�digo v�lido");
		}
		new TipoOrdemDAO().deletar(codigo, c);
	}
	
	public static List<TipoOrdemServico> listar(Connection c)throws Exception{
		return new TipoOrdemDAO().listar(c);
	}
	
	public static TipoOrdemServico pesqCodigo(int codigo, Connection c)throws Exception{
		if(codigo == 0){
			throw new Exception("Digite um c�digo v�lido");
		}
		return new TipoOrdemDAO().pesqCodigo(codigo, c);
	}
	
	public static TipoOrdemServico pesqDescricao(String descricao, Connection c)throws Exception{
		if(descricao.length() < 4){
			throw new Exception("A descri��o do tipo de Ordem de servi�o deve ter no m�nimo 4 digitos");
		}
		return new TipoOrdemDAO().pesqDescricao(descricao, c);
	}
	
}
