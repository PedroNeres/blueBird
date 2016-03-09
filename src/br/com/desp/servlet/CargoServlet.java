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

import br.com.desp.beans.Cargo;
import br.com.desp.beans.Pagamento;
import br.com.desp.beans.TipoOrdemServico;
import br.com.desp.beans.TipoVeiculo;
import br.com.desp.bo.CargoBO;
import br.com.desp.bo.PagamentoBO;
import br.com.desp.bo.TipoOrdemBO;
import br.com.desp.bo.TipoVeiculoBO;
import br.com.desp.conexao.ConexaoFactory;

@WebServlet("/cargo")
public class CargoServlet extends HttpServlet{

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
		case "listar":
			listar(req);
			retorno = "listaCargo.jsp";
			break;
		default:
			break;
		}
		req.getRequestDispatcher(retorno).forward(req, resp);
		
	}

	private void listar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			List<Cargo> cargos = CargoBO.listar(c);
			req.setAttribute("cargo", cargos);
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
			
			Cargo car = new Cargo();
			car.setDescricao(descricao);
			
			CargoBO.cadastrar(car, c);
			req.setAttribute("msg", "Novo cargo cadastrado com sucesso!");
			
			c.commit();
			c.setAutoCommit(true);
		}catch(Exception e){
			try{
				req.setAttribute("erro", e.getMessage());
				c.rollback();
			}catch(Exception e2){
				req.setAttribute("erro", e.getMessage());
			}
		}
		
	}
	
}
