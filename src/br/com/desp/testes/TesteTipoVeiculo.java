package br.com.desp.testes;

import java.sql.Connection;

import br.com.desp.beans.TipoVeiculo;
import br.com.desp.bo.TipoVeiculoBO;
import br.com.desp.conexao.ConexaoFactory;

public class TesteTipoVeiculo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			TipoVeiculo tpVeiculo = new TipoVeiculo();
			
			tpVeiculo = TipoVeiculoBO.pesqCodigo(1, c);
		
			System.out.println(tpVeiculo.getCodigo());
			System.out.println(tpVeiculo.getDescricao());
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
