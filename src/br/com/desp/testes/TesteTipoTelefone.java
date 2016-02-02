package br.com.desp.testes;

import java.sql.Connection;

import br.com.desp.conexao.ConexaoFactory;

public class TesteTipoTelefone {
	
	public static void main(String[] args) {
		Connection c = null;
		
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			
		}catch(Exception e){
			
		}
	}

}
