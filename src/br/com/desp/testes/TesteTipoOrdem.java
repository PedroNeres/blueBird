package br.com.desp.testes;

import java.sql.Connection;

import br.com.desp.beans.TipoOrdemServico;
import br.com.desp.bo.TipoOrdemBO;
import br.com.desp.conexao.ConexaoFactory;

public class TesteTipoOrdem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			TipoOrdemServico tpOrdem = new TipoOrdemServico();
			
			tpOrdem.setDescricao("RENOVAÇÃO CNH");
			
			TipoOrdemBO.cadastrar(tpOrdem, c);
			System.out.println("Tipo de Ordem de serviço cadastrado com sucesso");
			c.commit();
			c.setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
