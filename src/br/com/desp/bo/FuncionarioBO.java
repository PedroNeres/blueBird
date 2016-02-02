package br.com.desp.bo;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import br.com.desp.beans.Funcionario;
import br.com.desp.beans.Pessoa;
import br.com.desp.beans.Usuario;
import br.com.desp.dao.FuncionarioDAO;
import br.com.desp.excecao.Excecoes;
import br.com.desp.util.DataUtil;

public abstract class FuncionarioBO {
	
	public static void cadastrar(Funcionario fun, Connection c)throws Exception{
		if(Long.toString(fun.getCpf()).length() != 11){
			throw new Exception("O cpf deve ter 11 digitos");
		}
		if(FuncionarioBO.pesqEmail(fun.getUsuario(), c).getCpf() != 0){
			throw new Exception("O cpf ja esta cadastrado");
		}
		if(fun.getSalario() < 500){
			throw new Exception("O salário deve ser superior a R$ 500,00");
		}
		if(DataUtil.validarData(fun.getDtAdmissao()) >= 0){
			throw new Exception("A data de Admissão não pode ser depois da data de hoje");
		}	
		new FuncionarioDAO().cadastrar(fun, c);
	}
	
	public static void destativar(Pessoa pes, Connection c)throws Exception{
		new FuncionarioDAO().desativar(pes, c);
	}
	
	public static void editar(Funcionario fun, Connection c)throws Exception{
		if(fun.getSalario() < 500){
			throw new Exception("O salário deve ser superior a R$ 500,00");
		}
		if(DataUtil.validarData(fun.getDtAdmissao()) >= 0){
			throw new Exception("A data de Admissão não pode ser depois da data de hoje");
		}
		new FuncionarioDAO().editar(fun, c);
	}
	
	public static List<Funcionario> listarTodos(Connection c)throws Exception{
		return new FuncionarioDAO().listarTodos(c);
	}
	
	public static List<Funcionario> listarFunFil(int cdFilial, Connection c)throws Exception{
		return new FuncionarioDAO().listarFunFilial(cdFilial, c);
	}
	
	public static Funcionario pesqEmail(Usuario usu, Connection c)throws Exception{
		if(usu.getEmail().length() < 10){
			throw new Exception("Digite um e-mail válido");
		}
		if(new FuncionarioDAO().pesqEmail(usu, c) == null){
			throw new Exception("E-mail inválido");
		}
		return new FuncionarioDAO().pesqEmail(usu, c);
	}
	
	public static Funcionario pesqCodigo(int codigo, Connection c)throws Exception{
		return new FuncionarioDAO().pesqCodigo(codigo, c);
	}

}
