<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- ajoute la librairie JSTL à la page -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
</head>
<body>
<h1>Page Accueil</h1>


      	
        
	 
<p>${connectedUser}</p>

<a href="${pageContext.request.contextPath}/ServletConnection">S'inscrire - se connecter</a>
<br>
<a href="${pageContext.request.contextPath}/ServletAjoutArticle">Vendre un article</a>

<c:if test="${connectedUser}!=null">
	<br>
	<a href="${pageContext.request.contextPath}/ServletDeconnexion">Déconnexion</a>
</c:if>

</body>
</html>