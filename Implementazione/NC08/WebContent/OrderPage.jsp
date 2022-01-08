<%@page import="Bean.UtenteBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>

<jsp:include page="/tools/header.jsp"> 
 <jsp:param name="pageTitle" value="Aquista prodotto"/>
</jsp:include>

<body>
<div class="container">
<div class="row mt-5">
	<form class="p-5" action="???" method="post">
		<button type="submit" class="btn btn-warning mt-2 mb-5" style="margin-left:20px; color:white">Acquista</button>
    	<div class="col-5">
    		<div class="container">
    		 	<h1>Dati di spedizione</h1>
    			<h3>Nome<input class="file" type="file" name="file" value="" maxlength="255" style="margin-left:80px; width:300px"></h3>
    			<h3>Cognome<input name="titolo" type="text" style="margin-left:33px; width:300px"></h3>
        		<h3>Via<input name="autore" type="text" style="margin-left:117px; width:300px"></h3>
		       	<h3>CAP<input name="descrizione" style="margin-left:103px; width:300px"></h3>
		       	<h3>Città<input name="descrizione" style="margin-left:95px; width:300px"></h3>        		
    		</div>    
   		</div>
   		<div class="col-7" style="margin-top:-290px; margin-left:600px">
    		<div class="container">
   				<h1>Metodo di pagamento</h1>
       			<h3>
       				<select name="nomeCategoria" style="width:300px" onchange="change(this)">
       					<option value="credito">Carta di credito</option>
       					<option value="contrassegno">Contrassegno</option>
       				</select>
       			</h3>
   				
   				<div id="cartaElements">
    				<h3>Numero di carta<input name="titolo" type="text" style="margin-left:20px; width:370px"></h3>
    				<h3>CCV<input name="titolo" type="text" style="margin-left:175px; width:370px"></h3>
  	    	  		<h3>Data di scadenza<input name="autore" type="text" style="margin-left:12px; width:370px"></h3>
   				</div>
   			</div>
   		</div>
   </form>
</div>
</div>
<script>
function change(obj) {

    var selectBox = obj;
    var selected = selectBox.options[selectBox.selectedIndex].value;
    var textarea = document.getElementById("text_area");

    if(selected == "credito"){
        document.getElementById("cartaElements").style.display = "block";
    }
    else{
    	document.getElementById("cartaElements").style.display = "none";
    }
}
</script>
</body>
</html>