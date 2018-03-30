<c:set var="root" value="${pageContext.request.contextPath}"/>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Espace Admin Best</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#"><a href="${root}/batiment"  >Espace bâtiment</a></li>
      <li><a href="#"><a href="${root}/zone" >Espace zone</a></li>
      <li><a href="#"><a href="${root}/sortie" >Espace sortie</a></li>     
    </ul>
     <ul class="nav navbar-nav navbar-right">
      <li><a href="${root}/deconnexion" > <span class="glyphicon glyphicon-user"></span> Déconnexion</a></li>
    </ul>
  </div>
</nav>