package Control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet AutenticazioneFacade gestisce gli accessi alle servlet HomeControl, 
 * ShopControl, ProdottoControl e UtenteControl.
 * Rimanda i parametri necessari alle servlet (sotto forma di attributi) 
 * rendendo più semplice l'accesso ad esse.
 * La scelta della servlet è data dal parametro action.
 *
 * @author Gioacchino Saraceno
 */
@WebServlet("/AutenticazioneFacade")
public class AutenticazioneFacade extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
  
    try {  
      String action = request.getParameter("action");
      if (action.equals("user")) {
        String username = request.getParameter("usrMail");
        String password = request.getParameter("usrPass");
        String isRegister = request.getParameter("registerOrNot");
        String logout = request.getParameter("logout");
        String nick = request.getParameter("usrNick");
        
        request.setAttribute("usrMail", username);
        request.setAttribute("usrPass", password);
        request.setAttribute("registerOrNot", isRegister);
        request.setAttribute("logout", logout);
        request.setAttribute("usrNick", nick);
        
        RequestDispatcher dispatcher = 
            this.getServletContext().getRequestDispatcher("/UtenteControl");
        dispatcher.forward(request, response);
      } else if (action.equals("shop")) {
        response.sendRedirect(request.getContextPath() + "/ShopControl");
      } else if (action.equals("home")) {
        response.sendRedirect(request.getContextPath() + "/HomeControl");
      } else if (action.equals("prodotto")) {
        String isbn = request.getParameter("isbn");
        request.setAttribute("isbn", isbn);
        
        RequestDispatcher dispatcher = 
                this.getServletContext().getRequestDispatcher("/Prodotto");
        dispatcher.forward(request, response);
      } else { 
        throw new Exception("Errore: Qualcosa è andato storto!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    doGet(request, response);
  }

}
