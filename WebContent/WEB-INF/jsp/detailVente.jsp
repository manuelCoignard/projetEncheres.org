<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
	<h1>Détail de vente</h1>
</center>
	


				
		<form action="<%=request.getContextPath()%>/ServletDetailVente" method="post">
		<input type="text" value ="${article.getNoArticle()}" name="id" hidden>	
		<!-- <input type="text" value ="${profilConnecte.getNoUtilisateur()}" name="idUtilisateur" hidden> -->	
				<table>
					<tr>
						<td><strong>${article.getNomArticle()}</strong></td>
					</tr>
					<tr>
						<td>Description: </td>
						<td><c:out value="${article.getDescription()}" /></td>
					</tr>
					<tr>
						<td>Catégorie : </td>
						<td><c:out value="${article.getCategorie().getLibelle()}" /></td>
					</tr>
					<tr>
						<td>Meilleur offre: </td>
						<td><c:out value="${article.getEnchere().montantEnchere} par : ${article.getEnchere().getUtilisateurId().getPseudo() }" /></td>
					</tr>
					<tr>
						<td>Mise à prix: </td>
						<!-- <td><c:out value="${article.getPrixInitial()}" /></td> -->
						<td><c:out value="${not empty tata? tata : article.getPrixInitial()}" /></td>
					</tr>
					<tr>
						<td>Fin de l'enchère : </td>
						<td><c:out value="${article.getFinDebutEncheres()}" /></td>
					</tr>
					<tr>
						<td>Retrait :</td>
						<td><c:out value="${article.getVendeur().getRue()}" /></td>
						<td><c:out value="${article.getVendeur().getCodePostal()}" /></td><td><c:out value="${article.getVendeur().getVille()}" /></td>
					</tr>
					<tr>
						<td>Vendeur :</td>
						<td><c:out value="${article.getVendeur().getPseudo()}" /></td>
						
					</tr>
				</table>
				<label for="enchere">Ma proposition:</label>
				<input type="number" name="enchere" id="enchere" min="${article.getEnchere().montantEnchere + 5}" value="${article.getEnchere().montantEnchere + 5}">
				<button type="submit" value="encherir">Enchérir</button>Votre solde est de : ${connectedUser.getCredit()}
				
</form>
</body>
</html>