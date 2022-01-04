<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="control.*, model.*,java.net.*,java.text.*, java.util.*, java.sql.SQLException"
    pageEncoding="ISO-8859-1"%>
<%
  if ((request.getAttribute("libri")==null) || (request.getAttribute("categorie") == null)){
    response.sendRedirect(response.encodeRedirectURL("./ShopControl"));
    return;
  }
  ArrayList<ProdottoBean> libri = (ArrayList<ProdottoBean>) request.getAttribute("libri");
  Set<String> categorie = (Set<String>) request.getAttribute("categorie");
%> 
<jsp:include page="/tools/header.jsp"> 
 <jsp:param name="pageTitle" value="Shop"/>
</jsp:include>
<div class="container mt-4">
  <% if(session.getAttribute("loggedUser") != null && session.getAttribute("role") == "admin" ){%> 
  <div class="text-center">
	<button type="button" class="btn btn-warning mt-2 mb-5" href="#" style="color:white;">Aggiungi nuovo libro</button>
  </div>
  <%}%>
  <div class="row">
  	<div class="col-4">
  		<div class="position-fixed">
  		<h3 class="mb-4">Categorie</h3>
  		<% int i = 0;
  		   Object[] cat = categorie.toArray();
 		   while(i < cat.length) { 
			 String s = cat[i].toString();
		   %><h5><a href="#<%= s %>"><%=s%></a></h5>
		   <% i++; } %> 
		</div>
  	</div>
  	<div class="col-8">
  		<div class="row">
  		<%
 		int j = 0;
 			while(j < libri.size()){
			ProdottoBean bean = (ProdottoBean) libri.get(j);
			%>
			<div id="<%= bean.getNomeCategoria() %>" style="height: auto; width: 200px; margin-bottom: 30px;">
			  	<a href="AutenticazioneFacade?action=prodotto&isbn=<%= bean.getIsbn()%>"><img src="ImageControl?isbn=<%= bean.getIsbn() %>" height="200px " onerror="this.src='tools/images/img_notFound.jpg';"></a>
			  	<h5><a href="AutenticazioneFacade?action=prodotto&isbn=<%= bean.getIsbn()%>"><%=bean.getTitolo()%></a></h5>
		  	</div>
  		<%j++; }%>	
  	   </div>  		
  	</div>  	
  </div>
</div>
</body>
</html>