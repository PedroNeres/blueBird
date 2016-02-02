package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.ItemServico;
import br.com.desp.dao.ItemServicoDAO;

public abstract class ItemServicoBO {
	
	public static void cadastrar(ItemServico is, Connection c)throws Exception{
		if(is.getOs().getNumero() == 0){
			throw new Exception("Escolha uma ordem de servi�o v�lida");
		}
		if(is.getQuantidade() == 0){
			throw new Exception("Quantidade de servi�o inv�lida");
		}
		if(is.getServico().getCodigo() == 0){
			throw new Exception("Escolha um servi�o v�lido");
		}
		if(is.getVlrTotal() != is.getServico().getVlrTotal() * is.getQuantidade()){
			throw new Exception("Por favor! Entre em contato com o desenvolvedor, h� algo de arrado.");
		}
		new ItemServicoDAO().cadastrar(is, c);
	}
	
	public static void deletar(ItemServico is, Connection c)throws Exception{
		if(is.getCodigo() == 0){
			throw new Exception("N�o foi encontrado o servi�o que seleciou para exclus�o");
		}
		new ItemServicoDAO().deletar(is, c);
	}
	
	public static void editar(ItemServico is, Connection c)throws Exception{
		if(is.getOs().getNumero() == 0){
			throw new Exception("Escolha uma ordem de servi�o v�lida");
		}
		if(is.getQuantidade() == 0){
			throw new Exception("Quantidade de servi�o inv�lida");
		}
		if(is.getServico().getCodigo() == 0){
			throw new Exception("Escolha um servi�o v�lido");
		}
		if(is.getVlrTotal() != is.getServico().getVlrTotal() * is.getQuantidade()){
			throw new Exception("Por favor! Entre em contato com o desenvolvedo, h� algo de arrado.");
		}
		new ItemServicoDAO().editar(is, c);
	}
	
	public static List<ItemServico> listarServOs(int nrOrdem, Connection c)throws Exception{
		if(nrOrdem == 0){
			throw new Exception("Selecione uma ordem de servi�o para listar seus itens");
		}
		return new ItemServicoDAO().listarServOs(nrOrdem, c);
	}
	
	public static ItemServico pesqCodigo(int codigo, Connection c)throws Exception{
		if(codigo == 0){
			throw new Exception("C�digo do item de servi�o inv�lido");
		}
		return new ItemServicoDAO().pesqCodigo(codigo, c);
	}
	
}