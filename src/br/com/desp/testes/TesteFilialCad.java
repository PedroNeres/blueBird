package br.com.desp.testes;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.desp.beans.Despachante;
import br.com.desp.beans.Endereco;
import br.com.desp.beans.Filial;
import br.com.desp.beans.Telefone;
import br.com.desp.beans.Usuario;
import br.com.desp.bo.DespachanteBO;
import br.com.desp.bo.FilialBO;
import br.com.desp.conexao.ConexaoFactory;

public class TesteFilialCad {
	
	public static void main(String[] args) {
		Connection c = null;
		
		try{
			c = ConexaoFactory.controlarInstancia().getConnection();
			c.setAutoCommit(false);
			Filial fil = new Filial();
			
			fil.setNome("DESPACHANTE GOL DE PLACA");
			
			Usuario user = new Usuario();
			user.setEmail("GILEADE@GOLDEPLACA.COM.BR");
			user.setPassword("135086");
			
			fil.setUsuario(user);
			
			fil.setStatus(0);
			
			Despachante desp = new Despachante();
			desp = DespachanteBO.pesqSsp(7081, c);
			
			fil.setDespachante(desp);
			
			List<Telefone> tels = new ArrayList<Telefone>();
			Telefone tel = new Telefone();
			tel.setDdd(11);
			tel.setNumero(24317010);
			tels.add(tel);
			tel = new Telefone();
			tel.setDdd(11);
			tel.setNumero(24322795);
			tels.add(tel);
			
			fil.setTelefone(tels);
			
			Endereco end = new Endereco();
			end.setBairro("JARDIM PRESIDENTE DUTRA");
			end.setCep(07170350);
			end.setCidade("GUARULHOS");
			end.setLogradouro("AV. PAPA JOÃO PAULO I");
			end.setNumero(3947);
			end.setComplemento("1 andar");
			end.setUf("SP");
			
			fil.setEndereco(end);
			
			FilialBO.cadastrar(fil, c);
			System.out.println("Filial Cadastrada com sucesso");
			c.commit();
			c.setAutoCommit(true);
			
		}catch(Exception e){
			try{
				c.rollback();
				e.printStackTrace();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}

}
