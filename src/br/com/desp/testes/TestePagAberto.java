package br.com.desp.testes;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.Pagamento;
import br.com.desp.bo.PagamentoBO;
import br.com.desp.conexao.ConexaoFactory;

public class TestePagAberto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			List<Pagamento> pags = PagamentoBO.listarPagAberto(c);
			
			for(Pagamento pag : pags){
				System.out.println(pag.getVlPagao());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
