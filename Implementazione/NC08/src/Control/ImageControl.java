package Control;

import Bean.ProdottoBean;
import Model.ProdottoModelDm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che controlla il caricamento delle immagini per le jsp.
 *
 * @author Farina Simone
 */
@WebServlet("/ImageControl")
public class ImageControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final ProdottoModelDm prodottoModelDm = new ProdottoModelDm();
  
  public ImageControl() {
    super(); 
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    String id = (String) request.getParameter("isbn");

    if (id != null) {
      byte[] bt;

      try {        
        ProdottoBean pb = (ProdottoBean) prodottoModelDm.doRetrieveByKey(id);
        bt = pb.getCopertina();
        
        ServletOutputStream out = response.getOutputStream();

        if (bt != null) {
          out.write(bt);
          response.setContentType("image/jpg");
        }
        
        out.close();
      } catch (Exception e) {
        System.out.println("error: " + e.getMessage());
      }
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    doGet(request, response);
  }
}
