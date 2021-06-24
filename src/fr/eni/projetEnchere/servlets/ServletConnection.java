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
import fr.eni.projetEnchere.bo.boUtilisateur;
import fr.eni.projetEnchere.exceptions.LecteurMessage;

/**
 * Servlet implementation class servletConnection
 */
@WebServlet("/ServletConnection")
public class ServletConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/connection.jsp");
		rd.forward(request, response);
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Récupération de l'identifiant et du mot de passe
		String id = request.getParameter("identifiant");
		String mdp = request.getParameter("motdepasse");
		
		//Envoi vers le UtilisateurManager
		boUtilisateur utilisateur = null;
		try {
			utilisateur = UtilisateurManager.getInstance().connexionUtilisateur(id, mdp);
			response.getWriter().append(utilisateur.toString());
		} catch (BusinessException be) {
			List<String> lstMsgError = new ArrayList<>();
			for (int code : be.getListeCodesErreur()) {
				lstMsgError.add(LecteurMessage.getMessageErreur(code));
			}
			
			request.setAttribute("listeMessagesErreur", lstMsgError);
			
			doGet(request, response);
		}
	}
}