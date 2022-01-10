<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="/tools/header.jsp"> 
 <jsp:param name="pageTitle" value="Login"/>
</jsp:include>

<div class="container mt-5">
<h1 class="text-center">Login</h1>
<form class="p-5" name="login" action="AutenticazioneFacade?action=user" method="post">
	<div class="row pb-3">
		<h3><label for="mail">Email</label></h3>
		<input type="email" name="usrMail" id="mail" class="form-control">
	</div>
	<div class="row pb-3">
		<h3><label for="password">Password</label></h3>
		<input type="password" name="usrPass" id="password" class="form-control">
	</div>

    <input type="text" name="registerOrNot" value="N" hidden="true">
    <input type="text" name="logout" value="N" hidden="true">
    
    <div class="text-center">
       <button class="btn btn-warning" type="submit"><h3 style="color:white">Continua</h3></button>
    </div>	
    
    
</form>	
    <div class="m-3 text-center">   
    	<a href="./Registrazione.jsp" class="m-5"><button class="btn btn-warning"><h4 style="color:white">Registrati</h4></button></a>
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

</div>
</body>
</html>