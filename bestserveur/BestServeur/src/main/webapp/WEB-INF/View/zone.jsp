<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.css">
<link rel="stylesheet" href="${root}/resources/css/table.css">
<link rel="stylesheet" href="${root}/resources/css/common.css">
<title>Zone</title>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
    <br>
    <c:if test = "${not empty message}">
    	<c:if test = "${not empty message['detete']}">
		   	 <div class="alert alert-danger alert-dismissible fade in">
			    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			    <strong><c:out value="${message['detete']}"/></strong> 
			  </div>
		</c:if>
		<c:if test = "${not empty message['add']}">
			<div class="alert alert-success alert-dismissible fade in">
			    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			    <strong><c:out value="${message['add']}"/></strong> 
		 	 </div>
		</c:if>
	</c:if>
	<div class="container" >
		<div class="row">
			 <div class="col-lg-offset-3">
			 	 <form method="post" >					  
					  <div class="form-group row">
			    		<label for="nom" class="col-sm-2 col-form-label">Nom Zonet</label>
				    		<div class="col-sm-10">
				     			 <input type="text"  name="nom" class="form-control-plaintext" id="nomzone" placeholder="Nom zone">
				   			 </div>
  					  </div>
  					  
  					  <div class="form-group row ">
					    <label for="batiment" class="col-sm-2 col-form-label">Batiment</label>
						   	<div class="col-sm-10">
							    <select name="batiment"  class="form-control-plaintext" id="batiment">
							    	<c:forEach items="${Listbat}" var="bat">  
						          	   <option value=${bat.id}>${bat.name}</option>					             
						            </c:forEach> 	
						        </select>
						    </div>
					  </div>
					  
					  <br>
  					  <div class="col-lg-offset-3">	
  					  	<input type="submit"  id="valider" class="btn btn-primary"  value="Ajouter"> 
					  </div>
				</form>
			</div>	
	 	</div>
	</div>
	
	<div class="container" >
		<c:if test = "${not empty Listzone}">
		      
		        <table id="table" class="table table-striped table-bordered" cellspacing="0" width="100%">
		          <thead>
		            <tr>
		              <th>Id</th>
		              <th>Nom zone</th>		              
		            </tr>
		          </thead>
		          <tfoot>
		            <tr>
		              <th>Id</th>
		              <th>Nom zone</th>		              
		            </tr>
		        </tfoot>
		          <tbody id="myTable">  
		          	<c:forEach items="${Listzone}" var="zone">  
		          	 	<tr>
			              <td>${zone.id}</td>
			              <td>${zone.name}</td>			              
			            </tr>  
		            </c:forEach> 
		          </tbody>
		        </table>       
	    </c:if>
	</div>
	
	<script src="${root}/resources/js/jquery-3.3.1.min.js"></script>
    <script src="webjars/bootstrap/3.3.7-1/js/bootstrap.js"></script>
    <script src="${root}/resources/js/table.js"></script>
    <script src="${root}/resources/js/jquery.dataTables.min.js"></script>
    <script src="${root}/resources/js/dataTables.bootstrap.min.js"></script>
    <script src="${root}/resources/js/formvalidator.js"></script>
</body>
</html>