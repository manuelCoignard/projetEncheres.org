package fr.eni.projetEnchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JEditorPane;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bll.UtilisateurManager;
import fr.eni.projetEnchere.bo.boUtilisateur;


@WebServlet("/ServletProfil")
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// Je recupère de la servlet le profil utilisateur en session
		HttpSession session = request.getSession();		
		boUtilisateur profilConnecte = (boUtilisateur) session.getAttribute("connectedUser");
		
		if(profilConnecte = !empty) {
		//Je crée un utilisateur que je vais initialiser avec les données cherchées en bdd via la méthode connectionPseudo		
		boUtilisateur profilUtilisateur = new boUtilisateur();
		try {
			profilUtilisateur = UtilisateurManager.getInstance().profilParPseudo(profilConnecte.getPseudo());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// J'envoi l'objet l'utilisateur vers la jsp
		request.setAttribute("profilConnecte", profilUtilisateur);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profil.jsp");
		rd.forward(request, response);
	}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modificationProfil.jsp");
		rd.forward(request, response);
	}

}
