package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.Cargo;
import br.com.desp.dao.CargoDAO;

public abstract class CargoBO {

	public static void cadastrar(Cargo car, Connection c)throws Exception{
		if(car.getDescricao().length() < 3){
			throw new Exception("A descrição do cargo deve ter no mínimo 3 caractérs");
		}
		if(car.getDescricao().length() > 40){
			throw new Exception("A descrição do cargo deve ter no máximo 40 caractérs");
		}
		new CargoDAO().cadastar(car, c);
	}
	
	public static void excluir(Cargo car, Connection c)throws Exception{
		if(new CargoDAO().pesqCodigo(car.getCodigo(), c) == null){
			throw new Exception("Não existe Cargo com este código");
		}
		new CargoDAO().excluir(car, c);
	}
	
	public static void editar(Cargo car, Connection c)throws Exception{
		if(car.getDescricao().length() < 3){
			throw new Exception("A descrição do cargo deve ter no mínimo 3 caractérs");
		}
		if(car.getDescricao().length() > 40){
			throw new Exception("A descrição do cargo deve ter no máximo 40 caractérs");
		}
		new CargoDAO().editar(car, c);
	}
	
	public static List<Cargo> listar(Connection c)throws Exception{
		return new CargoDAO().listar(c);
	}
	
	public static Cargo pesqCodigo(Cargo car, Connection c)throws Exception{
		if(new CargoDAO().pesqCodigo(car.getCodigo(), c) == null){
			throw new Exception("Não existe este cargo cadastrado");
		}
		return new CargoDAO().pesqCodigo(car.getCodigo(), c);
	}
	
	public static Cargo pesqDescricao(Cargo car, Connection c)throws Exception{
		if(new CargoDAO().pesqDescricao(car.getDescricao(), c) == null){
			throw new Exception("Não existe Cargo com este código");
		}
		return new CargoDAO().pesqDescricao(car.getDescricao(), c);
	}
	
}
