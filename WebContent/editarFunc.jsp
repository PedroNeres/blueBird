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
            
                <div class="col-lg-12">
        
        			<div class="panel panel-default">
        				<div class="panel-heading">
        					<strong>Cadastrar Funcionário</strong>
        				</div>
        				<div class="panel-body">
        					<form action="funcionario" method="get">
        						<input type="hidden" name="acao" value="editar">
        						<input type="hidden" name="cdDespachante" id="cdDespachante" value="${user.filial.despachante.codigo }">
        						<input type="hidden" name="cdFilial" id="cdFilial" value="${user.filial.codigo }">
        						<input type="hidden" name="cdFuncionario" id="cdFuncionario" value="${fun.codigo }">
        						<div class="form-group col-md-12">
        							<label for="nome">Nome</label>
        							<input type="text" name="nome" id="nome" readonly="readonly" value="${fun.nome }" class="form-control">
        							
        						</div>
        						<div class="form-group col-md-7">
        							<label for="cpf">Cpf</label>
        							<input type="text" name="cpf" id="cpf" readonly="readonly" class="form-control" value="${fun.cpf }">
        					
        						</div>
        						<div class="form-group col-md-5">
        							<label for="dtAdmissao">Data Admissão</label>
        							<input type="text" name="dtAdmissao" readonly="readonly" id="data" maxlength="10" class="form-control"
        								value="<fmt:formatDate value='${fun.dtAdmissao.time }'/>">
        						</div>
        						
        							<legend>Endereço</legend>
        						
        						
        							<div class="form-group col-md-5">
        								<div class="input-group custom-search-form">
        			
        									<input type="text" name="cep" value="${fun.endereco.cep }" id="cep" class="form-control">
        								
        									<span class="input-group-btn">
                               					<a href="#" class="btn btn-default" id="buscarCep">
        											<i class="fa fa-search fa-fw"></i>
        										</a>                               				
                            				</span>
        								</div>
        							</div>
        						
        						<div class="form-group col-md-9">
        							<label for="logradouro">Loggradouro</label>
        							<input type="text" name="logradouro" value="${fun.endereco.logradouro }" id="logradouro" class="form-control">
        						</div>
        						<div class="form-group col-md-3">
        							<label for="numero">Numero</label>
        							<input type="text" name="numero" id="numero" value="${fun.endereco.numero }" class="form-control">
        						</div> 
        						<div class="form-group col-md-4">
        							<label for="complemento">Complemento</label>
        							<input type="text" name="complemento" id="complemento" value="${fun.endereco.complemento }" class="form-control">
        						</div>
        						<div class="form-group col-md-8">
        							<label for="bairro">Bairro</label>
        							<input type="text" name="bairro" value="${fun.endereco.bairro }" id="bairro" class="form-control">
        						</div> 
        						<div class="form-group col-md-10">
        							<label for="cidade">Cidade</label>
        							<input type="text" name="cidade" value="${fun.endereco.cidade }" id="cidade" class="form-control">
        						</div>
        						<div class="form-group col-md-2">
        							<label for="uf">UF</label>
        							<input type="text" name="uf" id="uf" value="${fun.endereco.uf }" class="form-control">
        						</div>
        					
        							<legend>Contato</legend>
        							
        							<c:forEach var="tel" items="${fun.telefone }">
        								<div class="form-group col-md-2">
        									<label for="ddd">DDD</label>
        									<input type="text" name="ddd1" value="${tel.ddd }" id="ddd" class="form-control">
        							</div>
        								<div class="form-group col-md-4">
        									<label for="tel${tel.numero }"></label>
        									<input type="text" name="tel1" value="${tel.numero }" id="tel" class="form-control">
        								</div>
        							</c:forEach>
        							
        							
        							
        							<legend>Usuário</legend>
        							  <div class="form-group col-md-12">
        							  	<label for="email">Email</label>
        								<div class="input-group">
        						
                                            <span class="input-group-addon">@</span>
                                            <input type="email" id="email" readonly="readonly" value="${fun.usuario.email }" class="form-control" name="email">
                                      	</div>
                                       </div>
                                       
                                     
                                     
                                     <legend>Função</legend>
                                     <div class="row">
                                     <div class="form-group col-md-6">
                                     	<label for="cargo">Cargo</label>
                                                <select id="cargo" name="cargo"  class="form-control">
                                                   
                                                    	<option value="${fun.cargo.codigo }">${fun.cargo.descricao }</option>
                                                    
                                                </select>
                                     </div>
                                     <div class="form-group col-md-6">
   										<label for="salario">Salário</label>
                                     	<div class="input-group">
                                     		
                                     		<span class="input-group-addon">R$</span>
                                     		<input type="text" name="salario" value='<fmt:formatNumber>${fun.salario }</fmt:formatNumber>' class="form-control">
                                     	</div>
                                     </div>
                                     </div>
                                     <div class="row">
                                     <div class="form-group col-md-8">
                                     	<label for="filial">
                                     		Filial
                                     	</label>
                                     	<select id="filial" name="filial" class="form-control">
        
                                  
                                     			<option value="${fun.filial.codigo }">${fun.filial.nome }</option>
                                
                                     	</select>
                                     </div>
                                     </div>
                                     <div class="row">
                					  <div class="form-group col-md-3">
                					  	<input type="submit" class="btn btn-success" value="Salvar">
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
	

	
	
	
	



</script>

</body>

</html>
