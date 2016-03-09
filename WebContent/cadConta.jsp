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
        					<strong>Cadastrar nova conta</strong>
        				</div>
        				<div class="panel-body">
        					<form action="contas" method="post">
        						<input type="hidden" name="acao" value="cadastrar">
        						<input type="hidden" name="cdFilial" id="cdFilial" value="${user.filial.codigo }">
        					<div class="row">
        						<div class="form-group col-md-6">
        							<label for="descricao">Descrição</label>
        							<input type="text" name="descricao" id="descricao" class="form-control"
        								placeholder="Descrição">
        							
        						</div>
        						<div class="form-group col-md-3">
        							<label for="diaVenc">Dia de Venc.</label>
        							<select name="diaVenc" id="diaVenc" class="form-control">
        								<option value="1">01</option>
        								<option value="2">02</option>
        								<option value="3">03</option>
        								<option value="4">04</option>
        								<option value="5">05</option>
        								<option value="6">06</option>
        								<option value="7">07</option>
        								<option value="8">08</option>
        								<option value="9">09</option>
        								<option value="10">10</option>
        								<option value="11">11</option>
        								<option value="12">12</option>
        								<option value="13">13</option>
        								<option value="14">14</option>
        								<option value="15">15</option>
        								<option value="16">16</option>
        								<option value="17">17</option>
        								<option value="18">18</option>
        								<option value="19">19</option>
        								<option value="20">20</option>
        								<option value="21">21</option>
        								<option value="22">22</option>
        								<option value="23">23</option>
        								<option value="24">24</option>
        								<option value="25">25</option>
        								<option value="26">26</option>
        								<option value="27">27</option>
        								<option value="28">28</option>
        								
        							</select>
        						</div>
                               </div>     
                               
                               <div class="row">
                               		<div class="form-group col-md-6">
                                     	<label for="valor">Valor</label>
                                     	<div class="input-group">
                                     		<span class="input-group-addon">R$</span>
                                     		<input type="text" name="valor" placeholder="00,0" class="form-control">
                                     	</div>
                                     </div>
                                     <div class="form-group col-md-3">
                                     	<label for="qtnParc">Qtn parcelas</label>
                                     	<input type="number" name="qtnParc" id="qtnParc" placeholder="Quant Parc" class="form-control">
                                     </div>
                               </div>
                               <div class="row">
                               		<div class="form-group col-md-8">
                               			<label for="observacao">Observações</label>
                               			<textarea rows="3" cols="10" name="observacao" id="observacao" class="form-control" placeholder="Observações"></textarea>
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
