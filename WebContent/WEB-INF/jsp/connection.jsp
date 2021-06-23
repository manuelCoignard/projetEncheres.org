<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- ajoute la librairie JSTL à la page -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connection</title>
</head>
<body>
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
	<br>
	<a href="${pageContext.request.contextPath}/ServletInscription">
		<input type="button" value="Créer un compte"> 
	</a>
	<br>
	<br>
	<a href="${pageContext.request.contextPath}">
		<input type="button" value="Retour à l'accueil"> 
	</a>
</body>
</html>