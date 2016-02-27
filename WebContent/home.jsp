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
                <div class="col-lg-12">
                    <h1 class="page-header">Painel de Controle!</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
                
                
            <div class="row">
                <div class="col-lg-10">
        
                <div class="row">
                	<div class="col-lg-4 col-md-4">
                		<div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-money fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${qtnCheques }</div>
                                    <div>Cheque!</div>
                                </div>
                            </div>
                        </div>
                        <a href="cheque?acao=listarAberto">
                            <div class="panel-footer">
                                <span class="pull-left">Mais detalhes</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                	</div>
                	<div class="col-lg-4 col-md-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-comments fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${qtnMensagens }</div>
                                    <div>Mensagens!</div>
                                </div>
                            </div>
                        </div>
                        <a href="mensagem?acao=listarTodos&email=${user.usuario.email }">
                            <div class="panel-footer">
                                <span class="pull-left">Mais detalhes</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4">
                    <div class="panel panel-danger">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-tasks fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${qtnContas }</div>
                                    <div>Contas!</div>
                                </div>
                            </div>
                        </div>
                        <a href="contas?acao=listarVencendo">
                            <div class="panel-footer">
                                <span class="pull-left">Mais detalhes</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                
             </div><!-- LINHA DE NOVAS MENSAGENS -->
             
             <c:if test="${user.cargo.codigo == 1}" >
             
             <div class="row">
             	<div class="col-md-12">
             		
             		<div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-dollar fa-fw"></i> Pagamentos a ser aprovados
                            <div class="pull-right">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                        A��es
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu pull-right" role="menu">
                                        <li><a href="pagamento?acao=aprovarTodos">Aprovar Todas</a>
                                        </li>
                                        <li><a href="pagamento?acao=listar">Exibir detalhes</a>
                                        
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                         
                               
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped">
                                            <thead>
                                                <tr>
                                                    <th>O.S</th>
                                                    <th>Cliente</th>
                                                
                                                    <th>Valor</th>
                                                    <th>Forma Pag</th>
                                                    <th>Atendente</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="pag" items="${pagAberto }"> 
                                                <tr>
                                                    <td>${pag.ordemServico.numero }</td>
                                                    <td>${pag.ordemServico.cliente.nome }</td>
                                                    
                                                    <td><fmt:formatNumber>${pag.vlPagao }</fmt:formatNumber></td>
                                                    <td>${pag.forPagamento.descricao }</td>
                                                    <td>${pag.ordemServico.atendente.nome }</td>
                                                    <td><a href="pagamento?acao=aprovarPag&cdPagamento=${pag.codigo }" class="fa fa-check" title="Aprovar"></a></td>
                                                </tr>  
                                            </c:forEach>                                            
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- /.table-responsive -->
                       
                        </div>
                        <!-- /.panel-body -->
                    </div>
             		
             	</div>
             </div>

			</c:if>

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
