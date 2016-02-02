package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.Servico;
import br.com.desp.dao.ServicoDAO;

public abstract class ServicoBO {
	
	public static void cadastrar(Servico ser, Connection c)throws Exception{
		if(ser.getDescricao().length() < 3){
			throw new Exception("A descri��o do servi�o precisa ter no m�nimo 3 caract�rs");
		}
		if(ser.getVlrHono() < 0){
			throw new Exception("O valor do honor�rio n�o pode ser negativo");
		}
		if(ser.getVlrTaxa() < 0){
			throw new Exception("O valor da taxa n�o pode ser negativa");
		}
		if(ser.getVlrHono() == 0 && ser.getVlrTaxa() == 0){
			throw new Exception("� preciso que o honor�rio ou a taxa seja preenchida!");
		}
		new ServicoDAO().cadastrar(ser, c);
	}
	
	public static void editar(Servico ser, Connection c)throws Exception{
		if(ser.getDescricao().length() < 3){
			throw new Exception("A descri��o do servi�o precisa ter no m�nimo 3 caract�rs");
		}
		if(ser.getVlrHono() < 0){
			throw new Exception("O valor do honor�rio n�o pode ser negativo");
		}
		if(ser.getVlrTaxa() < 0){
			throw new Exception("O valor da taxa n�o pode ser negativa");
		}
		new ServicoDAO().editar(ser, c);
	}
	
	public static void deletar(int codigo, Connection c)throws Exception{
		if(codigo == 0){
			throw new Exception("Digite um c�digo de servi�o v�lido");
		}
		new ServicoDAO().deletar(codigo, c);
	}
	
	public static Servico pesqCodigo(int codigo, Connection c)throws Exception{
		if(codigo == 0){
			throw new Exception("Digite um c�digo de servi�o v�lido");
		}
		return new ServicoDAO().pesqCodigo(codigo, c);
	}
	
	public static Servico pesqDescricao(String descricao, Connection c)throws Exception{
		if(descricao.length() < 3){
			throw new Exception("A descri��o do servi�o tem que ter no m�nimo 3 caract�rs");
		}
		return new ServicoDAO().pesqDescricao(descricao, c);
	}
	
	public static List<Servico> listar(Connection c)throws Exception{
		return new ServicoDAO().listar(c);
	}

}
