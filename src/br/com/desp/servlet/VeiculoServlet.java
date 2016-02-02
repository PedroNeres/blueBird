package br.com.desp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.desp.beans.Cliente;
import br.com.desp.beans.Funcionario;
import br.com.desp.beans.OrdemServico;
import br.com.desp.beans.Servico;
import br.com.desp.beans.TipoOrdemServico;
import br.com.desp.beans.TipoVeiculo;
import br.com.desp.beans.Veiculo;
import br.com.desp.bo.ClienteBO;
import br.com.desp.bo.ServicoBO;
import br.com.desp.bo.TipoOrdemBO;
import br.com.desp.bo.TipoVeiculoBO;
import br.com.desp.bo.VeiculoBO;
import br.com.desp.conexao.ConexaoFactory;

@WebServlet("/veiculo")
public class VeiculoServlet extends HttpServlet{

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
		case "verificar":
			retorno = verificar(req);
			break;
		}
		req.getRequestDispatcher(retorno).forward(req, resp);
	}
	
	private String verificar(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		String retorno = "";
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			String cpf = req.getParameter("cpf");
			Cliente cli = ClienteBO.pesqCpf(cpf, c);
			req.setAttribute("cliente", cli);
			
			String renavam = req.getParameter("renavam");
			String placa = req.getParameter("placa").toUpperCase();
			retorno = "cadVeiculo.jsp";
			req.setAttribute("msg", "Este veículo ainda não é cadastrado, cadastre para continuar!");
			Veiculo veic = new Veiculo();
			int ren = 0;
			if(!renavam.equals("")){
				ren = Integer.parseInt(renavam);
			}
			veic.setPlaca(placa);
			veic.setRenavam(ren);
			List<TipoVeiculo> tpVeiculo = TipoVeiculoBO.listar(c);
			req.setAttribute("tpVeiculo", tpVeiculo);
			if(!renavam.equals("")){
				if(VeiculoBO.pesqRenavam(ren, c).getRenavam() == ren){
					veic = VeiculoBO.pesqRenavam(ren, c);
					retorno = "cadastrarOs.jsp";
					req.setAttribute("msg", "Veículo ja cadastrado, conclua a ordem de serviço");
				}
			}else if(!placa.equals("")){
				if(VeiculoBO.pesqPlaca(placa, c).getPlaca().equals(placa)){
					veic = VeiculoBO.pesqPlaca(placa, c);
					retorno = "cadastrarOs.jsp";
					req.setAttribute("msg", "Veículo ja cadastrado, conclua a ordem de serviço");
				}
			}else{
				req.setAttribute("msg", null);
				req.setAttribute("erro", "Preencha pelo menos 1 dos ítens de busca.");
			}
			req.setAttribute("veic", veic);
			HttpSession sessao = req.getSession();
			List<TipoOrdemServico> tpOs = TipoOrdemBO.listar(c);
			List<Servico> servicos = ServicoBO.listar(c);
			sessao.setAttribute("servicos", servicos);
			sessao.setAttribute("tpOs", tpOs);
			OrdemServico os = new OrdemServico();
			os.setCliente(cli);
			os.setAtendente((Funcionario) sessao.getAttribute("user"));
			os.setVeiculo(veic);
			sessao.setAttribute("os", os);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return retorno;
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
			retorno = "cadastrarOs.jsp";
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
			
			Veiculo veic = new Veiculo();
			veic.setPlaca(req.getParameter("placa"));
			int ver = 0;
			try {
				veic.setRenavam(Long.parseLong(req.getParameter("renavam")));
			} catch (NumberFormatException e) {
				// TODO: handle exception
				ver = 1;
				e.printStackTrace();
				req.setAttribute("erro", "Digite somente números em renavam!");
			}
			veic.setModelo(req.getParameter("modelo"));
			veic.setTipo(TipoVeiculoBO.pesqCodigo(Integer.parseInt(req.getParameter("tipoVeiculo")), c));
			Cliente cli = ClienteBO.pesqCpf(req.getParameter("cpf"), c);
			req.setAttribute("cliente", cli);
			OrdemServico os = new OrdemServico();
			os.setCliente(cli);
			
			HttpSession sessao = req.getSession();
			List<TipoOrdemServico> tpOs = TipoOrdemBO.listar(c);
			List<Servico> servicos = ServicoBO.listar(c);
			sessao.setAttribute("servicos", servicos);
			sessao.setAttribute("tpOs", tpOs);
			Funcionario fun = new Funcionario();
			fun = (Funcionario) sessao.getAttribute("user");
			os.setAtendente(fun);
			System.out.println(os.getAtendente().getNome());
			if(ver == 0){
				veic.setCodigo(VeiculoBO.cadastrarVeic(veic, c));
				req.setAttribute("veic", veic);
				os.setVeiculo(veic);
				sessao.setAttribute("os", os);
				req.setAttribute("msg", "Veículo cadastrado com sucesso, conclua a ordem de serviço");
			}
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
				req.setAttribute("erro", e2.getMessage());
				e2.printStackTrace();
			}
		}
		
	}

}
