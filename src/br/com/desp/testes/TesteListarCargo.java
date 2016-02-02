package br.com.desp.testes;

import java.sql.Connection;

import br.com.desp.beans.Cargo;
import br.com.desp.bo.CargoBO;
import br.com.desp.conexao.ConexaoFactory;

public class TesteListarCargo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			Cargo car = new Cargo();
			car.setCodigo(1);
			Cargo cargo = CargoBO.pesqCodigo(car, c);
			System.out.println(cargo.getDescricao());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
