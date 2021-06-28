<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- ajoute la librairie JSTL � la page -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AjoutArticle</title>
</head>
<body>

<main>

	<form action="<%=request.getContextPath()%>/ServletAjoutArticle" method="POST">
	<center>
		<h2>Vendre un article</h2>
	</center>	
		<br>
		<div>
			<label for="nomArticle">Nom de l'article :</label><br>
	        <input type="text" id="nomArticle" name="nomArticle" required><br>
		    <br>
		    <label for="description">Description :<span style="color: darkred;">*</span></label><br>
		    <input type="text" id="description" name="description"required><br><br>
		    <label for="categorie">Cat�gories :</label><br>
                <select name="categorie" id="categorie">
                    <option value="1">Informatique</option>
                    <option value="2">Ameublement</option>
                    <option value="3">V�tement</option>
                    <option value="4">Sport&Loisirs</option>
                </select><br>
	    <br>
	    </div>
	    <div>
		    <label for="dateDebutEncheres">Date du debut des encheres :<span style="color: darkred;">*</span></label><br>
		    <input type="date" id="dateDebutEncheres" name="dateDebutEncheres" required><br>	    
		    <br>
		    <label for="dateFinEncheres">Date de la fin des encheres :<span style="color: darkred;">*</span></label><br>	    	    
	        <input type="date" name="dateFinEncheres" id="dateFinEncheres" required><br>
	        <br>
        </div>
        <div>
	        <label for="prixInitial">Prix initial de l'enchere :<span style="color: darkred;">*</span></label><br>
	        <input type="number" name="prixInitial" id="prixInitial"required><br>
			<br>
		</div>
		
	    <div>
	        <button type="submit" value="ok">Ajouter</button><br>
	        <br>

	        <a href="${pageContext.request.contextPath}/ServletPageAccueil">Annuler</a>

        </div> 	     
    </form>
    <form>
    	<div>
    	<fieldset>
    	<legend>Retrait</legend>
    	<label for="rue">Rue : </label>
    	<input type="text" name="rue" id="rue" value="${connectedUser.getRue()}"><br>
    	<label for="codepostal">Code Postal : </label>
    	<input type="text" name="codepostal" id="codepostal" value="${connectedUser.getCodePostal()}"><br>
    	<label for="ville">Ville : </label>
    	<input type="text" name="ville" id="ville" value="${connectedUser.getVille()}">
    	</fieldset>
    	</div>
    </form>
</main>

</body>
</html>