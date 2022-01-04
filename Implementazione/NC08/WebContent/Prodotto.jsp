<%@page import="Bean.ProdottoBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  ProdottoBean prodotto = (ProdottoBean) request.getAttribute("prodotto");
%> 
<jsp:include page="/tools/header.jsp">
    <jsp:param name="pageTitle" value="<%= prodotto.getTitolo() %>"/>
</jsp:include>
<html>
<body>
<div class="container">
<div class="row mt-5">
    <div class="col-4">
        <img src="ImageControl?isbn=<%= prodotto.getIsbn() %>" style="display: block; margin-left: auto;margin-right: auto;width: 80%;" onerror="this.src='tools/images/img_notFound.jpg';">
    </div>
    <div class="col-7">
    <div class="container">
    	<h1><%= prodotto.getTitolo() %></h1>
        <h2><%= prodotto.getAutore() %></h2>
       	<p><i><%= prodotto.getDescrizione() %></i></p>
       	<h6><%= prodotto.getNomeCategoria() %></h6>
        <h5>Prezzo: € <%= prodotto.getPrezzo() %></h5><br>
        <% if (prodotto.getQuantitaStock() > 0 ) { %>
        <form action="carrello" method="post">
            <label>Quantità:</label>
            <select name="aggNum" style="padding: 15px 25px; border: none; font-size: 14px;text-align: center">
                    <%
                    for (int i = 1; i <= prodotto.getQuantitaStock(); i++){
                    %>
                    <option value="<%= i %>"><%= i %></option>
                    <%}%>
            </select>
            <input type="hidden" name="prodCode" value="<%= prodotto.getIsbn()%>"> &nbsp;
            <button class="btn btn-outline-warning" type="submit">Aggiungi al carrello</button>
        </form>
        <% if(session.getAttribute("loggedUser") != null && session.getAttribute("role") == "admin" ){%> 
		<button type="button" class="btn btn-warning mt-5 mb-5" href="#" style="color:white;">Rimuovi libro</button>
	   <%}%>
        <% } else { %>
        <p>Non ci sono copie disponibili al momento, a breve ne arriveranno di nuove!</p>
        <%} %>       
    </div>    
   </div>
</div>
</div>
</body>
</html>
