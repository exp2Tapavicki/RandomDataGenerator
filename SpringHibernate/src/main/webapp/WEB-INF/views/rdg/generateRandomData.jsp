<html>
<head>
    <title>Generate Random Data</title>
</head>
<body>
	<div style="width: 98%; height: 98%" class="generic-container">
        <%@include file="../authheader.jsp" %>
		<%@include file="../menu.jsp" %>
		<div class="well lead"><i class="fa fa-database" aria-hidden="true"></i> Generate Random Data for Applicants and Vacancies</div>
		<form:form method="POST" modelAttribute="randomData"
			class="form-horizontal">
			<c:if test="${errors != null}">
				<div class="alert alert-danger">
				    <c:forEach items="${errors}" var="entry">
							<c:out value="${entry.key}"/>  : <c:out value="${entry.value}"/> <br />
					</c:forEach> 
				</div>
			</c:if>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="numberOfDataApplicant"><i class="fa fa-line-chart" aria-hidden="true"></i> Number
						of applicant </label>
					<div class="col-md-7">
					    <form:input type="hidden" path="modelName" id="modelName" value=""/>
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
					<label class="col-md-3 control-lable" for="numberOfDataVacancies"><i class="fa fa-line-chart" aria-hidden="true"></i> Number
						of vacancies </label>
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
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="downloadFile"><i class="fa fa-file" aria-hidden="true"></i> Download file </label>
                    <div class="col-md-7">
                        <form:checkbox path="downloadFile" class="form-control input-sm" />
                    </div>
                </div>
            </div>
			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Generate Random Data" class="btn btn-primary btn-sm" />
					or <a href="<c:url value='/rdg/generateRandomData' />">Cancel</a>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>