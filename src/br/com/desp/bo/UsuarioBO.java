package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.Filial;
import br.com.desp.beans.Funcionario;
import br.com.desp.beans.Usuario;
import br.com.desp.dao.UsuarioDAO;

public abstract class UsuarioBO {

	public static Funcionario logarFun(Usuario usu, Connection c)throws Exception{
		Funcionario fun = new UsuarioDAO().logarFun(usu, c);
		if(PessoaBO.pesqEmail(usu.getEmail(), c).getNome() == null){
			throw new Exception("E-mail inv�lido");
		}
		if(!usu.getPassword().equals(fun.getUsuario().getPassword())){
			throw new Exception("Senha inv�lida");
		}
		return fun;
	}
	
	public static List<Usuario> listar(Connection c)throws Exception{
		return new UsuarioDAO().listarUsuarios(c);
	}
	
	public static Filial logarFilial(Usuario usu, Connection c)throws Exception{
		Filial fil = new UsuarioDAO().logarFilial(usu, c);
		if(PessoaBO.pesqEmail(usu.getEmail(), c).getNome() == null){
			throw new Exception("E-mail inv�lido");
		}
		if(!usu.getPassword().equals(fil.getUsuario().getPassword())){
			throw new Exception("Senha inv�lida");
		}
		return fil;
	}
	
	public static void alterarSenha(Usuario usu, Connection c)throws Exception{
		if(usu.getPassword().length() < 5 || usu.getPassword().length() > 15){
			throw new Exception("A senha deve ter no m�nimo 5 e no m�ximo 15 caract�rs");
		}
		new UsuarioDAO().alterarSenha(usu, c);
	}
	
}
