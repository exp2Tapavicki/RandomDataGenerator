<html>
<head>
	<title>Vacancy Form</title>
</head>
<body>
 	<div style="overflow-y: scroll; width:98%; height:98%" class="generic-container">
 	<%@include file="../authheader.jsp" %>
 	<%@include file="../menu.jsp" %>
	<div class="well lead"><i class="fa fa-address-card" aria-hidden="true"></i> Vacancy Form</div>
 	<form:form method="POST" modelAttribute="vacancy" class="form-horizontal">
		<form:input type="hidden" path="id" id="id"/>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="vacancyName"><i class="fa fa-id-badge" aria-hidden="true"></i>  Vacancy Name</label>
				<div class="col-md-7">
					<form:input type="text" path="vacancyName" id="vacancyName" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="vacancyName" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="vacancyCode"><i class="fa fa-code" aria-hidden="true"></i> Vacancy Code</label>
				<div class="col-md-7">
					<form:input type="text" path="vacancyCode" id="vacancyCode" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="vacancyCode" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-actions floatRight">
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/rdg/vacancieslist' />">Cancel</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="Add" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/rdg/vacancieslist' />">Cancel</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
	</div>
</body>
</html>