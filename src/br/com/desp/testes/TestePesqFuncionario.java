package br.com.desp.testes;

import java.sql.Connection;

import br.com.desp.beans.Funcionario;
import br.com.desp.beans.Usuario;
import br.com.desp.bo.FuncionarioBO;
import br.com.desp.conexao.ConexaoFactory;

public class TestePesqFuncionario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			Usuario usu = new Usuario();
			usu.setEmail("CARVALHO.PEDRO.N@GMAIL.COM");
			usu.setPassword("Helena135086");
			Funcionario fil = FuncionarioBO.pesqEmail(usu, c);
			
			System.out.println(fil.getNome());
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
