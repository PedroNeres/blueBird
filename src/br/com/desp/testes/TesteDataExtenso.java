package br.com.desp.testes;

public class TesteDataExtenso {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String data =  "15/12/2015";
		
		String strData = DataExtenso(data);
		
		System.out.println(strData);

	}
	
	public static String DataExtenso(String data){
		
		String[] meses = new String[12];
		meses[0] = "JANEIRO";
		meses[1] = "FEVEREIRO";
		meses[2] = "MARÇO";
		meses[3] = "ABRIL";
		meses[4] = "MAIO";
		meses[5] = "JUNHO";
		meses[6] = "JULHO";
		meses[7] = "AGOSTO";
		meses[8] = "SETEMBRO";
		meses[9] = "OUTUBRO";
		meses[10] = "NOVEMBRO";
		meses[11] = "DEZEMBRO";
		
		String dia = data.substring(0, 2);
		String mes = meses[Integer.parseInt(data.substring(3, 5)) - 1];
		String ano = data.substring(6, 10);
		
		String strData = dia + " de " + mes + " de " + ano + ".";
		
		return strData;
		
	}

}
