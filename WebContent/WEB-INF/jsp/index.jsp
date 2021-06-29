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
				<a href="${pageContext.request.contextPath}/ServletConnection">S'inscrire - se connecter</a>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/ServletAjoutArticle">Vendre un article</a>
				<a href="${pageContext.request.contextPath}/ServletProfil">Profil</a>
				<a href="${pageContext.request.contextPath}/ServletDeconnexion">Déconnexion</a>
			</c:otherwise>

		</c:choose> 
	</nav>
	
	<c:if test="${not empty connectedUser}">
		<section>
			<form action="" method="POST">
				<fieldset>
					<legend>Filtres :</legend>
					
					<input type="text" id="zoneRecherche" name="zoneRecherche" value=${not empty param.zoneRecherche?param.zoneRecherche:''}>
					
					<label for="categorie">Catégories : </label>
					<select name="categorie" id="categorie">
							<option value="0">Toutes</option>
						<c:forEach items="${lstCategories}" var="cat">
							<option value="${cat.getId()}" ${param.categorie eq cat.getId()?'selected':''}>${cat.getLibelle()}</option>
						</c:forEach>
					</select>
					
					<fieldset>
						<fieldset>						
							<input type="radio" id="optBtnAchats" name="achatsVente" value="achat" ${param.achatsVente eq 'achat'?'checked':''}>
							<label for="optBtnAchats">Achats</label>
							<br>
							<input type="checkbox" id="chkEncheresOuvertes" name="chkEncheresOuvertes" ${param.chkEncheresOuvertes eq 'on'?'checked':''}>
							<label for="chkEncheresOuvertes">Enchères ouvertes</label>
							<input type="checkbox" id="chkMesEncheres" name="chkMesEncheres" ${param.chkMesEncheres eq 'on'?'checked':''}>
							<label for="chkMesEncheres">Mes enchères</label>
							<input type="checkbox" id="chkMesEncheresEmportees" name="chkMesEncheresEmportees" ${param.chkMesEncheresEmportees eq 'on'?'checked':''}>
							<label for="chkMesEncheresEmportees">Mes enchères remportées</label>
						</fieldset>
						<fieldset>
							<input type="radio" id="optBtnVentes" name="achatsVente" value="vente" ${param.achatsVente eq 'vente'?'checked':''}>
							<label for="optBtnVentes">Mes ventes</label>
							<br>
							<input type="checkbox" id="chkMesVentesEnCours" name="chkMesVentesEnCours" ${param.chkMesVentesEnCours eq 'on'?'checked':''}>
							<label for="chkMesVentesEnCours">Mes ventes en cours</label>
							<input type="checkbox" id="chkVentesNonDebutees" name="chkVentesNonDebutees" ${param.chkVentesNonDebutees eq 'on'?'checked':''}>
							<label for="chkVentesNonDebutees">Mes ventes non débutées</label>
							<input type="checkbox" id="chkVentesTerminees" name="chkVentesTerminees" ${param.chkVentesTerminees eq 'on'?'checked':''}>
							<label for="chkVentesTerminees">Mes ventes terminées</label>
						</fieldset>
					</fieldset>
					
					<input type="submit" value="Rechercher">
					
				</fieldset>
			</form>
		</section>
	</c:if>

	<c:choose>
		<c:when test="${not empty listeArticle}">
			<c:forEach items="${listeArticle}" var="article">
				<fieldset>
				<table>
					<tr>
						<td><a href="${pageContext.request.contextPath}/ServletDetailVente?id=${article.getNoArticle()}" name="nomArticle">${article.nomArticle}</a></td>
					</tr>
					<tr>
						<td>Fin de l'enchère</td>
						<td><c:out value="${article.finDebutEncheres}" /></td>
					</tr>
					<tr>
						<td>Prix</td>
						<td><c:out value="${article.prixVente}" /></td>
					</tr>
					<tr>
						<td>Vendeur</td>
						<td><a href="${pageContext.request.contextPath}/ServletProfil?pseudoProfil=${article.getVendeur().getPseudo()}"><c:out value="${article.getVendeur().getPseudo()}" /></a></td>
			
					</tr>
				</table>
				</fieldset>
			</c:forEach>
		</c:when>
		<c:otherwise>
			Aucun article à afficher !
		</c:otherwise>
	
	</c:choose>	

	<footer>
		<c:if test="${!empty requestScope.listeMessagesErreur }">
		<ul>
			<c:forEach var="msg" items="${requestScope.listeMessagesErreur }">
				<li>${msg}</li>
			</c:forEach>
		</ul>
	</c:if>
	</footer>

</body>
</html>