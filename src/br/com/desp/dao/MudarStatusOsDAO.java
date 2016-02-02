package br.com.desp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.desp.beans.MudarStatus;
import br.com.desp.bo.FuncionarioBO;
import br.com.desp.bo.OrdemServicoBO;
import br.com.desp.bo.StatusOsBO;

public class MudarStatusOsDAO {
	
	public int mudarStatus(MudarStatus ms, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_MUDAR_STATUS_OS(cd_status_os, nr_ordem, cd_funcionario,"
				+ "dt_mudanca, ds_observacao) VALUES(?,?,?,?,?)";
		PreparedStatement estrutura = c.prepareStatement(sql, new String[]{"cd_mudar_status_os"});
		estrutura.setInt(1, ms.getStatus().getCodigo());
		estrutura.setInt(2, ms.getOrdemServico().getNumero());
		estrutura.setInt(3, ms.getFuncionario().getCodigo());
		estrutura.setDate(4, new Date(ms.getDtMudanca().getTimeInMillis()));
		estrutura.setString(5, ms.getObservacao());
		estrutura.execute();
		
		ResultSet rs = estrutura.getGeneratedKeys();
		rs.next();
		ms.setCodigo(Integer.parseInt(rs.getString(1)));
		
		rs.close();
		estrutura.close();
		return ms.getCodigo();
	}
	
	public List<MudarStatus> listarMudancas(int nrOrdem, Connection c)throws Exception{
		List<MudarStatus> mStatus = new ArrayList<MudarStatus>();
		MudarStatus ms = null;
		String sql = "SELECT * FROM T_DESP_MUDAR_STATUS_OS WHERE nr_ordem = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, nrOrdem);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			ms = new MudarStatus();
			Calendar cal = Calendar.getInstance();
			ms.setCodigo(rs.getInt("cd_mudar_status_os"));
			cal.setTime(rs.getDate("dt_mudanca"));
			ms.setDtMudanca(cal);
			ms.setFuncionario(FuncionarioBO.pesqCodigo(rs.getInt("cd_funcionario"), c));
			ms.setObservacao(rs.getString("ds_observacao"));
			ms.setOrdemServico(OrdemServicoBO.pesqNumeroOs(rs.getInt("nr_ordem"), c));
			ms.setStatus(StatusOsBO.pesqCodigo(rs.getInt("cd_status_os"), c));
			mStatus.add(ms);
		}
		rs.close();
		estrutura.close();
		return mStatus;
	}
	
	public MudarStatus pesqCodigo(int codigo, Connection c)throws Exception{
		MudarStatus ms = new MudarStatus();
		String sql = "SELECT * FROM T_DESP_MUDAR_STATUS_OS WHERE cd_mudar_status_os = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			ms = new MudarStatus();
			Calendar cal = Calendar.getInstance();
			ms.setCodigo(rs.getInt("cd_mudar_status_os"));
			cal.setTime(rs.getDate("dt_mudanca"));
			ms.setDtMudanca(cal);
			ms.setFuncionario(FuncionarioBO.pesqCodigo(rs.getInt("cd_funcionario"), c));
			ms.setObservacao(rs.getString("ds_observacao"));
			
			ms.setStatus(StatusOsBO.pesqCodigo(rs.getInt("cd_status_os"), c));
		}
		rs.close();
		estrutura.close();
		return ms;
	}
	
	

}
