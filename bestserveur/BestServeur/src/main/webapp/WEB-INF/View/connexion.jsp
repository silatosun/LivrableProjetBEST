<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.css">
<link rel="stylesheet" href="${root}/resources/css/common.css">
<title>Connexion</title>
</head>
<body>
	<div class="container">
		<div class="row">
			 <div class="col-lg-offset-3">
					<form method="post">						
					   <legend class=" col-sm-7 " ><p class="text-center">Connexion</p></legend>
					  <div class="col-sm-7">
						  <div class="form-group">	 
						  		<label for="Email1">Email </label>
						    	<input type="email" class="form-control" name="email" id="email" aria-describedby="email" placeholder="Enter email">		    
						  </div>
					  </div>
					  <br>
					  <div class="col-sm-7">
						  <div class="form-group">						  
							    <label for="Password1">Password</label>
							    <input type="password" class="form-control" name="password" id="ppassword" placeholder="Password">
							</div>
					  </div>
					  <div class="col-lg-offset-3 col-sm-10">		 
					 	 <button type="submit" class="btn btn-primary">Connexion</button>
					  </div>
					  <br> <br>
					  <a href="${root}/inscription" class=" col-sm-7 " ><p class="text-center">Inscription !</p></a>
					</form>						
				</div>
			</div>
	</div>
    
    <script src="${root}/resources/js/jquery-3.3.1.min.js"></script>
    <script src="webjars/bootstrap/3.3.7-1/js/bootstrap.js"></script>
    <script src="${root}/resources/js/formvalidator.js"></script>
</body>
</html>
