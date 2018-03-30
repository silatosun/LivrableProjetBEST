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
<title>Batiment</title>
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
		
		<c:if test = "${not empty message['alerte']}">
			<div class="alert alert-success alert-dismissible fade in">
			    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			    <strong><c:out value="${message['alerte']}"/></strong> 
		 	 </div>
		</c:if>
	</c:if>
	<div class="container" >
		<div class="row">
			 <div class="col-lg-offset-3">
			 	 <form method="post" >					  
					  <div class="form-group row">
			    		<label for="nom" class="col-sm-2 col-form-label">Nom batiment</label>
				    		<div class="col-sm-10">
				     			 <input type="text"  name="nom" class="form-control-plaintext" id="nombat" placeholder="Nom batiment">
				   			 </div>
  					  </div>
  					  <div class="col-lg-offset-3">		  
					  	<input type="submit"  id="valider" class="btn btn-primary"  value="Ajouter"> 
					  </div>
				</form>
			</div>	
	 	</div>
	</div>
	
	<div class="container" >
		<c:if test = "${not empty Listbat}">
		      
		        <table id="table" class="table table-striped table-bordered" cellspacing="0" width="100%">
		          <thead>
		            <tr>
		              <th>Id</th>
		              <th>Nom bâtiment</th>
		              <th>Action</th>
		              <th>Alerter</th>
		            </tr>
		          </thead>
		          <tfoot>
		            <tr>
		              <th>Id</th>
		              <th>Nom bâtiment</th>
		              <th>Action</th>
		              <th>Alerter</th>
		            </tr>
		        </tfoot>
		          <tbody id="myTable">  
		          	<c:forEach items="${Listbat}" var="bat">  
		          	 	<tr>
			              <td>${bat.id}</td>
			              <td>${bat.name}</td>
			              <td><a href="${root}/batiment?id=${bat.id}" class="btn btn-primary btn-lg " role="button" > <span class="glyphicon glyphicon-trash"></span></a></td>
			              <td><a href="${root}/batiment?idBatiment=${bat.id}&alerte=TRUE" class="btn btn-primary" role="button" > <span class="glyphicon glyphicon-alert"></span></a></td>
			              <td><a href="${root}/batiment?idBatiment=${bat.id}&alerte=FALSE" class="btn btn-primary" role="button" > <span class="glyphicon glyphicon-remove-circle"></span></a></td>			            
			            </tr>  
		            </c:forEach> 
		          </tbody>
		        </table>       
	    </c:if>
	</div>
	
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>-->
	<script src="${root}/resources/js/jquery-3.3.1.min.js"></script>
    <script src="webjars/bootstrap/3.3.7-1/js/bootstrap.js"></script>
    <script src="${root}/resources/js/table.js"></script>
    <script src="${root}/resources/js/jquery.dataTables.min.js"></script>
    <script src="${root}/resources/js/dataTables.bootstrap.min.js"></script>
    <script src="${root}/resources/js/formvalidator.js"></script>
</body>
</html>