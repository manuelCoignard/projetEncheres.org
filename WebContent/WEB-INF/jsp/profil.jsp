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

	<c:if test="${not empty connectedUser}">
		<h3>Vous êtes connecté en tant que : ${connectedUser.getPseudo()}</h3>
	</c:if>

			


<form action="<%=request.getContextPath()%>/ServletProfil" method="POST">
<button type="submit" value="ok">Modifier le profil</button>
</form>

</body>
</html>