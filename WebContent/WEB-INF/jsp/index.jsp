<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ajoute la librairie JSTL à la page -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/styleIndex.css">
<title>Accueil</title>
</head>

<body>

	<nav id="connection-utilisateur">
		<c:choose>
			<c:when test="${empty connectedUser}">
				<div id="lien-connection"><a href="${pageContext.request.contextPath}/ServletConnection">S'inscrire - se connecter</a></div>
			</c:when>
			<c:otherwise>
				<div id="vendre"><a href="${pageContext.request.contextPath}/ServletAjoutArticle">Vendre un article</a></div>
				<div id="profil"><a href="${pageContext.request.contextPath}/ServletProfil?pseudoProfil=${connectedUser.getPseudo()}">Profil</a></div>
				<div id="deconnexion"><a href="${pageContext.request.contextPath}/ServletDeconnexion">Déconnexion</a></div>				
			</c:otherwise>
		</c:choose> 
	</nav>
	
	<div id="statut-connection">
		<c:if test="${not empty connectedUser}">
			<strong>Vous êtes connecté en tant que : ${connectedUser.getPseudo()}</strong>
		</c:if>
	</div>
				
	<h1>Page Accueil</h1>
	
	<c:if test="${not empty connectedUser}">
		<section>
			<form action="${pageContext.request.contextPath}/ServletPageAccueil" method="POST">
				<fieldset>
					<legend>Filtres :</legend>
					<input type="text" list="lstChoix" id="zoneRecherche" name="zoneRecherche" value=${not empty param.zoneRecherche?param.zoneRecherche:''}>
					
					<datalist id="lstChoix">
						<c:forEach items="${listeArticle}" var="article">
							<option value="${article.getNomArticle() }">
						</c:forEach>
					</datalist>
					
					<label for="categorie">Catégories : </label>
					<select name="categorie" id="categorie">
							<option value="0">Toutes</option>
						<c:forEach items="${lstCategories}" var="cat">
							<option value="${cat.getId()}" ${param.categorie eq cat.getId()?'selected':''}>${cat.getLibelle()}</option>
						</c:forEach>
					</select>
					
					<fieldset>
						<input type="radio" id="optBtnAchats" name="achatsVente" value="achat" ${param.achatsVente != 'vente'?'checked':''} onclick="document.getElementById('frmAchats').disabled=false;document.getElementById('frmVentes').disabled=true;document.getElementById('chkMesVentesEnCours').checked=false;document.getElementById('chkVentesNonDebutees').checked=false;document.getElementById('chkVentesTerminees').checked=false;">
						<label for="optBtnAchats">Achats</label>
						<fieldset ${param.achatsVente == 'vente'?'disabled':''} id="frmAchats">
							<c:choose>
								<c:when test="${param.achatsVente != 'vente' && empty param.chkEncheresOuvertes && empty param.chkMesEncheres && empty param.chkMesEncheresEmportees}">
									<input type="checkbox" id="chkEncheresOuvertes" name="chkEncheresOuvertes" value=1 checked=true}>
								</c:when>
								<c:otherwise>
									<input type="checkbox" id="chkEncheresOuvertes" name="chkEncheresOuvertes" value=1 ${not empty param.chkEncheresOuvertes?'checked':''}>
								</c:otherwise>
							</c:choose>
							<label for="chkEncheresOuvertes">Enchères ouvertes</label>
							<input type="checkbox" id="chkMesEncheres" name="chkMesEncheres" value=2 ${param.chkMesEncheres != null?'checked':''}>
							<label for="chkMesEncheres">Mes enchères</label>
							<input type="checkbox" id="chkMesEncheresEmportees" name="chkMesEncheresEmportees" value=4 ${param.chkMesEncheresEmportees != null?'checked':''}>
							<label for="chkMesEncheresEmportees">Mes enchères remportées</label>
						</fieldset>
						
						<input type="radio" id="optBtnVentes" name="achatsVente" value="vente" ${param.achatsVente eq 'vente'?'checked':''} onclick="document.getElementById('frmVentes').disabled=false;document.getElementById('frmAchats').disabled=true;document.getElementById('chkEncheresOuvertes').checked=false;document.getElementById('chkMesEncheres').checked=false;document.getElementById('chkMesEncheresEmportees').checked=false;">
						<label for="optBtnVentes">Mes ventes</label>
						<fieldset ${param.achatsVente != 'vente'?'disabled':''} id="frmVentes">
							<c:choose>
								<c:when test="${param.achatsVente == 'vente' && param.chkMesVentesEnCours == null && param.chkVentesNonDebutees == null && param.chkVentesTerminees == null}">
									<input type="checkbox" id="chkMesVentesEnCours" name="chkMesVentesEnCours" value=1 checked=true}>
								</c:when>
								<c:otherwise>
									<input type="checkbox" id="chkMesVentesEnCours" name="chkMesVentesEnCours" value=1 ${param.chkMesVentesEnCours != null?'checked':''}>
								</c:otherwise>
							</c:choose>
							<label for="chkMesVentesEnCours">Mes ventes en cours</label>
							<input type="checkbox" id="chkVentesNonDebutees" name="chkVentesNonDebutees" value=2 ${param.chkVentesNonDebutees != null?'checked':''}>
							<label for="chkVentesNonDebutees">Mes ventes non débutées</label>
							<input type="checkbox" id="chkVentesTerminees" name="chkVentesTerminees" value=4 ${param.chkVentesTerminees != null?'checked':''}>
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
					
					<c:if test="${not empty connectedUser}">
						<td></td>
						<td>Filtre : </td>
					</c:if>
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