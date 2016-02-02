package br.com.desp.bo;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import br.com.desp.beans.MudarStatus;
import br.com.desp.dao.MudarStatusOsDAO;

public abstract class MudarStatusOsBO {

	public static int mudarStatus(MudarStatus ms, Connection c)throws Exception{
		if(ms.getDtMudanca().get(Calendar.ERA) > Calendar.getInstance().get(Calendar.ERA) || 
				ms.getDtMudanca().get(Calendar.YEAR) > Calendar.getInstance().get(Calendar.YEAR) ||
				ms.getDtMudanca().get(Calendar.DAY_OF_YEAR) > Calendar.getInstance().get(Calendar.DAY_OF_YEAR)){
			throw new Exception("A data de Admissão não pode ser depois da data de hoje");
		}
		if(FuncionarioBO.pesqCodigo(ms.getFuncionario().getCodigo(), c).getNome() == null){
			throw new Exception("É necessário estar logado para mudar um status");
		}
		if(ms.getObservacao().length() > 120){
			throw new Exception("É 120 o máximo de caractérs para observações");
		}
	
		ms.setCodigo(new MudarStatusOsDAO().mudarStatus(ms, c));
	
		return ms.getCodigo();
	}
	
	public static List<MudarStatus> listarOrdem(int nrOrdem, Connection c)throws Exception{
		if(OrdemServicoBO.pesqNumeroOs(nrOrdem, c).getNumero() == 0){
			throw new Exception("Número de ordem inválido");
		}
		return new MudarStatusOsDAO().listarMudancas(nrOrdem, c);
	}
	
	public static MudarStatus pesqCodigo(int codigo, Connection c)throws Exception{
		
		return new MudarStatusOsDAO().pesqCodigo(codigo, c);
	}
	
}
