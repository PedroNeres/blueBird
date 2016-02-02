package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.Despachante;
import br.com.desp.beans.Filial;
import br.com.desp.beans.Pessoa;
import br.com.desp.bo.DespachanteBO;
import br.com.desp.bo.PessoaBO;

public class FilialDAO {
	
	public void cadastrar(Filial fil, Connection c)throws Exception{
		Pessoa pes = new Pessoa();
		pes.setEndereco(fil.getEndereco());
		pes.setNome(fil.getNome());
		pes.setStatus(1);
		pes.setTelefone(fil.getTelefone());
		pes.setUsuario(fil.getUsuario());
		fil.setCodigo(PessoaBO.cadastrar(pes, c));
		Despachante desp = new Despachante();
		if(DespachanteBO.pesqCodigo(fil.getDespachante().getCodigo(), c) == null){
			desp.setCodigo(DespachanteBO.cadastrar(fil.getDespachante(), c));
			fil.setDespachante(desp);
		}
		String sql = "INSERT INTO T_DESP_FILIAL (cd_filial, cd_despachante) VALUES (?,?)";
		PreparedStatement estrutura
		= c.prepareStatement(sql);
		estrutura.setInt(1, fil.getCodigo());
		estrutura.setInt(2, fil.getDespachante().getCodigo());
		estrutura.execute();
		estrutura.close();
	}
	
	public void editar(Filial fil, Connection c)throws Exception{
		Pessoa pes = new Pessoa();
		pes.setEndereco(fil.getEndereco());
		pes.setNome(fil.getNome());
		pes.setStatus(1);
		pes.setTelefone(fil.getTelefone());
		pes.setUsuario(fil.getUsuario());
		pes.setCodigo(fil.getCodigo());
		PessoaBO.atualizar(pes, c);
	}
	
	public void excluir(Filial fil, Connection c)throws Exception{
		Pessoa pes = new Pessoa();
		pes.setCodigo(fil.getCodigo());
		PessoaBO.desativar(pes, c);
	}
	
	public List<Filial> listar(Connection c)throws Exception{
		List<Filial> filiais = new ArrayList<Filial>();
		Filial fil = null;
		String sql = "SELECT * FROM T_DESP_FILIAL";
		PreparedStatement estrutura = c.prepareStatement(sql);
		ResultSet rs = estrutura.executeQuery();
		
		while(rs.next()){
			fil = new Filial();
			fil.setCodigo(rs.getInt("cd_filial"));
			Pessoa pes = PessoaBO.pesqCodigo(fil.getCodigo(), c);
			fil.setEndereco(pes.getEndereco());
			fil.setNome(pes.getNome());
			fil.setStatus(pes.getStatus());
			fil.setTelefone(pes.getTelefone());
			fil.setUsuario(pes.getUsuario());
			Despachante desp = DespachanteBO.pesqCodigo(fil.getDespachante().getCodigo(), c);
			fil.setDespachante(desp);
			filiais.add(fil);
			if(fil.getStatus() == 0){
				filiais.remove(fil);
			}
		}
		rs.close();
		estrutura.close();
		return filiais;
	}
	
	public Filial pesqCodigo(int codigo, Connection c)throws Exception{
		Filial fil = new Filial();
		String sql = "SELECT * FROM T_DESP_FILIAL WHERE cd_filial = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		ResultSet rs = estrutura.executeQuery();
		
		if(rs.next()){
			fil.setCodigo(rs.getInt("cd_filial"));
			Pessoa pes = PessoaBO.pesqCodigo(fil.getCodigo(), c);
			fil.setEndereco(pes.getEndereco());
			fil.setNome(pes.getNome());
			fil.setStatus(pes.getStatus());
			fil.setTelefone(pes.getTelefone());
			fil.setUsuario(pes.getUsuario());
			Despachante desp = DespachanteBO.pesqCodigo(rs.getInt("cd_despachante"), c);
			fil.setDespachante(desp);
		}
		rs.close();
		estrutura.close();
		return fil;
	}
	
	public List<Filial> listarFilDesp(int cdDespachante, Connection c)throws Exception{
		List<Filial> filiais = new ArrayList<Filial>();
		Filial fil = null;
		String sql = "SELECT * FROM T_DESP_FILIAL WHERE cd_despachante = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdDespachante);
		ResultSet rs = estrutura.executeQuery();
		
		while(rs.next()){
			fil = new Filial();
			fil.setCodigo(rs.getInt("cd_filial"));
			Pessoa pes = new Pessoa();
			pes = PessoaBO.pesqCodigo(fil.getCodigo(), c);
			fil.setEndereco(pes.getEndereco());
			fil.setNome(pes.getNome());
			fil.setStatus(pes.getStatus());
			fil.setTelefone(pes.getTelefone());
			fil.setUsuario(pes.getUsuario());
			Despachante desp = new Despachante();
			desp = DespachanteBO.pesqCodigo(cdDespachante, c);
			fil.setDespachante(desp);
			filiais.add(fil);
			
		}
		rs.close();
		estrutura.close();
		return filiais;
	}

}
