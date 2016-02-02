package br.com.desp.testes;

public class TesteData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String data = "2016-01-22";
		
		String ano = data.substring(0, 4);
		String mes = data.substring(5, 7);
		String dia = data.substring(8, 10);
		String data2 = dia+"/"+mes+"/"+ano;
		
		System.out.println(data2);

	}

}
