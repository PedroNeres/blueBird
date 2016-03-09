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

import br.com.desp.beans.Pagamento;
import br.com.desp.beans.TipoOrdemServico;
import br.com.desp.beans.TipoVeiculo;
import br.com.desp.bo.PagamentoBO;
import br.com.desp.bo.TipoOrdemBO;
import br.com.desp.bo.TipoVeiculoBO;
import br.com.desp.conexao.ConexaoFactory;

@WebServlet("/tipoOs")
public class TipoOsServlet extends HttpServlet{

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
		case "cadastrar":
			cadastrar(req);
			carregar(req);
			retorno = "home.jsp";
			break;
		case "listar":
			listar(req);
			retorno = "listaTipoOs.jsp";
			break;
		}
		req.getRequestDispatcher(retorno).forward(req, resp);
	
	}
	
	private void listar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			List<TipoOrdemServico> tiposOs = TipoOrdemBO.listar(c);
			req.setAttribute("tipoOs", tiposOs);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	private void carregar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();

			List<TipoOrdemServico> tiposOrdem = new ArrayList<TipoOrdemServico>();
			List<TipoVeiculo> tiposVeiculos = new ArrayList<TipoVeiculo>();
			
			tiposOrdem = TipoOrdemBO.listar(c);
			tiposVeiculos = TipoVeiculoBO.listar(c);

			req.setAttribute("tpVeiculo", tiposVeiculos);
			req.setAttribute("tpOrdem", tiposOrdem);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private void cadastrar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			String descricao = req.getParameter("descricao").toUpperCase();
			TipoOrdemServico tpos = new TipoOrdemServico();
			tpos.setDescricao(descricao);
			TipoOrdemBO.cadastrar(tpos, c);
			req.setAttribute("msg", "Tipo de Ordem de Serviço cadastrado com sucesso!");
			
			c.commit();
			c.setAutoCommit(true);
		}catch(Exception e){
			try{
				c.rollback();
				req.setAttribute("erro", e.getMessage());
			}catch(Exception e2){
				req.setAttribute("erro", e2.getMessage());
			}
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
			carregar(req);
			retorno = "home.jsp";
			break;

		}
		req.getRequestDispatcher(retorno).forward(req, resp);
		
	}
	
}
