package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.Endereco;
import br.com.desp.beans.Pessoa;
import br.com.desp.beans.Telefone;
import br.com.desp.beans.Usuario;
import br.com.desp.bo.EnderecoBO;
import br.com.desp.bo.TelefoneBO;

public class PessoaDAO {
	
	public int cadastrar(Pessoa pes, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_PESSOA (nm_pessoa, ds_email, ds_senha, nr_status)"
				+ "VALUES (?,?,?,1)";
		PreparedStatement estrutura = c.prepareStatement(sql, new String[]{"cd_pessoa"});
		
		estrutura.setString(1, pes.getNome());
		estrutura.setString(2, pes.getUsuario().getEmail());
		estrutura.setString(3, pes.getUsuario().getPassword());
		
		estrutura.execute();
		
		ResultSet rs = estrutura.getGeneratedKeys();
		rs.next();
		pes.setCodigo(Integer.parseInt(rs.getString(1)));
		
		rs.close();
		estrutura.close();
		
		TelefoneBO.gravar(pes.getTelefone(), c, pes.getCodigo());
		EnderecoBO.gravar(pes.getEndereco(), c, pes.getCodigo());
		
		return pes.getCodigo();
	}
	
	public void atualizar(Pessoa pes, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_PESSOA SET nm_pessoa = ? "
				+ "WHERE cd_pessoa = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setString(1, pes.getNome());
		estrutura.setInt(2, pes.getCodigo());
		estrutura.execute();
		estrutura.close();
		EnderecoBO.atualizar(pes.getEndereco(), c, pes.getCodigo());
		
	}
	
	public void excluir(Pessoa pes, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_PESSOA SET nr_status = 0 WHERE cd_pessoa = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, pes.getCodigo());
		estrutura.execute();
		estrutura.close();
	}
	
	public List<Pessoa> listar(Connection c)throws Exception{
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		Pessoa pes = null;
		Usuario usu = null;
		Endereco end = null;
		List<Telefone> tels = new ArrayList<Telefone>();
		String sql = "SELECT * FROM T_DESP_PESSOA WHERE nr_status = 1";
		PreparedStatement estrutura = c.prepareStatement(sql);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			pes = new Pessoa();
			usu = new Usuario();
			end = new Endereco();
			pes.setCodigo(rs.getInt("cd_pessoa"));
			pes.setNome(rs.getString("nm_pessoa"));
			usu.setEmail(rs.getString("ds_email"));
			pes.setStatus(rs.getInt("nr_status"));
			usu.setPassword(rs.getString("ds_senha"));
			pes.setUsuario(usu);
			end = EnderecoBO.buscarEndePes(pes.getCodigo(), c);
			pes.setEndereco(end);
			tels = TelefoneBO.buscarTels(pes.getCodigo(), c);
			pes.setTelefone(tels);
			pessoas.add(pes);
		}
		rs.close();
		estrutura.close();
		return pessoas;
	}
	
	public Pessoa pesqCodigo(int codigo, Connection c)throws Exception{
		Pessoa pes = new Pessoa();
		Usuario usu = new Usuario();
		Endereco end = new Endereco();
		List<Telefone> tels = new ArrayList<Telefone>();
		String sql = "SELECT * FROM T_DESP_PESSOA WHERE cd_pessoa = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			pes.setCodigo(rs.getInt("cd_pessoa"));
			pes.setNome(rs.getString("nm_pessoa"));
			pes.setStatus(rs.getInt("nr_status"));
			usu.setEmail(rs.getString("ds_email"));
			usu.setPassword(rs.getString("ds_senha"));
			pes.setUsuario(usu);
			end = EnderecoBO.buscarEndePes(pes.getCodigo(), c);
			pes.setEndereco(end);
			tels = TelefoneBO.buscarTels(pes.getCodigo(), c);
			pes.setTelefone(tels);
		}
		rs.close();
		estrutura.close();
		return pes;
	}
	
	public Pessoa pesqNome(String nome, Connection c)throws Exception{
		Pessoa pes = new Pessoa();
		Usuario usu = new Usuario();
		Endereco end = new Endereco();
		List<Telefone> tels = new ArrayList<Telefone>();
		String sql = "SELECT * FROM T_DESP_PESSOA WHERE nm_pessoa = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, nome);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			pes.setCodigo(rs.getInt("cd_pessoa"));
			pes.setNome(rs.getString("nm_pessoa"));
			pes.setStatus(rs.getInt("nr_status"));
			usu.setEmail(rs.getString("ds_email"));
			usu.setPassword(rs.getString("ds_senha"));
			pes.setUsuario(usu);
			end = EnderecoBO.buscarEndePes(pes.getCodigo(), c);
			pes.setEndereco(end);
			tels = TelefoneBO.buscarTels(pes.getCodigo(), c);
			pes.setTelefone(tels);
		}
		rs.close();
		estrutura.close();
		return pes;
	}
	
	public Pessoa pesqEmail(String email, Connection c)throws Exception{
		Pessoa pes = new Pessoa();
		Usuario usu = new Usuario();
		Endereco end = new Endereco();
		List<Telefone> tels = new ArrayList<Telefone>();
		String sql = "SELECT * FROM T_DESP_PESSOA WHERE ds_email = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, email);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			pes.setCodigo(rs.getInt("cd_pessoa"));
			pes.setNome(rs.getString("nm_pessoa"));
			pes.setStatus(rs.getInt("nr_status"));
			usu.setEmail(rs.getString("ds_email"));
			usu.setPassword(rs.getString("ds_senha"));
			pes.setUsuario(usu);
			end = EnderecoBO.buscarEndePes(pes.getCodigo(), c);
			pes.setEndereco(end);
			tels = TelefoneBO.buscarTels(pes.getCodigo(), c);
			pes.setTelefone(tels);
		}
		rs.close();
		estrutura.close();
		return pes;
	}
	
	public int verEmail(String email, Connection c)throws Exception{
		int ver = 0;
		String sql = "SELECT * FROM T_DESP_PESSOA WHERE ds_email = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, email);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			ver = 1;
		}
		rs.close();
		estrutura.close();
		return ver;
	}
	
	public List<Pessoa> listarDesativados(Connection c)throws Exception{
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		Pessoa pes = null;
		Usuario usu = null;
		Endereco end = null;
		List<Telefone> tels = new ArrayList<Telefone>();
		String sql = "SELECT * FROM T_DESP_PESSOA WHERE nr_status = 0";
		PreparedStatement estrutura = c.prepareStatement(sql);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			pes = new Pessoa();
			usu = new Usuario();
			end = new Endereco();
			pes.setCodigo(rs.getInt("cd_pessoa"));
			pes.setNome(rs.getString("nm_pessoa"));
			pes.setStatus(rs.getInt("nr_status"));
			usu.setEmail(rs.getString("ds_email"));
			usu.setPassword(rs.getString("ds_senha"));
			pes.setUsuario(usu);
			end = EnderecoBO.buscarEndePes(pes.getCodigo(), c);
			pes.setEndereco(end);
			tels = TelefoneBO.buscarTels(pes.getCodigo(), c);
			pes.setTelefone(tels);
			pessoas.add(pes);
		}
		rs.close();
		estrutura.close();
		return pessoas;
	}

}
