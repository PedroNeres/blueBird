<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="ModalAlterarSenha" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
    			  <div class="modal-header">
    			    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    			    <h4 class="modal-title" id="myModalLabel">Alterar Senha de usuário</h4>
    			  </div>
    			  	<form action="usuario" method="post">
    			  <div class="modal-body">
        	
        				<input type="hidden" name="acao" value="alterarSenha">
        			<div class="row">
        				<div class="form-group col-md-6">
        					<label for="email">E-mail</label>
        					<input type="email" name="email" id="senha" class="form-control" readonly value="${user.usuario.email }">
        				</div>
        				<div class="form-group col-md-6">
        					<label for="antigaSenha">Digite sua senha antiga</label>
        					<input type="password" name="antigaSenha" id="antigaSenha" class="form-control" placeholder="Senha Antiga">
        				</div>
        
        			</div>
        			<div class="row">
        				<div class="form-group col-md-6">
        					<label for="senha">Digite uma nova senha!</label>
        					<input type="password" name="senha" id="senha" class="form-control" placeholder="Nova senha">
        				</div>
        				<div class="form-group col-md-6">
        					<label for="confSenha">Confirme sua nova senha!</label>
        					<input type="password" name="confSenha" id="confSenha" class="form-control" placeholder="Confirmação de senha">
        				</div>
        			</div>
      			  </div>
     			  <div class="modal-footer">
        		   	<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
       				<input type="submit" class="btn btn-primary" value="Alterar">
      			   </div>
      			  	 </form>
    				</div>
  				</div>
			</div>
	