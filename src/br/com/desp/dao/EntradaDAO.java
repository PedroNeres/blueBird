package br.com.desp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.desp.beans.Entrada;
import br.com.desp.bo.FilialBO;

public class EntradaDAO {
	
	public void cadastrar(Entrada ent, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_ENTRADA(ds_entrada, vl_entrada, dt_entrada, cd_filial) VALUES(?,?,?,?)";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, ent.getDescricao());
		estrutura.setDouble(2, ent.getValor());
		estrutura.setDate(3, new Date(ent.getDtEntrada().getTimeInMillis()));
		estrutura.setInt(4, ent.getFilial().getCodigo());
		
		estrutura.execute();
		estrutura.close();
	}
	
	public void editar(Entrada ent, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_ENTRADA SET ds_entrada = ?, vl_entrada = ? WHERE cd_entrada = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setString(1, ent.getDescricao());
		estrutura.setDouble(2, ent.getValor());
		estrutura.setInt(3, ent.getCodigo());
		estrutura.execute();
		estrutura.close();
	}
	
	public void deletar(int codigo, Connection c)throws Exception{
		String sql = "DELETE FROM T_DESP_ENTRADA WHERE cd_entrada = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		estrutura.execute();
		estrutura.close();
	}
	
	public List<Entrada> listar(int cdFilial, Connection c)throws Exception{
		List<Entrada> entradas = new ArrayList<Entrada>();
		Entrada ent = null;
		
		String sql = "SELECT * FROM T_DESP_ENTRADA WHERE cd_filial = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		
		ResultSet rs = estrutura.executeQuery();
		
		while(rs.next()){
			ent = new Entrada();
			ent.setCodigo(rs.getInt("cd_entrada"));
			ent.setDescricao(rs.getString("ds_entrada"));
			ent.setValor(rs.getDouble("vl_entrada"));
			ent.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_entrada"));
			ent.setDtEntrada(cal);
			entradas.add(ent);
		}
		rs.close();
		estrutura.close();
		return entradas;
	}
	
	public List<Entrada> pesqData(int cdFilial, Calendar data1, Calendar data2, Connection c)throws Exception{
		List<Entrada> entradas = new ArrayList<Entrada>();
		Entrada ent = null;
		
		String sql = "SELECT * FROM T_DESP_ENTRADA WHERE cd_filial = ? AND dt_entrada BETWEEN ? AND ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		estrutura.setDate(2, new Date(data1.getTimeInMillis()));
		estrutura.setDate(3, new Date(data2.getTimeInMillis()));
		
		ResultSet rs = estrutura.executeQuery();
		
		while(rs.next()){
			ent = new Entrada();
			ent.setCodigo(rs.getInt("cd_entrada"));
			ent.setDescricao(rs.getString("ds_entrada"));
			ent.setValor(rs.getDouble("vl_entrada"));
			ent.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_entrada"));
			ent.setDtEntrada(cal);
			entradas.add(ent);
		}
		rs.close();
		estrutura.close();
		return entradas;
	}
	
	public Entrada pesqCodigo(int codigo, Connection c)throws Exception{
		Entrada ent = new Entrada();
		
		String sql = "SELECT * FROM T_DESP_ENTRADA WHERE cd_entrada = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		
		ResultSet rs = estrutura.executeQuery();
		
		while(rs.next()){
			ent.setCodigo(rs.getInt("cd_entrada"));
			ent.setDescricao(rs.getString("ds_entrada"));
			ent.setValor(rs.getDouble("vl_entrada"));
			ent.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_entrada"));
			ent.setDtEntrada(cal);
		}
		rs.close();
		estrutura.close();
		return ent;
	}

}
