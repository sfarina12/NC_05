package Control;

import Bean.UtenteBean;
import Model.UtenteModelDm;
import java.io.IOException;
import java.sql.SQLException;

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
    
    session = request.getSession();
    
    session.setAttribute("gmName", null);
    session.setAttribute("gmTxt", null);  
    
    if (logout.charAt(0) == 'Y') {
      request.getSession().removeAttribute("role");
      request.getSession().removeAttribute("loggedUser");
      request.getSession().invalidate();

      String redirectedPage = "/HomePage.jsp";
      response.sendRedirect(request.getContextPath() + redirectedPage);
      
      return;
    }
    String redirectedPage = "/Login.jsp";
    try {
      if (isRegister.charAt(0) == 'N') {
        int valueCheck = checkLogin(username, password, isRegister);
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
            session.setAttribute("gmName", "Errore");
            session.setAttribute("gmTxt", "Credenziali errate");  
        	
            session.setAttribute("loggedUser", null);
            session.setAttribute("logout", "N");
            session.setAttribute("role", "-1");
            redirectedPage = "/Login.jsp";
            break;
          default:break;
        }
      } else {
        int value = checkLogin(username, password, isRegister);
        boolean dontSave = false;
        if (value == -2) {
          redirectedPage = failedRegisterRedirectSettings("Errore", "email già presente!");
          dontSave = true;
          response.sendRedirect(request.getContextPath() + "/Login.jsp");
          throw new IllegalArgumentException("email già presente!");
        }
        if (value == -1) {
          UtenteBean user = new UtenteBean();
         
          user.setNickname((String) request.getAttribute("usrNick"));
          user.setMail((String) request.getAttribute("usrMail"));
          user.setPassword((String) request.getAttribute("usrPass"));
          user.setAdmin(false);

          // caso particolare in cui i campi sono vuoti
          if (user.getMail() == "" && user.getNickname() == "" && user.getPassword() == "") {
            redirectedPage = failedRegisterRedirectSettings("Errore", "i campi sono vuoti");
            dontSave = true;
            response.sendRedirect(request.getContextPath() + redirectedPage);
            throw new IllegalArgumentException("i campi sono vuoti");
          }
          if (user.getMail() == "") {
            redirectedPage = failedRegisterRedirectSettings("Errore",
                "l'email non rispetta la lunghezza di non più di 25 caratteri");
            dontSave = true;
            response.sendRedirect(request.getContextPath() + redirectedPage);
            throw new IllegalArgumentException(
                "l'email non rispetta la lunghezza di non più di 25 caratteri");
          } else if (user.getMail().length() > 25) {
            redirectedPage = failedRegisterRedirectSettings("Errore",
                "l'email non rispetta la lunghezza di non più di 25 caratteri");
            dontSave = true;
            response.sendRedirect(request.getContextPath() + redirectedPage);
            throw new IllegalArgumentException("l'email non rispetta la lunghezza");
          } else if (!user.getMail().matches("^\\w+([.-]?\\w+)@\\w+([.-]?\\w+)(.\\w{2,3})+$")) {
            redirectedPage = failedRegisterRedirectSettings("Errore", 
                "l'email non rispetta il formato");
            dontSave = true;
            response.sendRedirect(request.getContextPath() + redirectedPage);
            throw new IllegalArgumentException("l'email non rispetta il formato");
          }

          if (user.getPassword() == "") {
            redirectedPage = failedRegisterRedirectSettings("Errore",
                "la password non rispetta la lunghezza di almeno 8 caratteri");
            dontSave = true;
            throw new IllegalArgumentException("la password non rispetta la lunghezza");
          } else if (user.getPassword().length() < 8) {
            redirectedPage = failedRegisterRedirectSettings("Errore",
                "la password non rispetta la lunghezza di almeno 8 caratteri");
            dontSave = true;
            throw new IllegalArgumentException("la password non rispetta la lunghezza");
          } else if (!user.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            redirectedPage = failedRegisterRedirectSettings("Errore",
                "la password non rispetta il formato");
            dontSave = true;
            throw new IllegalArgumentException("la password non rispetta il formato");
          }

          if (user.getNickname() == "") {
            redirectedPage = failedRegisterRedirectSettings("Errore",
                "il ninckname non rispetta la lunghezza di alemeno un carattere");
            dontSave = true;
            throw new IllegalArgumentException("il ninckname non rispetta la lunghezza");
          } else if (!user.getNickname().matches("[a-zA-Z0-9_]*")) {
            redirectedPage = failedRegisterRedirectSettings("Errore",
                "il ninckname non rispetta la lunghezza di alemeno un carattere");
            dontSave = true;
            throw new IllegalArgumentException("il nickname non rispetta la lunghezza");
          }
          
          if (!dontSave) {
            model.doSave(user);
            redirectedPage = "/Login.jsp";  
            session.setAttribute("gmName", "Successo!");
            session.setAttribute("gmTxt", "registrazione avvenuta con successo");  
            response.sendRedirect(request.getContextPath() + redirectedPage);
            throw new IllegalArgumentException("utente Registrato!");
          }
        }
      }
    } catch (SQLException e) {
      session.setAttribute("loggedUser", null);
      session.setAttribute("logout", "N");
      session.setAttribute("role", "-1");
      redirectedPage = isRegister.charAt(0) == 'N' ? "/Login.jsp" : "/Registrazione.jsp";
    }
    response.sendRedirect(request.getContextPath() + redirectedPage);
  }
  
  private String failedRegisterRedirectSettings(String nameError, String contentError) {
    session.setAttribute("gmName", nameError);
    session.setAttribute("gmTxt", contentError);

    session.setAttribute("loggedUser", null);
    session.setAttribute("logout", "N");
    session.setAttribute("role", "-1");
    return "/Registrazione.jsp";  
  }
  
  /**
   * controlla se l'utente con quella mail e password esiste gia
   * e restituisce il ruolo dell'utente (admin o no).
   */
  private int checkLogin(String mail, String password, String isRegister) {
    try {
      UtenteBean user = (UtenteBean) model.doRetrieveByKey(mail);
      if ((user.getMail() != null) && (isRegister.charAt(0) == 'Y')) {
        return -2;
      }
      UtenteBean usr = (UtenteBean) model.doCheckLogin(mail, password);
    
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