package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.Pagamento;
import br.com.desp.bo.FormaPagamentoBO;
import br.com.desp.bo.OrdemServicoBO;

public class PagamentoDAO {
	
	public void cadastrar(Pagamento pag, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_PAGAMENTO (cd_forma_pagamento, nr_ordem, nr_identificacao, ds_observacao, vl_pago, nr_status) VALUES(?,?,?,?,?,?)";
		PreparedStatement estrutura = c.prepareStatement(sql);
		pag.setStatus(0);
		estrutura.setInt(1, pag.getForPagamento().getCodigo());
		estrutura.setInt(2, pag.getOrdemServico().getNumero());
		estrutura.setString(3, pag.getNumero());
		estrutura.setString(4, pag.getObservacoes());
		estrutura.setDouble(5, pag.getVlPagao());
		estrutura.setInt(6, pag.getStatus());
		estrutura.execute();
		estrutura.close();
	}
	
	public void editar(Pagamento pag, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_PAGAMENTO SET(cd_forma_pagamento = ?, nr_ordem =  ?, nr_identificacao = ?"
				+ "ds_observacao = ?, vl_pago = ?, nr_status = ?) WHERE cd_pagamento = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, pag.getForPagamento().getCodigo());
		estrutura.setInt(2, pag.getOrdemServico().getNumero());
		estrutura.setString(3, pag.getNumero());
		estrutura.setString(4, pag.getObservacoes());
		estrutura.setDouble(5, pag.getVlPagao());
		estrutura.setInt(6, pag.getStatus());
		estrutura.setInt(7, pag.getCodigo());
		estrutura.execute();
		estrutura.close();
	}
	
	public void aprovar(int cdPagamento, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_PAGAMENTO SET nr_status = 1 WHERE cd_pagamento = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdPagamento);
		estrutura.execute();
		estrutura.close();
	}
	
	public void aprovarTodos(Connection c)throws Exception{
		String sql = "UPDATE T_DESP_PAGAMENTO SET nr_status = 1";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.execute();
		estrutura.close();
	}
	
	public void deletar(int cdPagamento, Connection c)throws Exception{
		String sql = "DELETE FORM T_DESP_PAGAMENTO WHERE cd_pagamento = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdPagamento);
		estrutura.execute();
		estrutura.close();
	}
	
	public List<Pagamento> listarPagAber(Connection c)throws Exception{
		List<Pagamento> pagamentos = new ArrayList<Pagamento>();
		Pagamento pag = null;
		String sql = "SELECT * FROM T_DESP_PAGAMENTO WHERE nr_status = 0";
		PreparedStatement estrutura = c.prepareStatement(sql);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			pag = new Pagamento();
			pag.setCodigo(rs.getInt("cd_pagamento"));
			pag.setForPagamento(FormaPagamentoBO.pesqCodigo(rs.getInt("cd_forma_pagamento"), c));
			pag.setNumero(rs.getString("nr_identificacao"));
			pag.setObservacoes(rs.getString("ds_observacao"));
			System.out.println("teste");
			pag.setOrdemServico(OrdemServicoBO.pesqNumeroOs(rs.getInt("nr_ordem"), c));
			System.out.println("teste1");
			pag.setStatus(rs.getInt("nr_status"));
			pag.setVlPagao(rs.getDouble("vl_pago"));
			pagamentos.add(pag);
		}
		rs.close();
		estrutura.close();
		return pagamentos;
	}
	
	public List<Pagamento> listarPagOs(int nrOrdem, Connection c)throws Exception{
		List<Pagamento> pagamentos = new ArrayList<Pagamento>();
		Pagamento pag = null;
		String sql = "SELECT * FROM T_DESP_PAGAMENTO WHERE nr_ordem = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, nrOrdem);
		
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			pag = new Pagamento();
			pag.setCodigo(rs.getInt("cd_pagamento"));
			pag.setForPagamento(FormaPagamentoBO.pesqCodigo(rs.getInt("cd_forma_pagamento"), c));
			pag.setNumero(rs.getString("nr_identificacao"));
			pag.setObservacoes(rs.getString("ds_observacao"));
			pag.setStatus(rs.getInt("nr_status"));
			pag.setVlPagao(rs.getDouble("vl_pago"));
			pagamentos.add(pag);
		}
		rs.close();
		estrutura.close();
		return pagamentos;
	}
	
	public Pagamento pesqCodigo(int cdPagamento, Connection c)throws Exception{
		Pagamento pag = new Pagamento();
		String sql = "SELECT * FROM T_DESP_PAGAMENTO WHERE cd_pagamento = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdPagamento);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			pag.setCodigo(rs.getInt("cd_pagamento"));
			pag.setForPagamento(FormaPagamentoBO.pesqCodigo(rs.getInt("cd_forma_pagamento"), c));
			pag.setNumero(rs.getString("nr_identificacao"));
			pag.setObservacoes(rs.getString("ds_observacao"));
			pag.setOrdemServico(OrdemServicoBO.pesqNumeroOs(rs.getInt("nr_ordem"), c));
			pag.setStatus(rs.getInt("nr_status"));
			pag.setVlPagao(rs.getDouble("vl_pago"));
		}
		rs.close();
		estrutura.close();
		return pag;
	}

}
