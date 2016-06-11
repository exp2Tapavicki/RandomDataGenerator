<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Random Data Generation Form</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
 	<div style="overflow-y: scroll; width:98%; height:98%" class="generic-container">
	<div class="well lead">Random Data Generation Form</div>
 	<form:form method="POST" modelAttribute="randomDataGeneration" class="form-horizontal">
		<form:input type="hidden" path="id" id="id"/>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="basicClassConstants">Type of field</label>
				<div class="col-md-7">
					<form:select class="form-control" path="basicClassConstants" id="basicClassConstants" items="${classTypes}"/>
					<!--<form:input type="text" path="basicClassConstants" id="basicClassConstants" class="form-control input-sm" />-->
					<div class="has-error">
						<form:errors path="basicClassConstants" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="propertyName">Field name</label>
				<div class="col-md-7">
					<form:input type="text" path="propertyName" id="propertyName" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="propertyName" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="objMin">Minimum value</label>
				<div class="col-md-7">
					<form:input type="text" path="objMin" id="objMin" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="objMin" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="objMax">Maximum value</label>
				<div class="col-md-7">
					<form:input type="text" path="objMax" id="objMax" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="objMax" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="objPrecision">Precision</label>
				<div class="col-md-7">
					<form:input type="number" path="objPrecision" id="objPrecision" class="form-control input-sm" value="5" />
					<div class="has-error">
						<form:errors path="objPrecision" class="help-inline" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="bAllowNulls">Allow nulls for value</label>
				<div class="col-md-7">
					<form:checkbox path="bAllowNulls" id="bAllowNulls" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="bAllowNulls" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="objEnum">Allowed values splited with comma [,] </label>
				<div class="col-md-7">
					<form:input type="text" path="objEnum" id="objEnum" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="objEnum" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions floatRight">
			<c:choose>
				<c:when test="${edit}">
					<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/randomDataGeneration' />">Cancel</a>
				</c:when>
				<c:otherwise>
					<input type="submit" value="Add" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/randomDataGeneration' />">Cancel</a>
				</c:otherwise>
			</c:choose>
		</div>
	</form:form>
	</div>
</body>
</html>