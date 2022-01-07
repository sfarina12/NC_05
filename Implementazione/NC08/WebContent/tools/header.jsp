<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="Control.*, Model.*, Bean.*,java.net.*,java.text.*, java.util.*, java.sql.SQLException"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dodo.net - ${param.pageTitle }</title>
<!-- import libreria bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- import slick slider -->
<link rel="stylesheet" type="text/css" href="./tools/lib/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="./tools/lib/slick/slick-theme.css"/>
<!-- import file css esterno -->
<link href="./tools/css/style.css" rel="stylesheet">
</head>
<body>
<div class="navbar">
<ul class="nav">
  <li class="nav-item">
   <a class="nav-link" href="./HomePage.jsp"><img src="./tools/images/logo.jpg" height="60" alt="logo dodo colorato"></a>
  </li>
  <li class="nav-item pt-3">
  <h3><a class="nav-link" href="./HomePage.jsp">Dodo.net</a></h3>
  </li>
  <li class="nav-item pt-3 ms-3">
	<h3><a class="nav-link" href="./ShopPage.jsp">Shop</a></h3>
  </li>
   </ul>
<ul class="nav justify-content-end">
  <li class="nav-item pt-2">
   <h3><a class="nav-link" href="">Carrello</a></h3>
  </li>
  <% if(session.getAttribute("loggedUser") == null){%> 
	<li class="nav-item pt-2">
   		<h3><a class="nav-link" href="./Login.jsp">Autenticazione</a></h3>
  	</li>
  <%} else {%>
  	<li class="nav-item pt-2">
   		<h3><a class="nav-link" href="AutenticazioneFacade?action=user&logout=Y">Logout</a></h3>
  	</li>
  <%}%>

  
</ul>
</div>
