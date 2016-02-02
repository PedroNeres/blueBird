package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.Cargo;

	public class CargoDAO {
		public void cadastar(Cargo car, Connection c)throws Exception{
			String sql = "INSERT INTO T_DESP_TIPO_CARGO (ds_cargo) VALUES(?)";
			PreparedStatement estrutura = c.prepareStatement(sql);
		
			estrutura.setString(1, car.getDescricao());
	
			estrutura.execute();
			estrutura.close();
		}
		
		public void excluir(Cargo car, Connection c)throws Exception{
			String sql = "DELETE FROM T_DESP_TIPO_CARGO WHERE cd_cargo = ?";
			PreparedStatement estrutura = c.prepareStatement(sql);
			
			estrutura.setInt(1, car.getCodigo());
			
			estrutura.execute();
			estrutura.close();
		}
		
		public void editar(Cargo car, Connection c)throws Exception{
			String sql = "UPDATE T_DESP_TIPO_CARGO SET ds_cargo = ? WHERE cd_cargo = ?";
			PreparedStatement estrutura = c.prepareStatement(sql);
			
			estrutura.setString(1, car.getDescricao());
			estrutura.setInt(2, car.getCodigo());
			
			estrutura.execute();
			estrutura.close();
		}
		
		public List<Cargo> listar(Connection c)throws Exception{
			List<Cargo> cargos = new ArrayList<Cargo>();
			Cargo car = null;
			String sql = "SELECT * FROM T_DESP_TIPO_CARGO";
			PreparedStatement estrutura = c.prepareStatement(sql);
		
			ResultSet rs = estrutura.executeQuery();
			while(rs.next()){
				car = new Cargo();
				car.setCodigo(rs.getInt("cd_cargo"));
				car.setDescricao(rs.getString("ds_cargo"));
				cargos.add(car);
			}
			rs.close();
			estrutura.close();
			return cargos;
		}
		
		public Cargo pesqCodigo(int codigo, Connection c)throws Exception{
			Cargo car = new Cargo();
			String sql = "SELECT * FROM T_DESP_TIPO_CARGO WHERE cd_cargo = ?";
			PreparedStatement estrutura = c.prepareStatement(sql);
			
			estrutura.setInt(1, codigo);
			
			ResultSet rs = estrutura.executeQuery();
			if(rs.next()){
				car.setCodigo(rs.getInt("cd_cargo"));
				car.setDescricao(rs.getString("ds_cargo"));
			}
			rs.close();
			estrutura.close();
			return car;
		}
		
		public Cargo pesqDescricao(String descricao, Connection c)throws Exception{
			Cargo car = new Cargo();
			String sql = "SELECT * FROM T_DESP_TIPO_CARGO WHERE ds_cargo = ?";
			PreparedStatement estrutura = c.prepareStatement(sql);
			
			estrutura.setString(1, descricao);
			
			ResultSet rs = estrutura.executeQuery();
			if(rs.next()){
				car.setCodigo(rs.getInt("cd_cargo"));
				car.setDescricao(rs.getString("ds_codigo"));
			}
			rs.close();
			estrutura.close();
			return car;
		}
		
	}
