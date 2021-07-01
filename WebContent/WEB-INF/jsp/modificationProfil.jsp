<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/styleModificationProfil.css">
<title>ModificationProfil</title>
</head>
<body>
	<header>
		<h1>Modification de mon profil</h1>
	</header>

	<main>
	
		<form action="" method="POST">
			<section id="champs-saisi">
				<br>		
					<div class="pseudo">
						<label for="pseuso">Pseudo :</label>
				        <input type="text" id="pseudo" name="pseudo" placeholder="${profilConnecte.getPseudo() }"><br>
				    </div>		           
				<br>		
					<div class="nom">    
					    <label for="nom">Nom :</label>
					    <input type="text" id="nom" name="nom" placeholder="${profilConnecte.getNom() }"><br>	    
				    </div>
				<br>    
				    <div class="prenom">
					    <label for="prenom">Prenom :</label>
					    <input type="text" id="prenom" name="prenom" placeholder="${profilConnecte.getPrenom() }"><br>	    
					</div>    
				<br>
					<div class="email">    
					    <label for="email">Email :</label>   	    
				        <input type="email" name="email" id="email" placeholder="${profilConnecte.getEmail() }" size="25" pattern="^[\w+.-]+@\w+.\w{2,5}$"><br>	        
			        </div>
			    <br>    
			        <div class="telephone">
				        <label for="phone">Téléphone :</label>
				        <input type="phone" name="telephone" id="telephone" placeholder="${profilConnecte.getTelephone() }" size="15"><br>
					</div>	
				<br>
					<div class="rue">
						<label for="rue">Rue :</label>
						<input type="text" name="rue" id="rue" placeholder="${profilConnecte.getRue() }"><br>
					</div>
				<br>		
					<div class="codePostal">
						<label for="cp">Code postal :</label>
						<input type="text" name="codepostal" id="codepostal" placeholder="${profilConnecte.getCodePostal() }"><br>
					</div>	
				<br>
					<div class="ville">	
						<label for="ville">Ville :</label>
						<input type="text" name="ville" id="ville" placeholder="${profilConnecte.getVille() }"><br>		
					</div>
				<br>	
					<div class="passwordActuel">
						<label for="passwordActuel">Rentrez votre mot de passe actuel :</label>
				        <input type="password" name="passwordActuel" id="passwordActuel" size="30" minlength="6" ><br>
					</div>
				<br>
					<div class="password">
				        <label for="password">Tapez votre nouveau mot de passe :</label>
				        <input type="password" name="password" id="password" size="30" minlength="6" ><br>
					</div> 	
				<br>
					<div class="passwordbis">
				        <label for="passwordbis">Confirmation du mot de passe:<span style="color: darkred;">*</span></label>
				        <input type="password" name="passwordbis" id="passwordbis" size="30" minlength="6" ><br>
					</div>   
				<br>
					<div class="points">
						<label for="point">Votre Crédit : ${profilConnecte.getCredit() }</label>
						
					</div>	        	
	        </section>
	        	<br>
	        <nav id="lien-utilisateur">
				<div class="bouton-enregistrer">
					<button type="submit" value="ok">Enregistrer</button><br>
				</div>				
				
				<a href="${pageContext.request.contextPath }/supprimer?idProfil=${connectedUser.getNoUtilisateur()}" class="badge text-danger" title="Supprimer">Supprimer</a>
				
			</nav>			
	    </form>
	<br>
	
	<c:if test="${!empty requestScope.listeMessagesErreur }">
		<ul>
			<c:forEach var="msg" items="${requestScope.listeMessagesErreur }">
				<li>${msg}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<a href="${pageContext.request.contextPath}/ServletPageAccueil">Retour à l'accueil</a>
	            

	</main>

</body>
</html>