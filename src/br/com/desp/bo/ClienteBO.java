package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.Cliente;
import br.com.desp.dao.ClienteDAO;

public abstract class ClienteBO {

	public static void cadastrar(Cliente cli, Connection c)throws Exception{
		if(Long.toString(cli.getCpf()).length() != 11){
			throw new Exception("O cpf deve ter digitos");
		}
		if(FilialBO.pesqCodigo(cli.getFilial().getCodigo(), c).getNome() == null){
			throw new Exception("Escolha uma filial válida");
		}
		new ClienteDAO().cadastrar(cli, c);
	}
	
	public static void deletar(int codigo, Connection c)throws Exception{
		if(codigo == 0){
			throw new Exception("Digite um código válido");
		}
		new ClienteDAO().deletar(codigo, c);
	}
	
	public static void editar(Cliente cli, Connection c)throws Exception{
		if(Long.toString(cli.getCpf()).length() != 11){
			throw new Exception("O cpf deve ter digitos");
		}
		if(FilialBO.pesqCodigo(cli.getFilial().getCodigo(), c).getNome() == null){
			throw new Exception("Escolha uma filial válida");
		}
		new ClienteDAO().editar(cli, c);
	}
	
	public static List<Cliente> listar(Connection c)throws Exception{
		return new ClienteDAO().listarCliente(c);
	}
	
	public static List<Cliente> listarFilial(int cdFilial, Connection c)throws Exception{
		if(cdFilial == 0){
			throw new Exception("Escolha uma filial para busca");
		}
		return new ClienteDAO().listarClienteFilial(cdFilial, c);
	}
	
	public static Cliente pesqEmail(String email, Connection c)throws Exception{
		if(email.length() < 10){
			throw new Exception("Digite um e-mail válido");
		}
		return new ClienteDAO().pesqEmail(email, c);
	}
	
	public static Cliente pesqNome(String nome, Connection c)throws Exception{
		if(nome.length() < 3){
			throw new Exception("Digite um nome válido");
		}
		return new ClienteDAO().pesqNome(nome, c);
	}
	
	public static Cliente pesqCodigo(int codigo, Connection c)throws Exception{
		if(codigo == 0){
			throw new Exception("Digite um código válido");
		}
		return new ClienteDAO().pesqCodigo(codigo, c);
	}
	
	public static Cliente pesqCpf(String cpf, Connection c)throws Exception{
		if(cpf.length() != 11){
			throw new Exception("Digite um cpf válido");
		}
		if(new ClienteDAO().pesqCpf(cpf, c).getNome() == null){
			throw new Exception("Este cpf não esta cadastrado");
		}
		return new ClienteDAO().pesqCpf(cpf, c);
	}
	
}
