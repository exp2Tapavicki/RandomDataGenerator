<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Random Data Generator</title>
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
		 	<form:form method="POST" modelAttribute="randomData" class="form-horizontal">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="numberOfDataApplicant">Number
						of data</label>
					<div class="col-md-7">
						<form:input type="number" path="numberOfDataApplicant"
							id="numberOfDataApplicant" class="form-control input-sm" value="10" />
						<div class="has-error">
							<form:errors path="numberOfDataApplicant" class="help-inline" />
						</div>
					</div>
					<form:input type="hidden" path="numberOfDataVacancies" id="numberOfDataVacancies" value="0"/>
					 <input type="submit" value="Generate Random Data in JSON" class="btn btn-primary btn-sm"/>
                </div>
            </form:form>
			<div class="form-actions floatRight">
				<a href="<c:url value='/newRandomDataGeneration' />" class="btn btn-primary btn-sm">Add New Field</a>
			</div>
		</div>
		<div class="panel panel-default"
			style="overflow-y: scroll; height: 85%; width: 100%;">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of Fields </span>
			</div>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Class name</th>
							<th>Property name</th>
							<th>Boundary min value</th>
							<th>Boundary max value</th>
							<th>Boundary precision</th>
							<th>Boundary allow nulls</th>
							<th>Boundary enumeration</th>
							<th width="100"></th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${randomDataGenerationList}" var="randomDataGeneration">
							<tr>
									<td>${randomDataGeneration.basicClassConstants}</td>
									<td>${randomDataGeneration.propertyName}</td>
									<td>${randomDataGeneration.objMin}</td>
									<td>${randomDataGeneration.objMax}</td>
									<td>${randomDataGeneration.objPrecision}</td>
									<td>${randomDataGeneration.bAllowNulls}</td>
									<td>${randomDataGeneration.objEnum}</td>
								<td><a href="<c:url value='/edit-randomDataGeneration-${randomDataGeneration.id}' />"
									class="btn btn-success custom-width">edit</a></td>
								<td><a
									href="<c:url value='/delete-randomDataGeneration-${randomDataGeneration.id}' />"
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