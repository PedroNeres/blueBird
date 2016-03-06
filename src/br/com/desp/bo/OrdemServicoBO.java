package br.com.desp.bo;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import br.com.desp.beans.MudarStatus;
import br.com.desp.beans.OrdemServico;
import br.com.desp.dao.MudarStatusOsDAO;
import br.com.desp.dao.OrdemServicoDAO;

public abstract class OrdemServicoBO {
	
	public static int cadastrar(OrdemServico os, Connection c)throws Exception{
		if(os.getDtEntrada().get(Calendar.ERA) > Calendar.getInstance().get(Calendar.ERA) || 
				os.getDtEntrada().get(Calendar.YEAR) > Calendar.getInstance().get(Calendar.YEAR) ||
				os.getDtEntrada().get(Calendar.DAY_OF_YEAR) > Calendar.getInstance().get(Calendar.DAY_OF_YEAR)){
			throw new Exception("A data da OS n�o pode ser depois da data de hoje");
		}
		if(ClienteBO.pesqCodigo(os.getCliente().getCodigo(), c).getNome() == null){
			throw new Exception("C�digo do cliente inv�lido");
		}
		if(StatusOsBO.pesqCodigo(os.getStatus().getStatus().getCodigo(), c).getDescricao() == null){
			throw new Exception("Digite um c�digo v�lido");
		}
		if(FuncionarioBO.pesqCodigo(os.getAtendente().getCodigo(), c).getNome() == null){
			throw new Exception("Digite um c�digo de funcion�rio v�lido");
		}
		if(VeiculoBO.pesqCodigo(os.getVeiculo().getCodigo(), c).getPlaca() == null){
			throw new Exception("Digite um c�digo de veiculo v�lido");
		}
		if(os.getDesconto() < 0){
			throw new Exception("O valor de desconto n�o deve ser negativo");
		}
		return new OrdemServicoDAO().cadastrar(os, c);
	}
	
	public static void editar(OrdemServico os, Connection c)throws Exception{
		if(os.getDtEntrada().get(Calendar.ERA) > Calendar.getInstance().get(Calendar.ERA) || 
				os.getDtEntrada().get(Calendar.YEAR) > Calendar.getInstance().get(Calendar.YEAR) ||
				os.getDtEntrada().get(Calendar.DAY_OF_YEAR) > Calendar.getInstance().get(Calendar.DAY_OF_YEAR)){
			throw new Exception("A data da OS n�o pode ser depois da data de hoje");
		}
		if(ClienteBO.pesqCodigo(os.getCliente().getCodigo(), c).getNome() == null){
			throw new Exception("C�digo do cliente inv�lido");
		}
		if(StatusOsBO.pesqCodigo(os.getStatus().getStatus().getCodigo(), c).getDescricao() == null){
			throw new Exception("Digite um c�digo v�lido");
		}
		if(FuncionarioBO.pesqCodigo(os.getAtendente().getCodigo(), c).getNome() == null){
			throw new Exception("Digite um c�digo de funcion�rio v�lido");
		}
		if(VeiculoBO.pesqCodigo(os.getVeiculo().getCodigo(), c).getPlaca() == null){
			throw new Exception("Digite um c�digo de veiculo v�lido");
		}
		if(os.getDesconto() < 0){
			throw new Exception("O valor de desconto n�o deve ser negativo");
		}
		new OrdemServicoDAO().editar(os, c);
	}
	
	public static void mudarStatus(MudarStatus ms, Connection c)throws Exception{
		ms.setCodigo(new MudarStatusOsDAO().mudarStatus(ms, c));
		new OrdemServicoDAO().mudarStatus(ms, c);
	}
	
	public static List<OrdemServico> listar(int cdFilial, Connection c)throws Exception{
		return new OrdemServicoDAO().listar(cdFilial, c);
	}
	
	public static List<OrdemServico> pesqData(int cdFilial, Calendar data1, Calendar data2, Connection c)throws Exception{
		
		return new OrdemServicoDAO().pesqData(cdFilial, data1, data2, c);
	}
	
	public static List<OrdemServico> pesqFilial(int cdFilial, Connection c)throws Exception{
		if(FilialBO.pesqCodigo(cdFilial, c).getNome() == null){
			throw new Exception("C�digo da filial n�o encontrado");
		}
		return new OrdemServicoDAO().pesqFilial(cdFilial, c);
	}
	
	public static List<OrdemServico> pesqFilialData(Calendar data1, Calendar data2, int cdFilial, Connection c)throws Exception{
		if(FilialBO.pesqCodigo(cdFilial, c).getNome() == null){
			throw new Exception("C�digo da filial n�o encontrado");
		}
		
		return new OrdemServicoDAO().pesqFilialData(data1, data2, cdFilial, c);
	}
	
	public static List<OrdemServico> pesqCliente(int cdFilial, int cdCliente, Connection c)throws Exception{
		if(ClienteBO.pesqCodigo(cdCliente, c).getNome() == null){
			throw new Exception("C�digo do cliente inv�lido");
		}
		return new OrdemServicoDAO().pesqCliente(cdFilial, cdCliente, c);
	}
	
	public static List<OrdemServico> pesqClienteData(int cdFilial, Calendar data1, Calendar data2, int cdCliente, Connection c)throws Exception{
		if(ClienteBO.pesqCodigo(cdCliente, c).getNome() == null){
			throw new Exception("C�digo do cliente inv�lido");
		}
		if(data2.get(Calendar.ERA) > data1.get(Calendar.ERA) || 
				data2.get(Calendar.YEAR) > data1.get(Calendar.YEAR) ||
				data2.get(Calendar.DAY_OF_YEAR) > data1.get(Calendar.DAY_OF_YEAR)){
			throw new Exception("A data da OS n�o pode ser depois da data de hoje");
		}
		return new OrdemServicoDAO().pesqClienteData(cdFilial, data1, data2, cdCliente, c);
	}
	
	public static List<OrdemServico> pesqStatus(int cdFilial, int cdStatus, Connection c)throws Exception{
		if(StatusOsBO.pesqCodigo(cdStatus, c).getDescricao() == null){
			throw new Exception("Digite um c�digo v�lido");
		}
		return new OrdemServicoDAO().pesqStatus(cdFilial, cdStatus, c);
	}
	
	public static List<OrdemServico> pesqStatusData(int cdStatus, Calendar data1, Calendar data2, Connection c)throws Exception{
		if(StatusOsBO.pesqCodigo(cdStatus, c).getDescricao() == null){
			throw new Exception("Digite um c�digo v�lido");
		}
		if(data2.get(Calendar.ERA) > data1.get(Calendar.ERA) || 
				data2.get(Calendar.YEAR) > data1.get(Calendar.YEAR) ||
				data2.get(Calendar.DAY_OF_YEAR) > data1.get(Calendar.DAY_OF_YEAR)){
			throw new Exception("A data da OS n�o pode ser depois da data de hoje");
		}
		return new OrdemServicoDAO().pesqStatusData(cdStatus, data1, data2, c);
	}
	
	public static List<OrdemServico> pesqAtendente(int cdFilial, int cdFuncionario, Connection c)throws Exception{
		if(FuncionarioBO.pesqCodigo(cdFuncionario, c).getNome() == null){
			throw new Exception("Digite um c�digo de funcion�rio v�lido");
		}
		return new OrdemServicoDAO().pesqAtendente(cdFilial, cdFuncionario, c);
	}
	
	public static List<OrdemServico> pesqAtendData(int cdFilial, int cdFuncionario, Calendar data1, Calendar data2, Connection c)throws Exception{
		if(FuncionarioBO.pesqCodigo(cdFuncionario, c).getNome() == null){
			throw new Exception("Digite um c�digo de funcion�rio v�lido");
		}
		if(data2.get(Calendar.ERA) > data1.get(Calendar.ERA) || 
				data2.get(Calendar.YEAR) > data1.get(Calendar.YEAR) ||
				data2.get(Calendar.DAY_OF_YEAR) > data1.get(Calendar.DAY_OF_YEAR)){
			throw new Exception("A data da OS n�o pode ser depois da data de hoje");
		}
		return new OrdemServicoDAO().pesqAtendData(cdFilial, cdFuncionario, data1, data2, c);
	}
	
	public static List<OrdemServico> pesqVeiculo(int cdFilial, int cdVeiculo, Connection c)throws Exception{
		if(VeiculoBO.pesqCodigo(cdVeiculo, c).getPlaca() == null){
			throw new Exception("Digite um c�digo de veiculo v�lido");
		}
		return new OrdemServicoDAO().pesqVeiculo(cdFilial, cdVeiculo, c);
	}
	
	public static List<OrdemServico> pesVeicData(int cdFilial, int cdVeiculo, Calendar data1, Calendar data2, Connection c)throws Exception{
		if(VeiculoBO.pesqCodigo(cdVeiculo, c).getPlaca() == null){
			throw new Exception("Digite um c�digo de veiculo v�lido");
		}
		if(data2.get(Calendar.ERA) > data1.get(Calendar.ERA) || 
				data2.get(Calendar.YEAR) > data1.get(Calendar.YEAR) ||
				data2.get(Calendar.DAY_OF_YEAR) > data1.get(Calendar.DAY_OF_YEAR)){
			throw new Exception("A data da OS n�o pode ser depois da data de hoje");
		}
		return new OrdemServicoDAO().pesVeicData(cdFilial, cdVeiculo, data1, data2, c);
	}
	
	public static OrdemServico pesqNumeroOs(int nrOs, Connection c)throws Exception{
		if(new OrdemServicoDAO().pesqNumeroOs(nrOs, c).getNumero() == 0){
			throw new Exception("Digite um n�mero de os v�lido");
		}
		return new OrdemServicoDAO().pesqNumeroOs(nrOs, c);
	}
	
	public static OrdemServico pesqNumeroOsFilial(int cdFilial, int nrOs, Connection c)throws Exception{
		if(new OrdemServicoDAO().pesqNumeroOsFilial(cdFilial, nrOs, c).getNumero() == 0){
			throw new Exception("O.S n�o encontrada");
		}
		return new OrdemServicoDAO().pesqNumeroOsFilial(cdFilial, nrOs, c);
	}

}
