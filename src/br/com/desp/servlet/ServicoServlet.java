package br.com.desp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.desp.beans.Pagamento;
import br.com.desp.beans.Servico;
import br.com.desp.beans.TipoOrdemServico;
import br.com.desp.beans.TipoVeiculo;
import br.com.desp.bo.PagamentoBO;
import br.com.desp.bo.ServicoBO;
import br.com.desp.bo.TipoOrdemBO;
import br.com.desp.bo.TipoVeiculoBO;
import br.com.desp.conexao.ConexaoFactory;
import br.com.desp.util.NumeroUtil;

@WebServlet ("/servico")
public class ServicoServlet extends HttpServlet {

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
			carregarHome(req);
			retorno = "home.jsp";
			break;
		case "editar":
			editar(req);
			listar(req);
			retorno = "listarServicos.jsp";
			break;
		}
		
		req.getRequestDispatcher(retorno).forward(req, resp);
	}

	private void editar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			int codigo = Integer.parseInt(req.getParameter("codigo"));
			String descricao = req.getParameter("descricao");
			double hono = NumeroUtil.strDouble(req.getParameter("hono"));
			double taxa = NumeroUtil.strDouble(req.getParameter("taxa"));
			
			Servico ser = new Servico();
			ser.setCodigo(codigo);
			ser.setDescricao(descricao.toUpperCase());
			ser.setVlrHono(hono);
			ser.setVlrTaxa(taxa);
			ser.setVlrTotal(hono + taxa);
			
			ServicoBO.editar(ser, c);
			req.setAttribute("msg", "Serviço editado com sucesso!");
			
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
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String acao = req.getParameter("acao");
		String retorno = "";
		
		switch (acao) {
		case "cadastrar":
			cadastrar(req);
			carregarHome(req);
			retorno = "home.jsp";
			break;
		case "listar":
			listar(req);
			carregarHome(req);
			retorno = "listarServicos.jsp";
			break;
		case "carregarSer":
			carregarSer(req);
			retorno = "editarServico.jsp";
			break;
		}
		req.getRequestDispatcher(retorno).forward(req, resp);
	}

	private void carregarSer(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			int codigo = Integer.parseInt(req.getParameter("cdSer"));
			Servico ser = ServicoBO.pesqCodigo(codigo, c);
			
			req.setAttribute("ser", ser);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	private void listar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Connection c = null;
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			List<Servico> servicos = new ArrayList<Servico>();
			servicos = ServicoBO.listar(c);
			
			req.setAttribute("servicos", servicos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	private void carregarHome(HttpServletRequest req) {
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
			
			int aprovar = 0;
			
			String descricao = req.getParameter("descricao").toUpperCase();
			String strTaxa = req.getParameter("vlTaxa");
			String strHono = req.getParameter("vlHono");
			
			Servico ser = new Servico();
			ser.setVlrHono(0);
			ser.setVlrTaxa(0);
			
			if(!strTaxa.equals("")){
				ser.setVlrTaxa(NumeroUtil.strDouble(strTaxa));
			}
			if(!strHono.equals("")){
				ser.setVlrHono(NumeroUtil.strDouble(strHono));
			}
			ser.setVlrTotal(ser.getVlrHono()+ser.getVlrTaxa());
			ser.setDescricao(descricao);
			
			List<TipoOrdemServico> tpOrdem = new ArrayList<TipoOrdemServico>();
			List<TipoVeiculo> tpVeiculo = new ArrayList<TipoVeiculo>();
			
			String[] cdVeiculos = req.getParameterValues("tpVeiculo");
			String[] cdOrdem = req.getParameterValues("tpOrdem");
			
			for(int l = 0;l<cdOrdem.length; l++){
				TipoOrdemServico to = new TipoOrdemServico();
				if(!cdOrdem[l].equals("")){
					to.setCodigo(Integer.parseInt(cdOrdem[l]));
					tpOrdem.add(to);
				}
			}
			for(int l = 0;l < cdVeiculos.length; l++){
				TipoVeiculo tv = new TipoVeiculo();
				if(!cdVeiculos[l].equals("")){
					tv.setCodigo(Integer.parseInt(cdVeiculos[l]));
					tpVeiculo.add(tv);
				}
			}
			ser.setTipoOrdemServico(tpOrdem);
			ser.setTipoVeiculo(tpVeiculo);
			
			if(aprovar == 0){
				ServicoBO.cadastrar(ser, c);
				req.setAttribute("msg", "Serviço cadastrado com sucesso!");
			}
			
			c.commit();
			c.setAutoCommit(true);
		}catch(Exception e){
			try{
				c.rollback();
			}catch(Exception e2){
				req.setAttribute("erro", e.getMessage());
				e2.printStackTrace();
			}
			req.setAttribute("erro", e.getMessage());
			e.printStackTrace();
		}
	}
	
}
