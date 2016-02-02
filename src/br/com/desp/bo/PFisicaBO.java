package br.com.desp.bo;

import java.sql.Connection;

import br.com.desp.dao.PessoaFisicaDAO;

public abstract class PFisicaBO {
	
	public static int pesqCpf(long cpf, Connection c)throws Exception{
		int ver = new PessoaFisicaDAO().pesqCpf(cpf, c);
		if(ver == 1){
			throw new Exception("Este cpf ja é cadastrado!");
		}
		return ver;
	}

}
