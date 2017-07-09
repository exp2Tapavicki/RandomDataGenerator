<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Applicant Registration Form</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
	  
	<script>
	$(document).ready(function() {
	  $("#updateTime").datepicker();
	});
	</script>
</head>

<body>

 	<div style="width:98%; height:98%" class="generic-container">
	<div class="well lead">Applicant Registration Form</div>
 	<form:form method="POST" modelAttribute="applicant" class="form-horizontal">
		<form:input type="hidden" path="id" id="id"/>
		
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="firstName">First Name</label>
				<div class="col-md-7">
					<form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="firstName" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="lastName">Last Name</label>
				<div class="col-md-7">
					<form:input type="text" path="lastName" id="lastName" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="lastName" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="jmbg">JMBG</label>
				<div class="col-md-7">
					<form:input type="text" path="jmbg" id="jmbg" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="jmbg" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="yearOfBirth">Year Of Birth</label>
				<div class="col-md-7">
					<form:input type="number" path="yearOfBirth" id="yearOfBirth" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="yearOfBirth" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="email">Email</label>
				<div class="col-md-7">
					<form:input type="email" path="email" id="email" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="email" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="phone">Phone</label>
				<div class="col-md-7">
					<form:input type="tel" path="phone" id="email" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="phone" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="remark">Remark</label>
				<div class="col-md-7">
					<form:textarea type="text" path="remark" id="remark" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="remark" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="hiredAfter">Hired After</label>
				<div class="col-md-7">
					<form:checkbox path="hiredAfter" id="hiredAfter" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="hiredAfter" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="updateTime">Update Time</label>
				<div class="col-md-7">
				
					<form:input type="date" path="updateTime" id="updateTime" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="updateTime" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="vacancies">Vacancy</label>
				<div class="col-md-7">
					<form:select path="vacancies" items="${vacancies}" multiple="true" itemValue="id" itemLabel="vacancyName" class="form-control input-sm">
					</form:select>
					<div class="has-error">
						<form:errors path="vacancies" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-actions floatRight">
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/applicantslist' />">Cancel</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="Add" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/applicantslist' />">Cancel</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
	</div>
</body>
</html>