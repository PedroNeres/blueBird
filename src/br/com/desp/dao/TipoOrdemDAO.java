package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.TipoOrdemServico;

public class TipoOrdemDAO {
	
	public void cadastrar(TipoOrdemServico tipoOrdem, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_TIPO_ORDEM(ds_descricao) VALUES(?)";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, tipoOrdem.getDescricao());
		estrutura.execute();
		estrutura.close();
	}
	
	public void editar(TipoOrdemServico tipoOrdem, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_TIPO_ORDEM SET(ds_descricao = ?) WHERE cd_tipo_ordem = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, tipoOrdem.getDescricao());
		estrutura.setInt(2, tipoOrdem.getCodigo());
		estrutura.execute();
		estrutura.close();
	}
	
	public void deletar(int codigo, Connection c)throws Exception{
		String sql = "DELETE FROM T_DESP_TIPO_ORDEM WHERE cd_tipo_ordem = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		estrutura.execute();
		estrutura.close();
	}
	
	public List<TipoOrdemServico> listar(Connection c)throws Exception{
		List<TipoOrdemServico> tipos = new ArrayList<TipoOrdemServico>();
		TipoOrdemServico tipoOrdem = null;
		String sql = "SELECT * FROM T_DESP_TIPO_ORDEM";
		PreparedStatement estrutura = c.prepareStatement(sql);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			tipoOrdem = new TipoOrdemServico();
			tipoOrdem.setCodigo(rs.getInt("cd_tipo_ordem"));
			tipoOrdem.setDescricao(rs.getString("ds_descricao"));
			tipos.add(tipoOrdem);
		}
		rs.close();
		estrutura.close();
		return tipos;
		
	}
	
	public TipoOrdemServico pesqCodigo(int codigo, Connection c)throws Exception{
		TipoOrdemServico tipoOrdem = new TipoOrdemServico();
		String sql = "SELECT * FROM T_DESP_TIPO_ORDEM WHERE cd_tipo_ordem = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			tipoOrdem.setCodigo(rs.getInt("cd_tipo_ordem"));
			tipoOrdem.setDescricao(rs.getString("ds_descricao"));
		}
		rs.close();
		estrutura.close();
		return tipoOrdem;
	}
	
	public TipoOrdemServico pesqDescricao(String descricao, Connection c)throws Exception{
		TipoOrdemServico tipoOrdem = new TipoOrdemServico();
		String sql = "SELECT * FROM T_DESP_TIPO_ORDEM WHERE ds_descricao = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, descricao);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			tipoOrdem.setCodigo(rs.getInt("cd_tipo_ordem"));
			tipoOrdem.setDescricao(rs.getString("ds_descricao"));
		}
		rs.close();
		estrutura.close();
		return tipoOrdem;
	}

}
