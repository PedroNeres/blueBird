package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.desp.beans.Filial;
import br.com.desp.beans.Funcionario;
import br.com.desp.beans.Pessoa;
import br.com.desp.beans.Usuario;
import br.com.desp.bo.EnderecoBO;
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
		String sql = "UPDATE T_DESP_PESSOA SET ds_senha = ?"
				+ "WHERE cd_pessoa = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setString(1, usu.getPassword());
		Pessoa pes = PessoaBO.pesqEmail(usu.getEmail(), c);
		estrutura.setInt(2, pes.getCodigo());
		estrutura.execute();
		estrutura.close();
	}

}
