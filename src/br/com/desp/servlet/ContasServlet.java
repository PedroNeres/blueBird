package br.com.desp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.desp.beans.Contas;
import br.com.desp.beans.Filial;
import br.com.desp.beans.Saida;
import br.com.desp.bo.ContasBO;
import br.com.desp.bo.FilialBO;
import br.com.desp.bo.SaidaBO;
import br.com.desp.conexao.ConexaoFactory;
import br.com.desp.util.DataUtil;
import br.com.desp.util.NumeroUtil;

@WebServlet ("/contas")
public class ContasServlet extends HttpServlet{

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
		case "listarVencendo":
			listarVencendo(req);
			retorno = "listaContas.jsp";
			break;
		case "listar":
			listar(req);
			retorno = "listaContas.jsp";
			break;
		case "pesquisar":
			pesquisar(req);
			retorno = "listaContas.jsp";
			break;
		case "pagar":
			pagar(req);
			listarVencendo(req);
			retorno = "listaContas.jsp";
			break;
		default:
			retorno = "home.jsp";
			break;
		}
		req.getRequestDispatcher(retorno).forward(req, resp);
	}

	private void pagar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			int codigo = Integer.parseInt(req.getParameter("codigo"));
			int cdFilial = Integer.parseInt(req.getParameter("cdFilial"));
			Contas cont = ContasBO.pesqCodigo(codigo, cdFilial, c);
			cont.setStatus(0);
			cont.setQtParcelas(cont.getQtParcelas()-1);
			if(cont.getQtParcelas() == 0){
				cont.setStatus(2);
			}else{
				String dtVenc = DataUtil.CalendarString(cont.getProxVenc());
				int dia = Integer.parseInt(dtVenc.substring(0, 2));
				int mes = Integer.parseInt(dtVenc.substring(3,5));	
				int ano = Integer.parseInt(dtVenc.substring(6,10));
				if(mes == 12){
					ano ++;
					mes = 01;
				}
				mes ++;
				if(dia < 10){
					dtVenc = "0" + dia + "/" + mes + "/" + ano;
				}
				if(mes < 10){
					dtVenc = dia + "/0" + mes + "/" + ano;
				}
				if(dia < 10 && mes < 10){
					dtVenc = "0" + dia + "/0" + mes + "/" + ano;
				}
				cont.setProxVenc(DataUtil.converter(dtVenc));
			}
			
			Saida sai = new Saida();
			sai.setCodigo(0);
			sai.setDescricao("Pagamento da conta de " + cont.getDescricao());
			sai.setValor(cont.getValor());
			sai.setFilial(cont.getFilial());
			sai.setDtSaida(Calendar.getInstance());
			
			SaidaBO.cadastrar(sai, c);
			
			ContasBO.pagar(cont, c);
			req.setAttribute("msg", "Conta paga com sucesso");
			
			c.commit();
			c.setAutoCommit(true);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				req.setAttribute("erro", e1.getMessage());
			}
		}
		
	}

	private void pesquisar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			String descricao = req.getParameter("descricao");
			String strStatus = req.getParameter("status");
			List<Contas> contas = new ArrayList<Contas>();
			int cdFilial = Integer.parseInt(req.getParameter("cdFilial"));
			Contas cont = null;
			int ver = 0;
			if(descricao.length() >= 3){
				cont = ContasBO.pesqDescricao(descricao, cdFilial, c);
				contas.add(cont);
				ver = 1;
			}else if(strStatus.equals("0") || strStatus.equals("1")){
				int status = Integer.parseInt(strStatus);
				contas = ContasBO.pesqStatus(status, cdFilial, c);
				ver = 1;
			}else{
				req.setAttribute("msg", "É necessário preencher ao menos 1 ítem para pesquisar!");
			}
			
			if(ver == 1){
				req.setAttribute("contas", contas);
				req.setAttribute("msg", "Lista de contas filtrada com sucesso!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			req.setAttribute("erro", e.getMessage());
			e.printStackTrace();
		}
		
	}

	private void listar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			int cdFilial = Integer.parseInt(req.getParameter("cdFilial"));
			List<Contas> contas = ContasBO.listar(cdFilial, c);
			req.setAttribute("contas", contas);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	private void listarVencendo(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			int cdFilial = Integer.parseInt(req.getParameter("cdFilial"));
			List<Contas> contas = ContasBO.pesqStatus(1, cdFilial, c);
			req.setAttribute("contas", contas);
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
			listarVencendo(req);
			retorno = "listaContas.jsp";
			break;

		default:
			retorno = "home.jsp";
			break;
		}
		req.getRequestDispatcher(retorno).forward(req, resp);
	}

	private void cadastrar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			String descricao = req.getParameter("descricao");
			int diaVenc = Integer.parseInt(req.getParameter("diaVenc"));
			double valor = NumeroUtil.strDouble(req.getParameter("valor"));
			int qtnParc = Integer.parseInt(req.getParameter("qtnParc"));
			String observacoes = req.getParameter("observacao");
			Filial fil = FilialBO.pesqCodigo(Integer.parseInt(req.getParameter("cdFilial")), c);
			int status = 0;
			
			Contas cont = new Contas();
			
			cont.setDescricao(descricao.toUpperCase());
			cont.setDiaVencimento(diaVenc);
			cont.setValor(valor);
			cont.setQtParcelas(qtnParc);
			cont.setObservacoes(observacoes.toUpperCase());
			cont.setFilial(fil);
			cont.setStatus(status);
			
			String dtHoje = DataUtil.CalendarString(Calendar.getInstance());
			
			int diaHoje = Integer.parseInt(dtHoje.substring(0,2));
			int mesHoje = Integer.parseInt(dtHoje.substring(3,5));
			int anoHoje = Integer.parseInt(dtHoje.substring(6,10));
			
			if(mesHoje == 12){
				anoHoje ++;
			}
			
			String dtVenc = "";
			
			if(diaHoje < diaVenc){
				if(diaVenc < 10){
					dtVenc = "0" + diaVenc + "/" + mesHoje + "/" + anoHoje;
				}
				if(mesHoje < 10){
					dtVenc = diaVenc + "/0" + mesHoje + "/" + anoHoje;
				}
				if(diaVenc < 10 && mesHoje < 10){
					dtVenc = "0" + diaVenc + "/0" + mesHoje + "/" + anoHoje;
				}
			}else{
				if(diaVenc < 10){
					dtVenc = "0" + diaVenc + "/" + (mesHoje+1) + "/" + anoHoje;
				}
				if(mesHoje < 10){
					dtVenc = diaVenc + "/0" + (mesHoje+1) + "/" + anoHoje;
				}
				if(diaVenc < 10 && mesHoje < 10){
					dtVenc = "0" + diaVenc + "/0" + (mesHoje+1) + "/" + anoHoje;
				}
			}
			
			cont.setProxVenc(DataUtil.converter(dtVenc));
			
			ContasBO.cadastrar(cont, c);
			req.setAttribute("msg", "Nova conta cadastrada com sucesso!");
			
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
	
}
