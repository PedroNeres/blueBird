package br.com.desp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.desp.beans.Saida;
import br.com.desp.bo.FilialBO;

public class SaidaDAO {
	
	public void cadastrar(Saida sai, Connection c)throws Exception{
		
		String sql = "INSERT INTO T_DESP_SAIDA(ds_saida, vl_saida, dt_saida, cd_filial) VALUES(?,?,?,?)";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, sai.getDescricao());
		estrutura.setDouble(2, sai.getValor());
		estrutura.setDate(3, new Date(sai.getDtSaida().getTimeInMillis()));
		estrutura.setInt(4, sai.getFilial().getCodigo());
		
		estrutura.execute();
		estrutura.close();
	}
	
	public void editar(Saida sai, Connection c)throws Exception{
		
		String sql = "UPDATE T_DESP_SAIDA SET ds_saida = ?, vl_saida = ? WHERE cd_saida = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, sai.getDescricao());
		estrutura.setDouble(2, sai.getValor());
		estrutura.setInt(3, sai.getCodigo());
		
		estrutura.execute();
		estrutura.close();
	}
	
	public void deletar(int codigo, Connection c)throws Exception{
		String sql = "DELETE FROM T_DESP_SAIDA WHERE cd_saida = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		estrutura.execute();
		estrutura.close();
	}
	
	public List<Saida> listar(int cdFilial, Connection c)throws Exception{
		List<Saida> saidas = new ArrayList<Saida>();
		Saida sai = null;
		
		String sql = "SELECT * FROM T_DESP_SAIDA WHERE cd_filial = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		ResultSet rs = estrutura.executeQuery();
		
		while(rs.next()){
			sai = new Saida();
			sai.setCodigo(rs.getInt("cd_saida"));
			sai.setDescricao(rs.getString("ds_saida"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_saida"));
			sai.setDtSaida(cal);
			sai.setValor(rs.getDouble("vl_saida"));
			sai.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			saidas.add(sai);
		}
		rs.close();
		estrutura.close();
		return saidas;
	}
	
	public List<Saida> pesqData(int cdFilial, Calendar data1, Calendar data2, Connection c)throws Exception{
		List<Saida> saidas = new ArrayList<Saida>();
		Saida sai = null;
		
		String sql = "SELECT * FROM T_DESP_SAIDA WHERE cd_filial = ? AND dt_saida BETWEEN ? AND ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		estrutura.setDate(2, new Date(data1.getTimeInMillis()));
		estrutura.setDate(3, new Date(data2.getTimeInMillis()));
		ResultSet rs = estrutura.executeQuery();
		
		while(rs.next()){
			sai = new Saida();
			sai.setCodigo(rs.getInt("cd_saida"));
			sai.setDescricao(rs.getString("ds_saida"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_saida"));
			sai.setDtSaida(cal);
			sai.setValor(rs.getDouble("vl_saida"));
			sai.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			saidas.add(sai);
		}
		rs.close();
		estrutura.close();
		return saidas;
	}
	
	public Saida pesqCodigo(int codigo, Connection c)throws Exception{
		Saida sai = new Saida();
		String sql = "SELECT * FROM T_DESP_SAIDA WHERE cd_saida = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		ResultSet rs = estrutura.executeQuery();
		
		if(rs.next()){
			sai.setCodigo(rs.getInt("cd_saida"));
			sai.setDescricao(rs.getString("ds_saida"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_saida"));
			sai.setDtSaida(cal);
			sai.setValor(rs.getDouble("vl_saida"));
			sai.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
		}
		rs.close();
		estrutura.close();
		return sai;
	}

}
