package br.com.desp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.desp.beans.ItemServico;
import br.com.desp.beans.MudarStatus;
import br.com.desp.beans.OrdemServico;
import br.com.desp.beans.Pagamento;
import br.com.desp.bo.ClienteBO;
import br.com.desp.bo.FuncionarioBO;
import br.com.desp.bo.ItemServicoBO;
import br.com.desp.bo.MudarStatusOsBO;
import br.com.desp.bo.PagamentoBO;
import br.com.desp.bo.TipoOrdemBO;
import br.com.desp.bo.VeiculoBO;

public class OrdemServicoDAO {
	
	public int cadastrar(OrdemServico os, Connection c)throws Exception{
		String sql = "INSERT INTO T_DESP_ORDEM_SERVICO(cd_cliente, cd_funcionario, dt_entrada, vl_total, "
				+ "vl_desconto, vl_pago, cd_tipo_ordem, cd_veiculo, cd_mudar_status) VALUES(?,?,?,?,?,?,?,?,5)";
		PreparedStatement estrutura = c.prepareStatement(sql, new String[]{"nr_ordem"});
		estrutura.setInt(1, os.getCliente().getCodigo());
		estrutura.setInt(2, os.getAtendente().getCodigo());
		estrutura.setDate(3, new Date(os.getDtEntrada().getTimeInMillis()));
		estrutura.setDouble(4, os.getTotal());
		estrutura.setDouble(5, os.getDesconto());
		estrutura.setDouble(6, os.getVlrPago());
		estrutura.setInt(7, os.getTipo().getCodigo());
		estrutura.setInt(8, os.getVeiculo().getCodigo());
		estrutura.execute();
		
		ResultSet rs = estrutura.getGeneratedKeys();
		rs.next();
		os.setNumero(Integer.parseInt(rs.getString(1)));
		
		rs.close();
		estrutura.close();
		for(Pagamento pag: os.getPagamento()){
			pag.setOrdemServico(os);
			PagamentoBO.cadastrar(pag, c);
		}
		for(ItemServico itSer: os.getServicos()){
			itSer.setOs(os);
			ItemServicoBO.cadastrar(itSer, c);
		}
	
		
		VeiculoBO.cadastrar(os, c);
		
		return os.getNumero();
	}
	
	public void editar(OrdemServico os, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_ORDEM_SERVICO SET cd_cliente =? , cd_funcionario = ?, dt_entrada = ?, vl_total = ?, "
				+ "vl_desconto = ?, vl_pago = ?, cd_mudar_status = ?, cd_tipo_ordem = ? WHERE nr_ordem = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, os.getCliente().getCodigo());
		estrutura.setInt(2, os.getAtendente().getCodigo());
		estrutura.setDate(3, new Date(os.getDtEntrada().getTimeInMillis()));
		estrutura.setDouble(4, os.getTotal());
		estrutura.setDouble(5, os.getDesconto());
		estrutura.setDouble(6, os.getVlrPago());
		estrutura.setInt(7, os.getStatus().getCodigo());
		estrutura.setInt(8, os.getTipo().getCodigo());
		estrutura.setInt(9, os.getNumero());
		estrutura.execute();
		estrutura.close();
	}
	
	public void mudarStatus(MudarStatus ms, Connection c)throws Exception{
		String sql = "UPDATE T_DESP_ORDEM_SERVICO SET cd_mudar_status = ? WHERE nr_ordem = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, ms.getCodigo());
		estrutura.setInt(2, ms.getOrdemServico().getNumero());
		estrutura.execute();
		estrutura.close();
	}
	
	public List<OrdemServico> listar(int cdFilial, Connection c)throws Exception{
		List<OrdemServico> ordemServicos = new ArrayList<OrdemServico>();
		OrdemServico os = null;
		String sql = "SELECT * FROM T_DESP_ORDEM_SERVICO OS INNER JOIN T_DESP_FUNCIONARIO FUN ON(OS.CD_FUNCIONARIO = FUN.CD_FUNCIONARIO) WHERE FUN.CD_FILIAL = ? ORDER BY nr_ordem DESC";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			os = new OrdemServico();
			Calendar cal = Calendar.getInstance();
			os.setAtendente(FuncionarioBO.pesqCodigo(rs.getInt("cd_funcionario"), c));
			os.setCliente(ClienteBO.pesqCodigo(rs.getInt("cd_cliente"), c));
			os.setDesconto(rs.getDouble("vl_desconto"));
			cal.setTime(rs.getDate("dt_entrada"));
			os.setDtEntrada(cal);
			os.setNumero(rs.getInt("nr_ordem"));
			os.setPagamento(PagamentoBO.listarOrdem(os.getNumero(), c));
			os.setServicos(ItemServicoBO.listarServOs(os.getNumero(), c));
			os.setStatus(MudarStatusOsBO.pesqCodigo(rs.getInt("cd_mudar_status"), c));
			os.setTipo(TipoOrdemBO.pesqCodigo(rs.getInt("cd_tipo_ordem"), c));
			os.setTotal(rs.getDouble("vl_total"));
			os.setVeiculo(VeiculoBO.pesqCodigo(rs.getInt("cd_veiculo"), c));
			os.setVlrPago(rs.getDouble("vl_pago"));
			ordemServicos.add(os);
		}
		rs.close();
		estrutura.close();
		return ordemServicos;
	}
	
	public List<OrdemServico> pesqData(int cdFilial, Calendar data1, Calendar data2, Connection c)throws Exception{
		List<OrdemServico> ordemServicos = new ArrayList<OrdemServico>();
		OrdemServico os = null;
		String sql = "SELECT * FROM T_DESP_ORDEM_SERVICO OS INNER JOIN T_DESP_FUNCIONARIO FUN ON(OS.CD_FUNCIONARIO = FUN.CD_FUNCIONARIO) WHERE FUN.CD_FILIAL = ?  AND dt_entrada BETWEEN ? AND ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		estrutura.setDate(2, new Date(data1.getTimeInMillis()));
		estrutura.setDate(3, new Date(data2.getTimeInMillis()));
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			os = new OrdemServico();
			Calendar cal = Calendar.getInstance();
			os.setAtendente(FuncionarioBO.pesqCodigo(rs.getInt("cd_funcionario"), c));
			os.setCliente(ClienteBO.pesqCodigo(rs.getInt("cd_cliente"), c));
			os.setDesconto(rs.getDouble("vl_desconto"));
			cal.setTime(rs.getDate("dt_entrada"));
			os.setDtEntrada(cal);
			os.setNumero(rs.getInt("nr_ordem"));
			os.setPagamento(PagamentoBO.listarOrdem(os.getNumero(), c));
			os.setServicos(ItemServicoBO.listarServOs(os.getNumero(), c));
			os.setStatus(MudarStatusOsBO.pesqCodigo(rs.getInt("cd_mudar_status"), c));
			os.setTipo(TipoOrdemBO.pesqCodigo(rs.getInt("cd_tipo_ordem"), c));
			os.setTotal(rs.getDouble("vl_total"));
			os.setVeiculo(VeiculoBO.pesqCodigo(rs.getInt("cd_veiculo"), c));
			os.setVlrPago(rs.getDouble("vl_pago"));
			ordemServicos.add(os);
		}
		rs.close();
		estrutura.close();
		return ordemServicos;
	}
	
	public List<OrdemServico> pesqFilial(int cdFilial, Connection c)throws Exception{
		List<OrdemServico> ordemServicos = new ArrayList<OrdemServico>();
		OrdemServico os = null;
		String sql = "SELECT * FROM T_DESP_ORDEM_SERVICO OS INNER JOIN T_DESP_FUNCIONARIO FUN ON(OS.CD_FUNCIONARIO = FUN.CD_FUNCIONARIO) WHERE FUN.CD_FILIAL = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			os = new OrdemServico();
			Calendar cal = Calendar.getInstance();
			os.setAtendente(FuncionarioBO.pesqCodigo(rs.getInt("cd_funcionario"), c));
			os.setCliente(ClienteBO.pesqCodigo(rs.getInt("cd_cliente"), c));
			os.setDesconto(rs.getDouble("vl_desconto"));
			cal.setTime(rs.getDate("dt_entrada"));
			os.setDtEntrada(cal);
			os.setNumero(rs.getInt("nr_ordem"));
			os.setPagamento(PagamentoBO.listarOrdem(os.getNumero(), c));
			os.setServicos(ItemServicoBO.listarServOs(os.getNumero(), c));
			os.setStatus(MudarStatusOsBO.pesqCodigo(rs.getInt("cd_status"), c));
			os.setTipo(TipoOrdemBO.pesqCodigo(rs.getInt("cd_tipo_ordem"), c));
			os.setTotal(rs.getDouble("vl_total"));
			os.setVeiculo(VeiculoBO.pesqCodigo(rs.getInt("cd_veiculo"), c));
			os.setVlrPago(rs.getDouble("vl_pago"));
			if(FuncionarioBO.pesqCodigo(os.getAtendente().getCodigo(), c).getFilial().getCodigo() == cdFilial){
				ordemServicos.add(os);
			}
		}
		rs.close();
		estrutura.close();
		return ordemServicos;
	}
	
	public List<OrdemServico> pesqFilialData(Calendar data1, Calendar data2, int cdFilial, Connection c)throws Exception{
		List<OrdemServico> ordemServicos = new ArrayList<OrdemServico>();
		OrdemServico os = null;
		String sql = "SELECT * FROM T_DESP_ORDEM_SERVICO OS INNER JOIN T_DESP_FUNCIONARIO FUN ON(OS.CD_FUNCIONARIO = FUN.CD_FUNCIONARIO) AND FUN.CD_FILIAL = ? WHERE dt_entrada BETWEEN ? AND ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		estrutura.setDate(2, new Date(data1.getTimeInMillis()));
		estrutura.setDate(3, new Date(data2.getTimeInMillis()));
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			os = new OrdemServico();
			Calendar cal = Calendar.getInstance();
			os.setAtendente(FuncionarioBO.pesqCodigo(rs.getInt("cd_funcionario"), c));
			os.setCliente(ClienteBO.pesqCodigo(rs.getInt("cd_cliente"), c));
			os.setDesconto(rs.getDouble("vl_desconto"));
			cal.setTime(rs.getDate("dt_entrada"));
			os.setDtEntrada(cal);
			os.setNumero(rs.getInt("nr_ordem"));
			os.setPagamento(PagamentoBO.listarOrdem(os.getNumero(), c));
			os.setServicos(ItemServicoBO.listarServOs(os.getNumero(), c));
			os.setStatus(MudarStatusOsBO.pesqCodigo(rs.getInt("cd_status"), c));
			os.setTipo(TipoOrdemBO.pesqCodigo(rs.getInt("cd_tipo_ordem"), c));
			os.setTotal(rs.getDouble("vl_total"));
			os.setVeiculo(VeiculoBO.pesqCodigo(rs.getInt("cd_veiculo"), c));
			os.setVlrPago(rs.getDouble("vl_pago"));
			if(FuncionarioBO.pesqCodigo(os.getAtendente().getCodigo(), c).getFilial().getCodigo() == cdFilial){
				ordemServicos.add(os);
			}
		}
		rs.close();
		estrutura.close();
		return ordemServicos;
	}
	
	public List<OrdemServico> pesqCliente(int cdFilial, int cdCliente, Connection c)throws Exception{
		List<OrdemServico> ordemServicos = new ArrayList<OrdemServico>();
		OrdemServico os = null;
		String sql = "SELECT * FROM T_DESP_ORDEM_SERVICO OS INNER JOIN T_DESP_FUNCIONARIO FUN ON(OS.CD_FUNCIONARIO = FUN.CD_FUNCIONARIO) WHERE FUN.CD_FILIAL = ?  AND cd_cliente = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		estrutura.setInt(2, cdCliente);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			os = new OrdemServico();
			Calendar cal = Calendar.getInstance();
			os.setAtendente(FuncionarioBO.pesqCodigo(rs.getInt("cd_funcionario"), c));
			os.setCliente(ClienteBO.pesqCodigo(rs.getInt("cd_cliente"), c));
			os.setDesconto(rs.getDouble("vl_desconto"));
			cal.setTime(rs.getDate("dt_entrada"));
			os.setDtEntrada(cal);
			os.setNumero(rs.getInt("nr_ordem"));
			os.setPagamento(PagamentoBO.listarOrdem(os.getNumero(), c));
			os.setServicos(ItemServicoBO.listarServOs(os.getNumero(), c));
			os.setStatus(MudarStatusOsBO.pesqCodigo(rs.getInt("cd_mudar_status"), c));
			os.setTipo(TipoOrdemBO.pesqCodigo(rs.getInt("cd_tipo_ordem"), c));
			os.setTotal(rs.getDouble("vl_total"));
			os.setVeiculo(VeiculoBO.pesqCodigo(rs.getInt("cd_veiculo"), c));
			os.setVlrPago(rs.getDouble("vl_pago"));
			ordemServicos.add(os);
		}
		rs.close();
		estrutura.close();
		return ordemServicos;
	}
	
	public List<OrdemServico> pesqClienteData(int cdFilial, Calendar data1, Calendar data2, int cdCliente, Connection c)throws Exception{
		List<OrdemServico> ordemServicos = new ArrayList<OrdemServico>();
		OrdemServico os = null;
		String sql = "SELECT * FROM T_DESP_ORDEM_SERVICO OS INNER JOIN T_DESP_FUNCIONARIO FUN ON(OS.CD_FUNCIONARIO = FUN.CD_FUNCIONARIO) WHERE FUN.CD_FILIAL = ?  AND dt_entrada BETWEEN ? AND ? AND cd_cliente = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		estrutura.setDate(2, new Date(data1.getTimeInMillis()));
		estrutura.setDate(3, new Date(data2.getTimeInMillis()));
		estrutura.setInt(4, cdCliente);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			os = new OrdemServico();
			Calendar cal = Calendar.getInstance();
			os.setAtendente(FuncionarioBO.pesqCodigo(rs.getInt("cd_funcionario"), c));
			os.setCliente(ClienteBO.pesqCodigo(rs.getInt("cd_cliente"), c));
			os.setDesconto(rs.getDouble("vl_desconto"));
			cal.setTime(rs.getDate("dt_entrada"));
			os.setDtEntrada(cal);
			os.setNumero(rs.getInt("nr_ordem"));
			os.setPagamento(PagamentoBO.listarOrdem(os.getNumero(), c));
			os.setServicos(ItemServicoBO.listarServOs(os.getNumero(), c));
			os.setStatus(MudarStatusOsBO.pesqCodigo(rs.getInt("cd_status"), c));
			os.setTipo(TipoOrdemBO.pesqCodigo(rs.getInt("cd_tipo_ordem"), c));
			os.setTotal(rs.getDouble("vl_total"));
			os.setVeiculo(VeiculoBO.pesqCodigo(rs.getInt("cd_veiculo"), c));
			os.setVlrPago(rs.getDouble("vl_pago"));
			ordemServicos.add(os);
		}
		rs.close();
		estrutura.close();
		return ordemServicos;
	}
	
	public List<OrdemServico> pesqStatus(int cdFilial, int cdStatus, Connection c)throws Exception{
		List<OrdemServico> ordemServicos = new ArrayList<OrdemServico>();
		OrdemServico os = null;
		String sql = "SELECT * FROM T_DESP_ORDEM_SERVICO OS INNER JOIN T_DESP_FUNCIONARIO FUN ON(OS.CD_FUNCIONARIO = FUN.CD_FUNCIONARIO) INNER JOIN T_DESP_MUDAR_STATUS_OS ST ON(OS.CD_MUDAR_STATUS = ST.CD_MUDAR_STATUS_OS) WHERE ST.cd_status_os = ? AND FUN.CD_FILIAL = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		
		estrutura.setInt(1, cdStatus);
		estrutura.setInt(2, cdFilial);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			os = new OrdemServico();
			Calendar cal = Calendar.getInstance();
			os.setAtendente(FuncionarioBO.pesqCodigo(rs.getInt("cd_funcionario"), c));
			os.setCliente(ClienteBO.pesqCodigo(rs.getInt("cd_cliente"), c));
			os.setDesconto(rs.getDouble("vl_desconto"));
			cal.setTime(rs.getDate("dt_entrada"));
			os.setDtEntrada(cal);
			os.setNumero(rs.getInt("nr_ordem"));
			os.setPagamento(PagamentoBO.listarOrdem(os.getNumero(), c));
			os.setServicos(ItemServicoBO.listarServOs(os.getNumero(), c));
			os.setStatus(MudarStatusOsBO.pesqCodigo(rs.getInt("cd_mudar_status"), c));
			os.setTipo(TipoOrdemBO.pesqCodigo(rs.getInt("cd_tipo_ordem"), c));
			os.setTotal(rs.getDouble("vl_total"));
			os.setVeiculo(VeiculoBO.pesqCodigo(rs.getInt("cd_veiculo"), c));
			os.setVlrPago(rs.getDouble("vl_pago"));
			if(os.getStatus().getStatus().getCodigo() == cdStatus){
				ordemServicos.add(os);
			}
		}
		rs.close();
		estrutura.close();
		return ordemServicos;
	}
	
	public List<OrdemServico> pesqStatusData(int cdStatus, Calendar data1, Calendar data2, Connection c)throws Exception{
		List<OrdemServico> ordemServicos = new ArrayList<OrdemServico>();
		OrdemServico os = null;
		String sql = "SELECT * FROM T_DESP_ORDEM_SERVICO OS INNER JOIN T_DESP_FUNCIONARIO FUN ON(OS.CD_FUNCIONARIO = FUN.CD_FUNCIONARIO) WHERE FUN.CD_FILIAL = ? AND dt_entrada BETWEEN ? AND ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setDate(1, new Date(data1.getTimeInMillis()));
		estrutura.setDate(2, new Date(data2.getTimeInMillis()));
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			os = new OrdemServico();
			Calendar cal = Calendar.getInstance();
			os.setAtendente(FuncionarioBO.pesqCodigo(rs.getInt("cd_funcionario"), c));
			os.setCliente(ClienteBO.pesqCodigo(rs.getInt("cd_cliente"), c));
			os.setDesconto(rs.getDouble("vl_desconto"));
			cal.setTime(rs.getDate("dt_entrada"));
			os.setDtEntrada(cal);
			os.setNumero(rs.getInt("nr_ordem"));
			os.setPagamento(PagamentoBO.listarOrdem(os.getNumero(), c));
			os.setServicos(ItemServicoBO.listarServOs(os.getNumero(), c));
			os.setStatus(MudarStatusOsBO.pesqCodigo(rs.getInt("cd_mudar_status"), c));
			os.setTipo(TipoOrdemBO.pesqCodigo(rs.getInt("cd_tipo_ordem"), c));
			os.setTotal(rs.getDouble("vl_total"));
			os.setVeiculo(VeiculoBO.pesqCodigo(rs.getInt("cd_veiculo"), c));
			os.setVlrPago(rs.getDouble("vl_pago"));
			if(os.getStatus().getStatus().getCodigo() == cdStatus){
				ordemServicos.add(os);
			}
		}
		rs.close();
		estrutura.close();
		return ordemServicos;
	}
	
	public List<OrdemServico> pesqAtendente(int cdFilial, int cdFuncionario, Connection c)throws Exception{
		List<OrdemServico> ordemServicos = new ArrayList<OrdemServico>();
		OrdemServico os = null;
		String sql = "SELECT * FROM T_DESP_ORDEM_SERVICO OS INNER JOIN T_DESP_FUNCIONARIO FUN ON(OS.CD_FUNCIONARIO = FUN.CD_FUNCIONARIO) WHERE FUN.CD_FILIAL = ? AND cd_funcionario = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		estrutura.setInt(2, cdFuncionario);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			os = new OrdemServico();
			Calendar cal = Calendar.getInstance();
			os.setAtendente(FuncionarioBO.pesqCodigo(rs.getInt("cd_funcionario"), c));
			os.setCliente(ClienteBO.pesqCodigo(rs.getInt("cd_cliente"), c));
			os.setDesconto(rs.getDouble("vl_desconto"));
			cal.setTime(rs.getDate("dt_entrada"));
			os.setDtEntrada(cal);
			os.setNumero(rs.getInt("nr_ordem"));
			os.setPagamento(PagamentoBO.listarOrdem(os.getNumero(), c));
			os.setServicos(ItemServicoBO.listarServOs(os.getNumero(), c));
			os.setStatus(MudarStatusOsBO.pesqCodigo(rs.getInt("cd_status"), c));
			os.setTipo(TipoOrdemBO.pesqCodigo(rs.getInt("cd_tipo_ordem"), c));
			os.setTotal(rs.getDouble("vl_total"));
			os.setVeiculo(VeiculoBO.pesqCodigo(rs.getInt("cd_veiculo"), c));
			os.setVlrPago(rs.getDouble("vl_pago"));
			ordemServicos.add(os);
		}
		rs.close();
		estrutura.close();
		return ordemServicos;
	}
	
	public List<OrdemServico> pesqAtendData(int cdFilial, int cdFuncionario, Calendar data1, Calendar data2, Connection c)throws Exception{
		List<OrdemServico> ordemServicos = new ArrayList<OrdemServico>();
		OrdemServico os = null;
		String sql = "SELECT * FROM T_DESP_ORDEM_SERVICO OS INNER JOIN T_DESP_FUNCIONARIO FUN ON(OS.CD_FUNCIONARIO = FUN.CD_FUNCIONARIO) WHERE FUN.CD_FILIAL = ? AND dt_entrada BETWEEN ? AND ? AND cd_funcionario = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		estrutura.setDate(2, new Date(data1.getTimeInMillis()));
		estrutura.setDate(3, new Date(data2.getTimeInMillis()));
		estrutura.setInt(4, cdFuncionario);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			os = new OrdemServico();
			Calendar cal = Calendar.getInstance();
			os.setAtendente(FuncionarioBO.pesqCodigo(rs.getInt("cd_funcionario"), c));
			os.setCliente(ClienteBO.pesqCodigo(rs.getInt("cd_cliente"), c));
			os.setDesconto(rs.getDouble("vl_desconto"));
			cal.setTime(rs.getDate("dt_entrada"));
			os.setDtEntrada(cal);
			os.setNumero(rs.getInt("nr_ordem"));
			os.setPagamento(PagamentoBO.listarOrdem(os.getNumero(), c));
			os.setServicos(ItemServicoBO.listarServOs(os.getNumero(), c));
			os.setStatus(MudarStatusOsBO.pesqCodigo(rs.getInt("cd_status"), c));
			os.setTipo(TipoOrdemBO.pesqCodigo(rs.getInt("cd_tipo_ordem"), c));
			os.setTotal(rs.getDouble("vl_total"));
			os.setVeiculo(VeiculoBO.pesqCodigo(rs.getInt("cd_veiculo"), c));
			os.setVlrPago(rs.getDouble("vl_pago"));
			ordemServicos.add(os);
		}
		rs.close();
		estrutura.close();
		return ordemServicos;
	}
	
	public List<OrdemServico> pesqVeiculo(int cdFilial, int cdVeiculo, Connection c)throws Exception{
		List<OrdemServico> ordemServicos = new ArrayList<OrdemServico>();
		OrdemServico os = null;
		String sql = "SELECT * FROM T_DESP_ORDEM_SERVICO OS INNER JOIN T_DESP_FUNCIONARIO FUN ON(OS.CD_FUNCIONARIO = FUN.CD_FUNCIONARIO) WHERE FUN.CD_FILIAL = ? AND cd_veiculo = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		estrutura.setInt(2, cdVeiculo);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			os = new OrdemServico();
			Calendar cal = Calendar.getInstance();
			os.setAtendente(FuncionarioBO.pesqCodigo(rs.getInt("cd_funcionario"), c));
			os.setCliente(ClienteBO.pesqCodigo(rs.getInt("cd_cliente"), c));
			os.setDesconto(rs.getDouble("vl_desconto"));
			cal.setTime(rs.getDate("dt_entrada"));
			os.setDtEntrada(cal);
			os.setNumero(rs.getInt("nr_ordem"));
			os.setPagamento(PagamentoBO.listarOrdem(os.getNumero(), c));
			os.setServicos(ItemServicoBO.listarServOs(os.getNumero(), c));
			os.setStatus(MudarStatusOsBO.pesqCodigo(rs.getInt("cd_mudar_status"), c));
			os.setTipo(TipoOrdemBO.pesqCodigo(rs.getInt("cd_tipo_ordem"), c));
			os.setTotal(rs.getDouble("vl_total"));
			os.setVeiculo(VeiculoBO.pesqCodigo(rs.getInt("cd_veiculo"), c));
			os.setVlrPago(rs.getDouble("vl_pago"));
			ordemServicos.add(os);
		}
		rs.close();
		estrutura.close();
		return ordemServicos;
	}
	
	public List<OrdemServico> pesVeicData(int cdFilial, int cdVeiculo, Calendar data1, Calendar data2, Connection c)throws Exception{
		List<OrdemServico> ordemServicos = new ArrayList<OrdemServico>();
		OrdemServico os = null;
		String sql = "SELECT * FROM T_DESP_ORDEM_SERVICO OS INNER JOIN T_DESP_FUNCIONARIO FUN ON(OS.CD_FUNCIONARIO = FUN.CD_FUNCIONARIO) WHERE FUN.CD_FILIAL = ? AND dt_entrada BETWEEN ? AND ? AND cd_veiculo = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		estrutura.setInt(2, cdVeiculo);
		ResultSet rs = estrutura.executeQuery();
		while(rs.next()){
			os = new OrdemServico();
			Calendar cal = Calendar.getInstance();
			os.setAtendente(FuncionarioBO.pesqCodigo(rs.getInt("cd_funcionario"), c));
			os.setCliente(ClienteBO.pesqCodigo(rs.getInt("cd_cliente"), c));
			os.setDesconto(rs.getDouble("vl_desconto"));
			cal.setTime(rs.getDate("dt_entrada"));
			os.setDtEntrada(cal);
			os.setNumero(rs.getInt("nr_ordem"));
			os.setPagamento(PagamentoBO.listarOrdem(os.getNumero(), c));
			os.setServicos(ItemServicoBO.listarServOs(os.getNumero(), c));
			os.setStatus(MudarStatusOsBO.pesqCodigo(rs.getInt("cd_status"), c));
			os.setTipo(TipoOrdemBO.pesqCodigo(rs.getInt("cd_tipo_ordem"), c));
			os.setTotal(rs.getDouble("vl_total"));
			os.setVeiculo(VeiculoBO.pesqCodigo(rs.getInt("cd_veiculo"), c));
			os.setVlrPago(rs.getDouble("vl_pago"));
			ordemServicos.add(os);
		}
		rs.close();
		estrutura.close();
		return ordemServicos;
	}
	
	public OrdemServico pesqNumeroOsFilial(int cdFilial, int nrOs, Connection c)throws Exception{
		OrdemServico os = new OrdemServico();
		String sql = "SELECT * FROM T_DESP_ORDEM_SERVICO OS INNER JOIN T_DESP_FUNCIONARIO FUN ON(OS.CD_FUNCIONARIO = FUN.CD_FUNCIONARIO) WHERE FUN.CD_FILIAL = ? AND nr_ordem = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, cdFilial);
		estrutura.setInt(2, nrOs);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			Calendar cal = Calendar.getInstance();
			os.setAtendente(FuncionarioBO.pesqCodigo(rs.getInt("cd_funcionario"), c));
			os.setCliente(ClienteBO.pesqCodigo(rs.getInt("cd_cliente"), c));
			os.setDesconto(rs.getDouble("vl_desconto"));
			cal.setTime(rs.getDate("dt_entrada"));
			os.setDtEntrada(cal);
			os.setNumero(rs.getInt("nr_ordem"));
			os.setPagamento(PagamentoBO.listarOrdem(os.getNumero(), c));
			os.setServicos(ItemServicoBO.listarServOs(os.getNumero(), c));
			os.setStatus(MudarStatusOsBO.pesqCodigo(rs.getInt("cd_mudar_status"), c));
			os.setTipo(TipoOrdemBO.pesqCodigo(rs.getInt("cd_tipo_ordem"), c));
			os.setTotal(rs.getDouble("vl_total"));
			os.setVeiculo(VeiculoBO.pesqCodigo(rs.getInt("cd_veiculo"), c));
			os.setVlrPago(rs.getDouble("vl_pago"));
		}
		rs.close();
		estrutura.close();
		return os;
	}
	
	public OrdemServico pesqNumeroOs(int nrOs, Connection c)throws Exception{
		OrdemServico os = new OrdemServico();
		String sql = "SELECT * FROM T_DESP_ORDEM_SERVICO WHERE nr_ordem = ?";
		PreparedStatement estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, nrOs);
		ResultSet rs = estrutura.executeQuery();
		if(rs.next()){
			Calendar cal = Calendar.getInstance();
			os.setAtendente(FuncionarioBO.pesqCodigo(rs.getInt("cd_funcionario"), c));
			os.setCliente(ClienteBO.pesqCodigo(rs.getInt("cd_cliente"), c));
			os.setDesconto(rs.getDouble("vl_desconto"));
			cal.setTime(rs.getDate("dt_entrada"));
			os.setDtEntrada(cal);
			os.setNumero(rs.getInt("nr_ordem"));
			os.setPagamento(PagamentoBO.listarOrdem(os.getNumero(), c));
			os.setServicos(ItemServicoBO.listarServOs(os.getNumero(), c));
			os.setStatus(MudarStatusOsBO.pesqCodigo(rs.getInt("cd_mudar_status"), c));
			os.setTipo(TipoOrdemBO.pesqCodigo(rs.getInt("cd_tipo_ordem"), c));
			os.setTotal(rs.getDouble("vl_total"));
			os.setVeiculo(VeiculoBO.pesqCodigo(rs.getInt("cd_veiculo"), c));
			os.setVlrPago(rs.getDouble("vl_pago"));
		}
		rs.close();
		estrutura.close();
		return os;
	}

}
