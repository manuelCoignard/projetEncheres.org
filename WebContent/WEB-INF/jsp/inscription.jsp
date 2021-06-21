<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PageInscription</title>
</head>
<body>

<main>

	<form action="<%=request.getContextPath()%>/ServletInscription" method="POST">
	<center>
		<h2>Mon Profil</h2>
	</center>	
		<br>
		<div>
			<label for="pseuso">Pseudo :</label><br>
	        <input type="text" id="pseudo" name="pseudo"><br>
		    <br>
		    <label for="nom">Nom :<span style="color: darkred;">*</span></label><br>
		    <input type="text" id="text" name="nom" required><br>
	    <br>
	    </div>
	    <div>
		    <label for="prenom">Prenom :<span style="color: darkred;">*</span></label><br>
		    <input type="text" id="prenom" name="prenom" required><br>	    
		    <br>
		    <label for="email">Email : <span style="color: darkred;">*</span></label><br>	    	    
	        <input type="email" name="email" id="email" placeholder="votre email ?" size="15" pattern="^[\w+.-]+@\w+.\w{2,5}$" required><br>
	        <br>
        </div>
        <div>
	        <label for="phone">Téléphone :</label><br>
	        <input type="phone" name="telephone" id="phone" placeholder="votre telephone ?" size="15"><br>
			<br>
			<label for="rue">Rue :</label><br>
			<input type="text" name="rue" id="rue" placeholder="votre rue ?"><br>
		<br>
		</div>
		<div>
			<label for="cp">Code postal :</label><br>
			<input type="text" name="codepostale" id="cp" placeholder="votre cp ?"><br>
			<br>
			<label for="ville">Ville :</label><br>
			<input type="text" name="ville" id="town" placeholder="votre ville ?"><br>
		<br>
		</div>
		<div>
			<label for="password">Mot de passe :</label><br>
	        <input type="password" name="password" id="password" size="15" minlength="6" required><br>
			<br>
	        <label for="passwordbis">Répéter le mot de passe :</label><br>
	        <input type="password" name="passwordbis" id="passwordbis" size="15" minlength="6" required><br>
		    <br>
	    </div>
	    <div>
	        <button type="submit" value="ok">Créer</button><br>
	        <br>
	        <button type="reset">Annuler</button><!-- Le bouton annuler est de type "reset"-->
        </div> 	     
    </form>
    
</main>

</body>
</html>