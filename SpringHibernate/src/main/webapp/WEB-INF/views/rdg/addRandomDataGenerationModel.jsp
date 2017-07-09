<html>
<head>
	<title>Applicant Registration Form</title>
	<script>
	$(document).ready(function($){
	   $("#updateTime").datepicker({ dateFormat: 'dd/mm/yy' });
	});
	</script>
</head>

<body>
 	<div style="overflow-y: scroll; width:98%; height:98%" class="generic-container">
 	<%@include file="../authheader.jsp" %>
 	<%@include file="../menu.jsp" %>
	<div class="well lead"><i class="fa fa-commenting-o" aria-hidden="true"></i> Model Form</div>
 	<form:form method="POST" modelAttribute="randomDataGenerationModel" class="form-horizontal">
		<form:input type="hidden" path="id" id="id"/>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="modelName"><i class="fa fa-commenting-o" aria-hidden="true"></i> Model Name</label>
				<div class="col-md-7">
					<form:input type="text" path="modelName" id="modelName" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="modelName" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-actions floatRight">
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/rdg/randomDataGeneration' />">Cancel</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="Add" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/rdg/randomDataGeneration' />">Cancel</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
	</div>
</body>
</html>