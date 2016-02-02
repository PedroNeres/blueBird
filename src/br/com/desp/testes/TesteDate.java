package br.com.desp.testes;

import java.util.Calendar;

import br.com.desp.util.DataUtil;

public class TesteDate {
	
	public static void main(String[] args) {
		
		Calendar cal1 = Calendar.getInstance();
		String dt1 = "24/12/2015";
		cal1 = DataUtil.converter(dt1);
		Calendar cal2 = Calendar.getInstance();
		String dt2 = "23/12/2015";
		cal2 = DataUtil.converter(dt2);
		if( cal1.compareTo(cal2) == -1){
			System.out.println("Data 2 é maior que a primeira");
		}else if(cal1.compareTo(cal2) == 1 || cal1.compareTo(cal2) == 0){
			System.out.println("Data 2 é menor ou igual que a primeira");
		}
		
	}

}
