package br.com.desp.testes;

import java.util.Calendar;

public class TesteHora {
	
	public static void main(String[] args) {
		
		String[] diasSemana = {"Domingo", "Segunda-Feira", "Ter�a-Feira",  
	            "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "S�bado"};  
	      
	 String[] meses = {"Janeiro","Fevereiro","Mar�o","Abril","Maio","Junho",  
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
