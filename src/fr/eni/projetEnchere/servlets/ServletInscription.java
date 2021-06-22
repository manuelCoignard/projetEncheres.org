package fr.eni.projetEnchere.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import fr.eni.projetEnchere.bll.UtilisateurManager;



@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//je dispatch vers la servlet inscription
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp");

		rd.forward(request, response);	
	
	}

 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");//permet de gerer les accents
		
		// quand on rentre ds le formulaire reviens sur la page d'accueil
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		
		//1. je crée mes variables implémentées par les infos de l'utilisateur
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codepostal");
		String ville = request.getParameter("ville");
		String mdp = request.getParameter("password");
		
		//2. on envoie le tout à la BLL
		try {
			
			UtilisateurManager.getInstance().ajoutNouvelUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp);
			
		} catch (Exception e) {
			//TODO gerer validation erreur
			e.printStackTrace();
		}
		
		
		//3. 
		
		rd.forward(request, response);	
	}

}
