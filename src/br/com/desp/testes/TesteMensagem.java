package br.com.desp.testes;

import java.sql.Connection;
import java.util.Calendar;

import br.com.desp.beans.Mensagem;
import br.com.desp.bo.MensagemBO;
import br.com.desp.conexao.ConexaoFactory;

public class TesteMensagem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			
			Mensagem men = new Mensagem();
			
			men.setAssunto("Boas vindas");
			Calendar cal = Calendar.getInstance();
			men.setDtEnvio(cal);
			men.setEmailDestinatario("hpneres@gmail.com");
			men.setNomeDestinatario("Helena Neres");
			men.setEmailRemetente("carvalho.pedro.n@gmail.com");
			men.setNomeRemetente("Pedro Neres");
			men.setStatus(1);
			men.setMensagem("Boa tarde amiga, bem vindo ao despachante, vc vai amar trabalhar aqui. Precisando de qualquer ajuda pode contar comigo, bjs.");
			
			
			MensagemBO.enviar(men, c);
			
			System.out.println("Mensagem enviada com sucesso");
			
			c.commit();
			c.setAutoCommit(true);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
