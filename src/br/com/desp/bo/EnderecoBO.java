package br.com.desp.bo;

import java.sql.Connection;

import br.com.desp.beans.Endereco;
import br.com.desp.dao.EnderecoDAO;

public abstract class EnderecoBO {
	
	
	//CHAMANDO O M�TODO DE CADASTRAR DA CLASSE ENDERECODAO
	public static void gravar(Endereco e, Connection c, int indice)throws Exception{
		int cdEstado = 0;
		int cdCidade = 0;
		int cdBairro = 0;
		if(EnderecoBO.verificarEstado(e.getUf(), c) == 0) {
			cdEstado = EnderecoBO.gravarEstado(e.getUf(), c);
		}else{
			cdEstado = EnderecoBO.verificarEstado(e.getUf(), c);
		}
		if(EnderecoBO.verificarCidade(e.getCidade(), c) == 0){
			cdCidade = EnderecoBO.gravarCidade(cdEstado, e.getCidade(), c);
		}else{
			cdCidade = EnderecoBO.verificarCidade(e.getCidade(), c);
		}
		if(EnderecoBO.veriricarBairro(e.getBairro(), c) == 0){
			cdBairro = EnderecoBO.gravarBairro(cdCidade, e.getBairro(), c);
		}else{
			cdBairro = EnderecoBO.veriricarBairro(e.getBairro(), c);
		}
		if(EnderecoBO.vericarCep(e.getCep(), c) == 0){
			EnderecoBO.gravarLogradouro(e, cdBairro, c);
		}
		
		if(e.getNumero() < 1){
			throw new Exception("Digite um n�mero do endereco v�lido");
		}
		if(Integer.toString(e.getCep()).length() > 8 || e.getCep() == 0){
			throw new Exception("Digite um Cep v�lido com no m�ximo 8 d�gitos");
		}
		new EnderecoDAO().cadastrar(e, c, indice);
	}
	
	public static void atualizar(Endereco e, Connection c, int indice)throws Exception{
		int cdEstado = 0;
		int cdCidade = 0;
		int cdBairro = 0;
		if(new EnderecoDAO().verificarEstado(e.getUf(), c) == 0){
			cdEstado = new EnderecoDAO().gravarEstado(e.getUf(), c);
		}else{
			cdEstado = new EnderecoDAO().verificarEstado(e.getUf(), c);
		}
		if(new EnderecoDAO().verificarCidade(e.getCidade(), c) == 0){
			cdCidade = new EnderecoDAO().gravarCidade(cdEstado, e.getCidade(), c);
		}else{
			cdCidade = new EnderecoDAO().verificarCidade(e.getCidade(), c);
		}
		if(new EnderecoDAO().verificarBairro(e.getBairro(), c) == 0){
			cdBairro = new EnderecoDAO().gravarBairro(cdCidade, e.getBairro(), c);
		}else{
			cdBairro = new EnderecoDAO().verificarBairro(e.getBairro(), c);
		}
		if(new EnderecoDAO().verificarLogradouro(e.getCep(), c) == 0){
			new EnderecoDAO().gravarLogradouro(e, cdBairro, c);
		}
		if(e.getNumero() < 1){
			throw new Exception("Digite um n�mero do endereco v�lido");
		}
		if(Integer.toString(e.getCep()).length() > 8 || e.getCep() == 0){
			throw new Exception("Digite um Cep v�lido com no m�ximo 8 d�gitos");
		}
		if(e.getComplemento().length() > 20){
			throw new Exception("Digite um complemento de no m�ximo 20 caract�rs");
		}
		new EnderecoDAO().atualizar(e, c, indice);
	}
	
	//CHAMANDO O M�TODO DE VERIFICAR CIDADE DA CLASSE ENDERECODAO
	public static int verificarCidade(String cidade, Connection c)throws Exception{
		return new EnderecoDAO().verificarCidade(cidade, c);
	}
	
	//CHAMANDO O M�TODO QUE RETORNA O C�DIGO DO ESTADO
	public static int verificarEstado(String estado, Connection c)throws Exception{
		return new EnderecoDAO().verificarEstado(estado, c);
	}
	
	public static Endereco buscarEndePes(int codigo, Connection c)throws Exception{
		if(codigo == 0){
			throw new Exception("Digite um c�digo de pessoa correto");
		}
		return new EnderecoDAO().buscarEndePes(codigo, c);
	}
	
	//CHAMANDO O M�TODO QUE RETORNA O C�DIGO DO BAIRRO
	public static int veriricarBairro(String bairro, Connection c)throws Exception{
		return new EnderecoDAO().verificarBairro(bairro, c);
	}
	
	//CHAMANDO O M�TODO QUE VERIFICA SE O CEP JA � CADASTRADO
	public static int vericarCep(int cep, Connection c)throws Exception{
		return new EnderecoDAO().verificarLogradouro(cep, c);
	}
	
	//CHAMANDO O M�TODO QUE GRAVA CIDADE
	public static int gravarCidade(int cdEstado, String cidade, Connection c)throws Exception{
		if(cidade.length() < 3 || cidade == null){
			throw new Exception("Digite uma cidade v�lida");
		}
		return new EnderecoDAO().gravarCidade(cdEstado, cidade, c);
	}
	
	
	//CHAMANDO O M�TODO QUE GRAVA O BAIRRO
	public static int gravarBairro(int cdCidade, String bairro, Connection c)throws Exception{
		if(bairro.length() < 3 || bairro == null){
			throw new Exception("Digite um bairro v�lido");
		}
		return new EnderecoDAO().gravarBairro(cdCidade, bairro, c);
	}

	
	//CHAMANDO O M�TODO QUE GRAVA LOGRADOURO
	public static void gravarLogradouro(Endereco e, int cdBairro, Connection c)throws Exception{
		if(e.getLogradouro().length() < 3 || e.getLogradouro().length() > 60){
			throw new Exception("O logradouro deve ter no m�nimo 3 caract�rs e no m�ximo 60");
		}
		if(Integer.toString(e.getCep()).length() > 8){
			throw new Exception("Digite um cep v�lido com no m�ximo 8 d�gitos");
		}
		new EnderecoDAO().gravarLogradouro(e, cdBairro, c);
	}
	
	//CHAMANDO O M�TODO QUE GRAVA O ESTADO
	public static int gravarEstado(String estado, Connection c)throws Exception{
		if(estado.length() < 2 || estado == null){
			throw new Exception("Digite um Estado v�lido");
		}
		return new EnderecoDAO().gravarEstado(estado, c);
	}
	
}
