package br.com.desp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.desp.beans.Contas;
import br.com.desp.bo.FilialBO;


public class ContasDAO {
	
	public void cadastrar(Contas cont, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_CONTAS (cd_filial, ds_conta, dia_vencimento,"
				+ "ds_observacao, qt_parcelas, nr_status, vl_conta, dt_prox_venc) "
				+ "VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, cont.getFilial().getCodigo());
		estrutura.setString(2, cont.getDescricao());
		estrutura.setInt(3, cont.getDiaVencimento());
		estrutura.setString(4, cont.getObservacoes());
		estrutura.setInt(5, cont.getQtParcelas());
		estrutura.setInt(6, cont.getStatus());
		estrutura.setDouble(7, cont.getValor());
		estrutura.setDate(8, new Date(cont.getProxVenc().getTimeInMillis()));
		
		estrutura.execute();
		estrutura.close();			
	}
	
	public void pagar(Contas cont, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_CONTAS SET qt_parcelas = ?, nr_status = ?, dt_prox_venc = ? WHERE cd_conta = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, cont.getQtParcelas());
		estrutura.setInt(2, cont.getStatus());
		estrutura.setDate(3, new Date(cont.getProxVenc().getTimeInMillis()));
		estrutura.setInt(4, cont.getCodigo());
		
		estrutura.execute();
		estrutura.close();
	}
	
	public void deletar(int codigo, Connection c)throws Exception{
		String sql = "DELETE FROM T_DESP_CONTAS WHERE cd_conta = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, codigo);
		estrutura.execute();
		estrutura.close();
	}
	
	public void alterarValor(Contas cont, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_CONTAS SET vl_conta = ? WHERE cd_conta = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setDouble(1, cont.getValor());
		estrutura.setInt(2, cont.getCodigo());
		
		estrutura.execute();
		estrutura.close();
	}
	
	public void alterarDiaVenc(Contas cont, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_CONTAS SET dia_vencimento = ?, dt_prox_venc = ? WHERE cd_conta = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, cont.getDiaVencimento());
		estrutura.setDate(2, new Date(cont.getProxVenc().getTimeInMillis()));
		estrutura.setInt(2, cont.getCodigo());
		
		estrutura.execute();
		estrutura.close();
	}
	
	public void vencendo(Contas cont, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_CONTAS SET nr_status = ? WHERE cd_conta = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, cont.getStatus());
		estrutura.setInt(2, cont.getCodigo());
		
		estrutura.execute();
		estrutura.close();
	}
	
	public List<Contas> listar(int cdFilial, Connection c)throws Exception{
		List<Contas> contas = new ArrayList<Contas>();
		Contas cont = null;
		
		String sql = "SELECT * FROM T_DESP_CONTAS WHERE cd_filial = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		ResultSet rs = estrutura.executeQuery();
		
		while(rs.next()){
			cont = new Contas();
			cont.setCodigo(rs.getInt("cd_conta"));
			cont.setDescricao(rs.getString("ds_conta"));
			cont.setDiaVencimento(rs.getInt("dia_vencimento"));
			cont.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			cont.setObservacoes(rs.getString("ds_observacao"));
			cont.setQtParcelas(rs.getInt("qt_parcelas"));
			cont.setStatus(rs.getInt("nr_status"));
			cont.setValor(rs.getDouble("vl_conta"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_prox_venc"));
			cont.setProxVenc(cal);
			contas.add(cont);
		}
		rs.close();
		estrutura.close();
		return contas;
	}
	
	public Contas pesqCodigo(int codigo, int cdFilial, Connection c)throws Exception{
		Contas cont = new Contas();
		
		String sql = "SELECT * FROM T_DESP_CONTAS WHERE cd_conta = ? AND cd_filial = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		estrutura.setInt(2, cdFilial);
		ResultSet rs = estrutura.executeQuery();
		
		if(rs.next()){
			cont.setCodigo(rs.getInt("cd_conta"));
			cont.setDescricao(rs.getString("ds_conta"));
			cont.setDiaVencimento(rs.getInt("dia_vencimento"));
			cont.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			cont.setObservacoes(rs.getString("ds_observacao"));
			cont.setQtParcelas(rs.getInt("qt_parcelas"));
			cont.setStatus(rs.getInt("nr_status"));
			cont.setValor(rs.getDouble("vl_conta"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_prox_venc"));
			cont.setProxVenc(cal);
		}
		rs.close();
		estrutura.close();
		return cont;
	}
	
	public Contas pesqDescricao(String descricao, int cdFilial, Connection c)throws Exception{
		Contas cont = new Contas();
		
		String sql = "SELECT * FROM T_DESP_CONTAS WHERE ds_conta = ? AND cd_filial = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, descricao);
		estrutura.setInt(2, cdFilial);
		ResultSet rs = estrutura.executeQuery();
		
		if(rs.next()){
			cont.setCodigo(rs.getInt("cd_conta"));
			cont.setDescricao(rs.getString("ds_conta"));
			cont.setDiaVencimento(rs.getInt("dia_vencimento"));
			cont.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			cont.setObservacoes(rs.getString("ds_observacao"));
			cont.setQtParcelas(rs.getInt("qt_parcelas"));
			cont.setStatus(rs.getInt("nr_status"));
			cont.setValor(rs.getDouble("vl_conta"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_prox_venc"));
			cont.setProxVenc(cal);
		}
		rs.close();
		estrutura.close();
		return cont;
	}
	
	public List<Contas> pesqStatus(int nrStatus, int cdFilial, Connection c)throws Exception{
		List<Contas> contas = new ArrayList<Contas>();
		Contas cont = null;
		
		String sql = "SELECT * FROM T_DESP_CONTAS WHERE nr_status = ? AND cd_filial = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, nrStatus);
		estrutura.setInt(2, cdFilial);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			cont = new Contas();
			cont.setCodigo(rs.getInt("cd_conta"));
			cont.setDescricao(rs.getString("ds_conta"));
			cont.setDiaVencimento(rs.getInt("dia_vencimento"));
			cont.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			cont.setObservacoes(rs.getString("ds_observacao"));
			cont.setQtParcelas(rs.getInt("qt_parcelas"));
			cont.setStatus(rs.getInt("nr_status"));
			cont.setValor(rs.getDouble("vl_conta"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_prox_venc"));
			cont.setProxVenc(cal);
			contas.add(cont);
		}
		return contas;
	}

}
