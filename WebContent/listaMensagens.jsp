<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <%@include file="header.jsp" %>
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
            <div class="row">
    
            	<div class="col-lg-12">
            		<c:if test="${not empty msg }">
            		<br>
			<div class="alert alert-success">${msg }</div>
		</c:if>
		<c:if test="${not empty erro }">
			<br>
			<div class="alert alert-danger">${erro }</div>
		</c:if>
            	</div>
            </div>
         
                
                
            <div class="row">
                <div class="col-lg-10">
    
            <div class="panel panel-primary">
            	<div class="panel-heading">
            	
            		<div class="navbar-left">
                		<a class="navbar-brand" href="mensagem?acao=${acao }&email=${user.usuario.email }"><i class="fa fa-angle-double-left"></i> Mensagens ${texto }</a>
            		</div>
            
            		<div class="navbar-right">

            			<a class="navbar-brand" href="mensagem?acao=novaMensagem">| Nova <i class="fa fa-angle-double-right"></i></a>
            		</div>
            	</div>
            	<div class="panel-body">
            		
            		<div class="row">
            		
            			<div class="col-md-12">
            				<table class="table">
            					<thead>
            						<tr>
            							<th>Nome</th>
            							<th>Assunto</th>
            							<th>Data</th>
            						</tr>
            					</thead>
            					<tbody>
            						<c:forEach end="19" var="mens" items="${mensagem }">
            						
            							<c:choose>
            								<c:when test="${mens.status == 1}"><tr class="active"></c:when>
            								<c:when test="${mens.status == 0 }"><tr></c:when>
            							</c:choose>
            							
            								<td><a href="mensagem?acao=abrir&codigo=${mens.codigo }"><strong>${mens.nomeRemetente }</strong></a></td>
            								<td><a href="mensagem?acao=abrir&codigo=${mens.codigo }">${mens.assunto }</a> </td>
            								<td><a href="mensagem?acao=abrir&codigo=${mens.codigo }"><strong><fmt:formatDate value="${mens.dtEnvio.time }"/></strong></a></td>
            							
            							</tr>
            						
            						</c:forEach>
            					</tbody>
            				</table>
            			</div>
            		</div>
            	</div>
            </div>
            
        
        
                
            	 </div>
                <!-- /.col-lg-7 -->
              
                
            </div>
            <!-- /.row -->
            
        </div>   
        <!-- /#page-wrapper -->

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
    
    
    

</body>

</html>
