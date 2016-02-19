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
import javax.servlet.http.HttpSession;

import br.com.desp.beans.Cheque;
import br.com.desp.beans.Funcionario;
import br.com.desp.beans.Pagamento;
import br.com.desp.beans.Pessoa;
import br.com.desp.beans.TipoOrdemServico;
import br.com.desp.beans.TipoVeiculo;
import br.com.desp.beans.Usuario;
import br.com.desp.bo.ChequeBO;
import br.com.desp.bo.FuncionarioBO;
import br.com.desp.bo.PagamentoBO;
import br.com.desp.bo.PessoaBO;
import br.com.desp.bo.TipoOrdemBO;
import br.com.desp.bo.TipoVeiculoBO;
import br.com.desp.bo.UsuarioBO;
import br.com.desp.conexao.ConexaoFactory;
import br.com.desp.util.DataUtil;
import br.com.desp.util.EmailUtil;


@WebServlet ("/usuario")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String acao = req.getParameter("acao");
		String retorno = "";
		resp.setContentType("text/html;charset=UTF-8");
		switch (acao) {
		case "logout":
			logout(req);
			retorno = "index.jsp";
			break;
		case "carregarHome":
			carregarHome(req);
			retorno = "home.jsp";
			break;
		case "erro":
			req.setAttribute("erro", "Necessário login válido!");
			retorno = "index.jsp";
			break;
		}
		//ENCAMINHAR PARA A PÁGINA DE LOGIN
		req.getRequestDispatcher(retorno).forward(req, resp);
	}
	
	private void carregarHome(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			List<Pagamento> pags = new ArrayList<Pagamento>();
			List<TipoOrdemServico> tiposOrdem = new ArrayList<TipoOrdemServico>();
			List<TipoVeiculo> tiposVeiculos = new ArrayList<TipoVeiculo>();
			List<Cheque> cheques = new ArrayList<Cheque>();
			
			pags = PagamentoBO.listarPagAberto(c);
			tiposOrdem = TipoOrdemBO.listar(c);
			tiposVeiculos = TipoVeiculoBO.listar(c);
			cheques = ChequeBO.listarPendentes(c);
			
			int qtnCheques = 0;
			
			for(Cheque che: cheques){
				
					
				if(DataUtil.CalendarString(che.getDtDeposito()).equals(DataUtil.CalendarString(Calendar.getInstance()))){
					qtnCheques ++;
				}
						
					
			}
			
			req.setAttribute("pagAberto", pags);
			req.setAttribute("tpVeiculo", tiposVeiculos);
			req.setAttribute("tpOrdem", tiposOrdem);
			req.setAttribute("qtnCheques", qtnCheques);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private void logout(HttpServletRequest req) {	
		
		// TODO Auto-generated method stub
				//RECUPERANDO A SESSAO
				HttpSession session = req.getSession();
				//DESTRUINDO A SESSAO
				session.invalidate();
				
				
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String acao = req.getParameter("acao");
		String retorno = "";
		switch (acao) {
		case "logar":
			retorno = logar(req, resp);
			break;
		case "alterarSenha":
			alterarSenha(req);
			carregarHome(req);
			retorno = "home.jsp";
			break;
		}
		//ENCAMINHAR PARA A PÁGINA DA STRING RETORNO
		req.getRequestDispatcher(retorno).forward(req, resp);
	}
	
	private void alterarSenha(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Connection c = null;
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			Usuario usu = new Usuario();
			usu.setEmail(req.getParameter("email"));
			usu.setPassword(req.getParameter("senha"));
			String confSenha = req.getParameter("confSenha");
			
			System.out.println(usu.getPassword());
			System.out.println(confSenha);
			
			Pessoa pes = PessoaBO.pesqEmail(usu.getEmail(), c);
			if(!pes.getUsuario().getPassword().equals(req.getParameter("antigaSenha"))){
				req.setAttribute("erro", "A senha antiga esta incorreta, você precisa saber a senha"
						+ " antiga para poder alterá-la!");
			}else if(!usu.getPassword().equals(confSenha)){
				req.setAttribute("erro", "A confirmação de senha não confere!");
			}else{
				UsuarioBO.alterarSenha(usu, c);
				req.setAttribute("msg", "Sua senha foi alterada com sucesso!");
			}
				
			
			
			c.commit();
			c.setAutoCommit(true);
		}catch(Exception e){
			try {
				c.rollback();
				req.setAttribute("erro", e.getMessage());
				e.printStackTrace();
			} catch (Exception e2) {
				// TODO: handle exception
				req.setAttribute("erro", e2.getMessage());
				e2.printStackTrace();
			}
		}
		
	}

	private String logar(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		Connection c = null;
		String retorno = "";
		try{
			
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			
			String email = req.getParameter("email").toUpperCase();
			String senha = req.getParameter("senha");
			String remember = req.getParameter("remember");
			Usuario usu = new Usuario();
			usu.setEmail(email);
			usu.setPassword(senha);
			Funcionario fun = FuncionarioBO.pesqEmail(usu, c);
			Calendar dtHoje = Calendar.getInstance();
			
			int ver = PessoaBO.verEmail(email, c);
			HttpSession sessao = req.getSession();
			sessao.setAttribute("dtHoje", dtHoje);
			sessao.setMaxInactiveInterval(60);
			if(ver == 0){
				req.setAttribute("erro", "Este usuário não existe!");
				retorno = "index.jsp";
			}else if(fun.getUsuario().getEmail().equals(usu.getEmail()) && fun.getUsuario().getPassword().equals(usu.getPassword())){
				fun = UsuarioBO.logarFun(usu, c);
				sessao.setAttribute("user", fun);
				retorno = "home.jsp";
			}else if(!fun.getUsuario().getPassword().equals(usu.getPassword()) && remember == null){
				req.setAttribute("erro", "Senha inválida!");
				retorno = "index.jsp";
			}else if(remember.equals("Remember Me")){
				String LogEmail = req.getParameter("email");
				Usuario usuario = new Usuario();
				usuario.setEmail(LogEmail);
				Funcionario func = FuncionarioBO.pesqEmail(usuario, c);
				EmailUtil.rememberMe(func);
				req.setAttribute("msg", "Sua senha foi envia para seu e-mail!");
				retorno = "index.jsp";
			}else{
				req.setAttribute("erro", "Ops, Verifique seus dados.");
			}
			req.getSession().setMaxInactiveInterval(-1); // nunca expira
			carregarHome(req);
			
			
		}catch(Exception e){
			try {
				c.rollback();
				req.setAttribute("erro", e.getMessage());
			} catch (Exception e2) {
				// TODO: handle exception
				req.setAttribute("erro", e.getMessage());
			}
		}
		return retorno;
	}

	
}
