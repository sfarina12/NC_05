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
    ArrayList<ProdottoBean> carrelloRedyForBuy =
        (ArrayList<ProdottoBean>) request.getSession().getAttribute("carrello");
    
    OrdineBean ordine = new OrdineBean();
    
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
    LocalDateTime now = LocalDateTime.now();  
    UtenteBean utente = (UtenteBean) request.getAttribute("loggedUser");
    
    float total = 0;
    for (ProdottoBean a : carrelloRedyForBuy) {
      total += a.getPrezzo();
    }
    
    ordine.setData(dtf.format(now));
    ordine.setIndirizzo(request.getAttribute("indirizzo").toString());
    ordine.setMail(utente.getMail());
    ordine.setMetodoPagamento(
        request.getAttribute("metodPagamento").toString() == "1" ? true : false);
    ordine.setTotale(total);
    
    try {
      model.doSave(ordine);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    request.getSession().setAttribute("carrello", new ArrayList<ProdottoBean>());
    response.sendRedirect("/CartPage.jsp");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    doGet(request, response);
  }

}
