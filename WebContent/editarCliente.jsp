<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Pedro Neres">
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
        					<strong>Editar cliente</strong>
        				</div>
        				<div class="panel-body">
        					<form action="cliente" method="post">
        						<input type="hidden" name="acao" value="editar">
        						<input type="hidden" name="codigo" value="${cliente.codigo }">
        						<input type="hidden" name="cdFilial" value="${cliente.filial.codigo }">
        						
        						<div class="form-group col-md-12">
        							<label for="nome">Nome</label>
        							<input type="text" name="nome" value="${cliente.nome }" id="nome" class="form-control">
        							
        						</div>
        						<div class="form-group col-md-5">
        							<label for="cpf">CPF</label>
        							<input type="text" name="cpf" readonly id="cpf" value="${cliente.cpf }" class="form-control">
        					
        						</div>
        				
        						
        							<legend>Endereço</legend>
        						
        						
        							<div class="form-group col-md-5">
        								<label for="cep">Cep</label>
        								<input type="text" name="cep" value="${cliente.endereco.cep }" id="cep" class="form-control">        									
        							</div>
        						
        						<div class="form-group col-md-9">
        							<label for="logradouro">Logradouro</label>
        							<input type="text" name="logradouro" value="${cliente.endereco.logradouro }" id="logradouro" class="form-control"
        								placeholder="Logradouro">
        						</div>
        						<div class="form-group col-md-3">
        							<label for="numero">Número</label>
        							<input type="text" name="numero" id="numero" value="${cliente.endereco.numero }" class="form-control"
        								placeholder="Num">
        						</div> 
        						<div class="form-group col-md-4">
        							<label for="complemento">Complemento</label>
        							<input type="text" name="complemento" id="complemento" value="${cliente.endereco.complemento }" class="form-control"
        								placeholder="Comp">
        						</div>
        						<div class="form-group col-md-8">
        							<label for="bairro">Bairro</label>
        							<input type="text" name="bairro" id="bairro" value="${cliente.endereco.bairro }" id="bairro" class="form-control"
        								placeholder="Bairro">
        						</div> 
        						<div class="form-group col-md-10">
        							<label for="cidade">Cidade</label>
        							<input type="text" name="cidade" id="cidade" value="${cliente.endereco.cidade }" id="cidade" class="form-control"
        								placeholder="Cidade">
        						</div>
        						<div class="form-group col-md-2">
        							<label for="uf">UF</label>
        							<input type="text" name="uf" id="uf" value="${cliente.endereco.uf }" class="form-control"
        								placeholder="UF">
        						</div>
        					
        							<legend>Contato</legend>
        							<%int numero =  1;%>
        							<c:forEach var="tel" items="${cliente.telefone }">
        							
        							
        							<input type="hidden" name="cdTel<%=numero %>" value="${tel.codigo }">
        							<div class="form-group col-md-2">
        								<label for="ddd${tel.codigo }">DDD</label>
        								<input type="text" name="ddd<%= numero %>" id="ddd${tel.codigo }" value="${tel.ddd }" class="form-control"
        									placeholder="DDD">
        							</div>
        							<div class="form-group col-md-4">
        								<label for="tel${tel.codigo }">Tel</label>
        								<input type="text" name="tel<%=numero %>" id="tel${tel.codigo }" value="${tel.numero }" class="form-control"
        									placeholder="Telefone 1">
        							</div>
        							<%numero ++; %>
        							</c:forEach>
        							
        							<legend>Usuário</legend>
        							  <div class="form-group col-md-12">
        							  	<label for="email">E-mail</label>
        								<div class="input-group">
                                            <span class="input-group-addon">@</span>
                                            <input type="email" id="email" value="${cliente.usuario.email }" class="form-control" name="email" placeholder="E-mail">
                                      	</div>
                                       </div>
                        
                                     
                                     <div class="row">
                					  <div class="form-group col-md-3">
                					  	<input type="submit" class="btn btn-success" value="Editar">
                					  </div>
                					 </div>
        					</form>
        				</div>
        			</div>
                
           		</div>
                <!-- /.col-lg-7 -->
                
             
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
