package Control;

import Bean.ProdottoBean;
import Model.ProdottoModelDm;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementazione servlet ProdottoControl.
 *
 * @author Farina Simone
 */
@WebServlet("/Prodotto")
public class ProdottoControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final ProdottoModelDm prodottoModelDm = new ProdottoModelDm();
  /**
     * Costruttore vuoto.
     *
     * @see HttpServlet#HttpServlet()
     */
  public ProdottoControl() {
    super();
  }

  /**
   *  Metodo doGet().
   *
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String id = request.getParameter("isbn");
    ProdottoBean prodotto = new ProdottoBean();
    try {
      prodotto = (ProdottoBean) prodottoModelDm.doRetrieveByKey(id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    request.setAttribute("prodotto", prodotto);

    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Prodotto.jsp");
    requestDispatcher.forward(request, response);
  }

  /**
   * Metodo doPost().
   *
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {
    doGet(request, response);
  }
}