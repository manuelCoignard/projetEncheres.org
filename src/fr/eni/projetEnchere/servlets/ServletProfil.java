package fr.eni.projetEnchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bll.UtilisateurManager;
import fr.eni.projetEnchere.bo.boUtilisateur;


@WebServlet(urlPatterns = {"/ServletProfil", "/ServletPageAccueil"})
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// Je recupère de la servlet le profil utilisateur en session
		HttpSession session = request.getSession();		
		boUtilisateur profilConnecte = (boUtilisateur) session.getAttribute("connectedUser");
		
		// Je crée un utilisateur "profilUtilisateur" que je vais initialiser avec les données cherchées en bdd via la méthode connectionPseudo
		boUtilisateur profilUtilisateur = new boUtilisateur();
		
		if(profilConnecte == null) {
			String pseudo = request.getParameter("pseudoProfil");
			try {
				profilUtilisateur = UtilisateurManager.getInstance().profilParPseudo(pseudo);
			} catch (BusinessException e) {				
				e.printStackTrace();
			}
				
			
		}else {
			// recupere l'objet utilisateur en session et l'envoi dans "profilUtilisateur"
			profilUtilisateur = profilConnecte;
			
		}
			// J'envoi l'objet l'utilisateur "profilUtilisateur" vers la jsp
			request.setAttribute("profilConnecte", profilUtilisateur);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profil.jsp");
			rd.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modificationProfil.jsp");
		rd.forward(request, response);
	}

}
