<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- ajoute la librairie JSTL à la page -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/styleConnection.css">
<title>Connection</title>
</head>
<body>

<h1>Connection</h1>
	<section id="connection">
		<form method="POST" action="${pageContext.request.contextPath}/ServletConnection">		
			<div class="login">
				<label for="identifiant">Identifiant : </label>
				<input type="text" id="identifiant" name="identifiant" required>
			</div>
			<br>
			<div class="login">
				<label for="motdepasse">Mot de passe : </label>
				<input type="password" id="motdepasse" name="motdepasse" required>
			</div>
			<br>
			<input type="submit" value="Se connecter">
		</form>
	</section>
	<br>
	<section id="lien-connection">
		<br>
		<a href="${pageContext.request.contextPath}/ServletInscription">Créer un compte</a>
		<br>	
		<a href="${pageContext.request.contextPath}/ServletPageAccueil">Retour à l'accueil</a>
	</section>
	
	<c:if test="${!empty requestScope.listeMessagesErreur }">
		<br>
		<ul>
			<c:forEach var="msg" items="${requestScope.listeMessagesErreur }">
				<li>${msg}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>