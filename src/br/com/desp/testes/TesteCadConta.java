package br.com.desp.testes;

import java.sql.Connection;

import br.com.desp.beans.Contas;
import br.com.desp.bo.ContasBO;
import br.com.desp.bo.FilialBO;
import br.com.desp.conexao.ConexaoFactory;

public class TesteCadConta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			Contas cont = new Contas();
			
			cont.setDescricao("LUZ");
			cont.setDiaVencimento(10);
			cont.setFilial(FilialBO.pesqCodigo(25, c));
			cont.setObservacoes("teste observações");
			cont.setQtParcelas(1);
			cont.setStatus(0);
			cont.setValor(345);
			
			ContasBO.cadastrar(cont, c);
			
			System.out.println("Conta cadastrado com sucesso");
			
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
