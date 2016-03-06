package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.Servico;
import br.com.desp.beans.TipoOrdemServico;
import br.com.desp.beans.TipoVeiculo;

public class ServicoDAO {
	
	public void cadastrar(Servico ser, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_SERVICOS(ds_servico, vl_taxa, vl_hono, vl_total) "
				+ "VALUES(?,?,?,?)";
		PreparedStatement estrutura = c.prepareStatement(sql, new String[]{"cd_servico"});
		estrutura.setString(1, ser.getDescricao());
		estrutura.setDouble(2, ser.getVlrTaxa());
		estrutura.setDouble(3, ser.getVlrHono());
		estrutura.setDouble(4, ser.getVlrTotal());
		estrutura.execute();
		
		ResultSet rs = estrutura.getGeneratedKeys();
		rs.next();
		ser.setCodigo(Integer.parseInt(rs.getString(1)));
		new ServicoDAO().vincularTipoOrdem(ser, c);
		new ServicoDAO().vincularTipoVeiculo(ser, c);
		rs.close();
		estrutura.close();
	}
	
	public void vincularTipoOrdem(Servico ser, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_SERVICOS_TIPO_ORDEM(cd_servico, cd_tipo_ordem) VALUES(?,?)";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		for(TipoOrdemServico tp: ser.getTipoOrdemServico()){
			estrutura.setInt(1, ser.getCodigo());
			estrutura.setInt(2, tp.getCodigo());
			estrutura.execute();
		}
		estrutura.close();
	}
	
	public void vincularTipoVeiculo(Servico ser, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_SERVICOS_TIPO_VEICULO(cd_servico, cd_tipo_veiculo) VALUES(?,?)";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		for(TipoVeiculo tv: ser.getTipoVeiculo()){
			estrutura.setInt(1, ser.getCodigo());
			estrutura.setInt(2, tv.getCodigo());
			estrutura.execute();
		}
		estrutura.close();
	}
	
	public void editar(Servico ser, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_SERVICOS SET vl_taxa = ?, vl_hono = ?, vl_total = ? WHERE cd_servico = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setDouble(1, ser.getVlrTaxa());
		estrutura.setDouble(2, ser.getVlrHono());
		estrutura.setDouble(3, ser.getVlrTotal());
		estrutura.setInt(4, ser.getCodigo());
		estrutura.execute();
		estrutura.close();
	}
	
	public void deletar(int codigo, Connection c)throws Exception{
		String sql = "DELETE FROM T_DESP_SERVICOS WHERE cd_servico = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		estrutura.execute();
		estrutura.close();
	}
	
	public Servico pesqCodigo(int codigo, Connection c)throws Exception{
		Servico ser = new Servico();
		String sql = "SELECT * FROM T_DESP_SERVICOS WHERE cd_servico = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			ser.setCodigo(rs.getInt("cd_servico"));
			ser.setDescricao(rs.getString("ds_servico"));
			ser.setVlrHono(rs.getDouble("vl_hono"));
			ser.setVlrTaxa(rs.getDouble("vl_taxa"));
			ser.setVlrTotal(rs.getDouble("vl_total"));
		}
		rs.close();
		estrutura.close();
		return ser;
	}
	
	public Servico pesqDescricao(String descricao, Connection c)throws Exception{
		Servico ser = new Servico();
		String sql = "SELECT * FROM T_DESP_SERVICOS WHERE ds_servico = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, descricao);
		
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			ser.setCodigo(rs.getInt("cd_servico"));
			ser.setDescricao(rs.getString("ds_servico"));
			ser.setVlrHono(rs.getDouble("vl_hono"));
			ser.setVlrTaxa(rs.getDouble("vl_taxa"));
			ser.setVlrTotal(rs.getDouble("vl_total"));
		}
		rs.close();
		estrutura.close();
		return ser;
	}
	
	public List<Servico> listar(Connection c)throws Exception{
		List<Servico> servicos = new ArrayList<Servico>();
		Servico ser = null;
		String sql = "SELECT * FROM T_DESP_SERVICOS";
		
		PreparedStatement estrutura = c.prepareStatement(sql);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			ser = new Servico();
			ser.setCodigo(rs.getInt("cd_servico"));
			ser.setDescricao(rs.getString("ds_servico"));
			ser.setVlrHono(rs.getDouble("vl_hono"));
			ser.setVlrTaxa(rs.getDouble("vl_taxa"));
			ser.setVlrTotal(rs.getDouble("vl_total"));
			servicos.add(ser);
		}
		rs.close();
		estrutura.close();
		return servicos;
	}


}
