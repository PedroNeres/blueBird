package br.com.desp.testes;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.Pagamento;
import br.com.desp.beans.TipoOrdemServico;
import br.com.desp.beans.TipoVeiculo;
import br.com.desp.bo.PagamentoBO;
import br.com.desp.bo.TipoOrdemBO;
import br.com.desp.bo.TipoVeiculoBO;
import br.com.desp.conexao.ConexaoFactory;

public class TesteCarregarHome {
	
	public static void main(String[] args) {
		
		Connection c = null;
		
		try {
			
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			List<Pagamento> pags = new ArrayList<Pagamento>();
			List<TipoOrdemServico> tiposOrdem = new ArrayList<TipoOrdemServico>();
			List<TipoVeiculo> tiposVeiculos = new ArrayList<TipoVeiculo>();
			
			System.out.println("pags");
			pags = PagamentoBO.listarPagAberto(c);
			System.out.println("tipos");
			tiposOrdem = TipoOrdemBO.listar(c);
			System.out.println("toVeiculo");
			tiposVeiculos = TipoVeiculoBO.listar(c);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
