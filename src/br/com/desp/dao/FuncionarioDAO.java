package br.com.desp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.desp.beans.Cargo;
import br.com.desp.beans.Funcionario;
import br.com.desp.beans.Pessoa;
import br.com.desp.beans.Usuario;
import br.com.desp.bo.CargoBO;
import br.com.desp.bo.FilialBO;
import br.com.desp.bo.PessoaBO;

public class FuncionarioDAO {
	
	public void cadastrar(Funcionario fun, Connection c)throws Exception{
		Pessoa pes = new Pessoa();
		pes.setEndereco(fun.getEndereco());
		pes.setNome(fun.getNome());
		pes.setTelefone(fun.getTelefone());
		pes.setUsuario(fun.getUsuario());
		System.out.println(fun.getNome());
		fun.setCodigo(PessoaBO.cadastrar(pes, c));
		
		String sql = "INSERT INTO T_DESP_FUNCIONARIO (cd_funcionario, cd_filial, nr_cpf, cd_cargo, vl_salario, "
				+ "dt_admissao) VALUES(?,?,?,?,?,?)";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, fun.getCodigo());
		estrutura.setInt(2, fun.getFilial().getCodigo());
		estrutura.setString(3, Long.toString(fun.getCpf()));
		estrutura.setInt(4, fun.getCargo().getCodigo());
		estrutura.setDouble(5, fun.getSalario());
		estrutura.setDate(6, new Date(fun.getDtAdmissao().getTimeInMillis()));
		
		estrutura.execute();
		estrutura.close();
	}
	
	public void editar(Funcionario fun, Connection c)throws Exception{
		Pessoa pes = new Pessoa();
		pes.setEndereco(fun.getEndereco());
		pes.setNome(fun.getNome());
		pes.setTelefone(fun.getTelefone());
		pes.setUsuario(fun.getUsuario());
		pes.setCodigo(fun.getCodigo());
		PessoaBO.atualizar(pes, c);
		
			String sql = "UPDATE T_DESP_FUNCIONARIO SET cd_filial = ?, cd_cargo = ?, vl_salario = ?, "
					+ "dt_admissao = ? WHERE cd_funcionario = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, fun.getFilial().getCodigo());
		estrutura.setInt(2, fun.getCargo().getCodigo());
		estrutura.setDouble(3, fun.getSalario());
		estrutura.setDate(4, new Date(fun.getDtAdmissao().getTimeInMillis()));
		estrutura.setInt(5, fun.getCodigo());
		
		estrutura.execute();
		estrutura.close();
	}
	
	public void desativar(Pessoa pes, Connection c)throws Exception{
		PessoaBO.desativar(pes, c);
	}
	
	public List<Funcionario> listarFunFilial(int cdFilial, Connection c)throws Exception{
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		Funcionario fun = null;
		Pessoa pes = null;
		Cargo car = null;
		
		String sql = "SELECT * FROM T_DESP_FUNCIONARIO WHERE cd_filial = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			fun = new Funcionario();
			fun.setCodigo(rs.getInt("cd_funcionario"));
			fun.setCpf(Long.parseLong(rs.getString("nr_cpf")));
			fun.setSalario(rs.getDouble("vl_salario"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_admissao"));
			fun.setDtAdmissao(cal);
			Date dtRecisao = rs.getDate("dt_recisao");
			if(dtRecisao != null){
				cal.setTime(dtRecisao);
				fun.setDtRecisao(cal);
			}
			pes = new Pessoa();
			pes = PessoaBO.pesqCodigo(fun.getCodigo(), c);
			fun.setEndereco(pes.getEndereco());
			fun.setNome(pes.getNome());
			fun.setStatus(pes.getStatus());
			fun.setTelefone(pes.getTelefone());
			fun.setUsuario(pes.getUsuario());
			car = new Cargo();
			car.setCodigo(rs.getInt("cd_cargo"));
			car = CargoBO.pesqCodigo(car, c);
			fun.setCargo(car);
			if(fun.getStatus() == 1){
				funcionarios.add(fun);
			}
		}
		rs.close();
		estrutura.close();
		return funcionarios;
	}
	
	public List<Funcionario> listarTodos(Connection c)throws Exception{
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		Funcionario fun = null;
		Pessoa pes = null;
		Cargo car = null;
		
		String sql = "SELECT * FROM T_DESP_FUNCIONARIO";
		PreparedStatement estrutura = c.prepareStatement(sql);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			fun = new Funcionario();
			fun.setCodigo(rs.getInt("cd_funcionario"));
			fun.setCpf(Long.parseLong(rs.getString("nr_cpf")));
			fun.setSalario(rs.getDouble("vl_salario"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_admissao"));
			fun.setDtAdmissao(cal);
			Date dtRecisao = rs.getDate("dt_recisao");
			if(dtRecisao != null){
				cal.setTime(dtRecisao);
				fun.setDtRecisao(cal);
			}
			pes = new Pessoa();
			pes = PessoaBO.pesqCodigo(fun.getCodigo(), c);
			fun.setEndereco(pes.getEndereco());
			fun.setNome(pes.getNome());
			fun.setStatus(pes.getStatus());
			fun.setTelefone(pes.getTelefone());
			fun.setUsuario(pes.getUsuario());
			car = new Cargo();
			fun.setCargo(car);
			if(fun.getStatus() == 1){
				funcionarios.add(fun);
			}
		}
		rs.close();
		estrutura.close();
		return funcionarios;
	}
	
	public Funcionario pesqEmail(Usuario usu, Connection c)throws Exception{
		Pessoa pes = PessoaBO.pesqEmail(usu.getEmail(), c);
		Funcionario fun = new Funcionario();
		fun.setCodigo(pes.getCodigo());
		fun.setEndereco(pes.getEndereco());
		fun.setTelefone(pes.getTelefone());
		fun.setNome(pes.getNome());
		fun.setStatus(pes.getStatus());
		fun.setUsuario(pes.getUsuario());
		String sql = "SELECT * FROM T_DESP_FUNCIONARIO WHERE cd_funcionario = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, pes.getCodigo());
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			Cargo car = new Cargo();
			car.setCodigo(rs.getInt("cd_cargo"));
			fun.setCargo(CargoBO.pesqCodigo(car, c));
			fun.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			fun.setCpf(Long.parseLong(rs.getString("nr_cpf")));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_admissao"));
			fun.setDtAdmissao(cal);
			Date dtRecisao = rs.getDate("dt_recisao");
			if(dtRecisao != null){
				cal.setTime(rs.getDate("dt_recisao"));
				fun.setDtRecisao(cal);
			}
			fun.setSalario(rs.getDouble("vl_salario"));
		}
		rs.close();
		estrutura.close();
		return fun;
	}
	
	public Funcionario pesqCodigo(int codigo, Connection c)throws Exception{
		Pessoa pes = PessoaBO.pesqCodigo(codigo, c);
		Funcionario fun = new Funcionario();
		fun.setCodigo(pes.getCodigo());
		fun.setEndereco(pes.getEndereco());
		fun.setTelefone(pes.getTelefone());
		fun.setNome(pes.getNome());
		fun.setStatus(pes.getStatus());
		fun.setUsuario(pes.getUsuario());
		String sql = "SELECT * FROM T_DESP_FUNCIONARIO WHERE cd_funcionario = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, pes.getCodigo());
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			Cargo car = new Cargo();
			car.setCodigo(rs.getInt("cd_cargo"));
			fun.setCargo(CargoBO.pesqCodigo(car, c));
			fun.setFilial(FilialBO.pesqCodigo(rs.getInt("cd_filial"), c));
			fun.setCpf(Long.parseLong(rs.getString("nr_cpf")));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_admissao"));
			fun.setDtAdmissao(cal);
			Date dtRecisao = rs.getDate("dt_recisao");
			if(dtRecisao != null){
				cal.setTime(rs.getDate("dt_recisao"));
				fun.setDtRecisao(cal);
			}
			fun.setDtRecisao(cal);
			fun.setSalario(rs.getDouble("vl_salario"));
		}
		rs.close();
		estrutura.close();
		return fun;
	}

}
