package br.com.desp.util;

public class StringUtil {

	public static String primeiraPalavra(String frase){  
		int ind = frase.indexOf(" ");
		String paravra = frase.substring(0, ind);
		return paravra;
	}

}
