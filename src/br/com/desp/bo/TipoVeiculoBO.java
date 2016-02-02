package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.TipoVeiculo;
import br.com.desp.dao.TipoVeiculoDAO;

public abstract class TipoVeiculoBO {
	
	public static void cadastrar(TipoVeiculo tv, Connection c)throws Exception{
		if(tv.getDescricao().length() < 4){
			throw new Exception("A descrição do tipo de veículo deve ter no mínimo 4 digitos");
		}
		new TipoVeiculoDAO().cadastrar(tv, c);
	}
	
	public static void editar(TipoVeiculo tv, Connection c)throws Exception{
		if(tv.getDescricao().length() < 4){
			throw new Exception("A descrição do tipo de veículo deve ter no mínimo 4 digitos");
		}
		new TipoVeiculoDAO().editar(tv, c);
	}
	
	public static void deletar(int codigo, Connection c)throws Exception{
		if(codigo == 0){
			throw new Exception("Digite um código válido");
		}
		new TipoVeiculoDAO().deletar(codigo, c);
	}
	
	public static List<TipoVeiculo> listar(Connection c)throws Exception{
		return new TipoVeiculoDAO().listar(c);
	}
	
	public static TipoVeiculo pesqCodigo(int codigo, Connection c)throws Exception{
		return new TipoVeiculoDAO().pesqCodigo(codigo, c);
	}
	
	public static TipoVeiculo pesqDescricao(String descricao, Connection c)throws Exception{
		if(descricao.length() < 4){
			throw new Exception("A descrição do tipo de veículo deve ter no mínimo 4 digitos");
		}
		return new TipoVeiculoDAO().pesqDescricao(descricao, c);
	}

}
