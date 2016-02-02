package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.Cargo;
import br.com.desp.dao.CargoDAO;

public abstract class CargoBO {

	public static void cadastrar(Cargo car, Connection c)throws Exception{
		if(car.getDescricao().length() < 3){
			throw new Exception("A descri��o do cargo deve ter no m�nimo 3 caract�rs");
		}
		if(car.getDescricao().length() > 40){
			throw new Exception("A descri��o do cargo deve ter no m�ximo 40 caract�rs");
		}
		new CargoDAO().cadastar(car, c);
	}
	
	public static void excluir(Cargo car, Connection c)throws Exception{
		if(new CargoDAO().pesqCodigo(car.getCodigo(), c) == null){
			throw new Exception("N�o existe Cargo com este c�digo");
		}
		new CargoDAO().excluir(car, c);
	}
	
	public static void editar(Cargo car, Connection c)throws Exception{
		if(car.getDescricao().length() < 3){
			throw new Exception("A descri��o do cargo deve ter no m�nimo 3 caract�rs");
		}
		if(car.getDescricao().length() > 40){
			throw new Exception("A descri��o do cargo deve ter no m�ximo 40 caract�rs");
		}
		new CargoDAO().editar(car, c);
	}
	
	public static List<Cargo> listar(Connection c)throws Exception{
		return new CargoDAO().listar(c);
	}
	
	public static Cargo pesqCodigo(Cargo car, Connection c)throws Exception{
		if(new CargoDAO().pesqCodigo(car.getCodigo(), c) == null){
			throw new Exception("N�o existe este cargo cadastrado");
		}
		return new CargoDAO().pesqCodigo(car.getCodigo(), c);
	}
	
	public static Cargo pesqDescricao(Cargo car, Connection c)throws Exception{
		if(new CargoDAO().pesqDescricao(car.getDescricao(), c) == null){
			throw new Exception("N�o existe Cargo com este c�digo");
		}
		return new CargoDAO().pesqDescricao(car.getDescricao(), c);
	}
	
}
