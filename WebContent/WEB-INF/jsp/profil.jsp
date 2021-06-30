<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/styleProfil.css">
<title>profil</title>
</head>
<body>


	<h1>Profil utilisateur</h1>

				
				
				<table>
					<tr>
						<td><strong>Pseudo :</strong></td>
						<td><strong><c:out value="${profilConnecte.getPseudo()}" /></strong></td>
					</tr>
					<tr>
						<td>Nom :</td>
						<td><c:out value="${profilConnecte.getNom()}" /></td>
					</tr>
					<tr>
						<td>Prénom :</td>
						<td><c:out value="${profilConnecte.getPrenom()}" /></td>
					</tr>
					<tr>
						<td>Email :</td>
						<td><c:out value="${profilConnecte.getEmail()}" /></td>
					</tr>
					<tr>
						<td>Telephone :</td>
						<td><c:out value="${profilConnecte.getTelephone()}" /></td>
					</tr>
					<tr>
						<td>Rue :</td>
						<td><c:out value="${profilConnecte.getRue()}" /></td>
					</tr>
					<tr>
						<td>Code Postal :</td>
						<td><c:out value="${profilConnecte.getCodePostal()}" /></td>
					</tr>
					<tr>
						<td>Code Ville :</td>
						<td><c:out value="${profilConnecte.getVille()}" /></td>
					</tr>
				</table>
				<br>
	<footer>		
		<c:if test="${not empty connectedUser}">
			<a href="<%=request.getContextPath()%>/ServletModificationProfil">Modifier le profil</a>
		</c:if>
	</footer>
			




</body>
</html>