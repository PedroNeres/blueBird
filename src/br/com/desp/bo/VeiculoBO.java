package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.OrdemServico;
import br.com.desp.beans.Veiculo;
import br.com.desp.dao.VeiculoDAO;

public abstract class VeiculoBO {

	public static int cadastrarVeic(Veiculo veic, Connection c)throws Exception{
		if(Long.toString(veic.getRenavam()).length() != 11){
			throw new Exception("Digite um renavam válido");
		}
		if(veic.getPlaca().length() < 7){
			throw new Exception("Digite uma placa válida");
		}
		
		return new VeiculoDAO().cadastrarVeic(veic, c);
	}
	
	public static void cadastrar(OrdemServico os, Connection c)throws Exception{
		if(VeiculoBO.pesqCodigo(os.getVeiculo().getCodigo(), c).getPlaca() == null){
			os.getVeiculo().setCodigo(VeiculoBO.cadastrarVeic(os.getVeiculo(), c));
		}
		if(OrdemServicoBO.pesqNumeroOs(os.getNumero(), c).getNumero() == 0){
			throw new Exception("OS inválida");
		}
		new VeiculoDAO().cadastrar(os, c);
	}
	
	public static void editarVeic(Veiculo veic, Connection c)throws Exception{
		if(new VeiculoDAO().pesqCodigo(veic.getCodigo(), c).getPlaca() == null){
			throw new Exception("Código inválido");
		}
		if(veic.getPlaca().length() < 7){
			throw new Exception("A placa deve ter 7 caractérs");
		}
		if(Long.toString(veic.getRenavam()).length() != 11){
			throw new Exception("Digite um revanam válido, deve ter 11 caractérs");
		}
		if(TipoVeiculoBO.pesqCodigo(veic.getTipo().getCodigo(), c).getDescricao() == null){
			throw new Exception("Escolha um tipo de veículo");
		}
		new VeiculoDAO().editar(veic, c);
	}
	
	public static void deletar(Veiculo veic, Connection c)throws Exception{
		if(VeiculoBO.pesqCodigo(veic.getCodigo(), c).getPlaca() == null){
			throw new Exception("Veiculo não encontrado, por gentileza confira o código digitado");
		}
		new VeiculoDAO().deletar(veic, c);
	}
	
	public static List<Veiculo> listar(Connection c)throws Exception{
		return new VeiculoDAO().listar(c);
	}
	
	public static Veiculo pesqCodigo(int codigo, Connection c)throws Exception{
		if(new VeiculoDAO().pesqCodigo(codigo, c).getPlaca() == null){
			throw new Exception("Placa não encontrada");
		}
		return new VeiculoDAO().pesqCodigo(codigo, c);
	}
	
	public static Veiculo pesqPlaca(String placa, Connection c)throws Exception{
		if(placa.length() < 7){
			throw new Exception("Digite uma placa válida");
		}
		return new VeiculoDAO().pesqPlaca(placa, c);
	}
	
	public static Veiculo pesqRenavam(int renavam, Connection c)throws Exception{
		if(Integer.toString(renavam).length() != 11){
			throw new Exception("Digite um renavam válido");
		}
		return new VeiculoDAO().pesqRenavam(renavam, c);
	}
	
}
