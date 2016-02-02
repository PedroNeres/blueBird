package br.com.desp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.desp.beans.Telefone;
import br.com.desp.dao.TelefoneDAO;

public abstract class TelefoneBO {
	
	//M�TODO ESTATICO QUE CHAMA O M�TODO DE CADASTRAR DO TELEFONEDAO
	public static void gravar(List<Telefone> tel, Connection c, int indice)throws Exception{
		new TelefoneDAO().cadastrar(tel, c, indice);
	}
	
	public static List<Telefone> buscarTels(int cdPessoa, Connection c)throws Exception{
		return new TelefoneDAO().telefones(cdPessoa, c);
	}
	
	public static void atualizar(List<Telefone> tels, Connection c)throws Exception{
		for(Telefone t: tels){
			if(Integer.toString(t.getDdd()).length() != 2){
				throw new Exception("Digite um ddd v�lido");
			}
			if(Integer.toString(t.getNumero()).length() < 8 || Integer.toString(t.getNumero()).length() > 9){
				throw new Exception("Digite um n�mero de telefone v�lido");
			}
		}
		new TelefoneDAO().atualizar(tels, c);
	}
	
	//M�TODO ESTATICO QUE CHAMA O M�TODO DE RECUPERAR LISTA DE TIPO DE FONES
	public static List<Integer> recuperarTipos(Connection c)throws Exception{
		return new TelefoneDAO().recuperarTipos(c);
	}
}
