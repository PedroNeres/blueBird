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

  
      	
      	<div class="container">
      		<c:if test="${user.cargo.codigo > 2 }">
      		<% response.sendRedirect("usuario?acao=erro");%>
      	</c:if>
            
                
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
          		<h3>
          			<c:if test="${saldo >= 0 }">
          				<strong class="text-success">Saldo: <fmt:formatNumber>${saldo }</fmt:formatNumber></strong>
          			</c:if>
          			<c:if test="${saldo < 0 }">
          				<strong class="text-danger">Saldo: <fmt:formatNumber>${saldo }</fmt:formatNumber></strong>
          			</c:if>
          		</h3>
          	</div>
          	
          	
            <div class="row">
            
                <div class="col-md-6">
                <br>
                
               		 <div class="row">
          			 	<div class="panel panel-default">
          			 		<div class="panel-heading">
          			 			<h4>Entradas / <strong class="text-primary"><fmt:formatNumber>${totalEntradas }</fmt:formatNumber></strong> </h4>
          			 		</div>
          			 		<div class="panel-body">
          			 			<div class="table-responsive">
          			 			<table class="table table-striped">
          			 				<thead>
          			 					<tr>
          			 						<th>Descrição</th>
          			 						<th>Valor</th>
          			 						<th>Data</th>
          			 						<th></th>
          			 					</tr>
          			 				</thead>
          			 				
          			 				<tbody>
          			 					<c:forEach var="ent" items="${entradas }">
          			 						<tr>
          			 							<td>${ent.descricao }</td>
          			 							<td><fmt:formatNumber>${ent.valor }</fmt:formatNumber></td>
          			 							<td><fmt:formatDate value="${ent.dtEntrada.time }"/></td>
          			 							
          			 							
          			 						</tr>
          			 						
          			 					</c:forEach>
          			 				</tbody>
          			 			</table>
          			 		</div>
          			 		</div>
          			 	</div>
          			</div>		
 
               	
           		</div>
                <!-- /.col-lg-12-->
                
                
                <div class="col-md-6">
                <br>
                
               		 <div class="row">
          			 	<div class="panel panel-default">
          			 		<div class="panel-heading">
          			 			<h4>Saidas / <strong class="text-danger"><fmt:formatNumber>${totalSaidas }</fmt:formatNumber></strong> </h4>
          			 		</div>
          			 		<div class="panel-body">
          			 			<div class="table-responsive">
          			 			<table class="table table-striped">
          			 				<thead>
          			 					<tr>
          			 						<th>Descrição</th>
          			 						<th>Valor</th>
          			 						<th>Data</th>
          			 						<th></th>
          			 					</tr>
          			 				</thead>
          			 				
          			 				<tbody>
          			 					<c:forEach var="sai" items="${saidas }">
          			 						<tr>
          			 							<td>${sai.descricao }</td>
          			 							<td><fmt:formatNumber>${sai.valor }</fmt:formatNumber></td>
          			 							<td><fmt:formatDate value="${sai.dtSaida.time }"/>
          			 						
          			 							
          			 						</tr>
          			 						
          			 					</c:forEach>
          			 				</tbody>
          			 			</table>
          			 		</div>
          			 		</div>
          			 	</div>
          			</div>		
 
               	
           		</div>
                <!-- /.col-lg-6-->
            </div>
            <!-- /.row -->
      	</div>
   

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
    		window.print()
    </script>
   
    
</body>

</html>
