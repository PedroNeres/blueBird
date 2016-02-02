package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.ItemServico;
import br.com.desp.bo.OrdemServicoBO;
import br.com.desp.bo.ServicoBO;

public class ItemServicoDAO {
	
	public void cadastrar(ItemServico is, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_ITEM_SERVICO (cd_servico, qt_item_servico, vl_total, nr_ordem) VALUES(?,?,?,?)";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, is.getServico().getCodigo());
		estrutura.setInt(2, is.getQuantidade());
		estrutura.setDouble(3, is.getVlrTotal());
		estrutura.setInt(4, is.getOs().getNumero());
		estrutura.execute();
		estrutura.close();
	}
	
	public void deletar(ItemServico is, Connection c)throws Exception{
		String sql = "DELETE FROM T_DESP_ITEM_SERVICO WHERE cd_item_servico = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, is.getCodigo());
		estrutura.execute();
		estrutura.close();
	}
	
	public void editar(ItemServico is, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_ITEM_SERVICO (cd_servico = ?, qt_item_servico = ?, vl_total = ?, nr_ordem = ?)"
				+ " WHERE cd_item_Servico = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, is.getServico().getCodigo());
		estrutura.setInt(2, is.getQuantidade());
		estrutura.setDouble(3, is.getVlrTotal());
		estrutura.setInt(4, is.getOs().getNumero());
		estrutura.setInt(5, is.getCodigo());
		
		estrutura.execute();
		estrutura.close();
	}
	
	public List<ItemServico> listarServOs(int nrOrdem, Connection c)throws Exception{
		List<ItemServico> itens = new ArrayList<ItemServico>();
		ItemServico item = null;
		
		String sql = "SELECT * FROM T_DESP_ITEM_SERVICO WHERE nr_ordem = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, nrOrdem);
		
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			item = new ItemServico();
			item.setCodigo(rs.getInt("cd_item_servico"));
			
			item.setQuantidade(rs.getInt("qt_item_servico"));
			item.setServico(ServicoBO.pesqCodigo(rs.getInt("cd_servico"), c));
			item.setVlrTotal(rs.getDouble("vl_total"));
			itens.add(item);
		}
		rs.close();
		estrutura.close();
		return itens;
	}
	
	public ItemServico pesqCodigo(int codigo, Connection c)throws Exception{
		ItemServico item = new ItemServico();
		
		String sql = "SELECT * FROM T_DESP_ITEM_SERVICO WHERE cd_item_servico = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			item = new ItemServico();
			item.setCodigo(rs.getInt("cd_item_servico"));
			item.setOs(OrdemServicoBO.pesqNumeroOs(rs.getInt("nr_ordem"), c));
			item.setQuantidade(rs.getInt("qt_item_servico"));
			item.setServico(ServicoBO.pesqCodigo(rs.getInt("cd_servico"), c));
			item.setVlrTotal(rs.getDouble("vl_total"));
		}
		rs.close();
		estrutura.close();
		return item;
	}

}
