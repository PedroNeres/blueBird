package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PessoaFisicaDAO {
	
	public int pesqCpf(long cpf, Connection c)throws Exception{
		int ver = 0;
		String sql = "SELECT * FROM T_DESP_FUNCIONARIO WHERE nr_cpf = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setLong(1, cpf);
		estrutura.execute();
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			ver++;
		}
		rs.close();
		estrutura.close();
		return ver;
		
	}

}
