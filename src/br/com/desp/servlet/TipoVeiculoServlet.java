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

@WebServlet("/tipoVeiculo")
public class TipoVeiculoServlet extends HttpServlet {
	
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
			retorno = "listarTipoVeiculo.jsp";
			break;
		}
		req.getRequestDispatcher(retorno).forward(req, resp);
	}
	
	private void listar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			List<TipoVeiculo> tiposVeiculo = TipoVeiculoBO.listar(c);
			
			req.setAttribute("tiposVeiculo", tiposVeiculo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private void carregar(HttpServletRequest req) {
		// TODO Auto-generated method stub
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
			String tipoVeiculo = req.getParameter("descricao").toUpperCase();
			
			TipoVeiculo tv = new TipoVeiculo();
			tv.setDescricao(tipoVeiculo);
			
			TipoVeiculoBO.cadastrar(tv, c);
			req.setAttribute("msg", "Tipo de veiculo cadastrado com sucesso");
			
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
