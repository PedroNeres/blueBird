package br.com.desp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.desp.beans.Cheque;
import br.com.desp.beans.Cliente;
import br.com.desp.beans.Entrada;
import br.com.desp.beans.FormaPagamento;
import br.com.desp.beans.Funcionario;
import br.com.desp.beans.ItemServico;
import br.com.desp.beans.MudarStatus;
import br.com.desp.beans.OrdemServico;
import br.com.desp.beans.Pagamento;
import br.com.desp.beans.StatusOs;
import br.com.desp.beans.Veiculo;
import br.com.desp.bo.ChequeBO;
import br.com.desp.bo.ClienteBO;
import br.com.desp.bo.EntradaBO;
import br.com.desp.bo.FormaPagamentoBO;
import br.com.desp.bo.FuncionarioBO;
import br.com.desp.bo.OrdemServicoBO;
import br.com.desp.bo.PagamentoBO;
import br.com.desp.bo.ServicoBO;
import br.com.desp.bo.StatusOsBO;
import br.com.desp.bo.TipoOrdemBO;
import br.com.desp.bo.VeiculoBO;
import br.com.desp.conexao.ConexaoFactory;
import br.com.desp.util.DataUtil;
import br.com.desp.util.EmailUtil;
import br.com.desp.util.NumeroUtil;
import br.com.desp.util.StringUtil;

@WebServlet("/ordemServico")
public class OrdemServicoServlet extends HttpServlet {

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
		Connection c;
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			List<StatusOs> listStatus = StatusOsBO.listar(c);
			req.setAttribute("listStatus", listStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		switch (acao) {
		case "listar":
			listar(req);
			retorno = "listaOs.jsp";
			break;
		case "pesquisar":
			pesquisar(req);
			retorno = "listaOs.jsp";
			break;
		case "addOs":
			addOs(req);
			carregarAltOs(req);
			retorno = "alterarStatus.jsp";
			break;
		case "carregarAltOs":
			carregarAltOs(req);
			retorno = "alterarStatus.jsp";
			break;
		case "removeOs":
			removeOs(req);
			carregarAltOs(req);
			retorno = "alterarStatus.jsp";
			break;
		case "imprimir":
			imprimir(req);
			retorno = "imprimir.jsp";
			break;
		case "abrir":
			abrir(req);
			retorno = "ordemServico.jsp";
			break;
		case "addPagOs":
			addPagOs(req);
			retorno = "listaOs.jsp";
			break;
		default:
			retorno = "home.jsp";
			break;
		}
		req.getRequestDispatcher(retorno).forward(req, resp);
	}

	private void addPagOs(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			FormaPagamento forPagamento = FormaPagamentoBO.pesqCodigo
					(Integer.parseInt(req.getParameter("forPagamento")), c);
			String numero = req.getParameter("codPag");
			String observacoes = req.getParameter("desPag");
			
			OrdemServico ordemServico = OrdemServicoBO.pesqNumeroOs
					(Integer.parseInt(req.getParameter("numOs")), c);
			double vlPagao = NumeroUtil.strDouble(req.getParameter("vlrPag"));
			
			Pagamento pag = new Pagamento();
			pag.setForPagamento(forPagamento);
			pag.setNumero(numero);
			pag.setObservacoes(observacoes);
			pag.setOrdemServico(ordemServico);
			pag.setVlPagao(vlPagao);
			
			if(pag.getVlPagao() > (ordemServico.getTotal() - ordemServico.getVlrPago() + 1)){
				req.setAttribute("erro", "O valor pago não pode ser maior que o valor que falta");
			}else{
			
			PagamentoBO.cadastrar(pag, c);
			
			ordemServico.getPagamento().add(pag);
			ordemServico.setVlrPago(ordemServico.getVlrPago() + vlPagao);
			OrdemServicoBO.editar(ordemServico, c);
			
			Entrada ent = new Entrada();
			ent.setCodigo(0);
			ent.setDescricao("Pagamento adicionado da O.S nº" + ordemServico.getNumero());
			ent.setDtEntrada(ordemServico.getDtEntrada());
			ent.setValor(vlPagao);
			ent.setFilial(ordemServico.getAtendente().getFilial());
			EntradaBO.cadastrar(ent, c);
			
			req.setAttribute("msg", "O pagamento foi adicionado com sucesso");
			}
			c.commit();
			c.setAutoCommit(true);
			
		} catch (Exception e) {
			// TODO: handle exception
			try {
				c.rollback();
				req.setAttribute("erro", e.getMessage());
				e.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	private void abrir(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			int codigo = Integer.parseInt(req.getParameter("codigo"));
			
			OrdemServico os = OrdemServicoBO.pesqNumeroOs(codigo, c);
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
			String dataString = s.format(cal.getTime());
			dataString = DataUtil.DataExtenso(dataString);
			String valorStr = NumeroUtil.strExtenso(os.getTotal());
			req.setAttribute("valorStr", valorStr);
			req.setAttribute("strDtHoje", dataString);
			
			req.setAttribute("os", os);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	private void imprimir(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			int nrOs = Integer.parseInt(req.getParameter("numero"));
			
			OrdemServico os = OrdemServicoBO.pesqNumeroOs(nrOs, c);
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
			String dataString = s.format(cal.getTime());
			dataString = DataUtil.DataExtenso(dataString);
		
			String valorStr = NumeroUtil.strExtenso(os.getTotal());
			req.setAttribute("valorStr", valorStr);
			req.setAttribute("strDtHoje", dataString);
			
			req.setAttribute("os", os);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	private void removeOs(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			int numOS = Integer.parseInt(req.getParameter("numOS"));
			req.setAttribute("msg", "Ordem de serviço removido com sucesso!");
			for(OrdemServico os :listaOs){
				if(os.getNumero() == numOS){
					listaOs.remove(os);
				}
				
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	private void carregarAltOs(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			List<StatusOs> status = new ArrayList<StatusOs>();
			status = StatusOsBO.listar(c);
			int qtnAdd = 0;
			for(OrdemServico os:listaOs){
				qtnAdd++;
			}
			req.setAttribute("listaOs", listaOs);
			req.setAttribute("qtnAdd", qtnAdd);
			req.setAttribute("tpStatus", status);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	private static List<OrdemServico> listaOs = new ArrayList<OrdemServico>();
	private void addOs(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			int numOs = Integer.parseInt(req.getParameter("numOS"));
			
			OrdemServico os = new OrdemServico();
			os = OrdemServicoBO.pesqNumeroOs(numOs, c);
			
			listaOs.add(os);
			req.setAttribute("msg", "O.S adicionada com sucesso");
			req.setAttribute("listaOs", listaOs);
		} catch (Exception e) {
			// TODO: handle exception
			req.setAttribute("erro", e.getMessage());
		}
		
	}

	private void pesquisar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			String numOs = "";
			String placa = "";
			String cpf = "";
			String strStatus = "";
			
			numOs = req.getParameter("numOS");
			placa = req.getParameter("placa");
			cpf = req.getParameter("cpf");
			strStatus = req.getParameter("status");
			
			int cdFilial = Integer.parseInt(req.getParameter("cdFilial"));
			
		
			
			String data1 = req.getParameter("data1");
			String data2 = req.getParameter("data2");
			
	
			List<OrdemServico> listaOs = new ArrayList<OrdemServico>();
			
			if(!numOs.equals("")){
				int numOsInt = 0;
				try {
					numOsInt = Integer.parseInt(numOs);
				} catch (NumberFormatException e) {
					// TODO: handle exception
					e.printStackTrace();
					req.setAttribute("erro", e.getMessage());
				}
				
				OrdemServico os = new OrdemServico();
				os = OrdemServicoBO.pesqNumeroOsFilial(cdFilial, numOsInt, c);
				listaOs.add(os);
				req.setAttribute("msg", "Lista filtrada com sucesso!");
			}else if(!placa.equals("")){
				Veiculo veic = VeiculoBO.pesqPlaca(placa, c);
				listaOs = OrdemServicoBO.pesqVeiculo(cdFilial, veic.getCodigo(), c);
				req.setAttribute("msg", "Lista filtrada com sucesso!");
			}else if(!cpf.equals("")){
				Cliente cli = ClienteBO.pesqCpf(cpf, c);
				listaOs = OrdemServicoBO.pesqCliente(cdFilial, cli.getCodigo(), c);
				req.setAttribute("msg", "Lista filtrada com sucesso!");
			}else if(!strStatus.equals("")){
				int cdStatus = Integer.parseInt(strStatus);
				listaOs = OrdemServicoBO.pesqStatus(cdFilial, cdStatus, c);
				req.setAttribute("msg", "Lista filtrada com sucesso!");
			}else if(data1.length() == 10){
			
				data1 = DataUtil.arrumarData(data1);
				data2 = DataUtil.arrumarData(data2);
				Calendar calData1 = DataUtil.converter(data1);
				Calendar calData2 = DataUtil.converter(data2);
				listaOs = OrdemServicoBO.pesqData(cdFilial, calData1, calData2, c);
				req.setAttribute("msg", "Lista filtrada com sucesso!");
			}else{
				req.setAttribute("erro", "Preencha pelo menos um dos ítens de pesquisa!");
			}
			
			req.setAttribute("listaOs", listaOs);
			
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
			List<OrdemServico> listaOs = OrdemServicoBO.listar(cdFilial, c);
			for(OrdemServico os: listaOs){
				Funcionario fun = new Funcionario();
				fun = os.getAtendente();
				fun.setNome(StringUtil.primeiraPalavra(os.getAtendente().getNome()));
				os.setAtendente(fun);
				
				Cliente cli = new Cliente();
				cli = os.getCliente();
				cli.setNome(StringUtil.primeiraPalavra(os.getCliente().getNome()));
				os.setCliente(cli);
			}
			
			List<FormaPagamento> fo = FormaPagamentoBO.listar(c);
			req.setAttribute("formasPag", fo);
			req.setAttribute("listaOs", listaOs);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			req.setAttribute("erro", e.getMessage());
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String acao = req.getParameter("acao");
		String retorno = "";
		
		try {
			Connection c = ConexaoFactory.controlarInstancia().getConnection();
			List<StatusOs> listStatus = StatusOsBO.listar(c);
			req.setAttribute("listStatus", listStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch (acao) {
		case "addServico":
			addServico(req);
			retorno = "cadastrarOs.jsp";
			break;
		case "pagamento":
			pagamento(req);
			retorno = "pagamento.jsp";
			break;
		case "addPagamento":
			addPagamento(req);
			retorno = "pagamento.jsp";
			break;
		case "cadastrar":
			cadastrar(req);
			retorno = "ordemServico.jsp";
			break;
		case "alterarStatus":
			alterarStatus(req);
			carregarAltOs(req);;
			retorno = "alterarStatus.jsp";
			break;
		case "addPagOs":
			addPagOs(req);
			listar(req);
			retorno = "listaOs.jsp";
			break;
		}
		req.getRequestDispatcher(retorno).forward(req, resp);
	}

	private void alterarStatus(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			int cdFun = Integer.parseInt(req.getParameter("cdFun"));
			int cdStatus = Integer.parseInt(req.getParameter("status"));
			String observacao = req.getParameter("observacao");
			
			for(OrdemServico os:listaOs){
				MudarStatus ms = new MudarStatus();
				ms.setDtMudanca(Calendar.getInstance());
				ms.setFuncionario(FuncionarioBO.pesqCodigo(cdFun, c));
				ms.setObservacao(observacao);
				ms.setStatus(StatusOsBO.pesqCodigo(cdStatus, c));
				ms.setOrdemServico(OrdemServicoBO.pesqNumeroOs(os.getNumero(), c));
				os.setStatus(ms);
				OrdemServicoBO.mudarStatus(ms, c);
				EmailUtil.EmailMudarStatus(os);
			}
			req.setAttribute("msg", "Status alterado com sucesso!");
			
			listaOs = new ArrayList<OrdemServico>();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}
		
	}

	private void cadastrar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			OrdemServico os = new OrdemServico();
			HttpSession sessao = req.getSession();
			os =(OrdemServico) sessao.getAttribute("os");
			Calendar dtHoje = Calendar.getInstance();
			dtHoje = DataUtil.converter(req.getParameter("dtEntrada"));
			os.setDtEntrada(dtHoje);
			MudarStatus status = new MudarStatus();
			status.setDtMudanca(Calendar.getInstance());
			status.setFuncionario(os.getAtendente());
			status.setObservacao("ABERTURA DE OS");
			status.setStatus(StatusOsBO.pesqDescricao("ABERTO", c));
			os.setStatus(status);
			os.setNumero(OrdemServicoBO.cadastrar(os, c));
			for(Pagamento pags : listaPagtos){
				os.setVlrPago(os.getVlrPago() + pags.getVlPagao());
			}
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
			String dataString = s.format(cal.getTime());
			dataString = DataUtil.DataExtenso(dataString);
			double restante = os.getTotal() - os.getVlrPago();
			String valorStr = NumeroUtil.strExtenso(os.getTotal());
			req.setAttribute("valorStr", valorStr);
			req.setAttribute("strDtHoje", dataString);
			req.setAttribute("restante", restante);
			OrdemServico os2 = new OrdemServico();
			sessao.setAttribute("os", os2);
			req.setAttribute("os", os);
			
			Entrada ent = new Entrada();
			ent.setCodigo(0);
			ent.setDescricao("O.S nº" + os.getNumero() + ", serviço de " + os.getTipo().getDescricao());
			ent.setDtEntrada(os.getDtEntrada());
			ent.setValor(os.getVlrPago());
			ent.setFilial(os.getAtendente().getFilial());
			
			EntradaBO.cadastrar(ent, c);
			
			listaPagtos = new ArrayList<Pagamento>();
			servicos = new ArrayList<ItemServico>();
			req.setAttribute("msg", "Ordem de Serviço Lançada com sucesso");
			
			c.commit();
			c.setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			try {
				c.rollback();
				req.setAttribute("erro", e.getMessage());
				e.printStackTrace();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	
	private void cadastrarCheque(HttpServletRequest req) {
		Connection c = null;
		
		try {
			
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			Cheque cheque = new Cheque();
			cheque.setDtRecebimento(Calendar.getInstance());
			cheque.setDtDeposito(DataUtil.converter(req.getParameter("dtDeposito")));
			if(DataUtil.validarData(cheque.getDtDeposito()) == -1){
				cheque.setStatus(1);
			}else{
				cheque.setStatus(0);
			}
			cheque.setValor(Double.parseDouble(req.getParameter("vlrPag").replace(",", ".")));
			OrdemServico os = new OrdemServico();
			HttpSession sessao = req.getSession();
			os = (OrdemServico) sessao.getAttribute("os");
			int cdFilial = Integer.parseInt(req.getParameter("cdFilial"));
			cheque.setEmitente(os.getCliente().getNome());
			cheque.setNumero(Integer.parseInt(req.getParameter("codPag")));
			cheque.setCdFilial(cdFilial);
			ChequeBO.cadastrar(cheque, c);
			
		} catch (Exception e) {
			// TODO: handle exception
			req.setAttribute("erro", e.getMessage());
			e.printStackTrace();
		}
	}

	public static List<Pagamento> listaPagtos = new ArrayList<Pagamento>();
	private void addPagamento(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			OrdemServico os = new OrdemServico();
			HttpSession sessao = req.getSession();
			os = (OrdemServico) sessao.getAttribute("os");
			Pagamento pag = new Pagamento();
			pag.setForPagamento(FormaPagamentoBO.pesqCodigo(Integer.parseInt(req.getParameter("forPagamento")), c));
			
			
			
			pag.setNumero(req.getParameter("codPag"));
			pag.setObservacoes(req.getParameter("desPag"));
			pag.setVlPagao(Double.parseDouble(req.getParameter("vlrPag").replace(",", ".")));
			listaPagtos.add(pag);
			os.setPagamento(listaPagtos);
			List<FormaPagamento> forPags = new ArrayList<FormaPagamento>();
			forPags = FormaPagamentoBO.listar(c);
			req.setAttribute("formasPag", forPags);
			os.setVlrPago(0);
			for(Pagamento pags : listaPagtos){
				os.setVlrPago(os.getVlrPago() + pags.getVlPagao());
			}
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
			String dataString = s.format(cal.getTime());
			dataString = DataUtil.DataExtenso(dataString);
			double restante = os.getTotal() - os.getVlrPago();
			String valorStr = NumeroUtil.strExtenso(os.getTotal());
			req.setAttribute("valorStr", valorStr);
			req.setAttribute("strDtHoje", dataString);
			req.setAttribute("restante", restante);
			sessao.setAttribute("os", os);
			req.setAttribute("msg", "Pagamento adicionado com sucesso");
			if(pag.getForPagamento().getDescricao().equals("CHEQUE")){
				cadastrarCheque(req);
			}
		} catch (Exception e) {
			// TODO: handle exception
			req.setAttribute("erro", e.getMessage());
			e.printStackTrace();
		}
		
		
	}

	private void pagamento(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			String tpOs = req.getParameter("tpServico");
			
			
			
			List<FormaPagamento> forPags = new ArrayList<FormaPagamento>();
			
			forPags = FormaPagamentoBO.listar(c);
			
			req.setAttribute("formasPag", forPags);
			
			HttpSession sessao = req.getSession();
			OrdemServico os = new OrdemServico();
			os = (OrdemServico) sessao.getAttribute("os");
			os.setTipo(TipoOrdemBO.pesqCodigo(Integer.parseInt(tpOs), c));
			sessao.setAttribute("os", os);
			double restante = os.getTotal() - os.getVlrPago();
			sessao.setAttribute("restante", restante);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	public static List<ItemServico> servicos = new ArrayList<ItemServico>();
	private void addServico(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Connection c = null;
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			ItemServico itSer = new ItemServico();
			itSer.setQuantidade(Integer.parseInt(req.getParameter("serQtn")));
			itSer.setServico(ServicoBO.pesqCodigo(Integer.parseInt(req.getParameter("serCodigo")), c));
			itSer.setVlrTotal(itSer.getServico().getVlrTotal() * itSer.getQuantidade());
			
			HttpSession sessao = req.getSession();
			OrdemServico os = new OrdemServico();
			os = (OrdemServico) sessao.getAttribute("os");
			
			servicos.add(itSer);
			os.setServicos(servicos);
			os.setTotal(0);
			for(ItemServico it : os.getServicos()){
				os.setTotal(os.getTotal() + it.getVlrTotal());
			}
			sessao.setAttribute("os", os);
			sessao.setMaxInactiveInterval(60);
			req.setAttribute("msg", "Item de serviço adicionado com sucesso!");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}
		
	}
	
}
