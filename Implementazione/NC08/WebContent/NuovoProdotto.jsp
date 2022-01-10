<%@page import="Bean.UtenteBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>

<jsp:include page="/tools/header.jsp"> 
 <jsp:param name="pageTitle" value="Aggiungi prodotto"/>
</jsp:include>

<body>
<div class="container">
<div class="row mt-5">
	<form class="p-5" action="AdminProxy" nome="AddProductForm" enctype="multipart/form-data" method="post">
    	<div class="col-7">
    		<div class="container">
    			<h2>Copertina<input class="file" type="file" name="file" value="" maxlength="255" style="margin-left:100px; width:370px"></h2>
    			<h2>Titolo<input name="titolo" type="text" style="margin-left:160px; width:370px"></h2>
        		<h2>Autore<input name="autore" type="text" style="margin-left:145px; width:370px"></h2>
		       	<h2>Descrizione<input name="descrizione" style="margin-left:77px; width:370px"></h2>
       			<h2>Categoria
       				<select name="nomeCategoria" style="margin-left:95px; width:370px">
       					<option value="Bambini e ragazzi">Bambini e ragazzi</option>
       					<option value="Fantasy e Fantascienza">Fantasy e Fantascienza</option>
       					<option value="Fumetti e manga">Fumetti e manga</option>
       					<option value="Gialli Thriller e Horror">Gialli Thriller e Horror</option>
       					<option value="Narrativa italiana">Narrativa italiana</option>
       					<option value="Narrativa straniera">Narrativa straniera</option>
       					<option value="Saggistica">Saggistica</option>
       					<option value="Viaggi">Viaggi</option>
       				</select>
       			</h2>
        		<h2>Prezzo<input name="prezzo" type="text" style="margin-left:150px; width:370px"></h2>
        		<h2>ISBN<input name="isbn" type="text" style="margin-left:175px; width:370px"></h2>
        		<h2>Quantità stock<input name="quantitaStock" style="margin-left:35px; width:370px"></h2><br>
        		
        		<input class="btn btn-warning mt-5 mb-5" type="submit" nome="SendAddProductButton" value="Aggiungi il nuovo prodotto" style="margin-left:160px; width:370px">
        		
        		<input type="text" name="action" value="add" hidden="true">
    		</div>    
   		</div>
   </form>
</div>
</div>
</body>
</html>