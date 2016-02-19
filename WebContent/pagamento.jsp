<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <title>SG Despachante - ${user.nome }</title>

    <!-- Bootstrap Core CSS -->
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="dist/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="bower_components/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">
		<div class="hidden">
			<div id="morris-area-chart"></div>
			<div id="morris-bar-chart"></div>
			<div id="morris-donut-chart"></div>
		</div>
       <%@include file="../topo.jsp" %>
	
            

        <div id="page-wrapper">
          
                
          <br><br>  
          	<div class="row col-lg-12">
          		<c:if test="${not empty msg }">
			<div class="alert alert-success">${msg }</div>
		</c:if>
		<c:if test="${not empty erro }">
			<div class="alert alert-danger">${erro }</div>
		</c:if>
          	</div>    
            <div class="row">
            
                <div class="col-lg-7">
        
        			
        				<div class="panel panel-default">
        					<div class="panel-heading">
        						<strong>Adicionar Serviço</strong>
        					</div>
        					<div class="panel-body">
        				<form action="ordemServico" method="post">
        					<input type="hidden" name="acao" value="addPagamento">
        					<div class="row">
        						<div class="form-group col-md-6">
        							<label for="forPagamento">Forma de Pagamento</label>
        							<select name="forPagamento" id="forPagamento" class="form-control">
        								<option>Selecione</option>
        								<c:forEach var="fPag" items="${formasPag }">
        									<option value="${fPag.codigo }">${fPag.descricao }</option>
        								</c:forEach>
        							</select>
        						</div>
        						<div class="form-group col-md-6">
        							<label for="vlrPag">Valor</label>
        							<div class="input-group">
                           				 <span class="input-group-addon">R$</span>
        								<input type="text" name="vlrPag" id="vlrPag" class="form-control">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        					<div class="form-group col-md-7">
        						<textarea rows="3" cols="10" class="form-control" name="desPag" placeholder="Descrição"></textarea>
        					</div>
        					<div class="form-group col-md-5">
        						<label for="codPag">Número Pag</label>
        						<input type="text" name="codPag" id="codPag" class="form-control" 
        						placeholder="Número">
        					</div>
        					</div>
        					<div class="row ">
        						<div class="form-group col-md-12">
        							<input type="checkbox" name="pDatado" id="pDatado">
        							<label for="pDatado">Cheque pré datado</label>
        						</div>
        						<div class="form-group col-md-5 dtCheque">
        							<label for="dtDeposito">Data pra depósto</label>
        							<input type="text" name="dtDeposito" id="dtDeposito" class="form-control" value="<fmt:formatDate value='${dtHoje.time }'/>">
        						</div>
        					</div>
        					<div class="row">
        						<div class="form-group col-md-4">
        							<input type="submit" value="Adicionar" class="btn btn-primary">
        						</div>
        					</div>
        					</form>
        					</div>
        					</div>
        				
        					<div class="panel panel-default">
        				<div class="panel-heading">
        					<strong>Lançar Os</strong>
        				</div>
        				
        				<div class="panel-body">
        					<form action="ordemServico" method="post">
        						<input type="hidden" name="acao" value="cadastrar">
        						<input type="hidden" name="cpf" value="${os.cliente.cpf }">
        						<input type="hidden" name="renavam" value="${os.veiculo.renavam }">
        						<input type="hidden" name="filial" value="${os.atendente.filial.codigo }">
        						<input type="hidden" name="funcionario" value="${os.atendente.codigo }">
        						<div class="row">
        						<div class="form-group col-md-6">
        							<label for="placa">Placa</label>
        							<input type="text" readonly name="placa" value="${os.veiculo.placa }" id="placa" class="form-control">
        						</div>
        						<div class="form-group col-md-6">
        							<label for="cliente">Cliente</label>
        							<input type="text" readonly name="cliente" value="${os.cliente.nome }" id="cliente" class="form-control">
        							
        						</div>
        						</div>
        						
        						<div class="row">
        							<div class="form-group col-md-6">
        								<label for="dtHoje">Data</label>
        								<input type="text" name="dtEntrada" id="dtHoje" readonly="readonly"
        								value="<fmt:formatDate value='${dtHoje.time }'/>" class="form-control">
        							</div>
        							<div class="form-group col-md-6">
        								<label for="tpServico">Serviço</label>
        								<input type="text" readonly="readonly" id="tpServico" name="tpServico"
        									class="form-control" value="${os.tipo.descricao }">
        								
        							</div>
        						</div>
        						
        						<div class="row">
        							<div class="form-group col-md-6">
        								<input type="submit" class="btn btn-primary" value="Concluir">
        							</div>
        						</div>
        						
        					</form>
        				</div>
        				
        				</div>
                
           		</div>
                <!-- /.col-lg-7 -->
                
                
                <div class="col-lg-5"> 	
                	
                	<div class="panel panel-primary">
                	<div class="panel-heading">
                		<strong>Lista de Pagamentos</strong>
                	</div>
                	<div class="panel-body">
                	             		
                		<div class="form-group col-md-12">
                			
                			<div class="input-group">
                            <span class="input-group-addon">Total</span>
                			<input type="text" name="vlrTotalOs" id="vlrTotalOS"
                				class="form-control" readonly="readonly" value='R$ <fmt:formatNumber>${os.total }</fmt:formatNumber>'>
                			</div>
                		</div>
                	
                	<hr>
                	
                		<div class="form-group col-md-12">
                			<label for="pagamentos">Pagamentos</label>
                		<c:forEach var="pag" items="${os.pagamento }">
                			<div class="input-group">
                				<span class="input-group-addon">${pag.forPagamento.descricao }</span>
                				<input type="text" name="pagmentos" id="pagamentos" readonly="readonly"
                					class="form-control" value='R$ <fmt:formatNumber>${pag.vlPagao }</fmt:formatNumber>'>
                			</div>
                		</c:forEach>
                		
                		<hr>
                		
                		<div class="form-group col-md-12">
                			<div class="input-group">
                				<span class="input-group-addon">Restante</span>
                				<input type="text" name="restante" id="restante" readonly="readonly"
                					class="form-control" value='R$ <fmt:formatNumber>${restante }</fmt:formatNumber>'>
                			</div>
                		</div>
                		
                	</div>	
                		</div>
                	</div>
                	
                </div>
                <!-- /.col-lg-5 -->
            </div>
            <!-- /.row -->
            </div>
        </div>   
        <!-- /#page-wrapper -->

    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="bower_components/raphael/raphael-min.js"></script>
    <script src="bower_components/morrisjs/morris.min.js"></script>
    <script src="js/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    
    <script type="text/javascript">
	
    $(".dtCheque").hide();
    
    
    
    $("#pDatado").click(function(){
    	if($("#pDatado").is(":checked") == true){
        	$(".dtCheque").fadeIn(1000);
        }else{
        	$(".dtCheque").fadeOut(1000);
        }
    });
    
    

	</script>

</body>

</html>
