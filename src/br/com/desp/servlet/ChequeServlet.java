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
import br.com.desp.bo.ChequeBO;
import br.com.desp.conexao.ConexaoFactory;
import br.com.desp.util.DataUtil;

@WebServlet("/cheque")
public class ChequeServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String acao = req.getParameter("acao");
		String retorno = "";
		
		switch (acao) {
		case "listarAberto":
			listarAberto(req);
			retorno = "listarCheque.jsp";
			break;
		case "listar":
			listar(req);
			retorno = "listarCheque.jsp";
			break;
		case "depositar":
			depositar(req);
			listarAberto(req);
			retorno = "listarCheque.jsp";
			break;
		case "pesquisar":
			pesquisar(req);
			retorno = "listarCheque.jsp";
			break;
		default:
			retorno = "home.jsp";
			break;
		}
		req.getRequestDispatcher(retorno).forward(req, resp);
	}

	private void pesquisar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			int cdFilial = Integer.parseInt(req.getParameter("cdFilial"));
			List<Cheque> cheques = ChequeBO.listar(cdFilial, c);
			List<Cheque> che = new ArrayList<Cheque>();
			
			int cont = 0;
			String strEmitente = req.getParameter("emitente");
			String strNumero = req.getParameter("num");
			int intNumero = 0;
			if(!strNumero.equals("")){
				intNumero = Integer.parseInt(strNumero);
			}
			int intStatus = 5;
			String strStatus = req.getParameter("status");
			if(!strStatus.equals("Selecione")){
				intStatus = Integer.parseInt(strStatus);
			}
			for(Cheque cheque : cheques){
				if(cheque.getEmitente().equalsIgnoreCase(req.getParameter("emitente"))){
					cont ++; 
					che.add(cheque);
				}
				if(cheque.getNumero() == intNumero){
					cont ++;
					che.add(cheque);
				}
				if(cheque.getStatus() == intStatus){
					cont ++;
					che.add(cheque);
				}
			}
			
			String dtInicio = req.getParameter("data1");
			String dtFim = req.getParameter("data2");
			int cdfilial = Integer.parseInt(req.getParameter("cdFilial"));
			if(!dtInicio.equals("")){
				cont = 0;
				Calendar data1 = DataUtil.converter(DataUtil.arrumarData(dtInicio));
				Calendar data2 = Calendar.getInstance();
				if(dtFim.equals("")){
					data2 = Calendar.getInstance();
				}else{
					data2 = DataUtil.converter(DataUtil.arrumarData(dtFim));
				}
				che = ChequeBO.pesqData(data1, data2, cdFilial, c);
				for(Cheque cc : che){
					cont ++;
				}
			}
			
			req.setAttribute("cheques", che);
			
			if(cont == 0){
				req.setAttribute("msg", "Não foi encontrado nenhum cheque com os dados pesquisados.");
			}else{
				req.setAttribute("msg", "Lista de cheques filtrada com sucesso!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			req.setAttribute("erro", e.getMessage());
			e.printStackTrace();
		}
		
	}

	private void depositar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			int codigo = Integer.parseInt(req.getParameter("codigo"));
			
			ChequeBO.depositar(codigo, c);
			
			req.setAttribute("msg", "Status de cheque alterado com sucesso");
			
			c.commit();
			c.setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			req.setAttribute("erro", e.getMessage());
			e.printStackTrace();
			try {
				c.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}

	private void listar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			int cdFilial = Integer.parseInt(req.getParameter("cdFilial"));
			List<Cheque> cheques = new ArrayList<Cheque>();
			cheques = ChequeBO.listar(cdFilial, c);
			req.setAttribute("cheques", cheques);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}
		
	}

	private void listarAberto(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			int cdFilial = Integer.parseInt(req.getParameter("cdFilial"));
			List<Cheque> cheques = new ArrayList<Cheque>();
			cheques = ChequeBO.listarPendentes(cdFilial, c);
			req.setAttribute("cheques", cheques);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}
		
	}

}
