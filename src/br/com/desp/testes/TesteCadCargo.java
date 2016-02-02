package br.com.desp.testes;

import java.sql.Connection;

import br.com.desp.beans.Cargo;
import br.com.desp.bo.CargoBO;
import br.com.desp.conexao.ConexaoFactory;

public class TesteCadCargo {
	
	public static void main(String[] args) {
		Connection c = null;
		
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			Cargo car = new Cargo();
			car.setAcesso(1);
			car.setDescricao("Atendente");
			
			CargoBO.cadastrar(car, c);
			System.out.println("Cargo castrado com sucesso");
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
