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
    
    float totale = 0;
      
    for (ProdottoBean bean : carrello) {
      totale += bean.getPrezzo();
    }
    
    salveProdottoProvvisorio(totale, request, ordineModelDm);
    int lastId = getIdFromProvvisorio(ordineModelDm, request);
    
    try {
      for (ProdottoBean bean : carrello) {
        int quantita = 0;
        for (ProdottoBean underBean : carrello) {
          if (underBean.getIsbn().equals(bean.getIsbn())) {
            quantita++;
          }
        }      
        ComposizioneBean composition = new ComposizioneBean(lastId, bean.getIsbn(), quantita);
        composizioneModelDm.doSave(composition);
      }
    } catch (Exception e) {
      jout(e.getMessage());
    }
    
    request.getRequestDispatcher("/OrderPage.jsp").forward(request, response);
  }
  
  private void salveProdottoProvvisorio(float totale, HttpServletRequest request,
      OrdineModelDm ordineModelDm) {
    try {
      OrdineBean ordineProvvisorio = new OrdineBean();
      
      ordineProvvisorio.setData(java.time.LocalDate.now().toString());
      ordineProvvisorio.setIndirizzo("INDIRIZZO");
      ordineProvvisorio.setMail(
            ((UtenteBean) request.getSession().getAttribute("loggedUser")).getMail());
      ordineProvvisorio.setMetodoPagamento(false);
      ordineProvvisorio.setTotale(totale);
        
      ordineModelDm.doSave(ordineProvvisorio);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  
  private int getIdFromProvvisorio(OrdineModelDm ordineModelDm, HttpServletRequest request) {
    try {
      ArrayList<OrdineBean> ordiniDiMail = ordineModelDm.doRetrieveAll(
          ((UtenteBean) request.getSession().getAttribute("loggedUser")).getMail());

      return ordiniDiMail.get(ordiniDiMail.size() - 1).getIdOrdine(); 
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return -1;
  }
  
  private void jout(Object obj) {
    System.out.println(obj);
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
