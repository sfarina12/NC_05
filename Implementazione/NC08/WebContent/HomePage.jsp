<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="Control.*, Model.*, Bean.*,java.net.*,java.text.*, java.util.*, java.sql.SQLException"%>
<%
  if(request.getAttribute("ultimi_libri")==null){
    response.sendRedirect(response.encodeRedirectURL("./HomeControl"));
    return;
  }
  ArrayList<ProdottoBean> ultimiLibri = (ArrayList<ProdottoBean>) request.getAttribute("ultimi_libri");
%> 

<jsp:include page="/tools/header.jsp"> 
 <jsp:param name="pageTitle" value="HomePage"/>
</jsp:include>
<div class="container mt-5">
<div class="row">
 <div class="col-md-5 m-5">
  <img class="centered mb-5" src="./tools/images/logo.jpg" height="200">
  <p id="paragrafo-home">Benvenuti su Dodo.net! Il sito della libreria Dodo, su cui potrai consultare il catalogo dei libri presenti in negozio ed acquistarli per averli direttamente a casa tua.<br>Per avere tutti i benefici di questo sito ti consigliamo di autenticarti il prima possibile!<br>Nel mentre, dai un'occhiata agli ultimi arrivi.</p>
 </div>
 <div class="col-md-6 mt-5">
 	<h3 id="paragrafo-home" class="mb-5">Ultimi arrivi</h3>
 	<div class="new-books-container">
 		<!-- libri -->
 		<%
 		int i = 1;
 			while(i < ultimiLibri.size()){
			ProdottoBean bean = (ProdottoBean) ultimiLibri.get(i);
			%>
			<article>
		  	<a href="AutenticazioneFacade?action=prodotto&isbn=<%= bean.getIsbn()%>"><img src="ImageControl?isbn=<%= bean.getIsbn() %>" height="300px " onerror="this.src='tools/images/img_notFound.jpg';"></a>
		  	<h4><a href="AutenticazioneFacade?action=prodotto&isbn=<%= bean.getIsbn()%>"><%=bean.getTitolo()%></a></h4>
		  	</article>
		  	<% i++; } %>
	</div>
 </div>
</div>
</div>

<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="./tools/lib/slick/slick.min.js"></script>
<script type="text/javascript" src="./tools/js/homepage.js"></script>
</body>
</html>