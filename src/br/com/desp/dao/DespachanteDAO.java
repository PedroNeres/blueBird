package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.desp.beans.Despachante;


public class DespachanteDAO {
	
	public Despachante pesqCodigo(int codigo, Connection c)throws Exception{
		Despachante desp = new Despachante();
		String sql = "SELECT * FROM T_DESP_DESPACHANTE WHERE cd_despachante = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		estrutura.execute();
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			desp.setCodigo(rs.getInt("cd_despachante"));
			desp.setSsp(rs.getLong("nr_ssp"));
		}
		rs.close();
		estrutura.close();
		return desp;
	}
	
	public Despachante pesqSssp(int ssp, Connection c)throws Exception{
		Despachante desp = new Despachante();
		String sql = "SELECT * FROM T_DESP_DESPACHANTE WHERE nr_ssp = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, ssp);
		
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			desp.setCodigo(rs.getInt("cd_despachante"));
			desp.setSsp(rs.getLong("nr_ssp"));
		}
		rs.close();
		estrutura.close();
		return desp;
	}
	
	public int cadastrar(Despachante desp, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_DESPACHANTE (nr_ssp) VALUES(?)";
		PreparedStatement estrutura = c.prepareStatement(sql, new String[]{"cd_pessoa"});
		estrutura.setLong(1, desp.getSsp());
		estrutura.execute();
		
		ResultSet rs = estrutura.getGeneratedKeys();
		rs.next();
		desp.setCodigo(Integer.parseInt(rs.getString(1)));
		rs.close();
		estrutura.close();
		return desp.getCodigo();
	}

}
