package Control;

import Bean.ProdottoBean;
import Model.ProdottoModelDm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AdminControl gestisce le azioni di cancellazione e aggiunta dei prodotti.
 *
 * @author Farina Simone
 */
@WebServlet("/AdminControl")
public class AdminControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final ProdottoModelDm prodottoModelDm = new ProdottoModelDm();
  public AdminControl() {
    super();
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    String action = (String) request.getAttribute("action");
    try {
      if (action.equals("delete")) {
        String isbn = (String) request.getAttribute("isbn");
        prodottoModelDm.doDelete(isbn);
        response.sendRedirect(request.getContextPath() + "/ShopControl");
      } else if (action.equals("add")) {
        String isbn = (String) request.getAttribute("isbn");
        String titolo = (String) request.getAttribute("titolo");
        String autore = (String) request.getAttribute("autore");
        float prezzo = Float.parseFloat((String) request.getAttribute("prezzo"));
        String descrizione = (String) request.getAttribute("descrizione");
        String nomeCategoria = (String) request.getAttribute("nomeCategoria");
        int quantitaStock = Integer.parseInt((String) request.getAttribute("quantitaStock"));
        byte[] file = (byte[]) request.getAttribute("file");

        ProdottoBean bean = new ProdottoBean();
        bean.setIsbn(isbn);
        bean.setAutore(autore);
        bean.setDescrizione(descrizione);
        bean.setTitolo(titolo);
        bean.setNomeCategoria(nomeCategoria);
        bean.setPrezzo(prezzo);
        bean.setQuantitaStock(quantitaStock);
        bean.setCopertina(file);
        prodottoModelDm.doSave(bean);
        response.sendRedirect(request.getContextPath() + "/ShopControl");
      } else {
        throw new Exception("azione sconosciuta");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {

    doGet(request, response);
  }

}
