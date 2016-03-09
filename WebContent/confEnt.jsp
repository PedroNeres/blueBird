<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="ModalConfEntrada${ent.codigo }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
    			  <div class="modal-header">
    			    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    			    <h4 class="modal-title" id="myModalLabel">Confirmar Senha</h4>
    			  </div>
    			  	<form action="caixa" method="post">
    			  <div class="modal-body">
        	
        				<input type="hidden" name="acao" value="carregarEntrada">
        				<input type="hidden" name="codigo" value="${ent.codigo }">
        				<input type="hidden" name="cdFilial" value="${user.filial.codigo }">
        				<input type="hidden" name="senha" value="${user.usuario.password }">	
        				<div class="row">
        						<div class="form-group col-md-6">
        							<label for="confSenha">Senha</label>
        							<input type="password" name="confSenha" id="confSenha" class="form-control" placeholder="Digite a senha">
        						</div>
        					</div>
        				
        				
      			  </div>
     			  <div class="modal-footer">
        		   	<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
       				<input type="submit" class="btn btn-primary" value="Verificar">
      			   </div>
      			  	 </form>
    				</div>
  				</div>
			</div>
	