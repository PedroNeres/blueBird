package br.com.desp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.desp.beans.Cargo;
import br.com.desp.beans.Endereco;
import br.com.desp.beans.Filial;
import br.com.desp.beans.Funcionario;
import br.com.desp.beans.Pessoa;
import br.com.desp.beans.Telefone;
import br.com.desp.beans.Usuario;
import br.com.desp.bo.CargoBO;
import br.com.desp.bo.FilialBO;
import br.com.desp.bo.FuncionarioBO;
import br.com.desp.bo.PFisicaBO;
import br.com.desp.bo.PessoaBO;
import br.com.desp.conexao.ConexaoFactory;
import br.com.desp.util.DataUtil;

@WebServlet("/funcionario")
public class FuncionarioServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String acao = req.getParameter("acao");
		String retorno = "";
		
		resp.setContentType("text/html;charset=UTF-8");
		
		switch (acao) {
		case "carregar":
			carregar(req);
			retorno = "cadSupervisor.jsp";
			break;
		case "carregarFun":
			carregarFunc(req);
			retorno = "editarFunc.jsp";
			break;
		case "verCpf":
			verCpf(req);
			retorno = "cadSupervisor.jsp";
			break;
		case "cadSupervisor":
			cadSupervisor(req);
			retorno = "cadSupervisor.jsp";
			break;
		case "excluir":
			excluir(req);
			retorno = "listarFuncionarios.jsp";
			break;
		case "editar":
			editar(req);
			listar(req);
			retorno = "listarFuncionarios.jsp";
			break;
		case "listarFun":
			listar(req);
			retorno = "listarFuncionarios.jsp";
			break;
		}
		req.getRequestDispatcher(retorno).forward(req, resp);
	}

	private void excluir(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Connection c = null;
		
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			int codigo = Integer.parseInt(req.getParameter("codigo"));
			Pessoa pes = PessoaBO.pesqCodigo(codigo, c);
			FuncionarioBO.destativar(pes, c);
			listar(req);
			req.setAttribute("msg", pes.getNome() + " desativado com sucesso!");
			
			c.commit();
			c.setAutoCommit(true);
		}catch(Exception e){
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}
		
	}

	private void carregarFunc(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		
		Connection c = null;
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();

			int codigo = Integer.parseInt(req.getParameter("cdFun"));
			
			Funcionario fun = new Funcionario();
			fun = FuncionarioBO.pesqCodigo(codigo, c);
			System.out.println(fun.getNome());
			System.out.println(fun.getEndereco().getBairro());
			req.setAttribute("fun", fun);

		}catch(Exception e){
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}
		
		
	}

	private void listar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
			int cdFilial = Integer.parseInt(req.getParameter("cdFilial"));
			funcionarios = FuncionarioBO.listarFunFil(cdFilial, c);
			
			req.setAttribute("funcionarios", funcionarios);
			
		}catch(Exception e){
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}
		
	}

	private void verCpf(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try{
			Long verCpf = Long.parseLong(req.getParameter("cpf"));
			c = ConexaoFactory.controlarInstancia().getConnection();
			carregar(req);
			String nome = req.getParameter("nome");
			String cpf = req.getParameter("cpf");
			req.setAttribute("nome", nome);
			req.setAttribute("cpf", cpf);
			PFisicaBO.pesqCpf(verCpf, c);
		}catch(Exception e){
			req.setAttribute("erro", e.getMessage());
			e.printStackTrace();
		}
		
	}

	private void carregar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Connection c = null;
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			int cdDespachante = Integer.parseInt(req.getParameter("cdDespachante"));
			int cdFilial = Integer.parseInt(req.getParameter("cdFilial"));
			
			 List<Filial> filiais = FilialBO.listFilDesp(cdDespachante, c);
			req.setAttribute("filiais", filiais);
			
			
			Filial fil = FilialBO.pesqCodigo(cdFilial, c);
			req.setAttribute("fil", fil);
			
			List<Cargo> car = new ArrayList<Cargo>();
			car = CargoBO.listar(c);
			req.setAttribute("cargo", car);
			
			Calendar dateFormat = Calendar.getInstance();
		
			req.setAttribute("dtAdmissao", dateFormat);
			
			
			c.commit();
			c.setAutoCommit(true);
		}catch(Exception e){
			try{
				c.rollback();
			}catch(Exception e2){
				req.setAttribute("erro", e2.getMessage());
			}
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		String acao = req.getParameter("acao");
		String retorno = "";
		
		switch (acao) {
		case "cadSupervisor":
			cadSupervisor(req);
			retorno = "cadSupervisor.jsp";
			break;
		case "editar":
			editar(req);
			listar(req);
			retorno = "listarFuncionarios.jsp";
			break;

		}
		req.getRequestDispatcher(retorno).forward(req, resp);
		
	}

	private void editar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			Funcionario fun = new Funcionario();
			Cargo car = new Cargo();
			car.setCodigo(Integer.parseInt(req.getParameter("cargo")));
			fun.setCargo(CargoBO.pesqCodigo(car, c));
			fun.setCodigo(Integer.parseInt(req.getParameter("cdFuncionario")));
			fun.setCpf(Long.parseLong(req.getParameter("cpf")));
			fun.setDtAdmissao(DataUtil.converter(req.getParameter("dtAdmissao")));
			Endereco end = new Endereco();
			end.setBairro(req.getParameter("bairro"));
			end.setCep(Integer.parseInt(req.getParameter("cep")));
			end.setCidade(req.getParameter("cidade"));
			end.setComplemento(req.getParameter("complemento"));
			end.setLogradouro(req.getParameter("logradouro"));
			end.setNumero(Integer.parseInt(req.getParameter("numero")));
			end.setUf(req.getParameter("uf"));
			fun.setEndereco(end);
			System.out.println(fun.getEndereco().getBairro() + "teste");
			Filial fil = new Filial();
			fil = FilialBO.pesqCodigo(Integer.parseInt(req.getParameter("filial")), c);
			fun.setFilial(fil);
			fun.setNome(req.getParameter("nome"));
			fun.setSalario(Double.parseDouble(req.getParameter("salario")));
			
			FuncionarioBO.editar(fun, c);
			req.setAttribute("msg", "Usuário editado com sucesso!");
			c.commit();
			c.setAutoCommit(true);
		} catch (Exception e) {
			try{
				c.rollback();
				e.printStackTrace();
			}catch(Exception e2){
				req.setAttribute("erro", e2.getMessage());
				e2.printStackTrace();
			}
			req.setAttribute("erro", e.getMessage());
			// TODO: handle exception
		}
		
	}

	private void cadSupervisor(HttpServletRequest req){
		// TODO Auto-generated method stub
		
		Connection c = null;
		int aprova = 0;
		try{
			
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			carregar(req);
			
			
			Funcionario fun = new Funcionario();
			
			String nome = req.getParameter("nome").toUpperCase();
			
			req.setAttribute("nome", nome);
			long cpf = Long.parseLong(req.getParameter("cpf"));
			req.setAttribute("cpf", cpf);
			String strData = req.getParameter("dtAdmissao");
			
			Calendar dtAdmissao = DataUtil.converter(strData);
			req.setAttribute("dtAdmissao", dtAdmissao);
			
			fun.setNome(nome);
			
			fun.setCpf(cpf);
			fun.setDtAdmissao(dtAdmissao);
	
			Endereco end = new Endereco();
			int cep = Integer.parseInt(req.getParameter("cep"));
			req.setAttribute("cep", cep);
			String logradouro = req.getParameter("logradouro").toUpperCase();
			req.setAttribute("logradouro", logradouro);
				int numero = 0;
			try{
				numero = Integer.parseInt(req.getParameter("numero"));
			}catch(NumberFormatException e){
				req.setAttribute("erro", "O número do logradouro inválido");
			}
			req.setAttribute("numero", numero);
			String complemento = req.getParameter("complemento").toUpperCase();
			req.setAttribute("complemento", complemento);
			String bairro = req.getParameter("bairro").toUpperCase();
			req.setAttribute("bairro", bairro);
			String cidade = req.getParameter("cidade").toUpperCase();
			
			req.setAttribute("cidade", cidade);
			String uf = req.getParameter("uf").toUpperCase();
			req.setAttribute("uf", uf);
			end.setCep(cep);
			end.setLogradouro(logradouro);
			end.setNumero(numero);
			if(complemento != null){
				end.setComplemento(complemento);
			}
			end.setBairro(bairro);
			end.setCidade(cidade);
			end.setUf(uf);
			
			fun.setEndereco(end);
			List<Telefone> tels = new ArrayList<Telefone>();
			
			Telefone tel = null;
			String ddd1 = req.getParameter("ddd1");
			req.setAttribute("ddd1", ddd1);
			String tel1 = req.getParameter("tel1");
			req.setAttribute("tel1", tel1);
			try{
				tel = new Telefone();
				tel.setDdd(Integer.parseInt(ddd1));
				tel.setNumero(Integer.parseInt(tel1));
				tels.add(tel);
			}catch(NumberFormatException e){
				c.rollback();
				aprova = 1;
				req.setAttribute("erro", "Cadastre ao menos um telefone!!!");
			}
			String ddd2 = req.getParameter("ddd2");
			req.setAttribute("ddd2", ddd2);
			String tel2 = req.getParameter("tel2");
			req.setAttribute("tel2", tel2);
			if(!ddd2.equals("") || !tel2.equals("")){
				tel = new Telefone();
				tel.setDdd(Integer.parseInt(ddd2));
				tel.setNumero(Integer.parseInt(tel2));
				tels.add(tel);
			}
			
			fun.setTelefone(tels);
			
			Usuario usu = new Usuario();
			String email = req.getParameter("email").toUpperCase();
			req.setAttribute("email", email);
			String senha = req.getParameter("senha");
			String cSenha = req.getParameter("cSenha");
			if(senha.equals(cSenha)){
				usu.setEmail(email);
				usu.setPassword(senha);
			}else{
				req.setAttribute("erro", "Confirmação de senha inválida!");
				aprova = 1;
			}
			fun.setUsuario(usu);
			
			Cargo cargo = new Cargo();
			int car = Integer.parseInt(req.getParameter("cargo"));
			cargo.setCodigo(car);
			fun.setCargo(cargo);
			
			double salario = Double.parseDouble(req.getParameter("salario"));
			req.setAttribute("salario", req.getParameter("salario"));
			fun.setSalario(salario);
			
			Filial filial = new Filial();
			int fil = Integer.parseInt(req.getParameter("filial"));
			filial.setCodigo(fil);
			fun.setFilial(filial);
			if(aprova == 0){
				FuncionarioBO.cadastrar(fun, c);
				req.setAttribute("msg", "Funcionário cadastrado com sucesso!");
				c.commit();
				c.setAutoCommit(true);
			}else{
				c.rollback();
			}
			
			
		}catch(Exception e){
			try{
				c.rollback();
				
			}catch(Exception e2){
				req.setAttribute("erro", e2.getMessage());
			}
			
			req.setAttribute("erro", e.getMessage());
		}
		
	}

}
