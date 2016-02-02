package br.com.desp.testes;

public class TestePrimeiraPalavra {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String frase = "PEDRO DE PAULA NERES";  
		int ind = frase.indexOf(" ");
		String paravra = frase.substring(0, ind);
		System.out.println(paravra);
	}

}
