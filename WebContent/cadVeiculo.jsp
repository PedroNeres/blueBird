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
            
                <div class="col-lg-10">
        
        			<div class="panel panel-default">
        				<div class="panel-heading">
        					<strong>Cadastrar Veículo</strong>
        				</div>
        				<div class="panel-body">
        					<form action="veiculo" method="post">
        						<input type="hidden" name="acao" value="cadastrar">
        						<input type="hidden" name="cpf" value="${cliente.cpf }">
        						<div class="row">
        						<div class="form-group col-md-12">
        							<input type="text" name="modelo" value="${veic.modelo }" id="nome" class="form-control"
        								placeholder="Modelo">
        							
        						</div>
        						</div>
        						<div class="row">
        						<div class="form-group col-md-6">
        							<input type="text" name="placa" id="placa" value="${veic.placa }" class="form-control"
        								placeholder="Placa - Ex AAA0000">
        					
        						</div>       						
        						<div class="form-group col-md-6">
        							<input type="text" name="renavam" id="renavam" value="${veic.renavam }" class="form-control"
        								placeholder="Renavam">
        						</div>
        						</div>
        						<div class="row">
        						<div class="form-group col-md-12">
        							<select class="form-control" name="tipoVeiculo">
        								<option>Selecione</option>
        								<c:forEach var="tp" items="${tpVeiculo }">
        									<option value="${tp.codigo }">${tp.descricao }</option>
        								</c:forEach>
        							</select>
        						</div>	
        						</div>
        						<div class="row">
        							<div class="form-group col-md-5">
        								<input type="submit" value="Cadastrar" class="btn btn-primary">
        							</div>
        						</div>
        					</form>
        				</div>
        			</div>
                
           		</div>
              
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
$("#buscarCep").click(function(){
	
	//CHAMAR O WEBSERVICE PARA BUSCA DE CEP
	
	$.ajax({
		url : "http://api.postmon.com.br/v1/cep/" + $("#cep").val(),
		method: "GET",
		success: function(data){
			$("#logradouro").val(data.logradouro.toUpperCase());
			$("#bairro").val(data.bairro.toUpperCase());
			$("#cidade").val(data.cidade.toUpperCase());
			$("#uf").val(data.estado.toUpperCase());
		}
	});
});
	
	$("#nome").blur(function(){
		$("#nome").val($(this).val().toUpperCase());
	});
	
	$("#email").blur(function(){
		$("#email").val($(this).val().toUpperCase());
	});
	
	$("#data").keyup(function(){
		if($(this).val().length == 2){
			$(this).val($(this).val() + '/');
		}
		if($(this).val().length == 5){
			$(this).val($(this).val() + '/');
		}
		if($(this).val().length == 10){
			$("#cep").focus();
		}
	});
	
	$("#cpf").keyup(function(){
		if($(this).val().length >= 11){
			$("#cep").focus();
		}
	});

</script>

</body>

</html>
