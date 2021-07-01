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
import javax.servlet.http.HttpSession;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bll.UtilisateurManager;
import fr.eni.projetEnchere.bo.boUtilisateur;
import fr.eni.projetEnchere.exceptions.LecteurMessage;


@WebServlet(urlPatterns = {"/ServletModificationProfil", "/supprimer"})
public class ServletModificationProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();		
		boUtilisateur profilConnecte = (boUtilisateur) session.getAttribute("connectedUser");
		
		// J'envoi l'objet utilisateur "profilConnecte" vers la jsp
		request.setAttribute("profilConnecte", profilConnecte);
		
		// On regarde quelle url a été utilisée pour arriver dans la servlet
		String urlUtilisee = request.getServletPath();
		
		// Si c'est l'url supprimer on désactive l'utilisateur en bdd puis on est redirigé vers la page d'accueil
		if(urlUtilisee.equals("/supprimer")) {
			int id = Integer.parseInt(request.getParameter("idProfil"));
			
			try {
				UtilisateurManager.getInstance().desactivationProfil(id);
				
			} catch (BusinessException be) {
				be.printStackTrace();
				List<String> listeMsgError = new ArrayList<>();
				for (int code : be.getListeCodesErreur()) {
					listeMsgError.add(LecteurMessage.getMessageErreur(code));
						
				}
				//Envoi de la liste 
				request.setAttribute("ListeMessageErreur",listeMsgError);
			
			// On repart vers la servlet page d'accueil
			response.sendRedirect("ServletPageAccueil");
			}
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modificationProfil.jsp");
			rd.forward(request, response);
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
