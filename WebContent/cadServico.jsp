<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="ModalCadServico" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
    			  <div class="modal-header">
    			    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    			    <h4 class="modal-title" id="myModalLabel">Cadastrar novo tipo de serviço</h4>
    			  </div>
    			  	<form action="servico" method="post">
    			  <div class="modal-body">
        	
        				<input type="hidden" name="acao" value="cadastrar">
        				<div class="row">
        				<div class="form-group col-md-6">
        					<label for="descricao">Descrição</label>
        					<input type="text" name="descricao" id="descricao" class="form-control" placeholder="Descrição">
        				</div>
        
        				</div>
        					<div class="row">
        						<div class="form-group col-md-6">
        							<label for="vlTaxa">Valor Taxa</label>
        							<input type="text" name="vlTaxa" id="vlTaxa" 
        							class="form-control" placeholder="Ex 0,00">
        						</div>
        						<div class="form-group col-md-6">
									<label for="vlHono">Valor Honorário</label>
									<input type="text" name="vlHono" id="vlHono"
									class="form-control" placeholder="Ex 0,00">         						
        						</div>
        					</div>
        					<div class="row">
       							<div class="col-md-6">
       								<a title='Todos' id='todos' onclick='marcardesmarcar();' ><i class='fa fa-check fa-fw'></i> Selecionar todos</a>
       							</div>
       						</div>
        					<div class="row">
        						
        						<div class="form-group col-md-6">
        							<h3>Tipo de Veiculo</h3>
        							<c:forEach var="tv" items="${tpVeiculo }">
        								<input type="checkbox" class='marcar' name="tpVeiculo" id="tv${tv.codigo }" value="${tv.codigo }">
        								<label for="tv${tv.codigo }">${tv.descricao }</label><br>
        							</c:forEach>
        						</div>
        						<div class="form-group col-md-6">
        							<h3>Tipo de Ordem</h3>
        							<c:forEach var="to" items="${tpOrdem }">
        								<input type="checkbox" class='marcar' name="tpOrdem" id="to${to.codigo }" value="${to.codigo }">
        								<label for="to${to.codigo }">${to.descricao }</label><br>
        							</c:forEach>
        						</div>	
        					</div>
        				
      			  </div>
     			  <div class="modal-footer">
        		   	<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
       				<input type="submit" class="btn btn-primary" value="Cadastrar">
      			   </div>
      			  	 </form>
    				</div>
  				</div>
			</div>
	