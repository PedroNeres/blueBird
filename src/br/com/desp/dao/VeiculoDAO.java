package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.OrdemServico;
import br.com.desp.beans.Veiculo;
import br.com.desp.bo.TipoVeiculoBO;

public class VeiculoDAO {
	
	public int cadastrarVeic(Veiculo veic, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_VEICULO(nr_placa, nr_renavam, cd_tipo_veiculo, ds_modelo)VALUES(?,?,?,?)";
		PreparedStatement estrutura = c.prepareStatement(sql, new String[]{"cd_veiculo"});
		estrutura.setString(1, veic.getPlaca());
		estrutura.setLong(2, veic.getRenavam());
		estrutura.setInt(3, veic.getTipo().getCodigo());
		estrutura.setString(4, veic.getModelo());
		System.out.println(veic.getModelo());
		estrutura.execute();
		
		ResultSet rs = estrutura.getGeneratedKeys();
		rs.next();
		veic.setCodigo(Integer.parseInt(rs.getString(1)));
		
		rs.close();
		estrutura.close();
		return veic.getCodigo();
	}
	
	public void cadastrar(OrdemServico os, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_VEICULO_ORDEM(cd_veiculo, nr_ordem) VALUES(?,?)";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, os.getVeiculo().getCodigo());
		estrutura.setInt(2, os.getNumero());
		estrutura.execute();
		estrutura.close();
	}
	
	public int editar(Veiculo veic, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_VEICULO SET nr_placa =?, nr_renavam = ?, cd_tipo_veiculo = ?, ds_modelo = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, veic.getPlaca());
		estrutura.setLong(2, veic.getRenavam());
		estrutura.setInt(3, veic.getTipo().getCodigo());
		estrutura.setString(4, veic.getModelo());
		estrutura.execute();
		estrutura.close();
		return veic.getCodigo();
	}
	
	public void deletar(Veiculo veic, Connection c)throws Exception{
		String sql = "DELETE FROM T_DESP_VEICULO WHERE cd_veiculo = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, veic.getCodigo());
		estrutura.execute();
		estrutura.close();
	}
	
	public List<Veiculo> listar(Connection c)throws Exception{
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		Veiculo veic = null;
		String sql = "SELECT * FROM T_DESP_VEICULO";
		PreparedStatement estrutura = c.prepareStatement(sql);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			veic = new Veiculo();
			veic.setCodigo(rs.getInt("cd_veiculo"));
			veic.setPlaca(rs.getString("nr_placa"));
			veic.setRenavam(rs.getLong("nr_renavam"));
			veic.setModelo(rs.getString("ds_renavam"));
			veic.setTipo(TipoVeiculoBO.pesqCodigo(rs.getInt("cd_tipo_veiculo"), c));
			veiculos.add(veic);
		}
		return veiculos;
	}
	
	public Veiculo pesqCodigo(int codigo, Connection c)throws Exception{
		Veiculo veic = new Veiculo();
		String sql = "SELECT * FROM T_DESP_VEICULO WHERE cd_veiculo = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			veic.setCodigo(rs.getInt("cd_veiculo"));
			veic.setPlaca(rs.getString("nr_placa"));
			veic.setRenavam(rs.getLong("nr_renavam"));
			veic.setModelo(rs.getString("ds_modelo"));
			veic.setTipo(TipoVeiculoBO.pesqCodigo(rs.getInt("cd_tipo_veiculo"), c));
		}
		return veic;
	}
	
	public Veiculo pesqPlaca(String placa, Connection c)throws Exception{
		Veiculo veic = new Veiculo();
		String sql = "SELECT * FROM T_DESP_VEICULO WHERE nr_placa = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, placa);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			veic.setCodigo(rs.getInt("cd_veiculo"));
			veic.setPlaca(rs.getString("nr_placa"));
			veic.setRenavam(rs.getLong("nr_renavam"));
			veic.setModelo(rs.getString("ds_modelo"));
			veic.setTipo(TipoVeiculoBO.pesqCodigo(rs.getInt("cd_tipo_veiculo"), c));
		}
		return veic;
	}
	
	public Veiculo pesqRenavam(int renavam, Connection c)throws Exception{
		Veiculo veic = new Veiculo();
		String sql = "SELECT * FROM T_DESP_VEICULO WHERE nr_renavam = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, renavam);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			veic.setCodigo(rs.getInt("cd_veiculo"));
			veic.setPlaca(rs.getString("nr_placa"));
			veic.setRenavam(rs.getLong("nr_renavam"));
			veic.setModelo(rs.getString("ds_modelo"));
			veic.setTipo(TipoVeiculoBO.pesqCodigo(rs.getInt("cd_tipo_veiculo"), c));
		}
		return veic;
	}

}
