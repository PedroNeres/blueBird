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
          					<form action="contas" method="get" class="col-md-12">
          					<input type="hidden" name="acao" value="pesquisar">
          					<input type="hidden" name="cdFilial" value="${user.filial.codigo }"> 
          					<legend><h4>Filtrar Contas</h4></legend>
          					<div class="row">
          						<div class="form-group col-md-5">
          							<label for="status">Status</label>
          							<select name="status" id="status" class="form-control">
          								<option>Selecione</option>
          								<option value="0">Aberto</option>
          								<option value="1">Vencendo</option>
          								<option value="2">Fechado</option>
          							</select>
          						</div>
          					
          						<div class="form-group col-md-4">
          							<label for="descricao">Descrição</label>
          							<input type="text" id="descricao" name="descricao" placeholder="descrição da conta" class="form-control"> 
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
          			 						<th>cod. Conta</th>
          			 						<th>Descrição</th>
          			 						<th>Valor</th>
          			 						<th>Qtn Parc.</th>
          			 						<th>Vencimento</th>
          			 						<th>Status</th>
          			 						<th><a href="contas?acao=listar&cdFilial=${user.filial.codigo }">Mostrar Todos</a></th>
          			 						<th><a href="cadConta.jsp" title="Nova Conta"><i class="btn btn-default btn-sm glyphicon glyphicon-plus"></i></a></th>
          			 					</tr>
          			 				</thead>
          			 				
          			 				<tbody>
          			 					<c:forEach var="cont" items="${contas }">
          			 					
          			 					<c:choose>
											<c:when test="${cont.status == 0 }">
												<tr>
          			 								<td>${cont.codigo }</td>
          			 								<td>${cont.descricao }</td>
          			 								<td>R$ <fmt:formatNumber>${cont.valor }</fmt:formatNumber></td>
          			 								<td>${cont.qtParcelas }</td>
          			 								<td>
          			 									<c:if test="${cont.status == 0  || cont.status == 1 }"><fmt:formatDate value="${cont.proxVenc.time }"/></c:if>
          			 									</td>
          			 								<td>
          			 									<c:if test="${cont.status == 0 }">Aberto</c:if>
          			 									<c:if test="${cont.status == 1 }">Vencendo</c:if>
          			 									<c:if test="${cont.status == 2 }">Fechado</c:if>
          			 								</td>
          			 								<td>
          			 									
          			 								</td>
          			 								<td>       	
          			 									<a href="contas?acao=pagar&codigo=${cont.codigo }&cdFilial=${user.filial.codigo}">Pagar</a>
          			 								<td>
          			 							</tr>
											</c:when>
											<c:when test="${cont.status == 1}">
												<tr class="danger">
          			 								<td>${cont.codigo }</td>
          			 								<td>${cont.descricao }</td>
          			 								<td>R$ <fmt:formatNumber>${cont.valor }</fmt:formatNumber></td>
          			 								<td>${cont.qtParcelas }</td>
          			 								<td>
          			 									<c:if test="${cont.status == 0  || cont.status == 1 }"><fmt:formatDate value="${cont.proxVenc.time }"/></c:if>
          			 									</td>
          			 								<td>
          			 									<c:if test="${cont.status == 0 }">Aberto</c:if>
          			 									<c:if test="${cont.status == 1 }">Vencendo</c:if>
          			 									<c:if test="${cont.status == 2 }">Fechado</c:if>
          			 								</td>
          			 								<td>
          			 									
          			 								</td>
          			 								<td>
          			 									<c:if test="${cont.status == 1 }">
          			 										<a href="contas?acao=pagar&codigo=${cont.codigo }&cdFilial=${user.filial.codigo}">Pagar</a>
          			 									</c:if>
          			 								</td>
          			 							</tr>
											</c:when>
											<c:when test="${cont.status == 2 }">
												<tr class="success">
          			 								<td>${cont.codigo }</td>
          			 								<td>${cont.descricao }</td>
          			 								<td>R$ <fmt:formatNumber>${cont.valor }</fmt:formatNumber></td>
          			 								<td>${cont.qtParcelas }</td>
          			 								
          			 									<td>
          			 									<c:if test="${cont.status == 0  || cont.status == 1 }"><fmt:formatDate value="${cont.proxVenc.time }"/></c:if>
          			 									</td>
          			 								
          			 								<td>
          			 									<c:if test="${cont.status == 0 }">Aberto</c:if>
          			 									<c:if test="${cont.status == 1 }">Vencendo</c:if>
          			 									<c:if test="${cont.status == 2 }">Fechado</c:if>
          			 								</td>
          			 								<td>
          			 									
          			 								</td>
          			 								<td>
          			 									<c:if test="${cont.status == 1 }">
          			 										<a href="contas?acao=pagar&codigo=${cont.codigo }&cdFilial=${user.filial.codigo}">Pagar</a>
          			 									</c:if>
          			 								</td>
          			 							</tr>
											</c:when>
										</c:choose>
          			 						
          			 						
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
