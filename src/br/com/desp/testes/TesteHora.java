package br.com.desp.testes;

import java.util.Calendar;

public class TesteHora {
	
	public static void main(String[] args) {
		
		String[] diasSemana = {"Domingo", "Segunda-Feira", "Terça-Feira",  
	            "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado"};  
	      
	 String[] meses = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho",  
	            "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};  
	      
	 Calendar calendar = Calendar.getInstance();  
	      
	 String str = "Bom dia! " + diasSemana[calendar.get(Calendar.DAY_OF_WEEK)-1]  
	            + ", " + calendar.get(Calendar.DAY_OF_MONTH) + " de "  
	            + meses[calendar.get(Calendar.MONTH)] + " de "   
	            + calendar.get(Calendar.YEAR) + " - "   
	            + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);  
	 System.out.println(str);  
	 
	}

}
