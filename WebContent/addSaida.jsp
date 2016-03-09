<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="ModalAddSaida" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
    			  <div class="modal-header">
    			    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    			    <h4 class="modal-title" id="myModalLabel">Adicionar saida</h4>
    			  </div>
    			  	<form action="caixa" method="post">
    			  <div class="modal-body">
        	
        				<input type="hidden" name="acao" value="cadSaida">
        				<input type="hidden" name="cdFilial" value="${user.filial.codigo }">	
        				<div class="row">
        						<div class="form-group col-md-6">
        							<label for="descricao">Descrição</label>
        							<input type="text" name="descricao" id="descricao" class="form-control" placeholder="Descrição">
        						</div>
        						<div class="form-group col-md-6">
        							<label for="valor">Valor</label>
        							<div class="input-group">
                           				 <span class="input-group-addon">R$</span>
        								<input type="text" name="valor" id="valor" placeholder="Valor Pagamento" class="form-control">
        							</div>
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
	