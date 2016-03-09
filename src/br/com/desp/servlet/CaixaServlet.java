package br.com.desp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.desp.beans.Entrada;
import br.com.desp.beans.Filial;
import br.com.desp.beans.Saida;
import br.com.desp.bo.EntradaBO;
import br.com.desp.bo.FilialBO;
import br.com.desp.bo.SaidaBO;
import br.com.desp.conexao.ConexaoFactory;
import br.com.desp.util.DataUtil;
import br.com.desp.util.NumeroUtil;

@WebServlet("/caixa")
public class CaixaServlet extends HttpServlet{

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
		case "listarFilial":
			listarFilial(req);
			retorno = "caixa.jsp";
			break;
		case "listarRelatorio":
			listarFilial(req);
			retorno = "rel.jsp";
			break;
		case "pesqData":
			pesqData(req);
			retorno = "rel.jsp";
			break;
		case "imprimir":
			pesqData(req);
			retorno = "impRel.jsp";
			break;
		default:
			retorno = "home.jsp";
			break;
		}
		req.getRequestDispatcher(retorno).forward(req, resp);
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String acao = req.getParameter("acao");
		String retorno = "";
		
		switch (acao) {
		case "deletarEntrada":
			deletarEntrada(req);
			listarFilial(req);
			retorno = "caixa.jsp";
			break;
		case "deletarSaida":
			deletarSaida(req);
			listarFilial(req);
			retorno = "caixa.jsp";
			break;
		case "cadEntrada":
			cadEntrada(req);
			listarFilial(req);
			retorno = "caixa.jsp";
			break;
		case "cadSaida":
			cadSaida(req);
			listarFilial(req);
			retorno = "caixa.jsp";
			break;
		case "editarEntrada":
			editarEntrada(req);
			listarFilial(req);
			retorno = "caixa.jsp";
			break;
		case "editarSaida":
			editarSaida(req);
			listarFilial(req);
			retorno = "caixa.jsp";
			break;
		case "carregarEntrada":
			retorno = carregarEntrada(req);
			break;
		case "carregarSaida":
			retorno = carregarSaida(req);
			break;
		default:
			retorno = "home.jsp";
			break;
		}
		req.getRequestDispatcher(retorno).forward(req, resp);
	}

	
	private void editarSaida(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			Saida sai = new Saida();
			sai.setCodigo(Integer.parseInt(req.getParameter("codigo")));
			sai.setDescricao(req.getParameter("descricao"));
			sai.setFilial(FilialBO.pesqCodigo(Integer.parseInt(req.getParameter("cdFilial")), c));
			sai.setValor(NumeroUtil.strDouble(req.getParameter("valor")));
			
			SaidaBO.editar(sai, c);
			req.setAttribute("msg", "Saida editada com sucesso");
			c.commit();
			c.setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}



	private void cadSaida(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			Saida sai = new Saida();
			sai.setCodigo(0);
			sai.setDescricao(req.getParameter("descricao"));
			sai.setDtSaida(Calendar.getInstance());
			sai.setFilial(FilialBO.pesqCodigo(Integer.parseInt(req.getParameter("cdFilial")), c));
			sai.setValor(NumeroUtil.strDouble(req.getParameter("valor")));
			
			SaidaBO.cadastrar(sai, c);
			req.setAttribute("msg", "Nova saida cadastrada com sucesso!");
			
			c.commit();
			c.setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}
		
	}
	
	private void deletarSaida(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			String senha = req.getParameter("senha");
			String confSenha = req.getParameter("confSenha");
			
			int codigo = Integer.parseInt(req.getParameter("codigo"));
			
			if(senha.equals(confSenha)){
				SaidaBO.deletar(codigo, c);
				req.setAttribute("msg", "Saida deletada com sucesso!");
			}else{
				req.setAttribute("erro", "A senha esta incorreta");
			}
			
			c.commit();
			c.setAutoCommit(true);
			
		} catch (Exception e) {
			// TODO: handle exception
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			req.setAttribute("erro", e.getMessage());
			e.printStackTrace();
		}
	}

	private void pesqData(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			List<Filial> filiais = FilialBO.listar(c);
			
			req.setAttribute("filiais", filiais);
			
			int cdFilial = Integer.parseInt(req.getParameter("cdFilial"));
			int filial = Integer.parseInt(req.getParameter("filial"));
			
			String dataInicio = req.getParameter("data1");
			String dataFim = req.getParameter("data2");
			
			req.setAttribute("data1", dataInicio);
			req.setAttribute("data2", dataFim);
			req.setAttribute("filial", filial);
			
			Calendar data1 = DataUtil.converter(DataUtil.arrumarData(req.getParameter("data1")));
			Calendar data2 = DataUtil.converter(DataUtil.arrumarData(req.getParameter("data2")));
			if(filial != 0){
				cdFilial = filial;
			}
			List<Entrada> entradas = EntradaBO.pesqData(cdFilial, data1, data2, c);
			List<Saida> saidas = SaidaBO.pesqData(cdFilial, data1, data2, c);
			req.setAttribute("entradas", entradas);
			req.setAttribute("saidas", saidas);
			
			double totalEntradas = 0;
			double totalSaidas = 0;
			
			for(Entrada en: entradas){
				totalEntradas+= en.getValor();
			}
			for(Saida sa: saidas){
				totalSaidas+= sa.getValor();
			}
			double saldo = totalEntradas - totalSaidas;
			
			req.setAttribute("totalEntradas", totalEntradas);
			req.setAttribute("totalSaidas", totalSaidas);
			req.setAttribute("saldo", saldo);
			
			req.setAttribute("msg", "Relatório de " + DataUtil.CalendarString(data1) + " á " + DataUtil.CalendarString(data2) + " da filial " + FilialBO.pesqCodigo(cdFilial, c).getNome());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}
	}

	private void listarFilial(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			int cdFilial = Integer.parseInt(req.getParameter("cdFilial"));
			Calendar data1 = Calendar.getInstance();
			Calendar data2 = Calendar.getInstance();
			List<Entrada> entradas = EntradaBO.pesqData(cdFilial, data1, data2, c);
			List<Saida> saidas = SaidaBO.pesqData(cdFilial, data1, data2, c);
			double totalEntradas = 0;
			double totalSaidas = 0;
			
			
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String diaInicio = s.format(data1.getTime());
			String diaFim = s.format(data2.getTime());
			
			req.setAttribute("data1", diaInicio);
			req.setAttribute("data2", diaFim);
			
			
			int filial = 0;
			req.setAttribute("filial", filial);
			
			for(Entrada en: entradas){
				totalEntradas+= en.getValor();
			}
			for(Saida sa: saidas){
				totalSaidas+= sa.getValor();
			}
			double saldo = totalEntradas - totalSaidas;
			
			List<Filial> filiais = FilialBO.listar(c);
			
			req.setAttribute("filiais", filiais);
			req.setAttribute("entradas", entradas);
			req.setAttribute("saidas", saidas);
			req.setAttribute("totalEntradas", totalEntradas);
			req.setAttribute("totalSaidas", totalSaidas);
			req.setAttribute("saldo", saldo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}
	}

	private void editarEntrada(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			Entrada ent = new Entrada();
			ent.setCodigo(Integer.parseInt(req.getParameter("codigo")));
			ent.setDescricao(req.getParameter("descricao").toUpperCase());
			ent.setDtEntrada(Calendar.getInstance());
			ent.setFilial(FilialBO.pesqCodigo(Integer.parseInt(req.getParameter("cdFilial")), c));
			ent.setValor(NumeroUtil.strDouble(req.getParameter("valor")));
			EntradaBO.editar(ent, c);
			req.setAttribute("msg", "Entrada editada com sucesso");
			
			c.commit();
			c.setAutoCommit(true);
			
		} catch (Exception e) {
			// TODO: handle exception
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}
		
	}

	private void cadEntrada(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			Entrada ent = new Entrada();
			ent.setCodigo(0);
			ent.setDescricao(req.getParameter("descricao"));
			ent.setDtEntrada(Calendar.getInstance());
			ent.setFilial(FilialBO.pesqCodigo(Integer.parseInt(req.getParameter("cdFilial")), c));
			ent.setValor(NumeroUtil.strDouble(req.getParameter("valor")));
			
			EntradaBO.cadastrar(ent, c);
			req.setAttribute("msg", "Nova entrada cadastrada com sucesso!");
			
			c.commit();
			c.setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}
		
	}
	
	private void deletarEntrada(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			String senha = req.getParameter("senha");
			String confSenha = req.getParameter("confSenha");
			
			int codigo = Integer.parseInt(req.getParameter("codigo"));
			
			if(senha.equals(confSenha)){
				EntradaBO.deletar(codigo, c);
				req.setAttribute("msg", "Entrada deletada com sucesso!");
			}else{
				req.setAttribute("erro", "A senha esta incorreta");
			}
			
			c.commit();
			c.setAutoCommit(true);
			
		} catch (Exception e) {
			// TODO: handle exception
			req.setAttribute("erro", e.getMessage());
			e.printStackTrace();
		}
		
	}
	

	private String carregarEntrada(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		String retorno = "";
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			int codigo = Integer.parseInt(req.getParameter("codigo"));
			
			String senha = req.getParameter("senha");
			String confSenha = req.getParameter("confSenha");
			
			
			if(senha.equals(confSenha)){
				Entrada ent = EntradaBO.pesqCodigo(codigo, c);
				req.setAttribute("ent", ent);
				retorno = "editarEntrada.jsp";
			}else{
				retorno = "caixa.jsp";
				listarFilial(req);
				req.setAttribute("erro", "A senha não confere");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retorno;
		
	}



	private String carregarSaida(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		String retorno = "";
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			int codigo = Integer.parseInt(req.getParameter("codigo"));
			
			String senha = req.getParameter("senha");
			String confSenha = req.getParameter("confSenha");
			
			
			if(senha.equals(confSenha)){
				Saida sai = SaidaBO.pesqCodigo(codigo, c);
				req.setAttribute("sai", sai);
				retorno = "editarSaida.jsp";
			}else{
				retorno = "caixa.jsp";
				listarFilial(req);
				req.setAttribute("erro", "A senha não confere");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retorno;
		
	}
	
	
}
