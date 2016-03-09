package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.Mensagem;
import br.com.desp.dao.MensagemDAO;

public abstract class MensagemBO {
	
	public static void enviar(Mensagem men, Connection c)throws Exception{
		if(men.getAssunto().length() < 1){
			throw new Exception("O assunto é obrigatório");
		}
		if(men.getEmailDestinatario().length() < 5){
			throw new Exception("Digite um e-mail válido");
		}
		if(men.getNomeDestinatario().length() < 2){
			throw new Exception("Digite um nome válido");
		}
		new MensagemDAO().enviar(men, c);
	}
	
	public static void abrir(int codigo, Connection c)throws Exception{
		new MensagemDAO().abrir(codigo, c);
	}
	
	public static void deletar(int codigo, Connection c)throws Exception{
		new MensagemDAO().deletar(codigo, c);
	}
	
	public static List<Mensagem> listarRecebidas(String email, Connection c)throws Exception{
		return new MensagemDAO().listarRecebidas(email, c);
	}
	
	public static List<Mensagem> listarEnviadas(String email, Connection c)throws Exception{
		return new MensagemDAO().listarEnviadas(email, c);
	}
	
	public static List<Mensagem> naoLidos(String email, Connection c)throws Exception{
		return new MensagemDAO().naoLidos(email, c);
	}
	
	public static List<Mensagem> pesqNome(String nome, String email, Connection c)throws Exception{
		return new MensagemDAO().pesqNome(nome, email, c);
	}
	
	public static Mensagem pesqCodigo(int codigo, Connection c)throws Exception{
		return new MensagemDAO().pesqCodigo(codigo, c);
	}

}
