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
          					
          					<form action="caixa" method="post">
          						<input type="hidden" name="acao" value="editarEntrada">
          						<input type="hidden" name="cdFilial" value="${user.filial.codigo }">
          						<input type="hidden" name="codigo" value="${ent.codigo }">
          						<legend><h4>Editar entrada</h4></legend>
          						<div class="row">
          							<div class="form-group col-md-6">
          								<label for="descricao">Descrição</label>
          								<input type="text" name="descricao" id="descricao" class="form-control" value="${ent.descricao }">
          							</div>
          						</div>
          						<div class="row">
          							<div class="form-group col-md-4">
          								<label for="valor">Valor</label>
          								<input type="text" name="valor" id="valor" class="form-control" value='<fmt:formatNumber>${ent.valor }</fmt:formatNumber>'>
     	     						</div>
        	  					</div>
          						
          						<div class="row">
         							<div class="form-group col-md-6">
         								<a href="caixa?acao=listarFilial&cdFilial=${user.filial.codigo }" class="btn btn-danger">Cancelar</a>
         								<input type="submit" value="Salvar" class="btn btn-success">
         							</div>
          						</div>
          					</form>
          				
          				</div>
          			</div>
          		</div>
          	</div>
          	
          	
        

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
