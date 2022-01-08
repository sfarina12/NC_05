<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="Control.*, Model.*, Bean.*,java.net.*,java.text.*, java.util.*, java.sql.SQLException"
    pageEncoding="ISO-8859-1"%>
<%
  ArrayList<ProdottoBean> libri = (ArrayList<ProdottoBean>) session.getAttribute("carrello");
%> 
<jsp:include page="/tools/header.jsp"> 
 <jsp:param name="pageTitle" value="Carrello"/>
</jsp:include>
<div class="container mt-4">
  <div class="row">
  	<div class="col-8">
  		<div class="text-center">
  			<h2>Totale 
  			<%
  				float totale=0;
  				for(int i=0;i<libri.size();i++) 
  				{
  					totale+=((ProdottoBean) libri.get(i)).getPrezzo();
  				}  
  			%><%=totale %></h2>
			<button type="button" class="btn btn-warning mt-2 mb-5">
				<a class="nav-link" href="ComposizioneControl" style="color:white;">Procedi all'acquisto</a>
			</button>
  		</div>
  		<div class="row">
  		<%
  		if(libri.size()>0)
 		{
  			int j = 0;
 			while(j < libri.size()){
			ProdottoBean bean = (ProdottoBean) libri.get(j);
			%>
			<div id="<%= bean.getNomeCategoria() %>" style="height: auto; width: 200px; margin-bottom: 30px;">
			  	<a href="AutenticazioneFacade?action=prodotto&isbn=<%= bean.getIsbn()%>"><img src="ImageControl?isbn=<%= bean.getIsbn() %>" height="200px " onerror="this.src='tools/images/img_notFound.jpg';"></a>
			  	<h5><a href="AutenticazioneFacade?action=prodotto&isbn=<%= bean.getIsbn()%>"><%=bean.getTitolo()%></a></h5>
		  	</div>
  		<%j++; }}else{%><h2>Nessun libro nel cerrello</h2><%}%>	
  	   </div>  		
  	</div>  	
  </div>
</div>
</body>
</html>