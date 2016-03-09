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

import br.com.desp.beans.Mensagem;
import br.com.desp.beans.Usuario;
import br.com.desp.bo.MensagemBO;
import br.com.desp.bo.UsuarioBO;
import br.com.desp.conexao.ConexaoFactory;
import br.com.desp.util.EmailUtil;

@WebServlet("/mensagem")
public class MensagemServlet extends HttpServlet{

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
		
		switch (acao) {
		case "abrir":
			abrir(req);
			retorno = "mensagem.jsp";
			break;
		case "listarTodos":
			listarTodos(req);
			retorno = "listaMensagens.jsp";
			break;
		case "listaEnviados":
			listaEnviados(req);
			retorno = "listaMensagens.jsp";
			break;
		case "novaMensagem":
			novaMensagem(req);
			retorno = "novaMensagem.jsp";
			break;
		case "responder":
			responder(req);
			retorno = "novaMensagem.jsp";
			break;
		case "perguntar":
			perguntar(req);
			retorno = "novaMensagem.jsp";
			break;
		default:
			retorno = "home.jsp";
			break;
		}
		
		req.getRequestDispatcher(retorno).forward(req, resp);
		
	}

	private void perguntar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
try {
			
			String nomeDestinatario = "PEDRO NERES";
			String emailDestinatario = "CARVALHO.PEDRO.N@GMAIL.COM";
			String assunto = "";
			
			Mensagem men = new Mensagem();
			
			men.setAssunto(assunto);
			men.setNomeDestinatario(nomeDestinatario);
			men.setEmailDestinatario(emailDestinatario);
			
			req.setAttribute("men", men);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	private void responder(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		try {
			
			String nomeDestinatario = req.getParameter("nome");
			String emailDestinatario = req.getParameter("email");
			String assunto = "Responsta a " + req.getParameter("assunto");
			
			Mensagem men = new Mensagem();
			
			men.setAssunto(assunto);
			men.setNomeDestinatario(nomeDestinatario);
			men.setEmailDestinatario(emailDestinatario);
			
			req.setAttribute("men", men);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	private void novaMensagem(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			List<Usuario> emails = new ArrayList<Usuario>();
			emails = UsuarioBO.listar(c);
			req.setAttribute("emails", emails);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	private void listaEnviados(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			String email = req.getParameter("email");
			List<Mensagem> mensagens = MensagemBO.listarEnviadas(email, c);
			req.setAttribute("acao", "listarTodos");
			req.setAttribute("texto", "Recebidas");
			req.setAttribute("mensagem", mensagens);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	private void listarTodos(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			String email = req.getParameter("email");
			List<Mensagem> mensagens = MensagemBO.listarRecebidas(email, c);
			req.setAttribute("acao", "listaEnviados");
			req.setAttribute("texto", "Enviadas");
			req.setAttribute("mensagem", mensagens);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	private void abrir(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			int codigo = Integer.parseInt(req.getParameter("codigo"));
			
			MensagemBO.abrir(codigo, c);
			
			Mensagem men = MensagemBO.pesqCodigo(codigo, c);
			
			req.setAttribute("men", men);
			
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
		case "enviar":
			enviar(req);
			listarTodos(req);
			retorno = "listaMensagens.jsp";
			break;

		default:
			listarTodos(req);
			retorno = "listaMensagens.jsp";
			break;
		}
		req.getRequestDispatcher(retorno).forward(req, resp);
	}

	private void enviar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			String nomeRemetente = req.getParameter("nomeRemetente").toUpperCase();
			String emailRemetente = req.getParameter("emailRemetente").toUpperCase();
			String nomeDestinatario = req.getParameter("nomeDestinatario").toUpperCase();
			String emailDestinatario = req.getParameter("emailDestinatario").toUpperCase();
			String assunto = req.getParameter("assunto").toUpperCase();
			String mensagem = req.getParameter("mensagem").toUpperCase();
			
			Mensagem men = new Mensagem();
			men.setAssunto(assunto);
			men.setDtEnvio(Calendar.getInstance());
			men.setEmailDestinatario(emailDestinatario);
			men.setEmailRemetente(emailRemetente);
			men.setMensagem(mensagem);
			men.setStatus(1);
			men.setNomeRemetente(nomeRemetente);
			men.setNomeDestinatario(nomeDestinatario);
			
			MensagemBO.enviar(men, c);
			EmailUtil.EnviarMensagem(men);
			
			req.setAttribute("msg", "Sua mensagem foi enviada com sucesso");
			
			c.commit();
			c.setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			req.setAttribute("erro", e.getMessage());
			e.printStackTrace();
		}
	}
	
}
