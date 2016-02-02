package br.com.desp.dao;

import java.sql.Connection;

import br.com.desp.beans.Filial;
import br.com.desp.beans.Funcionario;
import br.com.desp.beans.Pessoa;
import br.com.desp.beans.Usuario;
import br.com.desp.bo.FilialBO;
import br.com.desp.bo.FuncionarioBO;
import br.com.desp.bo.PessoaBO;

public class UsuarioDAO {
	
	public Funcionario logarFun(Usuario usu, Connection c)throws Exception{
		Funcionario fun = FuncionarioBO.pesqEmail(usu, c);
		return fun;
	}
	
	public Filial logarFilial(Usuario usu, Connection c)throws Exception{
		Pessoa pes = PessoaBO.pesqEmail(usu.getEmail(), c);
		Filial fil = FilialBO.pesqCodigo(pes.getCodigo(), c);
		return fil;
	}
	
	public void alterarSenha(Usuario usu, Connection c)throws Exception{
		Pessoa pes = PessoaBO.pesqEmail(usu.getEmail(), c);
		pes.setUsuario(usu);
		PessoaBO.atualizar(pes, c);
	}

}
