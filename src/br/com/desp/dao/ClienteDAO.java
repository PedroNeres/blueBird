package br.com.desp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.Cliente;
import br.com.desp.beans.Pessoa;
import br.com.desp.bo.FilialBO;
import br.com.desp.bo.PessoaBO;

public class ClienteDAO {
	
	public void cadastrar(Cliente cli, Connection c)throws Exception{
		Pessoa pes = new Pessoa();
		pes.setCodigo(0);
		pes.setEndereco(cli.getEndereco());
		pes.setNome(cli.getNome());
		pes.setStatus(1);
		pes.setTelefone(cli.getTelefone());
		pes.setUsuario(cli.getUsuario());
		cli.setCodigo(PessoaBO.cadastrar(pes, c));

		String sql = "INSERT INTO T_DESP_CLIENTE (cd_cliente, cd_filial, nr_cpf) VALUES(?,?,?)";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cli.getCodigo());
		estrutura.setInt(2, cli.getFilial().getCodigo());
		estrutura.setString(3, Long.toString(cli.getCpf()));
		estrutura.execute();
		estrutura.close();
	}
	
	public void deletar(int codigo, Connection c)throws Exception{
		Pessoa pes = PessoaBO.pesqCodigo(codigo, c);
		PessoaBO.desativar(pes, c);
	}
	
	public void editar(Cliente cli, Connection c)throws Exception{
		Pessoa pes = new Pessoa();
		pes.setCodigo(cli.getCodigo());
		pes.setEndereco(cli.getEndereco());
		pes.setNome(cli.getNome());
		pes.setStatus(cli.getStatus());
		pes.setTelefone(cli.getTelefone());
		pes.setUsuario(cli.getUsuario());
		PessoaBO.atualizar(pes, c);
		String sql = "UPDATE T_DESP_CLIENTE SET(cd_filial = ?, nr_cpf = ?) WHERE cd_cliente = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cli.getFilial().getCodigo());
		estrutura.setString(2, Long.toString(cli.getCpf()));
		estrutura.execute();
		estrutura.close();
	}
	
	public List<Cliente> listarCliente(Connection c)throws Exception{
		List<Cliente> clientes = new ArrayList<Cliente>();
		Cliente cli = null;
		Pessoa pes = null;
		String sql = "SELECT * FROM T_DESP_CLIENTE";
		PreparedStatement estrutura = c.prepareStatement(sql);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			cli = new Cliente();
			cli.setCodigo(rs.getInt("cd_cliente"));
			cli.setCpf(Long.parseLong(rs.getString("nr_cpf")));
			cli.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			pes = PessoaBO.pesqCodigo(cli.getCodigo(), c);
			cli.setEndereco(pes.getEndereco());
			cli.setNome(pes.getNome());
			cli.setStatus(pes.getStatus());
			cli.setTelefone(pes.getTelefone());
			cli.setUsuario(pes.getUsuario());
			clientes.add(cli);
		}
		rs.close();
		estrutura.close();
		return clientes;
	}
	
	public List<Cliente> listarClienteFilial(int cdFilial, Connection c)throws Exception{
		List<Cliente> clientes = new ArrayList<Cliente>();
		Cliente cli = null;
		Pessoa pes = null;
		String sql = "SELECT * FROM T_DESP_CLIENTE WHERE cd_filial = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			cli = new Cliente();
			cli.setCodigo(rs.getInt("cd_cliente"));
			cli.setCpf(Long.parseLong(rs.getString("nr_cpf")));
			cli.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			pes = PessoaBO.pesqCodigo(cli.getCodigo(), c);
			cli.setEndereco(pes.getEndereco());
			cli.setNome(pes.getNome());
			cli.setStatus(pes.getStatus());
			cli.setTelefone(pes.getTelefone());
			cli.setUsuario(pes.getUsuario());
			clientes.add(cli);
		}
		rs.close();
		estrutura.close();
		return clientes;
	}
	
	public Cliente pesqCodigo(int codigo, Connection c)throws Exception{
		Cliente cli = new Cliente();
		String sql = "SELECT * FROM T_DESP_CLIENTE WHERE cd_cliente = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			cli.setCodigo(rs.getInt("cd_cliente"));
			cli.setCpf(Long.parseLong(rs.getString("nr_cpf")));
			cli.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			Pessoa pes = PessoaBO.pesqCodigo(cli.getCodigo(), c);
			cli.setEndereco(pes.getEndereco());
			cli.setNome(pes.getNome());
			cli.setStatus(pes.getStatus());
			cli.setTelefone(pes.getTelefone());
			cli.setUsuario(pes.getUsuario());
		}
		rs.close();
		estrutura.close();
		return cli;
	}
	
	public Cliente pesqEmail(String email, Connection c)throws Exception{
		Cliente cli = new Cliente();
		Pessoa pes = PessoaBO.pesqEmail(email, c);
		String sql = "SELECT * FROM T_DESP_CLIENTE WHERE cd_cliente = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, pes.getCodigo());
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			cli.setCodigo(pes.getCodigo());
			cli.setCpf(Long.parseLong(rs.getString("nr_cpf")));
			cli.setEndereco(pes.getEndereco());
			cli.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			cli.setNome(pes.getNome());
			cli.setStatus(pes.getStatus());
			cli.setTelefone(pes.getTelefone());
			cli.setUsuario(pes.getUsuario());
		}
		return cli;
	}
	
	public Cliente pesqNome(String nome, Connection c)throws Exception{
		Cliente cli = new Cliente();
		Pessoa pes = PessoaBO.pesqNome(nome, c);
		String sql = "SELECT * FROM T_DESP_CLIENTE WHERE cd_cliente = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, pes.getCodigo());
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			cli.setCodigo(pes.getCodigo());
			cli.setCpf(Long.parseLong(rs.getString("nr_cpf")));
			cli.setEndereco(pes.getEndereco());
			cli.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			cli.setNome(pes.getNome());
			cli.setStatus(pes.getStatus());
			cli.setTelefone(pes.getTelefone());
			cli.setUsuario(pes.getUsuario());
		}
		return cli;
	}
	
	public Cliente pesqCpf(String cpf, Connection c)throws Exception{
		Cliente cli = new Cliente();
		String sql = "SELECT * FROM T_DESP_CLIENTE WHERE nr_cpf = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, cpf);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			cli.setCodigo(rs.getInt("cd_cliente"));
			cli.setCpf(Long.parseLong(rs.getString("nr_cpf")));
			cli.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			Pessoa pes = PessoaBO.pesqCodigo(cli.getCodigo(), c);
			cli.setEndereco(pes.getEndereco());
			cli.setNome(pes.getNome());
			cli.setStatus(pes.getStatus());
			cli.setTelefone(pes.getTelefone());
			cli.setUsuario(pes.getUsuario());
		}
		rs.close();
		estrutura.close();
		return cli;
	}

}
