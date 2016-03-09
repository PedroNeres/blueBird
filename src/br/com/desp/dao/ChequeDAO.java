package br.com.desp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.desp.beans.Cheque;

public class ChequeDAO {
	
	public void cadastrar(Cheque che, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_CHEQUE (nr_cheque, nm_emitente, dt_recebimento, dt_deposito, nr_status, vl_cheque, cd_filial)"
				+ "VALUES (?,?,?,?,?,?,?)";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, che.getNumero());
		estrutura.setString(2, che.getEmitente());
		estrutura.setDate(3, new Date(che.getDtRecebimento().getTimeInMillis()));
		estrutura.setDate(4, new Date(che.getDtDeposito().getTimeInMillis()));
		estrutura.setInt(5, che.getStatus());
		estrutura.setDouble(6, che.getValor());
		estrutura.setInt(7, che.getCdFilial());
		
		estrutura.execute();
		estrutura.close();
	}
	
	public void alterar(Cheque che, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_CHEQUE SET nm_emitente = ?, dt_deposito = ?, vl_cheque = ? WHERE cd_cheque = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, che.getEmitente());
		estrutura.setDate(2, new Date(che.getDtDeposito().getTimeInMillis()));
		estrutura.setDouble(3, che.getValor());
		estrutura.setInt(4, che.getCodigo());
		
		estrutura.execute();
		estrutura.close();
	}
	
	public void depositar(int codigo, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_CHEQUE SET nr_status = 1 WHERE cd_cheque = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, codigo);
		
		estrutura.execute();
		estrutura.close();
	}
	
	public void reabrir(int codigo, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_CHEQUE SET nr_status = 0 WHERE cd_cheque = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, codigo);
		
		estrutura.execute();
		estrutura.close();
	}
	
	public List<Cheque> listarPendentes(int cdFilial, Connection c)throws Exception{
		List<Cheque> cheques = new ArrayList<Cheque>();
		Cheque che = null;
		String sql = "SELECT * FROM T_DESP_CHEQUE WHERE nr_status = 0 AND cd_filial = ? ORDER BY dt_deposito";
		
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		ResultSet rs = estrutura.executeQuery();
		
		while(rs.next()){
			che = new Cheque();
			che.setCodigo(rs.getInt("cd_cheque"));
			che.setNumero(rs.getInt("nr_cheque"));
			che.setEmitente(rs.getString("nm_emitente"));
			che.setCdFilial(rs.getInt("cd_filial"));
			Calendar dtRecebimento = Calendar.getInstance();
			dtRecebimento.setTime(rs.getDate("dt_recebimento"));
			che.setDtRecebimento(dtRecebimento);
			
			Calendar dtDeposito = Calendar.getInstance();
			dtDeposito.setTime(rs.getDate("dt_deposito"));
			che.setDtDeposito(dtDeposito);
			
			che.setStatus(rs.getInt("nr_status"));
			che.setValor(rs.getDouble("vl_cheque"));
			cheques.add(che);
		}
		rs.close();
		estrutura.close();
		return cheques;
	}
	
	public List<Cheque> listarDepositados(int cdFilial, Connection c)throws Exception{
		List<Cheque> cheques = new ArrayList<Cheque>();
		Cheque che = null;
		String sql = "SELECT * FROM T_DESP_CHEQUE WHERE nr_status = 1 AND cd_filial = ? ORDER BY dt_deposito";
		
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		ResultSet rs = estrutura.executeQuery();
		
		while(rs.next()){
			che = new Cheque();
			che.setCodigo(rs.getInt("cd_cheque"));
			che.setCdFilial(rs.getInt("cd_filial"));
			che.setNumero(rs.getInt("nr_cheque"));
			che.setEmitente(rs.getString("nm_emitente"));
			
			Calendar dtRecebimento = Calendar.getInstance();
			dtRecebimento.setTime(rs.getDate("dt_recebimento"));
			che.setDtRecebimento(dtRecebimento);
			
			Calendar dtDeposito = Calendar.getInstance();
			dtDeposito.setTime(rs.getDate("dt_deposito"));
			che.setDtDeposito(dtDeposito);
			
			che.setStatus(rs.getInt("nr_status"));
			che.setValor(rs.getDouble("vl_cheque"));
			cheques.add(che);
		}
		rs.close();
		estrutura.close();
		return cheques;
	}
	
	public List<Cheque> listar(int cdFilial, Connection c)throws Exception{
		List<Cheque> cheques = new ArrayList<Cheque>();
		Cheque che = null;
		String sql = "SELECT * FROM T_DESP_CHEQUE WHERE cd_filial = ? ORDER BY dt_deposito";
		
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		ResultSet rs = estrutura.executeQuery();
		
		while(rs.next()){
			che = new Cheque();
			che.setCodigo(rs.getInt("cd_cheque"));
			che.setNumero(rs.getInt("nr_cheque"));
			che.setCdFilial(rs.getInt("cd_filial"));
			che.setEmitente(rs.getString("nm_emitente"));
			
			Calendar dtRecebimento = Calendar.getInstance();
			dtRecebimento.setTime(rs.getDate("dt_recebimento"));
			che.setDtRecebimento(dtRecebimento);
			
			Calendar dtDeposito = Calendar.getInstance();
			dtDeposito.setTime(rs.getDate("dt_deposito"));
			che.setDtDeposito(dtDeposito);
			
			che.setStatus(rs.getInt("nr_status"));
			che.setValor(rs.getDouble("vl_cheque"));
			cheques.add(che);
		}
		rs.close();
		estrutura.close();
		return cheques;
	}
	
	public Cheque pesqEmitente(String emitente, int cdFilial, Connection c)throws Exception{
		Cheque che = new Cheque();
		
		String sql = "SELECT * FROM T_DESP_CHEQUE WHERE nm_emitente = ? AND cd_filial = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		
		estrutura.setString(1, emitente);
		estrutura.setInt(2, cdFilial);
		ResultSet rs = estrutura.executeQuery();
		
		if(rs.next()){
			che = new Cheque();
			che.setCodigo(rs.getInt("cd_cheque"));
			che.setNumero(rs.getInt("nr_cheque"));
			che.setCdFilial(rs.getInt("cd_filial"));
			che.setEmitente(rs.getString("nm_emitente"));
			
			Calendar dtRecebimento = Calendar.getInstance();
			dtRecebimento.setTime(rs.getDate("dt_recebimento"));
			che.setDtRecebimento(dtRecebimento);
			
			Calendar dtDeposito = Calendar.getInstance();
			dtDeposito.setTime(rs.getDate("dt_deposito"));
			che.setDtDeposito(dtDeposito);
			
			che.setStatus(rs.getInt("nr_status"));
			che.setValor(rs.getDouble("vl_cheque"));
		}
		
		rs.close();
		estrutura.close();
		return che;
	}
	
	public Cheque pesqCodigo(int codigo, int cdFilial, Connection c)throws Exception{
		Cheque che = new Cheque();
		
		String sql = "SELECT * FROM T_DESP_CHEQUE WHERE cd_cheque = ? AND cd_filial = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, codigo);
		estrutura.setInt(2, cdFilial);
		ResultSet rs = estrutura.executeQuery();
		
		if(rs.next()){
			che = new Cheque();
			che.setCodigo(rs.getInt("cd_cheque"));
			che.setNumero(rs.getInt("nr_cheque"));
			che.setEmitente(rs.getString("nm_emitente"));
			
			Calendar dtRecebimento = Calendar.getInstance();
			dtRecebimento.setTime(rs.getDate("dt_recebimento"));
			che.setDtRecebimento(dtRecebimento);
			che.setCdFilial(rs.getInt("cd_filial"));
			Calendar dtDeposito = Calendar.getInstance();
			dtDeposito.setTime(rs.getDate("dt_deposito"));
			che.setDtDeposito(dtDeposito);
			
			che.setStatus(rs.getInt("nr_status"));
			che.setValor(rs.getDouble("vl_cheque"));
		}
		
		rs.close();
		estrutura.close();
		return che;
	}
	
	public Cheque pesqNumero(int numero, int cdFilial, Connection c)throws Exception{
		Cheque che = new Cheque();
		
		String sql = "SELECT * FROM T_DESP_CHEQUE WHERE nr_cheque = ? AND cd_filial = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, numero);
		estrutura.setInt(2, cdFilial);
		ResultSet rs = estrutura.executeQuery();
		
		if(rs.next()){
			che = new Cheque();
			che.setCodigo(rs.getInt("cd_cheque"));
			che.setCdFilial(rs.getInt("cd_filial"));
			che.setNumero(rs.getInt("nr_cheque"));
			che.setEmitente(rs.getString("nm_emitente"));
			
			Calendar dtRecebimento = Calendar.getInstance();
			dtRecebimento.setTime(rs.getDate("dt_recebimento"));
			che.setDtRecebimento(dtRecebimento);
			
			Calendar dtDeposito = Calendar.getInstance();
			dtDeposito.setTime(rs.getDate("dt_deposito"));
			che.setDtDeposito(dtDeposito);
			
			che.setStatus(rs.getInt("nr_status"));
			che.setValor(rs.getDouble("vl_cheque"));
		}
		
		rs.close();
		estrutura.close();
		return che;
	}
	
	public List<Cheque> pesqData(Calendar data1, Calendar data2, int cdFilial, Connection c)throws Exception{
		Cheque che = null;
		List<Cheque> cheques = new ArrayList<Cheque>();
		
		String sql = "SELECT * FROM T_DESP_CHEQUE WHERE cd_filial = ? AND dt_deposito BETWEEN ? AND ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, cdFilial);
		estrutura.setDate(2, new Date(data1.getTimeInMillis()));
		estrutura.setDate(3, new Date(data2.getTimeInMillis()));
		
		ResultSet rs = estrutura.executeQuery();
		
		while(rs.next()){
			che = new Cheque();
			che.setCodigo(rs.getInt("cd_cheque"));
			che.setNumero(rs.getInt("nr_cheque"));
			che.setEmitente(rs.getString("nm_emitente"));
			che.setCdFilial(rs.getInt("cd_filial"));
			Calendar dtRecebimento = Calendar.getInstance();
			dtRecebimento.setTime(rs.getDate("dt_recebimento"));
			che.setDtRecebimento(dtRecebimento);
			
			Calendar dtDeposito = Calendar.getInstance();
			dtDeposito.setTime(rs.getDate("dt_deposito"));
			che.setDtDeposito(dtDeposito);
			
			che.setStatus(rs.getInt("nr_status"));
			che.setValor(rs.getDouble("vl_cheque"));
			cheques.add(che);
		}
		
		rs.close();
		estrutura.close();
		return cheques;
	}

}
