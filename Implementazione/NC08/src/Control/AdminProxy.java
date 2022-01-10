package Control;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Questa servlet si occupa di controllare se l'utente che intende usufruire
 * dei privilegi di modifica e aggiunta dei prodotti sia effettivamente un admin.
 *
 * @author Farina Simone
 */
@WebServlet("/AdminProxy")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
    maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AdminProxy extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
  public AdminProxy() {
    super();
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {

    try {
      HttpSession session = request.getSession();

      if (session.getAttribute("role") == null
          || ((String) session.getAttribute("role")).equals("normal")) {
        throw new Exception("Accesso negato");
      } else {
        String action = request.getParameter("action");

        if (action.equals("delete")) {
          String isbn = request.getParameter("isbn");
          request.setAttribute("isbn", isbn);
          request.setAttribute("action", action);
          RequestDispatcher dispatcher =
              this.getServletContext().getRequestDispatcher("/AdminControl");
          dispatcher.forward(request, response);
        } else if (action.equals("add")) {
          request.setAttribute("action", action);

          String appPath = request.getServletContext().getRealPath("");
          String savePath = appPath + File.separator + "UploadTemp";
          File fileSaveDir = new File(savePath);
          if (!fileSaveDir.exists()) { 
            fileSaveDir.mkdir();
          }

          for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            if (fileName != null && !fileName.equals("")) {
              part.write(savePath + File.separator + fileName);
              byte[] fileContent = Files.readAllBytes(
                  new File(savePath + File.separator + fileName).toPath());
              request.setAttribute("file", fileContent);
            }
          }
          String descrizione = request.getParameter("descrizione"); 
          String nomeCategoria = request.getParameter("nomeCategoria");
          String quantitaStock = request.getParameter("quantitaStock");
          String isbn = request.getParameter("isbn");
          String titolo = request.getParameter("titolo");
          String autore = request.getParameter("autore");
          String prezzo = request.getParameter("prezzo");

          request.setAttribute("isbn", isbn);
          request.setAttribute("titolo", titolo);
          request.setAttribute("autore", autore);
          request.setAttribute("prezzo", prezzo);
          request.setAttribute("descrizione", descrizione);
          request.setAttribute("nomeCategoria", nomeCategoria);
          request.setAttribute("quantitaStock", quantitaStock);
          RequestDispatcher dispatcher = 
              this.getServletContext().getRequestDispatcher("/AdminControl");
          dispatcher.forward(request, response);
        } else {
          throw new Exception("action errato!");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
  
  private String extractFileName(Part part) {
    String contentDisp = part.getHeader("content-disposition");
    String[] items = contentDisp.split(";");
    for (String s : items) {
      if (s.trim().startsWith("filename")) {
        return s.substring(s.indexOf("=") + 2, s.length() - 1);
      }
    }
    return "";
  }
}
