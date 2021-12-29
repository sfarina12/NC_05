package Control;

import Bean.ProdottoBean;
import Model.ProdottoModelDm;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La qui presente classe avrà il compito di prendere gli utimi sei libri inseriti
 * all'interno del database.
 *
 * @author Farina Simone
 */
@WebServlet("/HomeControl")
public class HomeControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final ProdottoModelDm prodottoModelDm = new ProdottoModelDm();
    
  public HomeControl() {  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ArrayList<ProdottoBean> prodotti = null;
    try {
      prodotti = new ArrayList<>(
          (LinkedList<ProdottoBean>) prodottoModelDm.doQuery("doRetrieveAll", null));
    } catch (SQLException e) {
      e.printStackTrace();
    }
        
    ArrayList<ProdottoBean> indexes = new ArrayList<ProdottoBean>();
        
    for (int i = 0; i < 6; i++) {
      indexes.add(prodotti.get(prodotti.size() - (i + 1)));
    }
        
    request.setAttribute("ultimi_libri", indexes);
    request.getRequestDispatcher("/HomePage.jsp").forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException { 
    doGet(request, response); 
  }
}


