package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.TipoVeiculo;

public class TipoVeiculoDAO {
	
	public void cadastrar(TipoVeiculo tv, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_TIPO_VEICULO(ds_tipo) VALUES(?)";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, tv.getDescricao());
		estrutura.execute();
		estrutura.close();
	}
	
	public void editar(TipoVeiculo tv, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_TIPO_VEICULO SET(ds_tipo = ?) WHERE cd_tipo = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, tv.getDescricao());
		estrutura.setInt(2, tv.getCodigo());
		estrutura.execute();
		estrutura.close();
	}
	
	public void deletar(int codigo, Connection c)throws Exception{
		String sql = "DELETE FROM T_DESP_TIPO_VEICULO WHERE cd_tipo = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		estrutura.execute();
		estrutura.close();
	}
	
	public List<TipoVeiculo> listar(Connection c)throws Exception{
		List<TipoVeiculo> tipos = new ArrayList<TipoVeiculo>();
		TipoVeiculo tv = null;
		String sql = "SELECT * FROM T_DESP_TIPO_VEICULO ORDER BY cd_tipo";
		PreparedStatement estrutura = c.prepareStatement(sql);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			tv = new TipoVeiculo();
			tv.setCodigo(rs.getInt("cd_tipo"));
			tv.setDescricao(rs.getString("ds_tipo"));
			tipos.add(tv);
		}
		rs.close();
		estrutura.close();
		return tipos;
	}
	
	public TipoVeiculo pesqCodigo(int codigo, Connection c)throws Exception{
		TipoVeiculo tv = new TipoVeiculo();
		String sql = "SELECT * FROM T_DESP_TIPO_VEICULO WHERE cd_tipo = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			tv.setCodigo(rs.getInt("cd_tipo"));
			tv.setDescricao(rs.getString("ds_tipo"));
		}
		rs.close();
		estrutura.close();
		return tv;
	}
	
	public TipoVeiculo pesqDescricao(String descricao, Connection c)throws Exception{
		TipoVeiculo tv = new TipoVeiculo();
		String sql = "SELECT * FROM T_DESP_TIPO_VEICULO WHERE ds_tipo = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, descricao);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			tv.setCodigo(rs.getInt("cd_tipo"));
			tv.setDescricao(rs.getString("ds_tipo"));
		}
		rs.close();
		estrutura.close();
		return tv;
	}

}
