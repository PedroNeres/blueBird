package br.com.desp.testes;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.Cargo;
import br.com.desp.beans.Funcionario;
import br.com.desp.bo.CargoBO;
import br.com.desp.bo.FuncionarioBO;
import br.com.desp.conexao.ConexaoFactory;

public class TesteListFuncionario {
	
	public static void main(String[] args) {
		Connection c = null;
		
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			List<Funcionario> funcionarios = FuncionarioBO.listarFunFil(25, c);
			Cargo car1 = new Cargo();
			car1.setCodigo(1);
			Cargo car = CargoBO.pesqCodigo(car1, c);
			System.out.println(car.getDescricao());
			
			for(Funcionario fun: funcionarios){
				System.out.println("Nome: " + fun.getNome());
				System.out.println("Email: " + fun.getUsuario().getEmail());
				System.out.println("Cargo: " + fun.getCargo().getDescricao());
				System.out.println();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
