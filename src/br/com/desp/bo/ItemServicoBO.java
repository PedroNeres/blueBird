package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.ItemServico;
import br.com.desp.dao.ItemServicoDAO;

public abstract class ItemServicoBO {
	
	public static void cadastrar(ItemServico is, Connection c)throws Exception{
		if(is.getOs().getNumero() == 0){
			throw new Exception("Escolha uma ordem de serviço válida");
		}
		if(is.getQuantidade() == 0){
			throw new Exception("Quantidade de serviço inválida");
		}
		if(is.getServico().getCodigo() == 0){
			throw new Exception("Escolha um serviço válido");
		}
		if(is.getVlrTotal() != is.getServico().getVlrTotal() * is.getQuantidade()){
			throw new Exception("Por favor! Entre em contato com o desenvolvedor, há algo de arrado.");
		}
		new ItemServicoDAO().cadastrar(is, c);
	}
	
	public static void deletar(ItemServico is, Connection c)throws Exception{
		if(is.getCodigo() == 0){
			throw new Exception("Não foi encontrado o serviço que seleciou para exclusão");
		}
		new ItemServicoDAO().deletar(is, c);
	}
	
	public static void editar(ItemServico is, Connection c)throws Exception{
		if(is.getOs().getNumero() == 0){
			throw new Exception("Escolha uma ordem de serviço válida");
		}
		if(is.getQuantidade() == 0){
			throw new Exception("Quantidade de serviço inválida");
		}
		if(is.getServico().getCodigo() == 0){
			throw new Exception("Escolha um serviço válido");
		}
		if(is.getVlrTotal() != is.getServico().getVlrTotal() * is.getQuantidade()){
			throw new Exception("Por favor! Entre em contato com o desenvolvedo, há algo de arrado.");
		}
		new ItemServicoDAO().editar(is, c);
	}
	
	public static List<ItemServico> listarServOs(int nrOrdem, Connection c)throws Exception{
		if(nrOrdem == 0){
			throw new Exception("Selecione uma ordem de serviço para listar seus itens");
		}
		return new ItemServicoDAO().listarServOs(nrOrdem, c);
	}
	
	public static ItemServico pesqCodigo(int codigo, Connection c)throws Exception{
		if(codigo == 0){
			throw new Exception("Código do item de serviço inválido");
		}
		return new ItemServicoDAO().pesqCodigo(codigo, c);
	}
	
}