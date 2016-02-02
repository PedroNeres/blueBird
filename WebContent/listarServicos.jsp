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
       <%@include file="../topo.jsp" %>
	
            

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
                
                <table class="table">
                	<tr>
                		<th>Servi�os</th>
                		<th>Valor Taxa</th>
                		<th>Valor Honor�rio</th>
                		<th>Valor Total</th>
        
                		<th></th>
                		<th><a href="" data-toggle="modal" data-target="#ModalCadServico" title="Novo Servi�o"><i class="btn btn-default btn-sm glyphicon glyphicon-plus"></i></a></th>
                	</tr>
                	<c:forEach var="ser" items="${servicos }">
                		<tr>
                			<td>${ser.descricao }</td>
                			<fmt:setLocale value="pt_BR"/>
                			<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${ser.vlrTaxa }"></fmt:formatNumber></td>
                			<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${ser.vlrHono }"></fmt:formatNumber></td>
                			<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${ser.vlrTotal }"></fmt:formatNumber></td>
                		
                			<td><a href="servico?acao=carregarSer&cdSer=${ser.codigo}" title="Editar" class="btn btn-success btn-sm glyphicon glyphicon-pencil"></a> </td>
							 
                		</tr>
                		
                		
                		
                	</c:forEach>
                </table>
        	


        			
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
    function marcardesmarcar(){
    	  $('.marcar').each(
    	         function(){
    	           if ($(this).prop( "checked")) 
    	           $(this).prop("checked", false);
    	           else $(this).prop("checked", true);               
    	         }
    	    );
    	}
    
    </script>

</body>

</html>
