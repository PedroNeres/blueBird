package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.StatusOs;

public class StatusOsDAO {
	
	public void cadastrar(StatusOs status, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_STATUS_OS(ds_status_os) VALUES(?)";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, status.getDescricao());
		estrutura.execute();
		estrutura.close();
	}
	
	public void editar(StatusOs status, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_STATUS_OS SET(ds_status_os = ?) WHERE cd_status_os = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, status.getDescricao());
		estrutura.setInt(2, status.getCodigo());
		estrutura.execute();
		estrutura.close();
	}
	
	public void deletar(StatusOs status, Connection c)throws Exception{
		String sql = "DELETE FROM T_DESP_STATUS_OS WHERE cd_status_os = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, status.getCodigo());
		estrutura.execute();
		estrutura.close();
	}
	
	public List<StatusOs> listar(Connection c)throws Exception{
		List<StatusOs> status = new ArrayList<StatusOs>();
		StatusOs sta = null;
		String sql = "SELECT * FROM T_DESP_STATUS_OS";
		PreparedStatement estrutura = c.prepareStatement(sql);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			sta = new StatusOs();
			sta.setCodigo(rs.getInt("cd_status_os"));
			sta.setDescricao(rs.getString("ds_status_os"));
			status.add(sta);
		}
		rs.close();
		estrutura.close();
		return status;
	}
	
	public StatusOs pesqCodigo(int codigo, Connection c)throws Exception{
		StatusOs status = new StatusOs();
		String sql = "SELECT * FROM T_DESP_STATUS_OS WHERE cd_status_os = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			status.setCodigo(rs.getInt("cd_status_os"));
			status.setDescricao(rs.getString("ds_status_os"));
		}
		rs.close();
		estrutura.close();
		return status;
	}
	
	public StatusOs pesqDescricao(String descricao, Connection c)throws Exception{
		StatusOs status = new StatusOs();
		String sql = "SELECT * FROM T_DESP_STATUS_OS WHERE ds_status_os = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, descricao);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			status.setCodigo(rs.getInt("cd_status_os"));
			status.setDescricao(rs.getString("ds_status_os"));
		}
		rs.close();
		estrutura.close();
		return status;
	}
}
