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
            
                <div class="col-lg-7">
        
        			<div class="panel panel-default">
        				<div class="panel-heading">
        					<strong>Cadastrar Funcionário</strong>
        				</div>
        				<div class="panel-body">
        					<form action="funcionario" method="get">
        						<input type="hidden" name="acao" value="cadSupervisor">
        						<input type="hidden" name="cdDespachante" id="cdDespachante" value="${user.filial.despachante.codigo }">
        						<input type="hidden" name="cdFilial" id="cdFilial" value="${user.filial.codigo }">
        						<div class="form-group col-md-12">
        							<input type="text" name="nome" id="nome" class="form-control"
        								placeholder="Nome">
        							<input type="hidden" name="nome2" id="nome2" value="${nome }">
        						</div>
        						<div class="form-group col-md-7">
        							<input type="text" name="cpf" id="cpf" class="form-control"
        								placeholder="CPF">
        							<input type="hidden" name="cpf2" id="cpf2" value="${cpf }">
        						</div>
        						<div class="form-group col-md-5">
        							<input type="text" name="dtAdmissao" id="data" maxlength="10" class="form-control"
        								value="<fmt:formatDate value='${dtAdmissao.time }'/>">
        						</div>
        						
        							<legend>Endereço</legend>
        						
        						
        							<div class="form-group col-md-5">
        								<div class="input-group custom-search-form">
        									<input type="text" name="cep" value="${cep }" id="cep" class="form-control"
        										placeholder="Cep">
        								
        									<span class="input-group-btn">
                               					<a href="#" class="btn btn-default" id="buscarCep">
        											<i class="fa fa-search fa-fw"></i>
        										</a>                               				
                            				</span>
        								</div>
        							</div>
        						
        						<div class="form-group col-md-9">
        							<input type="text" name="logradouro" value="${logradouro }" id="logradouro" class="form-control"
        								placeholder="Logradouro">
        						</div>
        						<div class="form-group col-md-3">
        							<input type="text" name="numero" value="${numero }" class="form-control"
        								placeholder="Num">
        						</div> 
        						<div class="form-group col-md-4">
        							<input type="text" name="complemento" value="${complemento }" class="form-control"
        								placeholder="Comp">
        						</div>
        						<div class="form-group col-md-8">
        							<input type="text" name="bairro" value="${bairro }" id="bairro" class="form-control"
        								placeholder="Bairro">
        						</div> 
        						<div class="form-group col-md-10">
        							<input type="text" name="cidade" value="${cidade }" id="cidade" class="form-control"
        								placeholder="Cidade">
        						</div>
        						<div class="form-group col-md-2">
        							<input type="text" name="uf" id="uf" value="${uf }" class="form-control"
        								placeholder="UF">
        						</div>
        					
        							<legend>Contato</legend>
        							
        							<div class="form-group col-md-2">
        								<input type="text" name="ddd1" value="${ddd1 }" class="form-control"
        									placeholder="DDD">
        							</div>
        							<div class="form-group col-md-4">
        								<input type="text" name="tel1" value="${tel1 }" class="form-control"
        									placeholder="Telefone 1">
        							</div>
        							<div class="form-group col-md-2">
        								<input type="text" name="ddd2" value="${ddd2 }" class="form-control"
        									placeholder="DDD">
        							</div>
        							<div class="form-group col-md-4">
        								<input type="text" name="tel2" value="${tel2 }" class="form-control"
        									placeholder="Telefone 2">
        							</div>
        							
        							<legend>Usuário</legend>
        							  <div class="form-group col-md-12">
        								<div class="input-group">
                                            <span class="input-group-addon">@</span>
                                            <input type="email" id="email" value="${email }" class="form-control" name="email" placeholder="E-mail">
                                      	</div>
                                       </div>
                                       <div class="col-md-12 alert alert-danger" id="erroMsg">
                                       		<b id="erro"></b>
                                       </div>
                                      <div class="form-group col-md-6">
                                      	<input type="password" name="senha" id="senha" class="form-control"
                                      		placeholder="Senha">
                                      </div>
                                      <div class="form-group col-md-6">
                                      	<input type="password" name="cSenha" id="cSenha" class="form-control"
                                      		placeholder="Confirmar senha">
                                     </div>
                                     
                                     
                                     <legend>Função</legend>
                                     <div class="row">
                                     <div class="form-group col-md-6">
                                     	<label for="cargo">Cargo</label>
                                                <select id="cargo" name="cargo"  class="form-control">
                                                    <option>Selecione</option>
                                                    <c:forEach var="car" items="${cargo }">
                                                    	<option value="${car.codigo }">${car.descricao }</option>
                                                    </c:forEach>
                                                </select>
                                     </div>
                                     <div class="form-group col-md-6">
                                     	<label for="salario">Salário</label>
                                     	<div class="input-group">
                                     		<span class="input-group-addon">R$</span>
                                     		<input type="text" name="salario" value="${salario }" placeholder="00.0" class="form-control">
                                     	</div>
                                     </div>
                                     </div>
                                     <div class="row">
                                     <div class="form-group col-md-8">
                                     	<label for="filial">
                                     		Filial
                                     	</label>
                                     	<select id="filial" name="filial" class="form-control">
                                     		<option>Selecione</option>
                                     		<c:forEach var="filial" items="${filiais }">
                                     			<option value="${filial.codigo }">${filial.nome }</option>
                                     		</c:forEach>
                                     	</select>
                                     </div>
                                     </div>
                                     <div class="row">
                					  <div class="form-group col-md-3">
                					  	<input type="submit" class="btn btn-success" value="Cadastrar">
                					  </div>
                					 </div>
        					</form>
        				</div>
        			</div>
                
           		</div>
                <!-- /.col-lg-7 -->
                
                
                <div class="col-lg-5">
                <form>
                	<fieldset>
                	<div class="panel panel-default">
                		<div class="panel-heading">
                			<strong>Despachante</strong>
                		</div>
                		<div class="panel-body">
                		
                		<input type="hidden" name="codigo" value="${fil.codigo }">
                			<div class="row">
                				<div class="form-group col-md-9">
                					<label for="nomeDesp">Nome</label>
                					<input type="text" readonly="readonly" class="form-control" name="nomeDesp" id="nomeDesp" value="${fil.nome }">
                				</div>
                				<div class="form-group  col-md-3">
                					<label for="ssp">SSP</label>
                					<input type="text" readonly="readonly" class="form-control" name="ssp" id="ssp" value="${fil.despachante.ssp }"> 
                				</div>
                			</div>
                			<div class="row">
                				<div class="form-group col-md-9">
                					<label for="logradouro">Logradouro</label>
                					<input type="text" readonly="readonly" class="form-control" name="logradouro" 
                						id="logradouro" value="${fil.endereco.logradouro }">
                				</div>
                				<div class="form-group col-md-3">
                					<label for="numEnd">Núm</label>
                					<input type="text" readonly="readonly" class="form-control" name="numEnd" 
                						id="numEnd" value="${fil.endereco.numero }">
                				</div>
                			</div>                	
                			
                			<div class="row">
                				<div class="form-group col-md-6">
                					<label for="bairro">Bairro</label>
                					<input type="text" class="form-control" name="bairro" 
                						id="bairro" readonly="readonly" value="${fil.endereco.bairro }">
                				</div>
                				<div class="form-group col-md-6">
                					<label for="complemento">Complemento</label>
                					<input type="text" readonly="readonly" class="form-control" name="complemento" 
                						id="complemento" value="${fil.endereco.complemento }">
                				</div>
                			</div>
                			
                			
                			<div class="row">
                				<div class="form-group col-md-9">
                					<label for="cidade">Cidade</label>
                					<input type="text" readonly="readonly" class="form-control" name="cidade" 
                						id="cidade" value="${fil.endereco.cidade }">
                				</div>
                				<div class="form-group col-md-3">
                					<label for="estado">Estado</label>
                					<input type="text" readonly="readonly" class="form-control" name="estado" 
                						id="estado" value="${fil.endereco.uf }">
                				</div>
                				
                				
                			</div>
                		
                		</div>
                	</div>
                	
                			
                			
                		</fieldset>
                	</form>
                	
                	
                	
                	
                	
                </div>
                <!-- /.col-lg-5 -->
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
	
	$("#cpf").blur(function(){
		if($(this).val().length > 2){
			window.location.href = "funcionario?acao=verCpf&cpf=" + $(this).val() + 
				"&nome="+ $("#nome").val() +"&cdDespachante=1" + "&cdFilial=25";
		}
	});
	
	$("#nome").val($("#nome2").val());
	
	$("#cpf").val($("#cpf2").val());
	
	$("#erroMsg").hide();
	
	$("#cSenha").blur(function(){
		if($(this).val() != $("#senha").val()){
			$("#erroMsg").html("A confirmação da senha esta incorreta!");
			$("#erroMsg").fadeIn(500);
		}else{
			$("#erroMsg").hide();
		}
	});
	
	$("#senha").blur(function(){
		
		if($(this).val().length < 5){
			$("#erroMsg").html("A senha deve ter no mínimo 5 caractteres!");
			$("#erroMsg").fadeIn(500);
		}else if($(this).val().length > 15){
			$("#erroMsg").html("A senha deve ter no máximo 15 caractteres!");
			$("#erroMsg").fadeIn(500);
		}else{
			$("#erroMsg").hide();
		}
	});



</script>

</body>

</html>
