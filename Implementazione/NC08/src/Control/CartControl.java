package Control;

import Bean.ProdottoBean;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che permette di prendere tutti gli ordini presenti nell sessione.
 *
 * @author Farina Simone
 */
@WebServlet("/CartControl")
public class CartControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
  public CartControl() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    ArrayList<ProdottoBean> carrello =
        (ArrayList<ProdottoBean>) request.getSession().getAttribute("carrello");

    request.setAttribute("carrello", carrello);
    request.getRequestDispatcher("/CartPage.jsp").forward(request, response);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    doGet(request, response);
  }

}
