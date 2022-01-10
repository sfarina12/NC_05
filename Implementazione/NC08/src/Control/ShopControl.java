package Control;

import Bean.ProdottoBean;
import Model.ProdottoModelDm;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShopControl.
 *
 * @author Farina Simone
 */
@WebServlet("/ShopControl")
public class ShopControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final ProdottoModelDm prodottoModelDm = new ProdottoModelDm();
  private Set<String> categorie;
  private ArrayList<ProdottoBean> prodotti;
  
  /**
   * Costruttore vuoto.
   *
   * @see HttpServlet#HttpServlet()
   */
  public ShopControl() {
    super();
  }

  /**
   * Quando invocato mostrerà tutti i prodotti nello shop.
   *
   * @param request responce
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    prodotti = null;
    categorie = new HashSet<>();
    try {
      prodotti = new ArrayList<>(
        (LinkedList<ProdottoBean>) prodottoModelDm.doRetrieveAll());
      for (ProdottoBean p : prodotti) {
        categorie.add(p.getNomeCategoria());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    prodotti = sortCategory(prodotti, categorie);
    
    request.setAttribute("libri", prodotti);
    request.setAttribute("categorie", categorie);
    request.getRequestDispatcher("/ShopPage.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    doGet(request, response);
  }

  private ArrayList<ProdottoBean> sortCategory(ArrayList<ProdottoBean> prodotti,
      Set<String> categorie2) {
    ArrayList<ProdottoBean> sorted = new ArrayList<ProdottoBean>(); 
    Object[] cat = categorie2.toArray();
    
    for (int a = 0; a < cat.length; a++) {
      for (int b = 0; b < prodotti.size(); b++) {
        if (prodotti.get(b).getNomeCategoria().equals(cat[a].toString())) {
          sorted.add(prodotti.get(b));
        }
      }
    }
    return sorted;
  }
  
}
