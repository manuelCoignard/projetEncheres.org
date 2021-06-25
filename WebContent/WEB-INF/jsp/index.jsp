<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ajoute la librairie JSTL à la page -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
</head>
<body>

	<h1>Page Accueil</h1>
	<c:if test="${not empty connectedUser}">
		<h3>Vous êtes connecté en tant que : ${connectedUser.getPseudo()}</h3>
	</c:if>

	<nav>
		<c:choose>
			<c:when test="${empty connectedUser}">
				<a href="${pageContext.request.contextPath}/ServletConnection">S'inscrire
					- se connecter</a>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/ServletAjoutArticle">Vendre
					un article</a>
				<a href="${pageContext.request.contextPath}/ServletDeconnexion">Déconnexion</a>
			</c:otherwise>

		</c:choose> 
	</nav>

	<section>
		<form action="" method="POST">
			<fieldset>
				<legend>Filtres :</legend>
				<input type="text" id="zone_recherche" name="zone_recherche">
				<label for="lst_categories">Catégories : </label>
				<select
					name="lst_categories" id="lst_categories">
						<option value="0">Toutes</option>
					<c:forEach items="${lstCategorie}" var="categorie">
						<option value="${categorie.getId()}">${categorie.getLibelle()}</option>
					</c:forEach>
				</select>
			</fieldset>
		</form>
	</section>
	
	<c:choose>
		<c:when test="${not empty listeArticle}">
			<table>
				<c:forEach items="${listeArticle}" var="article">
					<tr>
						<td><c:out value="${article.nomArticle}" /></td>
						<td><c:out value="${article.description}" /></td>
						<td><c:out value="${article.dateDebutEncheres}" /></td>
						<td><c:out value="${article.finDebutEncheres}" /></td>
						<td><c:out value="${article.prixInitial}" /></td>
						<td><c:out value="${article.prixVente}" /></td>
						<td><c:out value="${article.noCategorie}" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			Aucun article à afficher !
		</c:otherwise>
	
	</c:choose>	

	<footer>
		
	</footer>

</body>
</html>