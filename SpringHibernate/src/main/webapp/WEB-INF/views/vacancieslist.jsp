<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Vacancies List</title>
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
							<li class="#"><a
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
		<div class="row"style="width: 100%; height: 5%;">
			<div class="form-actions floatRight">
				<a href="<c:url value='/newvacancy' />"
					class="btn btn-primary btn-sm">Add New Vacancy</a>
			</div>
		</div>
		<div class="panel panel-default"
			style="overflow-y: scroll; height: 80%; width: 100%;">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of Vacancies </span>
			</div>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Vacancy Name</th>
							<th>Vacancy Code</th>
							<th width="100"></th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${vacancies}" var="vacancy">
							<tr>
								<td>${vacancy.vacancyName}</td>
								<td>${vacancy.vacancyCode}</td>
								<td><a href="<c:url value='/edit-vacancy-${vacancy.id}' />"
									class="btn btn-success custom-width">edit</a></td>
								<td><a
									href="<c:url value='/delete-vacancy-${vacancy.id}' />"
									class="btn btn-danger custom-width">delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>