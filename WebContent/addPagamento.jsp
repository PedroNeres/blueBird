<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="ModalAddPagamento${os.numero }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
    			  <div class="modal-header">
    			    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    			    <h4 class="modal-title" id="myModalLabel">Adicionar pagamento a Ordem de Serviço ${os.numero }</h4>
    			  </div>
    			  	<form action="ordemServico" method="post">
    			  <div class="modal-body">
        	
        				<input type="hidden" name="acao" value="addPagOs">
        				<input type="hidden" name="numOs" value="${os.numero }">
        				<input type="hidden" name="cdFilial" value="${user.filial.codigo }">	
        				<div class="row">
        						<div class="form-group col-md-6">
        							<label for="forPagamento">Forma de Pagamento</label>
        							<select name="forPagamento" id="forPagamento" class="form-control">
        								<option>Selecione</option>
        								<c:forEach var="fPag" items="${formasPag }">
        									<option value="${fPag.codigo }">${fPag.descricao }</option>
        								</c:forEach>
        							</select>
        						</div>
        						<div class="form-group col-md-6">
        							<label for="vlrPag">Valor</label>
        							<div class="input-group">
                           				 <span class="input-group-addon">R$</span>
        								<input type="text" name="vlrPag" id="vlrPag" class="form-control">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        					<div class="form-group col-md-7">
        						<textarea rows="3" cols="10" class="form-control" name="desPag" placeholder="Descrição"></textarea>
        					</div>
        					<div class="form-group col-md-5">
        						<label for="codPag">Número Pag</label>
        						<input type="text" name="codPag" id="codPag" class="form-control" 
        						placeholder="Número">
        					</div>
        					</div>
        					<div class="row ">
        						<div class="form-group col-md-12">
        							<input type="checkbox" name="pDatado" id="pDatado">
        							<label for="pDatado">Cheque pré datado</label>
        						</div>
        						<div class="form-group col-md-5 dtCheque">
        							<label for="dtDeposito">Data pra depósto</label>
        							<input type="text" name="dtDeposito" id="dtDeposito" class="form-control" value="<fmt:formatDate value='${dtHoje.time }'/>">
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
	