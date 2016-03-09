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
       <%@include file="topo.jsp" %>
	
              
        <%@include file="addPagamento.jsp" %>
        <%@include file="addServOs.jsp" %>
            

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
            	<div class="col-lg-1"></div>
            
                <div class="col-lg-10">
                
                <div class="row">
                
          			<div class="col-md-4"></div>
          			<a href="ordemServico?acao=imprimir&numero=${os.numero }" target="_blank" class="btn btn-primary">Imprimir</a>
               		<c:if test="${os.total - os.vlrPago > 0 }">
               			<a href="#" data-toggle="modal" data-target="#ModalAddPagamento${os.numero }" class="btn btn-success">Add Pagamento</a>
               		</c:if>
               		<a href="#" data-toggle="modal" data-target="#ModalAddServOs" class="btn btn-default">Add Serviço</a>
                </div>
                <br>
                
                <div class="row">
          		
          			<div class="panel panel-default">
          				<!-- heading panel os -->
          				<div class="panel-heading">
          				<div class="row">
          					<div class="col-md-4">
          						<img src="img/carOs.png" alt="carro em png" class="img-rounded img-responsive"/>
          					</div>
          					<div class="col-md-8">
          						<h4 class="text-center"><STRONG>JOSE CARLOS NOGUEIRA - SSP 07081</STRONG></h4>
          						
          							<address class="text-center">
          								AV. PAPA JOÃO PAULO I, 3947<br>
          								JD. PRESIDENTE DUTRA - GUARULHOS - SP<br>
          								<abbr title="Atendente">Atendente</abbr>
          								<strong>${os.atendente.nome }</strong>
          							</address>
          							<address class="text-center">
          	
          								<abbr title="Phone">Tel: </abbr> (11)2431-7010 / 2431-2795
          							</address>
          					</div>
          				</div>
          				</div>
          				<!-- body panel os -->
          				<div class="panel-body">
          					
          					<h4 class="text-center"><small>Licenciamento - Transferência - CNH - Serviços Junto ao DETRAN/SP</small></h4>
          					
          					<div class="row">
          						<div class="col-md-9"><h4>O.S <strong>${os.numero }</strong></h4></div>
          						<div class="col-md-3">
          							<div class="input-group">
                                     	<span class="input-group-addon">R$</span>
                                     	<input type="text" name="valorTotal" value='<fmt:formatNumber>${os.total }</fmt:formatNumber>' readonly="readonly" placeholder="00.0" class="form-control">
                                     </div>
          						</div>
          					</div>
          					<hr>
          					
          					<p class="text-justify col-md-7">
          						Cliente:  <strong> <u>${os.cliente.nome }</u></strong>
          					</p>
          					<p class="text-justify col-md-5">
          						<c:forEach var="tels" items="${os.cliente.telefone }">
          							<abbr title="Phone Cliente">Fone: </abbr><strong><u>(${tels.ddd }) ${tels.numero }</u></strong> -
          						</c:forEach>
          					</p>
          					<p class="text-justify col-md-12">
          						<abbr title="Endereço Cliente">End: </abbr>
          						<strong>${os.cliente.endereco.logradouro },</strong>
          					
          						Num. <strong>${os.cliente.endereco.numero }</strong>
          					</p>
          					
          					<p class="text-justify col-md-8">
          						<strong>${os.cliente.endereco.bairro },</strong>
          					</p>
          					<p class="text-justify col-md-4">
          						<abbr title="Complemento endereço">Comp.</abbr>
          						<strong>${os.cliente.endereco.complemento }</strong>
          					</p>
          					
          					<p class="text-justify col-md-8">
          						<strong>${os.cliente.endereco.cidade } </strong>
          					</p>
          					<p class="text-justify col-md-4">
          						<strong>${os.cliente.endereco.uf }</strong>
          					</p>
          					
          					<p class="text-justify col-md-12">
          						<abbr title="Valor total por extenso">A importância de: </abbr>
          						<strong>${valorStr }</strong>
          					</p>
          					
          					<p class="text-center col-md-12"> Para pagt. das despesas discriminadas na "ORDEM DE SERVIÇO" abaixo: <strong><abbr title="num. o.s">${os.numero }</abbr></strong> </p>
          						
          						
          					<p class="text-center col-md-12">
          						Guarulhos, <strong>${strDtHoje }</strong>
          					</p>
          						
          					<p class="text-justify col-md-4">
          						<abbr title="Veículo">Veículo: </abbr>
          						<strong>${os.veiculo.modelo }</strong>
          					</p>
          					<p class="text-justify col-md-3">
          						<abbr title="Placa do veículo">Placa: </abbr>
          						<strong>${os.veiculo.placa }</strong>
          					</p>
          					<p class="text-justify col-md-5">
          						<abbr title="Renaval do veículo">Renavam: </abbr>
          						<strong>${os.veiculo.renavam }</strong>
          					</p>
          					<hr>
          					<div class="table responsive">
          						<table class="table table-striped">
          						<thead>
          							<tr>
          								<th class="col-md-8">Serviços</th>
          								<th class="col-md-4">Valor</th>
          							</tr>
          						</thead>
          						<tbody>
          							<c:forEach var="serv" items="${os.servicos }">
          								<tr>
          									<td>${serv.servico.descricao }</td>
          									<td><fmt:formatNumber>${serv.vlrTotal }</fmt:formatNumber></td>
          								</tr>
          							</c:forEach>
          						</tbody>
          					</table>
          					</div>
          					
          					<div class="table-responsive">
          						<table class="table">
          							<tr>
          								<th class="col-md-8">Total</th>
          								<td class="col-md-4">${os.total }</td>
          							</tr>
          						</table>
          					</div>
          					
          					<div class="table-responsive">
          						<table class="table table-striped">
          							<thead>
          								<tr>
          									<th class="col-md-8">Forma de Pagamento</th>
          									<th class="col-md-4">Valor</th>
          								</tr>
          							</thead>
          							<tbody>
          								<c:forEach var="pagto" items="${os.pagamento }">
          									<tr>
          										<td>${pagto.forPagamento.descricao }</td>
          										<td><fmt:formatNumber>${pagto.vlPagao }</fmt:formatNumber></td>
          									</tr>
          								</c:forEach>
          								<tr>
          									<th>Falta</th>
          									<td><fmt:formatNumber>${os.total - os.desconto - os.vlrPago }</fmt:formatNumber></td>
          								</tr>
          							</tbody>
          						</table>
          					</div>
          					
          				</div>
          				
          				
          			</div>
		
        	  	</div> 
               	
           		</div>
                <!-- /.col-lg-8 -->
                
                
             
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
    	$("#print").click(function(){
    		window.print()
    	});
    </script>
    
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
