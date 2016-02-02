package br.com.desp.testes;

import java.sql.Connection;

import br.com.desp.conexao.ConexaoFactory;

public abstract class TesteRollback {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			c.rollback();
			System.out.println("Rollback execultado com sucesso");
			c.setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
