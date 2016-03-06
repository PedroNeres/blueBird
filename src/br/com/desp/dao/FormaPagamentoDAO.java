package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.FormaPagamento;

public class FormaPagamentoDAO {
	
	public void cadastrar(FormaPagamento fp, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_FORMA_PAGAMENTO(ds_forma_pagamento) VALUES(?)";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setString(1, fp.getDescricao());
		
		estrutura.execute();
		estrutura.close();
	}
	
	public void deletar(FormaPagamento fp, Connection c)throws Exception{
		String sql = "DELET FROM T_DESP_FORMA_PAGAMENTO WHERE cd_forma_pagamento = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, fp.getCodigo());
		
		estrutura.execute();
		estrutura.close();
	}
	
	public void editar(FormaPagamento fp, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_FORMA_PAGAMENTO (ds_forma_pagamento = ?) WHERE cd_forma_pagamento = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, fp.getDescricao());
		estrutura.setInt(2, fp.getCodigo());
		
		estrutura.execute();
		estrutura.close();
	}
	
	public List<FormaPagamento> listar(Connection c)throws Exception{
		List<FormaPagamento> formPagamentos = new ArrayList<FormaPagamento>();
		FormaPagamento fp = null;
		
		String sql = "SELECT * FROM T_DESP_FORMA_PAGAMENTO ORDER BY cd_forma_pagamento";
		
		PreparedStatement estrutura = c.prepareStatement(sql);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			fp = new FormaPagamento();
			fp.setCodigo(rs.getInt("cd_forma_pagamento"));
			fp.setDescricao(rs.getString("ds_forma_pagamento"));
			formPagamentos.add(fp);
		}
		rs.close();
		estrutura.close();
		return formPagamentos;
	}
	
	public FormaPagamento pesCodigo(int codigo, Connection c)throws Exception{
		FormaPagamento fp = new FormaPagamento();
		
		String sql = "SELECT * FROM T_DESP_FORMA_PAGAMENTO WHERE cd_forma_pagamento = ?";
		
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			fp.setCodigo(rs.getInt("cd_forma_pagamento"));
			fp.setDescricao(rs.getString("ds_forma_pagamento"));
		}
		rs.close();
		estrutura.close();
		return fp;
	}

}
