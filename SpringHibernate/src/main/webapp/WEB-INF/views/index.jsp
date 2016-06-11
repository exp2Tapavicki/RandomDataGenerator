<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Home</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
	<div style="width:98%; height:98%" class="generic-container">
	 	<div class="well">
	 		<nav class="navbar navbar-default">
			  <div class="container-fluid">
			    <!-- Collect the nav links, forms, and other content for toggling -->
			    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			      <ul class="nav navbar-nav">
			        <li class="active"><a href="<c:url value='/' />">Home</a></li>
			      </ul> 
			      <ul class="nav navbar-nav">
			        <li class="#"><a href="<c:url value='/generateRandomData' />">Generate Random Data</a></li>
			      </ul> 
			      <ul class="nav navbar-nav">
			        <li class="#"><a href="<c:url value='/randomDataGeneration' />">Random Data Generator</a></li>
			      </ul> 
			    </div><!-- /.navbar-collapse -->
			  </div><!-- /.container-fluid -->
			</nav>
	 	</div>
	 	<nav class="navbar navbar-default">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		    </div>
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		        <li class="#"><a href="<c:url value='/applicantslist' />"> Applicants</a></li>
		        <li class="#"><a href="<c:url value='/vacancieslist' />"> Vacancies</a></li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
   	</div>
</body>
</html>