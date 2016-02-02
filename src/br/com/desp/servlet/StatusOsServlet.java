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
import br.com.desp.beans.StatusOs;
import br.com.desp.beans.TipoOrdemServico;
import br.com.desp.beans.TipoVeiculo;
import br.com.desp.bo.PagamentoBO;
import br.com.desp.bo.StatusOsBO;
import br.com.desp.bo.TipoOrdemBO;
import br.com.desp.bo.TipoVeiculoBO;
import br.com.desp.conexao.ConexaoFactory;

@WebServlet("/status")
public class StatusOsServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	private void cadastrar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			StatusOs sta = new StatusOs();
			sta.setDescricao(req.getParameter("descricao").toUpperCase());
			
			StatusOsBO.cadastrar(sta, c);
			req.setAttribute("msg", "Novo tipo de status cadastrado com sucesso!");
			
			c.commit();
			c.setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				c.rollback();
				
			} catch (Exception e2) {
				// TODO: handle exception
				req.setAttribute("erro", e2.getMessage());
			}
			req.setAttribute("erro", e.getMessage());
		}
		
	}
}
