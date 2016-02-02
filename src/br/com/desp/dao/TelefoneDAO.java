package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.Telefone;

public class TelefoneDAO {
	
	
	
	public List<Telefone> telefones(int cdPessoa, Connection c)throws Exception{
		
		List<Telefone> tels = new ArrayList<Telefone>();
		Telefone tel = null;
		String sql = "SELECT * FROM T_DESP_TELEFONE TEL INNER JOIN T_DESP_FONE_PESSOA FPES "
				+ "ON(TEL.CD_FONE = FPES.CD_FONE) WHERE FPES.CD_PESSOA = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdPessoa);
		ResultSet resultado = estrutura.executeQuery();
		
		while(resultado.next()){
			tel = new Telefone();
			tel.setDdd(resultado.getInt("nr_ddd"));
			tel.setNumero(resultado.getInt("nr_fone"));
			tel.setCodigo(resultado.getInt("cd_fone"));
			tels.add(tel);
		}
		resultado.close();
		estrutura.close();
		return tels;
	}
	
	public void cadastrar(List<Telefone> t, Connection conexao, int indice) throws Exception {
			
			String sql = "INSERT INTO T_DESP_TELEFONE (nr_ddd, nr_fone)"+
					"VALUES (?, ?)";
			
			PreparedStatement estruturaTel = conexao.prepareStatement(sql, new String[]{"CD_FONE"});
			
			String sql2 = "INSERT INTO T_DESP_FONE_PESSOA (CD_PESSOA, CD_FONE, NR_RAMAL)"
					+ "VALUES(?, ?, ?)";
			
			PreparedStatement estruturaTel2 = conexao.prepareStatement(sql2);
			
			for(Telefone tel: t){
				
				estruturaTel.setInt(1, tel.getDdd());
				estruturaTel.setInt(2, tel.getNumero());
				estruturaTel.execute();
				
				//PEGANDO OS CÓDIGOS CHAVE PRIMÁRIA
				ResultSet rs = estruturaTel.getGeneratedKeys();
				
				//PASSANDO PARA PRÓXIMA LINHA, AONDE ESTA A CHAVE
				rs.next();
				
				//ADICIONANDO A CHAVE NA VARIÁVEL INDICE
				int indice2 = Integer.parseInt(rs.getString(1));
				
				estruturaTel2.setInt(1, indice);
				estruturaTel2.setInt(2, indice2);
				estruturaTel2.setInt(3, 0);
				
				estruturaTel2.execute();
				
			}
			estruturaTel.close();
			estruturaTel2.close();
		}
	
	
	public void atualizar(List<Telefone> t, Connection conexao) throws Exception {
		
		String sql = "UPDATE T_DESP_TELEFONE SET (nr_ddd = ?, nr_fone = ?)"+
				"WHERE cd_fone = ?";
		
		PreparedStatement estruturaTel = conexao.prepareStatement(sql);
		
		for(Telefone tel: t){
			estruturaTel.setInt(1, tel.getDdd());
			estruturaTel.setInt(2, tel.getNumero());
			estruturaTel.setInt(3, tel.getCodigo());
			estruturaTel.execute();
		}
		estruturaTel.close();
	}
	
	public List<Integer> recuperarTipos(Connection conexao)throws Exception{
		
		List<Integer> tipos = new ArrayList<Integer>();
		
		String sql = "SELECT CD_TIPO_FONE FROM T_DESP_TIPO_FONE";
		PreparedStatement estrutura = conexao.prepareStatement(sql);
		ResultSet resultado = estrutura.executeQuery();
		
		while(resultado.next()){
			tipos.add(resultado.getInt("cd_tipo_fone"));
		}
		estrutura.close();
		resultado.close();
		return tipos;
		
	}

}
