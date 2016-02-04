
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 		<%if (session.getAttribute("user") == null){%>
      		<% response.sendRedirect("usuario?acao=erro");%>
      	<%}%>
 
 <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="usuario?acao=carregarHome">SG Despachante</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>NOME DO USUÁRIO</strong>
                                    <span class="pull-right text-muted">
                                        <em>DATA E HORA</em>
                                    </span>
                                </div>
                                <div>MENSAGEM</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>NOME DO USUÁRIO</strong>
                                    <span class="pull-right text-muted">
                                        <em>DATA E HORA</em>
                                    </span>
                                </div>
                                <div>MENSAGEM</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Todas Mensagens</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-messages -->
                </li>
               
               
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                    	<li><a href="#"><i class="fa fa-user fa-fw"></i>${user.nome } - ${user.cargo.codigo }</a></li>
                    	<li class="divider"></li>
                        <li><a href="#"><i class="fa fa-pencil fa-fw"></i> Editar Perfil</a>
                        </li>
                        <li><a href="" data-toggle="modal" data-target="#ModalAlterarSenha"><i class="fa fa-gear fa-fw"></i> Alterar Senha</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="usuario?acao=logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->
        </nav>
        
        <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                        	<form action="ordemServico" method="get">
                            <div class="input-group custom-search-form">
                            	<input type="hidden" name="acao" value="pesquisar">
                                <input type="text" class="form-control" name="numOS" placeholder="Search O.S ....">
                                <span class="input-group-btn">
                               
                                <input type="submit" class="btn btn-default" value="Buscar">
                            </span>
                            </div>
                            </form>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="usuario?acao=carregarHome"><i class="fa fa-dashboard fa-fw"></i> ${user.nome }</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-building-o fa-fw"></i> Ordem de Serviços<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="verCliente.jsp"><i class="fa fa-plus-circle fa-fw"></i>Nova O.S</a>
                                </li>
                                <li>
                                    <a href="ordemServico?acao=listar"><i class="fa fa-th-list fa-fw"></i>Listar</a>
                                </li>
                                <li>
                                    <a href="#" data-toggle="modal" data-target="#ModalCadTipoOs"><i class="fa fa-star fa-fw"></i>Novo tipo O.S</a>
                                </li>
                                <li>
                                    <a href="ordemServico?acao=carregarAltOs"><i class="fa fa-gears fa-fw"></i> Alterar Status</a>
                                    
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-male fa-fw"></i> Cliente<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="cadCliente.jsp"><i class="fa fa-plus-circle fa-fw"></i>Cadastrar</a>
                                </li>
                                <li>
                                    <a href="cliente?acao=listar"><i class="fa fa-th-list fa-fw"></i>Listar</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        
                        <c:if test="${user.cargo.codigo == 1 }">
                        
                        <li>
                            <a href="#"><i class="fa fa-user fa-fw"></i> Usuários<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                        
                        				 <li>
                                        	<a href="funcionario?acao=carregar&cdDespachante=${user.filial.despachante.codigo }&cdFilial=${user.filial.codigo }"><i class="fa fa-plus-circle fa-fw"></i>Novo Usuário</a>
                                        </li>
                        				
                                        <li>
                                            <a href="funcionario?acao=listarFun&cdFilial=${user.filial.codigo }"><i class="fa fa-th-list fa-fw"></i>Listar</a>
                                        </li>
                                       
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        
                        </c:if>
                        
                        <li>
                            <a href="#"><i class="fa fa-gears fa-fw"></i> Configurar <span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">Serviço <span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="" data-toggle="modal" data-target="#ModalCadServico"><i class="fa fa-plus-circle fa-fw"></i>Novo</a>
                                        </li>
                                        <li>
                                            <a href="servico?acao=listar"><i class="fa fa-th-list fa-fw"></i>Listar</a>
                                        </li>
                                    </ul>
                                    <!-- /.nav-third-level -->
                                </li>
                                
                                <li>
                                    <a href="#">Cargo <span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="#" data-toggle="modal" data-target="#ModalcadCargo"><i class="fa fa-plus-circle fa-fw"></i>Novo</a>
                                        </li>
                                        <li>
                                            <a href="#"><i class="fa fa-th-list fa-fw"></i>Listar</a>
                                        </li>
                                    </ul>
                                    <!-- /.nav-third-level -->
                                </li>
                                
                                <li>
                                    <a href="#">Formas de Pagamento <span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="#" data-toggle="modal" data-target="#ModalcadFormaPagamento"><i class="fa fa-plus-circle fa-fw"></i>Novo</a>
                                        </li>
                                        <li>
                                            <a href="#"><i class="fa fa-th-list fa-fw"></i>Listar</a>
                                        </li>
                                    </ul>
                                    <!-- /.nav-third-level -->
                                </li>
                                
                                <li>
                                    <a href="#">Tipo O.S <span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="#" data-toggle="modal" data-target="#ModalCadTipoOs"><i class="fa fa-plus-circle fa-fw"></i>Novo</a>
                                        </li>
                                        <li>
                                            <a href="#"><i class="fa fa-th-list fa-fw"></i>Listar</a>
                                        </li>
                                    </ul>
                                    <!-- /.nav-third-level -->
                                </li>
                                
                                <li>
                                    <a href="#">Tipo Veiículo <span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="" data-toggle="modal" data-target="#ModalCadTipoVeiculo"><i class="fa fa-plus-circle fa-fw"></i>Novo</a>
                                        </li>
                                        <li>
                                            <a href="#"><i class="fa fa-th-list fa-fw"></i>Listar</a>
                                        </li>
                                    </ul>
                                    <!-- /.nav-third-level -->
                                </li>
                                 <li>
                                    <a href="#">Tipo Status <span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="" data-toggle="modal" data-target="#ModalCadTipoStatus"><i class="fa fa-plus-circle fa-fw"></i>Novo</a>
                                        </li>
                                        <li>
                                            <a href="#"><i class="fa fa-th-list fa-fw"></i>Listar</a>
                                        </li>
                                    </ul>
                                    <!-- /.nav-third-level -->
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
            
            <%@include file="cadServico.jsp" %>
            <%@include file="cadTipoVeiculo.jsp" %>
            <%@include file="cadTipoOs.jsp" %>
            <%@include file="cadFormaPagamento.jsp" %>
            <%@include file="cadCargo.jsp" %>
            <%@include file="cadTipoStatus.jsp" %>
            <%@include file="alterarSenha.jsp" %>
      