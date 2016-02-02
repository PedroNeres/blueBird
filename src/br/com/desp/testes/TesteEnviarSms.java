package br.com.desp.testes;

import javax.swing.JOptionPane;

import br.com.facilitamovel.bean.Retorno;
import br.com.facilitamovel.bean.SmsSimples;
import br.com.facilitamovel.service.SendMessage;

public class TesteEnviarSms {
	
	private static String user = "pedroneres";
	private static String password = "Helena135086";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String destinatario = JOptionPane.showInputDialog("Digite o numero do destinatário");
		String mensagem = JOptionPane.showInputDialog("Digite a Mensagem a ser enviada");
		
		try {
			simple(destinatario, mensagem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void simple(String destinatario, String mensagem) throws Exception{
		SmsSimples sms = new SmsSimples();
		sms.setUser(user);
		sms.setPassword(password);
		sms.setDestinatario(destinatario);
		sms.setMessage(mensagem);
		Retorno retorno = SendMessage.simpleSend(sms);
		System.out.println("Código: " + retorno.getCodigo());
		System.out.println("Descrição: " + retorno.getMensagem());
	}

}
