package br.com.desp.testes;

import java.sql.Connection;

import br.com.desp.beans.Funcionario;
import br.com.desp.bo.FuncionarioBO;
import br.com.desp.conexao.ConexaoFactory;

public class TestePesqFunciCo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Connection c = null;
		
		try {
			
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			Funcionario fun = FuncionarioBO.pesqCodigo(26, c);
			
			System.out.println(fun.getNome());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
