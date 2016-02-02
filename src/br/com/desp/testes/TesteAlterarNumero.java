package br.com.desp.testes;

public class TesteAlterarNumero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String strNumero = "25,35";
		
		
		strNumero = strNumero.replace(",", ".");
		double numero = Double.parseDouble(strNumero);
		System.out.println(numero);

	}

}
