package Control;

import Bean.ComposizioneBean;
import Bean.OrdineBean;
import Bean.ProdottoBean;
import Bean.UtenteBean;
import Model.ComposizioneModelDm;
import Model.OrdineModelDm;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che permette di creare gli ordini partendo dai prodotti presenti 
 * all'interno del carrello carrello.
 *
 * @author Farina Simone
 */
@WebServlet("/ComposizioneControl")
public class ComposizioneControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final ComposizioneModelDm composizioneModelDm = new ComposizioneModelDm();
  private final OrdineModelDm ordineModelDm = new OrdineModelDm();
     
  /**
   * costruttore.
   */
  public ComposizioneControl() {
    super();
  }
  
  /**
   * Quando invocato costruirà l'ordine collegando a quest'ultimo
   * tutti gli eventuali "composizioni".
   *
   * @param request responce
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    ArrayList<ProdottoBean> carrello =
        (ArrayList<ProdottoBean>) request.getSession().getAttribute("carrello");
    
    ArrayList<OrdineBean> ordini;
    try {
      ordini = ordineModelDm.doRetrieveAll(
          ((UtenteBean) request.getSession().getAttribute("loggedUser")).getMail());

      int lastOrderId = ordini.get(ordini.size() - 1).getIdOrdine();
      lastOrderId += 1;
      float totale = 0;
      
      for (ProdottoBean bean : carrello) {
        int quantita = 0;
        for (ProdottoBean underBean : carrello) {
          if (underBean.getIsbn().equals(bean.getIsbn())) {
            quantita++;
            totale += underBean.getPrezzo();
          }
        }
        ComposizioneBean composition = new ComposizioneBean(lastOrderId, bean.getIsbn(), quantita);
        composizioneModelDm.doSave(composition);
      }
      
      OrdineBean nuovoOrdine = new OrdineBean(
          java.time.LocalDate.now().toString(), 
          lastOrderId, 
          (String) request.getSession().getAttribute("indirizzo"), 
          ((UtenteBean) request.getSession().getAttribute("loggedUser")).getMail(), 
          false, 
          totale);
      
      ordineModelDm.doSave(nuovoOrdine);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Quando invocato chiamerà il doGet(). 
   *
   * @param request responce
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    doGet(request, response);
  }

}
