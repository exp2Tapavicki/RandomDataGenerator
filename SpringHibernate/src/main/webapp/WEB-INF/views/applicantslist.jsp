<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Applicants List</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
	<div style="overflow-y: scroll; width:98%; height:98%" class="generic-container">
	 	<div class="well" style="width:100%; height: 20%;">
	 		<nav class="navbar navbar-default">
			  <div class="container-fluid">
			    <!-- Collect the nav links, forms, and other content for toggling -->
			    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			      <ul class="nav navbar-nav">
			        <li class="#"><a href="<c:url value='/' />">Home</a></li>
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
	 	<div class="well" style="overflow-y: scroll; height: 40%; width: 100%;">
            <form:form method="POST" modelAttribute="applicant" class="form-horizontal">
            <form:input type="hidden" path="id" id="id"/>
                <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="firstName">First Name</label>
                    <div class="col-md-7">
                        <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                    </div>
                </div>
                </div>
                <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="lastName">Last Name</label>
                    <div class="col-md-7">
                        <form:input type="text" path="lastName" id="lastName" class="form-control input-sm" />
                    </div>
                </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="jmbg">JMBG</label>
                        <div class="col-md-7">
                            <form:input type="text" path="jmbg" id="jmbg" class="form-control input-sm"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="vacancies">Vacancy</label>
                    <div class="col-md-7">
                        <form:select path="vacancies" items="${vacancies}" multiple="true" itemValue="id" itemLabel="vacancyName" class="form-control input-sm">
                        </form:select>
                    </div>
                </div>
                </div>
                <div class="row">
                    <div class="form-actions floatRight">
                        <a href="<c:url value='/newapplicant' />" class="btn btn-primary btn-sm">Add New Applicant</a>
                        <input type="submit" value="Search" class="btn btn-primary btn-sm"/>
                    </div>
                </div>
            </form:form>
	 	</div>
		<div class="panel panel-default" style="overflow-y: scroll; height: 120%; width: 100%;">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">List of Applicants </span></div>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Firstname</th>
							<th>Lastname</th>
							<th>JMBG</th>
							<th>Year Of Birth</th>
							<th>Email</th>
							<th>Phone</th>
							<!--<th>Remark</th>-->
							<th>Hired After</th>
							<th>Update Time</th>
							<th>Vacancies</th>
							<th width="100"></th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${applicants}" var="applicant">
							<tr>
								<td>${applicant.firstName}</td>
								<td>${applicant.lastName}</td>
								<td>${applicant.jmbg}</td>
								<td>${applicant.yearOfBirth}</td>
								<td>${applicant.email}</td>
								<td>${applicant.phone}</td>
								<!-- <td>${applicant.remark}</td> -->
								<td>${applicant.hiredAfter}</td>
								<td>${applicant.updateTime}</td>
								<td><c:forEach items="${applicant.vacancies}" var="vacancy"> ${vacancy.vacancyName} </c:forEach></td>
								<td><a
									href="<c:url value='/edit-applicant-${applicant.id}' />"
									class="btn btn-success custom-width">edit</a></td>
								<td><a
									href="<c:url value='/delete-applicant-${applicant.id}' />"
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