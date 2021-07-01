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
				//Si pb envoi de la liste d'erreur
				request.setAttribute("ListeMessageErreur",listeMsgError);
			}
			
			// On repart vers la servlet page d'accueil
			response.sendRedirect("ServletPageAccueil");
			
		}else {
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modificationProfil.jsp");
			rd.forward(request, response);
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String> listeMsgError = new ArrayList<>();
		
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codepostal");
		String ville = request.getParameter("ville");
		String mdpActuel = request.getParameter("passwordActuel");
		String mdpModifie = request.getParameter("password");
		String mdpBis = request.getParameter("passwordbis");
		
		try {
			UtilisateurManager.getInstance().updateUtilisateur(pseudo, nom, prenom, email, telephone, rue,
					codePostal, ville, mdpModifie);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// test de savoir si le mot de passe bis est le meme que le premier
				if (mdpModifie.equals(mdpBis)) {

					// 2. on envoie le tout à la BLL
					try {

						UtilisateurManager.getInstance().ajoutNouvelUtilisateur(pseudo, nom, prenom, email, telephone, rue,
								codePostal, ville, mdpModifie);

						// 3. Si l'update s'est bien passée je réaffiche la page avec message
						String messageOk = "Les modifications sur le profil ont bien été prises en compte";
						request.setAttribute("messageok", messageOk);
						
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modificationProfil.jsp");
						rd.forward(request, response);

					} catch (BusinessException be) {				
						for (int code : be.getListeCodesErreur()) {
							listeMsgError.add(LecteurMessage.getMessageErreur(code));
						}
					
					}

				} else {
					// si mdp ne correspondent pas envoi l'erreur ds la liste
					BusinessException be = new BusinessException();
					be.ajouterErreur(CodesErreursServlets.PASSWORD_CONFIRMATION_ERREUR);			
					for (int code : be.getListeCodesErreur()) {
						listeMsgError.add(LecteurMessage.getMessageErreur(code));
					}

				}
				// Envoi de la liste d'erreur
				request.setAttribute("ListeMessageErreur", listeMsgError);

				// 4. Si pb lors de l'update réaffiche le formulaire
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modificationProfil.jsp");
				rd.forward(request, response);
		
		
		
	}

}
