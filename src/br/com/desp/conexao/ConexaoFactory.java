package br.com.desp.conexao;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConexaoFactory {
	
	private static ConexaoFactory conexao = null;
	
	public static ConexaoFactory controlarInstancia(){
		if (conexao == null){
			conexao = new ConexaoFactory();
		}
		return conexao;
	}
	
	public Connection getConnection() throws Exception{
		
			Class.forName("com.mysql.jdbc.Driver");
		
		return DriverManager.getConnection("jdbc:mysql://mysql01.sgpquality2.hospedagemdesites.ws/sgpquality2", "sgpquality2", "Helena135086");
		//return DriverManager.getConnection(url2);
		
	}

}
