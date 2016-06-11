<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Generate Random Data</title>
    <link href="<c:url value='/static/css/bootstrap.css' />"
        rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
	<div style="width: 98%; height: 98%" class="generic-container">
		<div class="well">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="#"><a href="<c:url value='/' />">Home</a></li>
						</ul>
						<ul class="nav navbar-nav">
							<li class="active"><a
								href="<c:url value='/generateRandomData' />">Generate Random Data</a></li>
						</ul>
						<ul class="nav navbar-nav">
						  <li class="#"><a href="<c:url value='/randomDataGeneration' />">Random Data Generator</a></li>
						</ul> 
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</div>
		<div class="well lead">Generate Random Data for Applicants and Vacancies</div>
		<form:form method="POST" modelAttribute="randomData"
			class="form-horizontal">
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="numberOfDataApplicant">Number
						of applicant</label>
					<div class="col-md-7">
						<form:input type="number" path="numberOfDataApplicant"
							id="numberOfDataApplicant" class="form-control input-sm" value="10" />
						<div class="has-error">
							<form:errors path="numberOfDataApplicant" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="numberOfDataVacancies">Number
						of vacancies</label>
					<div class="col-md-7">
						<form:input type="number" path="numberOfDataVacancies"
							id="numberOfDataVacancies" class="form-control input-sm" value="3" />
						<div class="has-error">
							<form:errors path="numberOfDataVacancies" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Generate Random Data" class="btn btn-primary btn-sm" />
					or <a href="<c:url value='/generateRandomData' />">Cancel</a>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>