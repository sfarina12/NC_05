package Control;

import Bean.UtenteBean;
import Model.UtenteModelDm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet che implementa la login e la registrazione di un utente.
 *
 * @author Farina Simone
 */
@WebServlet("/UtenteControl")
public class UtenteControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  static UtenteModelDm model;
  static HttpSession session;
       
  /**
   * Costruttore vuoto.
   *
   * @see HttpServlet#HttpServlet()
   */
  public UtenteControl() {
    super(); 
    model =  new UtenteModelDm();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * parametri necessari per il login:
   *   -usrMail (contiene la mail dell'utente);
   *   -usrPass (contiene la password dell'utente);
   *   -usrNick (contiene il nickname dell'utente);
   *   -registerOrNot (può assumere due valori: Y o N. 
   *     indica se si deve effettuare una registrazione);
   *   -logout (può assumere due valori: Y o N. indica se si deve effettuare un' logout).
   * parametri restituiti:
   *   -role (può assumere 3 valori: -1, normal e admin.
   *     Indica se l'utente è un admin oppure un utente normale).
   */
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
  
    String username = (String) request.getAttribute("usrMail");
    String password = (String) request.getAttribute("usrPass");
    String isRegister = (String) request.getAttribute("registerOrNot");
    String logout = (String) request.getAttribute("logout");

    if (logout.charAt(0) == 'Y') {
      request.getSession().removeAttribute("role");
      request.getSession().removeAttribute("loggedUser");
      request.getSession().invalidate();

      String redirectedPage = "/HomePage.jsp";
      response.sendRedirect(request.getContextPath() + redirectedPage);
      
      return;
    }
    session = request.getSession();
    String redirectedPage = "/Login.jsp";
    try {
      if (isRegister.charAt(0) == 'N') {
        int valueCheck = checkLogin(username, password);
        switch (valueCheck) {
          case 0:
            request.getSession().setAttribute("role", "normal"); 
            redirectedPage = "/HomePage.jsp";
            break;
          case 1:
            request.getSession().setAttribute("role", "admin"); 
            redirectedPage = "/ShopPage.jsp";
            break;
          case -1:
            session.setAttribute("loggedUser", null);
            session.setAttribute("logout", "N");
            session.setAttribute("role", "-1");
            redirectedPage = "/Login.jsp";
            break;
          default:break;
        }
      } else {
        if (checkLogin(username, password) == -1) {
          UtenteBean user = new UtenteBean();
          
          user.setNickname((String) request.getAttribute("usrNick"));
          user.setMail((String) request.getAttribute("usrMail"));
          user.setPassword((String) request.getAttribute("usrPass"));
          user.setAdmin(false);

          model.doSave(user);

          redirectedPage = "/ShopPage.jsp";
        }
      }
    } catch (Exception e) {
      session.setAttribute("loggedUser", null);
      session.setAttribute("logout", "N");
      session.setAttribute("role", "-1");
      redirectedPage = "/Login.jsp";
    }
    response.sendRedirect(request.getContextPath() + redirectedPage);
  }
  
  /**
   * controlla se l'utente con quella mail e password esiste gia
   * e restituisce il ruolo dell'utente (admin o no).
   */
  private int checkLogin(String mail, String password) throws Exception {
    
    UtenteBean usr = (UtenteBean) model.doCheckLogin(mail, password);
    System.out.println(usr.getMail());
    try {
      if (usr != null) {
        session.setAttribute("loggedUser", usr);

        if (usr.getMail() == null) {
          return -1;
        }
        
        if (usr.isAdmin() == false) {
          return 0;
        }
        if (usr.isAdmin() == true) {
          return 1;
        }
      } else {
        throw new Exception("Invalid login and password");
      }
    } catch (Exception e) {
      System.out.println("Errore utente");
    }    
    return -1;
  }
}