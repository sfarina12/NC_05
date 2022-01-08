package Control;

import Bean.ProdottoBean;
import Model.ProdottoModelDm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet che permette di prendere tutti gli ordini presenti nell sessione.
 *
 * @author Farina Simone
 */
@WebServlet("/CartControl")
public class CartControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private ProdottoModelDm prodottoDm;
  static HttpSession session;
  
  public CartControl() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    prodottoDm = new ProdottoModelDm();
    session = request.getSession();
    String pageToForward = "CartPage.jsp";
    
    ArrayList<ProdottoBean> carrello =
        (ArrayList<ProdottoBean>) session.getAttribute("carrello");

    if (carrello == null) {
      carrello = new ArrayList<ProdottoBean>();
    } 
    
    String action = request.getParameter("action");
    
    if (action != null) {
      switch (action) {
        case "add":
          try {
            String isbn = request.getParameter("isbn");
          
            ProdottoBean bean;
            bean = prodottoDm.doRetrieveByKey(isbn);
            
            int quantita = Integer.parseInt(request.getParameter("aggNum"));
            
            for (int i = 0; i < quantita; i++) {
              carrello.add(bean);
            }
          
            request.setAttribute("prodotto", bean);
            pageToForward = "/Prodotto.jsp;";
          } catch (SQLException e) {
            e.printStackTrace();
          }
          break;
        case "delete":
          try {
            String isbn = request.getParameter("isbn");
            boolean stop = false;
            
            for (int i = 0; i < carrello.size() && !stop; i++) {
              if (isbn.equals(carrello.get(i).getIsbn())) {
                carrello.remove(i);
                stop = true;
              }
            }
          
            ProdottoBean bean;
            bean = prodottoDm.doRetrieveByKey(isbn);
            request.setAttribute("prodotto", bean);
            pageToForward = "/Prodotto.jsp;";
          } catch (SQLException e) {
            e.printStackTrace();
          }
          break;
        default:break;
      }
    }
    
    session.setAttribute("carrello", carrello);
    request.getRequestDispatcher(pageToForward).forward(request, response);
  }
  
  private void jout(Object obj) {
    System.out.println(obj);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    doGet(request, response);
  }

}
