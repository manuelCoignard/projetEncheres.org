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
	<form method="POST" action="ServletConnection">
		
		<div class="login">
			<label for="identifiant">Identifiant : </label>
			<input type="text" id="identifiant" name="identifiant">
		</div>
		<br>
		<div class="login">
			<label for="motdepasse">Motdepasse : </label>
			<input type="password" id="motdepasse" name="motdepasse">
		</div>
		<br>
		<button type="submit">Se connecter</button>
		<a href="${pageContext.request.contextPath}/ServletInscription">Créer un compte</a>
	</form>

</body>
</html>