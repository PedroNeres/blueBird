package br.com.desp.testes;

import java.sql.Connection;

import br.com.desp.conexao.ConexaoFactory;
import br.com.desp.excecao.Excecoes;

public class TesteConexao {

	public static void main(String[] args) throws Excecoes {
		Connection c = null;
		
			try{
				c = ConexaoFactory.controlarInstancia().getConnection();
				System.out.println("Abriu conex�o");
			}catch(Exception e){
				System.out.println("Erro de conex�o");
				e.printStackTrace();
			}finally{
				
				try{
					c.close();
				}catch(Exception e){
					System.out.println(e);
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
	}

}
