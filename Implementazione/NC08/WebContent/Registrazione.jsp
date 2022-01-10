<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="/tools/header.jsp"> 
 <jsp:param name="pageTitle" value="Registrazione"/>
</jsp:include>
<div class="container mt-5">
	<h1 class="text-center">Registrazione</h1>
	<form class="p-5" name="registrazione" action="AutenticazioneFacade?action=user" method="post">
		<div class="row pb-3">
		<h3><label for="nickname">Nickname</label></h3>
		<input type="text" name="usrNick" id="nickname" class="form-control">
		</div>
		<div class="row pb-3">
		<h3><label for="mail">Email</label></h3>
		<input type="email" name="usrMail" id="mail" class="form-control">
		</div>
		<div class="row pb-3">
		<h3><label for="password">Password</label></h3>
		<input type="password" name="usrPass" id="password" class="form-control">
		</div>
		<input type="text" name="registerOrNot" value="Y" hidden="true">
	    <input type="text" name="logout" value="N" hidden="true">
	    <div class="text-center">
		<button class="btn btn-warning" type="submit"><h3 style="color:white">Registrati</h3></button>
		</div>
	</form>
</div>

<messages>
	<%if(session.getAttribute("gmName")!=null && session.getAttribute("gmName")!=""){%>
		<div id="generalMessage">
			<nome><%=session.getAttribute("gmName")%></nome>
			<text><%=session.getAttribute("gmTxt")%></text>
		</div>
	<%}%>
	<div id="generalMessage" style="opacity: 0;"><img src="./icons/deleteW_96px.png"><nome></nome><text></text></div>
</messages> 

</body>
</html>