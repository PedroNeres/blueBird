package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.Pessoa;
import br.com.desp.dao.PessoaDAO;

public abstract class PessoaBO {
	
	public static int cadastrar(Pessoa pes, Connection c)throws Exception{
		if(pes.getNome().length() < 4){
			throw new Exception("O nome deve ter no m�nimo 4 letras");
		}
		if(pes.getNome().length() > 60){
			throw new Exception("O nome deve ter no m�ximo 60 letras");
		}
		if(pes.getUsuario().getEmail().length() < 7){
			throw new Exception("Digite um e-mail v�lido");
		}
		if(pes.getUsuario().getPassword().length() > 60){
			throw new Exception("O e-mail n�o pode ter mais de 60 caracters");
		}
		if(pes.getUsuario().getPassword().length() < 5 || pes.getUsuario().getPassword().length() > 15){
			throw new Exception("A senha deve ter no m�nimo 5, e no m�ximo 15 caract�rs");
		}
		if(PessoaBO.pesqEmail(pes.getUsuario().getEmail(), c).getUsuario() != null){
			throw new Exception("Este email ja esta cadastrado!");
		}
		return new PessoaDAO().cadastrar(pes, c);
	}
	
	public static void atualizar(Pessoa pes, Connection c)throws Exception{
		if(pes.getNome().length() < 4){
			throw new Exception("O nome deve ter no m�nimo 4 letras");
		}
		if(pes.getNome().length() > 60){
			throw new Exception("O nome deve ter no m�ximo 60 letras");
		}
		new PessoaDAO().atualizar(pes, c);
	}
	
	public static void desativar(Pessoa pes, Connection c)throws Exception{
		if(new PessoaDAO().pesqCodigo(pes.getCodigo(), c) == null){
			throw new Exception("O c�digo digitado n�o existe");
		}
		new PessoaDAO().excluir(pes, c);
	}
	
	public static List<Pessoa> listar(Connection c)throws Exception{
		if(new PessoaDAO().listar(c) == null){
			throw new Exception("N�o existe nenhum cadastro");
		}
		return new PessoaDAO().listar(c);
	}
	
	public static Pessoa pesqCodigo(int codigo, Connection c)throws Exception{
		if(new PessoaDAO().pesqCodigo(codigo, c) == null){
			throw new Exception("N�o tem nenhum registro com este c�digo");
		}
		return new PessoaDAO().pesqCodigo(codigo, c);
	}
	
	public static Pessoa pesqNome(String nome, Connection c)throws Exception{
		if(new PessoaDAO().pesqNome(nome, c) == null){
			throw new Exception("Verifique o nome digitado, e consulte novamente");
		}
		return new PessoaDAO().pesqNome(nome, c);
	}
	
	public static Pessoa pesqEmail(String email, Connection c)throws Exception{
		if(new PessoaDAO().pesqEmail(email, c) == null){
			throw new Exception("O e-mail digitado n�o existe");
		}
		return new PessoaDAO().pesqEmail(email, c);
	}
	
	public static int verEmail(String email, Connection c)throws Exception{
		return new PessoaDAO().verEmail(email, c);
	}
	
	public static List<Pessoa> listarDesativados(Connection c)throws Exception{
		if(new PessoaDAO().listarDesativados(c) == null){
			throw new Exception("N�o existe nenhum usu�rio ou desativado");
		}
		return new PessoaDAO().listarDesativados(c);
	}

}
