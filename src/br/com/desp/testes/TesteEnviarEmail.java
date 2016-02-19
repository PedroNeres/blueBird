package br.com.desp.testes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class TesteEnviarEmail {

	public static void main(String[] args) throws EmailException, IOException {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		
				HtmlEmail email = new HtmlEmail();
				email.setHostName("smtp.gmail.com");
				email.addTo("macmatriz@hotmail.com", "Mac Matriz");//destinatário
				email.setFrom("carvalho.pedro.n@gmail.com", "Pedro Neres");//remetente
				email.setSubject("Sistema de Gerenciamento para Despachante"); //titulo
				email.setSmtpPort(465);
				
				BufferedReader br = new BufferedReader(new FileReader("C:/sgDespachante.html"));
				String html = "";
				while(br.ready()){
				   String linha = br.readLine();
				   
				   html = html+linha;
				}
				
				
				
				
				email.setHtmlMsg(html);
				br.close();
				email.setSSL(true);
				System.out.println("set ssl");
				email.setAuthentication("carvalho.pedro.n@gmail.com", "helena135086");
				email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");
				
				email.send();
				System.out.println("emailenviado com sucesso");

	}

}
