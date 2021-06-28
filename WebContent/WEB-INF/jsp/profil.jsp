<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>profil</title>
</head>
<body>

<center>
	<h1>Profil</h1>
</center>

				
				
				<table>
					<tr>
						<td>Pseudo</td>
						<td><c:out value="${profilConnecte.getPseudo()}" /></td>
					</tr>
					<tr>
						<td>Nom</td>
						<td><c:out value="${profilConnecte.getNom()}" /></td>
					</tr>
					<tr>
						<td>Prénom</td>
						<td><c:out value="${profilConnecte.getPrenom()}" /></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><c:out value="${profilConnecte.getEmail()}" /></td>
					</tr>
					<tr>
						<td>Telephone</td>
						<td><c:out value="${profilConnecte.getTelephone()}" /></td>
					</tr>
					<tr>
						<td>Rue</td>
						<td><c:out value="${profilConnecte.getRue()}" /></td>
					</tr>
					<tr>
						<td>Code Postal</td>
						<td><c:out value="${profilConnecte.getCodePostal()}" /></td>
					</tr>
					<tr>
						<td>Code Ville</td>
						<td><c:out value="${profilConnecte.getVille()}" /></td>
					</tr>
				</table>
				
		<br>
	<c:if test="${not empty connectedUser}">
		<form action="<%=request.getContextPath()%>/ServletProfil" method="POST">
		<button type="submit" value="ok">Modifier le profil</button>
		</form>
	</c:if>

			




</body>
</html>