package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.OrdemServico;
import br.com.desp.beans.Veiculo;
import br.com.desp.dao.VeiculoDAO;

public abstract class VeiculoBO {

	public static int cadastrarVeic(Veiculo veic, Connection c)throws Exception{
		if(Long.toString(veic.getRenavam()).length() != 11){
			throw new Exception("Digite um renavam v�lido");
		}
		if(veic.getPlaca().length() < 7){
			throw new Exception("Digite uma placa v�lida");
		}
		
		return new VeiculoDAO().cadastrarVeic(veic, c);
	}
	
	public static void cadastrar(OrdemServico os, Connection c)throws Exception{
		if(VeiculoBO.pesqCodigo(os.getVeiculo().getCodigo(), c).getPlaca() == null){
			os.getVeiculo().setCodigo(VeiculoBO.cadastrarVeic(os.getVeiculo(), c));
		}
		if(OrdemServicoBO.pesqNumeroOs(os.getNumero(), c).getNumero() == 0){
			throw new Exception("OS inv�lida");
		}
		new VeiculoDAO().cadastrar(os, c);
	}
	
	public static void editarVeic(Veiculo veic, Connection c)throws Exception{
		if(new VeiculoDAO().pesqCodigo(veic.getCodigo(), c).getPlaca() == null){
			throw new Exception("C�digo inv�lido");
		}
		if(veic.getPlaca().length() < 7){
			throw new Exception("A placa deve ter 7 caract�rs");
		}
		if(Long.toString(veic.getRenavam()).length() != 11){
			throw new Exception("Digite um revanam v�lido, deve ter 11 caract�rs");
		}
		if(TipoVeiculoBO.pesqCodigo(veic.getTipo().getCodigo(), c).getDescricao() == null){
			throw new Exception("Escolha um tipo de ve�culo");
		}
		new VeiculoDAO().editar(veic, c);
	}
	
	public static void deletar(Veiculo veic, Connection c)throws Exception{
		if(VeiculoBO.pesqCodigo(veic.getCodigo(), c).getPlaca() == null){
			throw new Exception("Veiculo n�o encontrado, por gentileza confira o c�digo digitado");
		}
		new VeiculoDAO().deletar(veic, c);
	}
	
	public static List<Veiculo> listar(Connection c)throws Exception{
		return new VeiculoDAO().listar(c);
	}
	
	public static Veiculo pesqCodigo(int codigo, Connection c)throws Exception{
		if(new VeiculoDAO().pesqCodigo(codigo, c).getPlaca() == null){
			throw new Exception("Placa n�o encontrada");
		}
		return new VeiculoDAO().pesqCodigo(codigo, c);
	}
	
	public static Veiculo pesqPlaca(String placa, Connection c)throws Exception{
		if(placa.length() < 7){
			throw new Exception("Digite uma placa v�lida");
		}
		return new VeiculoDAO().pesqPlaca(placa, c);
	}
	
	public static Veiculo pesqRenavam(int renavam, Connection c)throws Exception{
		if(Integer.toString(renavam).length() != 11){
			throw new Exception("Digite um renavam v�lido");
		}
		return new VeiculoDAO().pesqRenavam(renavam, c);
	}
	
}
