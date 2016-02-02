package br.com.desp.testes;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.Filial;
import br.com.desp.bo.FilialBO;
import br.com.desp.conexao.ConexaoFactory;

public class TesteListDespachante {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection c = null;
		
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();
			
			List<Filial> filiais = new ArrayList<Filial>();
			
			filiais = FilialBO.listFilDesp(1, c);
			
			for(Filial fil:filiais){
				System.out.println(fil.getNome());
				System.out.println(fil.getDespachante().getCodigo());
			}
			
			Filial fil = FilialBO.pesqCodigo(25, c);
			System.out.println(fil.getNome());
			System.out.println(fil.getDespachante().getSsp());
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
