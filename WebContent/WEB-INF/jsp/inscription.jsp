<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- ajoute la librairie JSTL à la page -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/styleInscription.css">
<title>PageInscription</title>
</head>
<body>


<main>

<h1>Création de mon profil utilisateur</h1>

	<form action="<%=request.getContextPath()%>/ServletInscription" method="POST">				
	<br>		
		<div class="pseudo">
			<label for="pseuso">Pseudo :<span style="color: darkred;">*</span></label><br>
	        <input type="text" id="pseudo" name="pseudo" placeholder="votre pseudo ?"><br>
	    </div>		           
	<br>		
		<div class="nom">    
		    <label for="nom">Nom :<span style="color: darkred;">*</span></label><br>
		    <input type="text" id="nom" name="nom" placeholder="votre nom ?" required><br>	    
	    </div>
	<br>    
	    <div class="prenom">
		    <label for="prenom">Prenom :<span style="color: darkred;">*</span></label><br>
		    <input type="text" id="prenom" name="prenom" placeholder="votre prénom ?" required><br>	    
		</div>    
	<br>
		<div class="email">    
		    <label for="email">Email : <span style="color: darkred;">*</span></label><br>	    	    
	        <input type="email" name="email" id="email" placeholder="votre email ?" size="15" pattern="^[\w+.-]+@\w+.\w{2,5}$" required><br>	        
        </div>
    <br>    
        <div class="telephone">
	        <label for="phone">Téléphone :</label><br>
	        <input type="phone" name="telephone" id="telephone" placeholder="votre telephone ?" size="15"><br>
		</div>	
	<br>
		<div class="rue">
			<label for="rue">Rue :<span style="color: darkred;">*</span></label><br>
			<input type="text" name="rue" id="rue" placeholder="votre rue ?"><br>
		</div>
	<br>		
		<div class="codePostal">
			<label for="cp">Code postal :<span style="color: darkred;">*</span></label><br>
			<input type="text" name="codepostal" id="codepostal" placeholder="votre cp ?"><br>
		</div>	
	<br>
		<div class="ville">	
			<label for="ville">Ville :<span style="color: darkred;">*</span></label><br>
			<input type="text" name="ville" id="ville" placeholder="votre ville ?"><br>		
		</div>
	<br>	
		<div class="password">
			<label for="password">Mot de passe :<span style="color: darkred;">*</span></label><br>
	        <input type="password" name="password" id="password" size="30" minlength="6" required><br>
		</div>	
	<br>
		<div class="passwordbis">
	        <label for="passwordbis">Répéter le mot de passe :<span style="color: darkred;">*</span></label><br>
	        <input type="password" name="passwordbis" id="passwordbis" size="30" minlength="6" required><br>
		</div>   
	<br>
	    
	    <div class="boutonCreer">
	        <button type="submit" value="ok">Créer</button><br>
	        <br>
		</div>
		
		<div class="messageErreur">
			<c:if test="${!empty requestScope.ListeMessageErreur }">
			<br>		
				<ul>			
					<c:forEach var="msg" items="${requestScope.ListeMessageErreur }">
						<li>${msg }</li>					
					</c:forEach>
				</ul>
			</c:if>
		</div>
		
		<div class="boutonAnnuler">
	        <a href="${pageContext.request.contextPath}/ServletConnection">Annuler</a>
        </div>         	     
    </form>
    
</main>

</body>
</html>