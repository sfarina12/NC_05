package Control;

import Bean.OrdineBean;
import Bean.ProdottoBean;
import Bean.UtenteBean;
import Model.OrdineModelDm;
import Model.UtenteModelDm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AcquistoControl gestisce gli acquisti da parte dell'utente.
 *
 * @author Farina Simone
 */

@WebServlet("/AcquistoControl")
public class AcquistoControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  static OrdineModelDm model;

  public AcquistoControl() {
    super();
    model =  new OrdineModelDm();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
      
    OrdineBean lastordineProvvisorio = getProvvisorio(model, request);
    
    lastordineProvvisorio.setMetodoPagamento(
        request.getParameter("metodPagamento").toString().equals("1") ? true : false);
    lastordineProvvisorio.setIndirizzo(
        "" + request.getParameter("via")
        + request.getParameter("cap")
        + request.getParameter("citta")
    );
    
    try {
      model.doUpdate(lastordineProvvisorio);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    request.getSession().setAttribute("carrello", null);
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("CartControl");
    requestDispatcher.forward(request, response);
  }

  private OrdineBean getProvvisorio(OrdineModelDm ordineModelDm, HttpServletRequest request) {
    try {
      ArrayList<OrdineBean> ordiniDiMail = ordineModelDm.doRetrieveAll(
          ((UtenteBean) request.getSession().getAttribute("loggedUser")).getMail());

      return ordiniDiMail.get(ordiniDiMail.size() - 1); 
    } catch (SQLException e) {
      e.printStackTrace();
    }
   
    return null;
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    doGet(request, response);
  }

}
