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
          		<div class="col-md-12">
          			<div class="panel panel-default">
          				<div class="panel-body">
          					<form action="ordemServico" method="get" class="col-md-12">
          					<input type="hidden" name="acao" value="pesquisar">
          					<input type="hidden" name="cdFilial" value="${user.filial.codigo }">
          					<legend><h4>Filtrar Ordem de Serviço</h4></legend>
          					<div class="row">
          						<div class="form-group col-md-2">
          							<input type="text" name="numOS" placeholder="Número OS" class="form-control">
          						</div>
          					
          						<div class="form-group col-md-4">
          							<input type="text" name="placa" placeholder="Placa Veículo" class="form-control" maxlength="7"> 
          						</div>
          					
          						<div class="form-group col-md-4">
          							<input type="text" name="cpf" placeholder="CPF" class="form-control" maxlength="11">
          						</div>
          					</div>
          					<div class="row">
          						<div class="form-group col-md-5">
          							<label for="data1">Data Inicio</label>
          							<input type="date" name="data1" id="data1" class="form-control">
          						</div>
          						<div class="form-group col-md-5">
          							<label for="data2">Data Término</label>
          							<input type="date" name="data2" id="data2" class="form-control">
          						</div>
          					</div>
          					
          					<div class="row">
          						<div class="form-group col-md-5">
          							<label for="status">Status</label>
          							<select name="status" id="status" class="form-control">
          								<option value="">Selecione</option>
          								<c:forEach var="st" items="${listStatus }">
          									<option value="${st.codigo }">${st.descricao }</option>
          								</c:forEach>
          							</select>
          						</div>
          					</div>
          					
          					<div class="row">
          						<div class="form-group col-md-12">
          							<input type="submit" value="Pesquisar" class="btn btn-primary col-md-3"><br>
          						</div>
          					</div>
          					<div class="row">
          						<div class="col-md-12">
          							<p><small>obs:*Não é necessário inserir todos os dados para pesquisa!</small></p>
          						</div>
          					</div>
          				</form>
          				</div>
          			</div>
          		</div>
          	</div>
          	
          	
            <div class="row">
            
                <div class="col-lg-12">
                
                
                <br>
                
               		 <div class="row">
          			 	<div class="panel panel-default">
          			 		<div class="table-responsive">
          			 			<table class="table table-striped">
          			 				<thead>
          			 					<tr>
          			 						<th>O.S</th>
          			 						<th>Cliente</th>
          			 						<th>Serviço</th>
          			 						<th>Total</th>
          			 						<th>Pago</th>
          			 			
          			 						<th>Placa</th>
          			 						<th>Atendente</th>
          			 						<th>Data</th>
          			 						<th>Status</th>
          			 						<th></th>
          			 					</tr>
          			 				</thead>
          			 				
          			 				<tbody>
          			 					<c:forEach end="9" var="os" items="${listaOs }">
          			 						<tr>
          			 							<td>${os.numero }</td>
          			 							<td>${os.cliente.nome }</td>
          			 							<td>${os.tipo.descricao }</td>
          			 							<td>R$ <fmt:formatNumber>${os.total }</fmt:formatNumber></td>
          			 							<td>R$ <fmt:formatNumber>${os.vlrPago }</fmt:formatNumber></td>
          			 							<td>${os.veiculo.placa }</td>
          			 							<td>${os.atendente.nome }</td>
          			 							<td><fmt:formatDate value="${os.dtEntrada.time }"/></td>
          			 							<td>${os.status.status.descricao }</td>
          			 							<td>
          			 								<a href="ordemServico?acao=abrir&codigo=${os.numero}">Abrir</a> / <c:if test="${os.total - os.vlrPago > 0 }"><a href="#" data-toggle="modal" data-target="#ModalAddPagamento${os.numero }">Add Pag</a></c:if>
          			 							</td>
          			 							
          			 						</tr>
          			 						<%@include file="addPagamento.jsp" %>
          			 					</c:forEach>
          			 				</tbody>
          			 			</table>
          			 		</div>
          			 	</div>
          			</div>		
 
               	
           		</div>
                <!-- /.col-lg-12-->
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
