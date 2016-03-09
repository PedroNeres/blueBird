package br.com.desp.testes;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.Mensagem;
import br.com.desp.bo.MensagemBO;
import br.com.desp.conexao.ConexaoFactory;

public class TesteListarMensagens {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try {
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			List<Mensagem> mensagens = new ArrayList<Mensagem>();
			
			String email = "carvalho.pedro.n@gmail.com";
			
			mensagens = MensagemBO.naoLidos(email, c);
			
			for(Mensagem men: mensagens){
				System.out.println(men.getMensagem());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
