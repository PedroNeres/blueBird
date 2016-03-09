package br.com.desp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.desp.beans.Mensagem;

public class MensagemDAO {
	
	public void enviar(Mensagem men, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_MENSAGENS(ds_mensagem, ds_assunto, ds_email_remetente, ds_nome_remetente,"
				+ " ds_email_destinatario, ds_nome_destinatario, dt_envio, nr_status) VALUES(?,?,?,?,?,?,?,?)";
		
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, men.getMensagem());
		estrutura.setString(2, men.getAssunto());
		estrutura.setString(3, men.getEmailRemetente());
		estrutura.setString(4, men.getNomeRemetente());
		estrutura.setString(5, men.getEmailDestinatario());
		estrutura.setString(6, men.getNomeDestinatario());
		estrutura.setDate(7, new Date(men.getDtEnvio().getTimeInMillis()));
		estrutura.setInt(8, men.getStatus());
		
		estrutura.execute();
		estrutura.close();
	}
	
	public void abrir(int codigo, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_MENSAGENS SET nr_status = 0 WHERE cd_mensagem = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		estrutura.execute();
		estrutura.close();
	}
	
	public void deletar(int codigo, Connection c)throws Exception{
		String sql = "DELETE FROM T_DESP_MENSAGENS WHERE cd_mensagem = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		estrutura.execute();
		estrutura.close();
	}
	
	public List<Mensagem> listarRecebidas(String email, Connection c)throws Exception{
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		Mensagem men = null;
		String sql = "SELECT * FROM T_DESP_MENSAGENS WHERE ds_email_destinatario = ? ORDER BY dt_envio DESC";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, email);
		ResultSet rs = estrutura.executeQuery();
		
		while(rs.next()){
			men = new Mensagem();
			
			men.setAssunto(rs.getString("ds_assunto"));
			men.setCodigo(rs.getInt("cd_mensagem"));
			men.setEmailDestinatario(rs.getString("ds_email_destinatario"));
			men.setNomeDestinatario(rs.getString("ds_nome_destinatario"));
			men.setEmailRemetente(rs.getString("ds_email_remetente"));
			men.setNomeRemetente(rs.getString("ds_nome_remetente"));
			men.setStatus(rs.getInt("nr_status"));
			men.setMensagem(rs.getString("ds_mensagem"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_envio"));
			men.setDtEnvio(cal);
			mensagens.add(men);
		}
		rs.close();
		estrutura.close();
		return mensagens;
	}
	
	public List<Mensagem> listarEnviadas(String email, Connection c)throws Exception{
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		Mensagem men = null;
		String sql = "SELECT * FROM T_DESP_MENSAGENS WHERE ds_email_remetente = ? ORDER BY dt_envio DESC";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, email);
		ResultSet rs = estrutura.executeQuery();
		
		while(rs.next()){
			men = new Mensagem();
			
			men.setAssunto(rs.getString("ds_assunto"));
			men.setCodigo(rs.getInt("cd_mensagem"));
			men.setEmailDestinatario(rs.getString("ds_email_destinatario"));
			men.setNomeDestinatario(rs.getString("ds_nome_destinatario"));
			men.setEmailRemetente(rs.getString("ds_email_remetente"));
			men.setNomeRemetente(rs.getString("ds_nome_remetente"));
			men.setStatus(rs.getInt("nr_status"));
			men.setMensagem(rs.getString("ds_mensagem"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_envio"));
			men.setDtEnvio(cal);
			mensagens.add(men);
		}
		rs.close();
		estrutura.close();
		return mensagens;
	}
	
	public List<Mensagem> naoLidos(String email, Connection c)throws Exception{
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		Mensagem men = null;
		String sql = "SELECT * FROM T_DESP_MENSAGENS WHERE ds_email_destinatario = ? "
				+ "AND nr_status = 1 ORDER BY dt_envio DESC";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, email);
		ResultSet rs = estrutura.executeQuery();
		
		while(rs.next()){
			men = new Mensagem();
			
			men.setAssunto(rs.getString("ds_assunto"));
			men.setCodigo(rs.getInt("cd_mensagem"));
			men.setEmailDestinatario(rs.getString("ds_email_destinatario"));
			men.setNomeDestinatario(rs.getString("ds_nome_destinatario"));
			men.setEmailRemetente(rs.getString("ds_email_remetente"));
			men.setNomeRemetente(rs.getString("ds_nome_remetente"));
			men.setStatus(rs.getInt("nr_status"));
			men.setMensagem(rs.getString("ds_mensagem"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_envio"));
			men.setDtEnvio(cal);
			mensagens.add(men);
		}
		rs.close();
		estrutura.close();
		return mensagens;
	}
	
	public List<Mensagem> pesqNome(String nome, String email, Connection c)throws Exception{
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		Mensagem men = null;
		
		String sql = "SELECT * FROM T_DESP_MENSAGENS WHERE ds_email_destinatario = ? "
				+ "AND ds_nome_remetente = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setString(1, email);
		estrutura.setString(2, nome);
		
		ResultSet rs = estrutura.executeQuery();
		
		while(rs.next()){
			men = new Mensagem();
			men.setAssunto(rs.getString("ds_assunto"));
			men.setCodigo(rs.getInt("cd_mensagem"));
			men.setEmailDestinatario(rs.getString("ds_email_destinatario"));
			men.setNomeDestinatario(rs.getString("ds_nome_destinatario"));
			men.setEmailRemetente(rs.getString("ds_email_remetente"));
			men.setNomeRemetente(rs.getString("ds_nome_remetente"));
			men.setStatus(rs.getInt("nr_status"));
			men.setMensagem(rs.getString("ds_mensagem"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_envio"));
			men.setDtEnvio(cal);
			mensagens.add(men);
		}
		rs.close();
		estrutura.close();
		return mensagens;
	}
	
	public Mensagem pesqCodigo(int codigo, Connection c)throws Exception{
		Mensagem men = new Mensagem();
		
		String sql = "SELECT * FROM T_DESP_MENSAGENS WHERE cd_mensagem = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, codigo);
		ResultSet rs = estrutura.executeQuery();
		
		if(rs.next()){
			men.setAssunto(rs.getString("ds_assunto"));
			men.setCodigo(rs.getInt("cd_mensagem"));
			men.setEmailDestinatario(rs.getString("ds_email_destinatario"));
			men.setNomeDestinatario(rs.getString("ds_nome_destinatario"));
			men.setEmailRemetente(rs.getString("ds_email_remetente"));
			men.setNomeRemetente(rs.getString("ds_nome_remetente"));
			men.setStatus(rs.getInt("nr_status"));
			men.setMensagem(rs.getString("ds_mensagem"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(rs.getDate("dt_envio"));
			men.setDtEnvio(cal);
		}
		rs.close();
		estrutura.close();
		return men;
	}

}
