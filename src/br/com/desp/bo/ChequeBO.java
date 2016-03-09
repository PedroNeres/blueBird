package br.com.desp.bo;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import br.com.desp.beans.Cheque;
import br.com.desp.dao.ChequeDAO;

public abstract class ChequeBO {
	
	public static void cadastrar(Cheque che, Connection c)throws Exception{
		if(che.getEmitente().length() < 3){
			throw new Exception("Digite um emitente válido");
		}
		else if(che.getValor() < 10){
			throw new Exception("O cheque não pode ter valor abaixo de R$ 10,00");
		}
		new ChequeDAO().cadastrar(che, c);
	}
	
	public static void alterar(Cheque che, Connection c)throws Exception{
		if(che.getEmitente().length() < 3){
			throw new Exception("Digite um emitente válido");
		}
		else if(che.getValor() < 10){
			throw new Exception("O cheque não pode ter valor abaixo de R$ 10,00");
		}
		new ChequeDAO().alterar(che, c);
	}
	
	public static void depositar(int codigo, Connection c)throws Exception{
		new ChequeDAO().depositar(codigo, c);
	}
	
	public static void reabrir(int codigo, Connection c)throws Exception{
		new ChequeDAO().reabrir(codigo, c);
	}
	
	public static List<Cheque> listarPendentes(int cdFilial, Connection c)throws Exception{
		return new ChequeDAO().listarPendentes(cdFilial, c);
	}
	
	public static List<Cheque> listarDepositados(int cdFilial, Connection c)throws Exception{
		return new ChequeDAO().listarDepositados(cdFilial, c);
	}
	
	public static List<Cheque> listar(int cdFilial, Connection c)throws Exception{
		return new ChequeDAO().listar(cdFilial, c);
	}
	
	public Cheque pesqEmitente(String emitente, int cdFilial, Connection c)throws Exception{
		return new ChequeDAO().pesqEmitente(emitente, cdFilial, c);
	}
	
	public static Cheque pesqCodigo(int codigo, int cdFilial, Connection c)throws Exception{
		return new ChequeDAO().pesqCodigo(codigo, cdFilial, c);
	}
	
	public static Cheque pesqNumero(int numero, int cdFilial, Connection c)throws Exception{
		return new ChequeDAO().pesqNumero(numero, cdFilial, c);
	}
	
	public static List<Cheque> pesqData(Calendar data1, Calendar data2, int cdFilial, Connection c)throws Exception{
		return new ChequeDAO().pesqData(data1, data2, cdFilial, c);
	}

}
