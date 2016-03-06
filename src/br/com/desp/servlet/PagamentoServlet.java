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

import br.com.desp.beans.Cheque;
import br.com.desp.beans.Pagamento;
import br.com.desp.beans.TipoOrdemServico;
import br.com.desp.beans.TipoVeiculo;
import br.com.desp.bo.ChequeBO;
import br.com.desp.bo.PagamentoBO;
import br.com.desp.bo.TipoOrdemBO;
import br.com.desp.bo.TipoVeiculoBO;
import br.com.desp.conexao.ConexaoFactory;
import br.com.desp.util.DataUtil;

@WebServlet ("/pagamento")
public class PagamentoServlet extends HttpServlet {
	
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
		case "aprovarPag":
			retorno = "home.jsp";
			aprovarPag(req);
			carregarHome(req);
			break;
		case "aprovarTodos":
			retorno = "home.jsp";
			aprovarTodos(req);
			carregarHome(req);
			break;
		default:
			retorno = "home.jsp";
			break;
		}
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
			
			int cdFilial = Integer.parseInt(req.getParameter("cdFilail"));
			
			pags = PagamentoBO.listarPagAberto(c);
			tiposOrdem = TipoOrdemBO.listar(c);
			tiposVeiculos = TipoVeiculoBO.listar(c);
			cheques = ChequeBO.listarPendentes(cdFilial, c);
			
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
		
	

	private void aprovarTodos(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			PagamentoBO.aprovarTodos(c);
			
			req.setAttribute("msg", "Todos os pagamentos foram aprovados");
			
			c.commit();
			c.setAutoCommit(true);
			
		} catch (Exception e) {
			// TODO: handle exception
			req.setAttribute("erro", e.getMessage());
			e.printStackTrace();
		}
		
	}

	private void aprovarPag(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
Connection c = null;
		
		try {
			
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			int cdPagamento = Integer.parseInt(req.getParameter("cdPagamento"));
			
			PagamentoBO.aprovar(cdPagamento, c);
			req.setAttribute("msg", "Pagamento aprovado com sucesso!");
			
			c.commit();
			c.setAutoCommit(true);
			
		} catch (Exception e) {
			// TODO: handle exception
			req.setAttribute("erro", e.getMessage());
			e.printStackTrace();
		}
		
	}

}
