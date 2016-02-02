package br.com.desp.testes;

import java.sql.Connection;

import br.com.desp.beans.Despachante;
import br.com.desp.bo.DespachanteBO;
import br.com.desp.conexao.ConexaoFactory;

public class TesteCadDespachante {
	public static void main(String[] args) {
		Connection c = null;
		
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			Despachante desp = new Despachante();
			desp.setRazaoSociao("Despachante e Assossiação Gol de Placa");
			try{
				long ll = Long.parseLong("07081");
				desp.setSsp(ll);
			}catch(Exception e){
				e.printStackTrace();
			}
			DespachanteBO.cadastrar(desp, c);
			System.out.println("Despachante cadastrado com sucesso!");
			c.commit();
			c.setAutoCommit(true);
		}catch(Exception e){
			try{
				c.rollback();
				e.printStackTrace();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
}
