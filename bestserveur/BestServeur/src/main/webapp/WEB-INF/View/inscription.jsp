<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.css">
<link rel="stylesheet" href="${root}/resources/css/common.css">
<title>Inscription</title>
</head>
<body>
	<br>
	<c:if test = "${not empty message}">
    	<c:if test = "${not empty message['password']}">
		   	 <div class="alert alert-danger alert-dismissible fade in">
			    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			    <strong><c:out value="${message['password']}"/></strong> 
			  </div>
		</c:if>
		<c:if test = "${not empty message['email']}">
		   	 <div class="alert alert-danger alert-dismissible fade in">
			    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			    <strong><c:out value="${message['email']}"/></strong> 
			  </div>
		</c:if>
		<c:if test = "${not empty message['add']}">
			<div class="alert alert-success alert-dismissible fade in">
			    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			    <strong><c:out value="${message['add']}"/></strong> 
		 	 </div>
		</c:if>
	</c:if>
	<div class="container">
		<div class="row">
			 <div class="col-lg-offset-3">
					<form method="post">						
					   <legend class=" col-sm-7 " ><p class="text-center">Inscription</p></legend>
					  <div class="col-sm-7">
						  <div class="form-group">	 
						  		<label for="Email1">Email </label>
						    	<input type="email" class="form-control" name="email" id="email" aria-describedby="email" placeholder="Email">		    
						  </div>
					  </div>
					  <br>
					  <div class="col-sm-7">
						  <div class="form-group">						  
							    <label for="Password1">Password</label>
							    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
							</div>
					  </div>
					  <div class="col-sm-7">
						  <div class="form-group">						  
							    <label for="Password1">Confirm password</label>
							    <input type="password" class="form-control" name="confirmpassword" id="confirmpassword" placeholder="Confirm password">
							</div>
					  </div>
					  <div class="col-lg-offset-3 col-sm-10">		 
					 	 <button type="submit" class="btn btn-primary">S'inscrire</button>
					  </div>
					</form>	
				</div>
			</div>
	</div>
    
    <script src="${root}/resources/js/jquery-3.3.1.min.js"></script>
    <script src="webjars/bootstrap/3.3.7-1/js/bootstrap.js"></script>
    <script src="${root}/resources/js/formvalidator.js"></script>
</body>
</html>
