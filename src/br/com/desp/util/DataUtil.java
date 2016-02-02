package br.com.desp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataUtil {
	
	public static Calendar converter(String dataString){
		
		Calendar c = null;
		try{
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		 
		String data = dataString;
		 
		c = Calendar.getInstance();
		 
		c.setTime(formatoData.parse(data));
		
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
		
	}
	
	public static int validarData(Calendar data){
		
		Calendar dataHoje = Calendar.getInstance();
		
		return data.compareTo(dataHoje);
		
	}
	
	public static String arrumarData(String data){
		String ano = data.substring(0, 4);
		String mes = data.substring(5, 7);
		String dia = data.substring(8, 10);
		String data2 = dia+"/"+mes+"/"+ano;
		return data2;
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

	public static String CalendarString(Calendar cal){
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		String a = s.format(cal.getTime());
		return a;
	}

}
