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
            		
            		Nova Mensagem
            		
            	</div>
            	<div class="panel-body">
            		
            		<div class="row">
            		
            			<div class="col-md-12">
            				<form action="mensagem" method="post">
            					<input type="hidden" name="acao" value="enviar">
            					<input type="hidden" name="email" value="${user.usuario.email }">
            					<input type="hidden" name="emailRemetente" value="${user.usuario.email }">
            					<input type="hidden" name="nomeRemetente" value="${user.nome }">
            					
            					<div class="row">
            						<div class="form-group col-md-4">
            							<label for="nomeDestinatario">Destinatário</label>
            							<input type="text" name="nomeDestinatario" value="${men.nomeDestinatario }" placeholder="Nome" class="form-control">
            						</div>
            						<div class="form-group col-md-8">
            							<label for="emailDestinatario">Destinatário</label>
            							<input list="emails" name="emailDestinatario" value="${men.emailDestinatario }" placeholder="Email" class="form-control" required>
            							<datalist id="emails">
            								<c:forEach var="em" items="${emails }">
            									<option value="${em.email }">
            								</c:forEach>
            							</datalist>
            						</div>
            					</div> 
            					
            					<div class="row">
            						<div class="form-group col-md-12">
            							<label for="assunto">Assunto</label>
            							<input type="text" name="assunto" value="${men.assunto }" placeholder="Assunto" class="form-control" required>
            						</div>
            					</div>
            					
            					<div class="row">
            						<div class="form-group col-md-12">
            							<label for="mensagem">Mensagem</label>
            							<textarea name="mensagem" placeholder="Escreva aqui sua mensagem para o destinatário..." required class="form-control" cols="12" rows="12" maxlength="240"></textarea>
            						</div>
            					</div>
            					
            					<div class="row">
            						<div class="form-group col-md-12">
            							<span class="pull-right">
            								<a href="mensagem?acao=listarTodos&email=${user.usuario.email }" class="btn btn-danger">Cancelar</a>
            								<input type="submit" value="Enviar" class="btn btn-primary">
            							</span>
            						</div> 
            					</div>
            				</form>
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
