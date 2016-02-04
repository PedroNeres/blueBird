package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.desp.beans.Endereco;

public class EnderecoDAO {
	
	public void cadastrar(Endereco e, Connection conexao, int indice) throws Exception {
		
			String sql = "INSERT INTO T_DESP_PESSOA_ENDE (nr_cep, cd_pessoa, nr_endereco, ds_complemento)"+
			      "VALUES (?, ?, ?, ?)";
			
			PreparedStatement estruturaEnd = conexao.prepareStatement(sql);
			
			estruturaEnd.setInt(1, e.getCep());
			estruturaEnd.setInt(2, indice);
			estruturaEnd.setInt(3, e.getNumero());
			estruturaEnd.setString(4, e.getComplemento().toUpperCase());
			
			estruturaEnd.execute();
			estruturaEnd.close();
		}
	
	public void atualizar(Endereco e, Connection conexao, int indice) throws Exception{
		String sql = "UPDATE T_DESP_PESSOA_ENDE SET nr_cep = ?, nr_endereco = ?, ds_complemento = ?"+
			      "WHERE cd_pessoa = ?";
			
			PreparedStatement estruturaEnd = conexao.prepareStatement(sql);
			
			estruturaEnd.setInt(1, e.getCep());
			estruturaEnd.setInt(2, e.getNumero());
			estruturaEnd.setString(3, e.getComplemento().toUpperCase());
			estruturaEnd.setInt(4, indice);
			
			estruturaEnd.execute();
			estruturaEnd.close();
	}
	
	public int verificarCidade(String cidade, Connection conexao) throws Exception{
		int codigo = 0;
		String sql = "SELECT * FROM T_DESP_CIDADE WHERE NM_CIDADE = ?";
		PreparedStatement estrutura = conexao.prepareStatement(sql);
		estrutura.setString(1, cidade.toUpperCase());
		
		ResultSet resultado = estrutura.executeQuery();
		
		if(resultado.next()){
			codigo = resultado.getInt("CD_CIDADE");
		}
		resultado.close();
		estrutura.close();
	
		return codigo;
	}
	
	public int verificarEstado(String estado, Connection conexao) throws Exception{
		int indice = 0;
		String sql = "SELECT * FROM T_DESP_ESTADO WHERE NM_ESTADO = ?";
		PreparedStatement estrutura = conexao.prepareStatement(sql);
		estrutura.setString(1, estado.toUpperCase());
		ResultSet resultado = estrutura.executeQuery();
		
		if(resultado.next()){
			indice = resultado.getInt("CD_ESTADO");
		}
		resultado.close();
		estrutura.close();
		return indice;
	}
	
public int verificarBairro(String bairro, Connection conexao) throws Exception{
		int codigo = 0;
		String sql = "SELECT * FROM T_DESP_BAIRRO WHERE NM_BAIRRO = ?";
		PreparedStatement estrutura = conexao.prepareStatement(sql);
		estrutura.setString(1, bairro.toUpperCase());
		
		ResultSet resultado = estrutura.executeQuery();
		
		if(resultado.next()){
			codigo = resultado.getInt("CD_BAIRRO");
		}
		resultado.close();
		estrutura.close();
		
		return codigo;
	}

	public int verificarLogradouro(int cep, Connection c)throws Exception{
		int indice = 0;
		String sql = "SELECT * FROM T_DESP_LOGRADOURO WHERE NR_CEP = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cep);
		ResultSet resultado = estrutura.executeQuery();
		
		if(resultado.next()){
			indice ++;
		}
		resultado.close();
		estrutura.close();
		
		return indice;
		
	}
	
	public int gravarEstado(String estado, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_ESTADO (NM_ESTADO, SG_ESTADO)"
				+ "VALUES(?, ?)";
		
		PreparedStatement estrutura = c.prepareStatement(sql, new String[]{"CD_ESTADO"});
		
		estrutura.setString(1, estado.toUpperCase());
		estrutura.setString(2, "SP");
		estrutura.execute();
		
		//PEGANDO OS CÓDIGOS CHAVE PRIMÁRIA
		ResultSet rs = estrutura.getGeneratedKeys();
				
		//PASSANDO PARA PRÓXIMA LINHA, AONDE ESTA A CHAVE
		rs.next();
		
		int indice = Integer.parseInt(rs.getString(1));
		rs.close();
		estrutura.close();
		
		return indice;
	}
	
	public int gravarCidade(int cdEstado, String cidade, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_CIDADE (CD_ESTADO, NM_CIDADE)"
				+ "VALUES(?, ?)";
		
		PreparedStatement estrutura = c.prepareStatement(sql, new String[]{"CD_CIDADE"});
		
		estrutura.setInt(1, cdEstado);
		estrutura.setString(2, cidade.toUpperCase());
		estrutura.execute();
		
		//PEGANDO OS CÓDIGOS CHAVE PRIMÁRIA
		ResultSet rs = estrutura.getGeneratedKeys();
				
		//PASSANDO PARA PRÓXIMA LINHA, AONDE ESTA A CHAVE
		rs.next();
		
		int indice = Integer.parseInt(rs.getString(1));
		rs.close();
		estrutura.close();
		
		return indice;
	}
	
	public int gravarBairro(int cdCidade, String bairro, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_BAIRRO (CD_CIDADE, NM_BAIRRO)"
				+ "VALUES(?, ?)";
		
		PreparedStatement estrutura = c.prepareStatement(sql, new String[]{"CD_BAIRRO"});
		
		estrutura.setInt(1, cdCidade);
		estrutura.setString(2, bairro.toUpperCase());
		estrutura.execute();
		
		//PEGANDO OS CÓDIGOS CHAVE PRIMÁRIA
		ResultSet rs = estrutura.getGeneratedKeys();
				
		//PASSANDO PARA PRÓXIMA LINHA, AONDE ESTA A CHAVE
		rs.next();
		
		int indice = Integer.parseInt(rs.getString(1));
		rs.close();
		estrutura.close();
		
		return indice;
	}
	
	public void gravarLogradouro(Endereco e, int cdBairro, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_LOGRADOURO(NR_CEP, CD_BAIRRO, DS_LOGRADOURO)"
				+ "VALUES(?, ?, ?)";
		
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, e.getCep());
		estrutura.setInt(2, cdBairro);
		estrutura.setString(3, e.getLogradouro().toUpperCase());
		estrutura.execute();
		estrutura.close();
	}
	
	public Endereco buscarEndePes(int codigo, Connection c)throws Exception{
		Endereco end = new Endereco();
		String sql =  "SELECT PEND.NR_CEP 'CEP',"+
              "PEND.NR_ENDERECO 'NUMERO',"+
              "PEND.DS_COMPLEMENTO 'COMPLEMENTO',"+
              "LOG.DS_LOGRADOURO 'LOGRADOURO',"+
              "BAI.NM_BAIRRO 'BAIRRO',"+
              "CID.NM_CIDADE 'CIDADE',"+
              "EST.SG_ESTADO 'ESTADO' "+
"FROM    T_DESP_PESSOA_ENDE PEND INNER JOIN "+
              "T_DESP_LOGRADOURO LOG "+
"ON         (PEND.NR_CEP = LOG.NR_CEP) INNER JOIN "+
              "T_DESP_BAIRRO BAI "+
"ON         (LOG.CD_BAIRRO = BAI.CD_BAIRRO) INNER JOIN "+
              "T_DESP_CIDADE CID "+
"ON         (BAI.CD_CIDADE = CID.CD_CIDADE) INNER JOIN "+
              "T_DESP_ESTADO EST "+
"ON          (CID.CD_ESTADO = EST.CD_ESTADO) "+
"WHERE  PEND.CD_PESSOA = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		estrutura.execute();
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			end.setCep(rs.getInt("CEP"));
			end.setLogradouro(rs.getString("LOGRADOURO"));
			end.setNumero(rs.getInt("NUMERO"));
			end.setComplemento(rs.getString("COMPLEMENTO"));
			end.setBairro(rs.getString("BAIRRO"));
			end.setCidade(rs.getString("CIDADE"));
			end.setUf(rs.getString("ESTADO"));
		}
		
		return end;
	}
}
