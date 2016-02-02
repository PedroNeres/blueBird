package br.com.desp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.desp.beans.Cliente;
import br.com.desp.beans.Endereco;
import br.com.desp.beans.Pagamento;
import br.com.desp.beans.Telefone;
import br.com.desp.beans.TipoOrdemServico;
import br.com.desp.beans.TipoVeiculo;
import br.com.desp.beans.Usuario;
import br.com.desp.bo.ClienteBO;
import br.com.desp.bo.FilialBO;
import br.com.desp.bo.PagamentoBO;
import br.com.desp.bo.TipoOrdemBO;
import br.com.desp.bo.TipoVeiculoBO;
import br.com.desp.conexao.ConexaoFactory;

@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet{
	
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
		carregar(req);
		switch (acao) {
		case "listar":
			listar(req);
			retorno = "listarClientes.jsp";
			break;
		case "buscar":
			buscarCpf(req);
			retorno = "listarClientes.jsp";
			break;
		case "verificar":
			retorno = verificar(req);
			break;
		}
		req.getRequestDispatcher(retorno).forward(req, resp);
	}

	private void carregar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
Connection c = null;
		
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			List<Pagamento> pags = new ArrayList<Pagamento>();
			List<TipoOrdemServico> tiposOrdem = new ArrayList<TipoOrdemServico>();
			List<TipoVeiculo> tiposVeiculos = new ArrayList<TipoVeiculo>();
			
			pags = PagamentoBO.listarPagAberto(c);
			tiposOrdem = TipoOrdemBO.listar(c);
			tiposVeiculos = TipoVeiculoBO.listar(c);
			
			req.setAttribute("pagAberto", pags);
			req.setAttribute("tpVeiculo", tiposVeiculos);
			req.setAttribute("tpOrdem", tiposOrdem);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private String verificar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Connection c = null;
		String retorno = "";
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();
			String cpf = req.getParameter("buscarCpf");
			
			retorno = "osCadCliente.jsp";
			req.setAttribute("msg", "Cliente não cadastrado, cadastre para continuar");
			if(ClienteBO.pesqCpf(cpf, c).getCpf() == Long.parseLong(cpf)){
				Cliente cli = ClienteBO.pesqCpf(cpf, c);
				req.setAttribute("cliente", cli);
				retorno = "verVeiculo.jsp";
				req.setAttribute("msg", "Cliente ja cadastrado, conclua a solicitação de O.S");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return retorno;
	}

	private void buscarCpf(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			String cpf =req.getParameter("buscarCpf");
			
			Cliente cli = ClienteBO.pesqCpf(cpf, c);
			List<Cliente> clientes = new ArrayList<Cliente>();
			clientes.add(cli);
			req.setAttribute("cliente", clientes);
		} catch (Exception e) {
			req.setAttribute("erro", e.getMessage());
			// TODO: handle exception
		}
		
	}

	private void listar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Connection c = null;
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			List<Cliente> clientes = new ArrayList<Cliente>();
			
			clientes = ClienteBO.listar(c);
			
			req.setAttribute("cliente", clientes);
			c.commit();
			c.setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String acao = req.getParameter("acao");
		String retorno = "";
		switch (acao) {
		case "cadastrar":
			cadastrar(req);
			retorno = "cadCliente.jsp";
			break;
		case "osCadastrar":
			cadastrar(req);
			retorno = "verVeiculo.jsp";
		}
		req.getRequestDispatcher(retorno).forward(req, resp);
	}

	private void cadastrar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		int aprova = 0;
		
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			Cliente cli = new Cliente();
			cli.setCpf(Long.parseLong(req.getParameter("cpf").toUpperCase()));
			cli.setNome(req.getParameter("nome").toUpperCase());
			cli.setFilial(FilialBO.pesqCodigo(Integer.parseInt(req.getParameter("cdFilial")), c));
			
			Usuario usu = new Usuario();
			usu.setEmail(req.getParameter("email").toUpperCase());
			try{
				usu.setPassword(req.getParameter("cpf"));
			}catch(NumberFormatException e){
				try {
					c.rollback();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				aprova ++;
				req.setAttribute("erro", "O cpf deve conter apenas números");
			}
			cli.setUsuario(usu);
			
			Endereco end = new Endereco();
			end.setBairro(req.getParameter("bairro").toUpperCase());
			end.setCep(Integer.parseInt(req.getParameter("cep")));
			end.setCidade(req.getParameter("cidade").toUpperCase());
			end.setComplemento(req.getParameter("complemento").toUpperCase());
			end.setLogradouro(req.getParameter("logradouro").toUpperCase());
			
			try {
				end.setNumero(Integer.parseInt(req.getParameter("numero")));
			} catch (NumberFormatException e) {
				// TODO: handle exception
				aprova ++;  
				try {
					c.rollback();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				req.setAttribute("erro", "O número do endereço deve conter apenas números");
			}
			end.setUf(req.getParameter("uf").toUpperCase());
			cli.setEndereco(end);
			
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
			
			cli.setTelefone(tels);
			req.setAttribute("cliente", cli);
			if(aprova == 0){
				ClienteBO.cadastrar(cli, c);
				req.setAttribute("msg", "Cliente cadastrado com sucesso");
				c.commit();
			}
			
			c.setAutoCommit(true);
		}catch(Exception e){
			try {
				c.rollback();
				e.printStackTrace();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}

}
