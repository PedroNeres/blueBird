package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.Servico;
import br.com.desp.dao.ServicoDAO;

public abstract class ServicoBO {
	
	public static void cadastrar(Servico ser, Connection c)throws Exception{
		if(ser.getDescricao().length() < 3){
			throw new Exception("A descrição do serviço precisa ter no mínimo 3 caractérs");
		}
		if(ser.getVlrHono() < 0){
			throw new Exception("O valor do honorário não pode ser negativo");
		}
		if(ser.getVlrTaxa() < 0){
			throw new Exception("O valor da taxa não pode ser negativa");
		}
		if(ser.getVlrHono() == 0 && ser.getVlrTaxa() == 0){
			throw new Exception("É preciso que o honorário ou a taxa seja preenchida!");
		}
		new ServicoDAO().cadastrar(ser, c);
	}
	
	public static void editar(Servico ser, Connection c)throws Exception{
		if(ser.getDescricao().length() < 3){
			throw new Exception("A descrição do serviço precisa ter no mínimo 3 caractérs");
		}
		if(ser.getVlrHono() < 0){
			throw new Exception("O valor do honorário não pode ser negativo");
		}
		if(ser.getVlrTaxa() < 0){
			throw new Exception("O valor da taxa não pode ser negativa");
		}
		new ServicoDAO().editar(ser, c);
	}
	
	public static void deletar(int codigo, Connection c)throws Exception{
		if(codigo == 0){
			throw new Exception("Digite um código de serviço válido");
		}
		new ServicoDAO().deletar(codigo, c);
	}
	
	public static Servico pesqCodigo(int codigo, Connection c)throws Exception{
		if(codigo == 0){
			throw new Exception("Digite um código de serviço válido");
		}
		return new ServicoDAO().pesqCodigo(codigo, c);
	}
	
	public static Servico pesqDescricao(String descricao, Connection c)throws Exception{
		if(descricao.length() < 3){
			throw new Exception("A descrição do serviço tem que ter no mínimo 3 caractérs");
		}
		return new ServicoDAO().pesqDescricao(descricao, c);
	}
	
	public static List<Servico> listar(Connection c)throws Exception{
		return new ServicoDAO().listar(c);
	}

}
