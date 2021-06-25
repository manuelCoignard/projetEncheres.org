package fr.eni.projetEnchere.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bll.UtilisateurManager;
import fr.eni.projetEnchere.exceptions.LecteurMessage;



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
			
			//3. Si L'inscription s'est bien passée je pars vers l'accueil
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
			rd.forward(request, response);
			
		} catch (BusinessException be) {			
			List<String> listeMsgError = new ArrayList<>();
			for (int code : be.getListeCodesErreur()) {
				listeMsgError.add(LecteurMessage.getMessageErreur(code));
					
			}
			//Envoi de la liste 
			request.setAttribute("ListeMessageErreur",listeMsgError);
			
			//4. Si pb lors de l'insertion réaffiche le formulaire			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp");
			rd.forward(request, response);
			
			
		}
		
			
	}

}
