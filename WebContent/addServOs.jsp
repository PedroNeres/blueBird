<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="ModalAddServOs" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
    			  <div class="modal-header">
    			    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    			    <h4 class="modal-title" id="myModalLabel">Adicionar novo servi�o a O.S ${os.numero }</h4>
    			  </div>
    			  	<form action="ordemServico" method="post">
    			  <div class="modal-body">
        	
        				<input type="hidden" name="acao" value="adicServi">
        				<input type="hidden" name="codigo" value="${os.numero }">
        			<div class="row">
        						<div class="form-group col-md-8">
        							<label for="servico">Servi�o</label>
        							<select name="serCodigo" id="servico" class="form-control">
        								<option>Selecione</option>
        								<c:forEach var="ser" items="${servicos }">
        									<option value="${ser.codigo }">${ser.descricao } - ${ser.vlrTotal }</option>
        								</c:forEach>
        							</select>
        						</div>
       
        					</div>
      			  </div>
     			  <div class="modal-footer">
        		   	<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
       				<input type="submit" class="btn btn-primary" value="Adicionar">
      			   </div>
      			  	 </form>
    				</div>
  				</div>
			</div>
	