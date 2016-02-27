package br.com.desp.util;

import org.apache.commons.mail.HtmlEmail;

import br.com.desp.beans.Cliente;
import br.com.desp.beans.Funcionario;
import br.com.desp.beans.Mensagem;
import br.com.desp.beans.OrdemServico;

public class EmailUtil {
	
	public static void EmailMudarStatus(OrdemServico os)throws Exception{
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");//servidor smtp
		email.addCc(os.getCliente().getUsuario().getEmail());//destinat�rio
		email.addCc(os.getAtendente().getUsuario().getEmail());
		email.setFrom("contato@goldeplaca.com.br", "Gol de Placa");//remetente
		email.setSubject("Status alterado - no-reply"); //titulo
		email.setSmtpPort(465); //Porta smtp
		
		//Carregando arquivo externo
		
		String html = "<html><head>"
				+ "<!-- Latest compiled and minified CSS -->"
+"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css' integrity='sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7' crossorigin='anonymous'>"

+"<!-- Optional theme -->"
+"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css' integrity='sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r' crossorigin='anonymous'>"

+"<!-- Latest compiled and minified JavaScript -->"
+"<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js' integrity='sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS' crossorigin='anonymous'></script>'"
				
				+ "</head><body>"
				+ "<div class='row'>"
				+ "<div class='col-md-4'></div>"
				+ "<div class='col-md-4'>"
				+ "<div class='panel panel-default'>"
				+ "<div class='panel-heading'>"
				+ "<h3 class='text-center'>O status da ordem de servi�o " + os.getNumero() +  " foi alterada.</h3>"
						+ "</div>"
						+ "<div class='panel-body'>"
				+ "<p class='text-justify'>"
				+ "Cliente: " + os.getCliente().getNome() +"<br>"
						+ "Atendente: " + os.getAtendente().getNome() + "<br>"
						+ "O.S: " + os.getNumero() + "<br>"
						+ "Status: " + os.getStatus().getStatus().getDescricao() + "<br>"
						+ "Data Altera��o:" + DataUtil.CalendarString(os.getStatus().getDtMudanca())	+ "<br><br>"
						+ "Observa��es: " + os.getStatus().getObservacao() 
				+ "</p>"
				+ "</div>"
				+ "</div>"
				+ "<div class='col-md-4'></div>"
				+ "</div>"
				+ "</body></html>";
		
		
		email.setHtmlMsg(html);
		email.setSSL(true);
		//AUTENTICAR NO SERVIDOR
		email.setAuthentication("carvalho.pedro.n@gmail.com", "helena135086");
		//MENSAGEM CASO EMAIL N�O SUPORTE MENSAGEM HTML
		email.setTextMsg("Seu servidor de e-mail n�o suporta mensagem HTML");
		//ENVIANDO MENSAGEM
		email.send();
	}
	
	public static void EnviarMensagem(Mensagem men)throws Exception{
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");//servidor smtp
		email.addCc(men.getEmailDestinatario(), men.getNomeDestinatario());//destinat�rio
		email.setFrom(men.getEmailRemetente(), men.getNomeRemetente());//remetente
		email.setSubject(men.getAssunto()); //titulo
		email.setSmtpPort(465); //Porta smtp
		
		//Carregando arquivo externo
		
		String html = "<html><head>"
				+ "<!-- Latest compiled and minified CSS -->"
+"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css' integrity='sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7' crossorigin='anonymous'>"

+"<!-- Optional theme -->"
+"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css' integrity='sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r' crossorigin='anonymous'>"

+"<!-- Latest compiled and minified JavaScript -->"
+"<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js' integrity='sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS' crossorigin='anonymous'></script>'"
				
				+ "</head><body>"
				+ "<div class='row'>"
				+ "<div class='col-md-4'></div>"
				+ "<div class='col-md-4'>"
				+ "<div class='panel panel-default'>"
				+ "<div class='panel-heading'>"
				+ "<h3 class='text-center'>Voc� recebeu uma mensagem de " + men.getEmailRemetente() + "."
						+ "</div>"
						+ "<div class='panel-body'>"
				+ "<p class='text-justify'>"
				+ "Assunto: " + men.getAssunto() +"<br>"
				+ "Para: " + men.getEmailDestinatario() + "<br>"
						+ "Mensagem: " + men.getMensagem() + "<br>"
						 
				+ "</p>"
				+ "</div>"
				+ "</div>"
				+ "<div class='col-md-4'></div>"
				+ "</div>"
				+ "</body></html>";
		
		
		email.setHtmlMsg(html);
		email.setSSL(true);
		//AUTENTICAR NO SERVIDOR
		email.setAuthentication("carvalho.pedro.n@gmail.com", "helena135086");
		//MENSAGEM CASO EMAIL N�O SUPORTE MENSAGEM HTML
		email.setTextMsg("Seu servidor de e-mail n�o suporta mensagem HTML");
		//ENVIANDO MENSAGEM
		email.send();
	}
	
	public static void EmailAbrirOs(OrdemServico os)throws Exception{
		
	}
	
	public static void EmailBemVindo(Cliente cli)throws Exception{
		
	}
	
	public static void rememberMe(Funcionario fun)throws Exception{
		
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");//servidor smtp
		email.addTo(fun.getUsuario().getEmail(), fun.getNome());//destinat�rio
		email.setFrom("noreply@sgdespachante.com.br", "No reply");//remetente
		email.setSubject("No reply, RemembeMe password"); //titulo
		email.setSmtpPort(465); //Porta smtp
		
		String html = "<html><body><p>"
				+ "Este � um e-mail autom�tico para relembrar senha de usu�rio.<br>"
				+ "Usu�rio: " + fun.getUsuario().getEmail()
				+ "<br>Senha: " + fun.getUsuario().getPassword()
				+ "</p></body></html>";
		email.setHtmlMsg(html);
		email.setSSL(true);
		//AUTENTICAR NO SERVIDOR
		email.setAuthentication("carvalho.pedro.n@gmail.com", "helena135086");
		//MENSAGEM CASO EMAIL N�O SUPORTE MENSAGEM HTML
		email.setTextMsg("Seu servidor de e-mail n�o suporta mensagem HTML");
		//ENVIANDO MENSAGEM
		email.send();
		
	}

}
