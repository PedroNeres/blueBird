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
          					<form action="cheque" method="get" class="col-md-12">
          					<input type="hidden" name="acao" value="pesquisar">
          					<input type="hidden" name="cdFilial" value="${user.filial.codigo }">
          					<legend><h4>Filtrar Cheque</h4></legend>
          					<div class="row">
          						<div class="form-group col-md-2">
          							<input type="text" name="num" placeholder="Número Cheque" class="form-control">
          						</div>
          					
          						<div class="form-group col-md-4">
          							<input type="text" name="emitente" placeholder="Emitente" class="form-control"> 
          						</div>
          					
          						
          					</div>
          					<div class="row">
          						<div class="form-group col-md-5">
          							<label for="status">Status</label>
          							<select name="status" id="status" class="form-control">
          								<option>Selecione</option>
          								<option value="0">Aberto</option>
          								<option value="1">Depositado</option>
          							</select>
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
          			 						<th>Num. Cheque</th>
          			 						<th>Valor</th>
          			 						<th>Emitente</th>
          			 						<th>Data Depósito</th>
          			 						<th>Status</th>
          			 						<th><a href="cheque?acao=listar&cdFilial=${user.filial.codigo }">Mostrar Todos</a></th>
          			 					</tr>
          			 				</thead>
          			 				
          			 				<tbody>
          			 					<c:forEach var="che" items="${cheques }">
          			 						
          			 						<tr>
          			 							<td>${che.numero }</td>
          			 							<td>R$ <fmt:formatNumber>${che.valor }</fmt:formatNumber></td>
          			 							<td>${che.emitente }</td>
          			 							<td><fmt:formatDate value="${che.dtDeposito.time }"/>
          			 							<td>
          			 								<c:if test="${che.status == 0 }">Aberto</c:if>
          			 								<c:if test="${che.status == 1 }">Depositado</c:if>
          			 							</td>
          			 							<td>
          			 								<c:if test="${che.status == 0 }">
          			 									<a href="cheque?acao=depositar&codigo=${che.codigo }">Depósito</a>
          			 								</c:if>
          			 							</td>
          			 							
          			 						</tr>
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
    
</body>

</html>
