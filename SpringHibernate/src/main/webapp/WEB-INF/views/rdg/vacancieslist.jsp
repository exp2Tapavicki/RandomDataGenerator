<html>
<head>
    <title>Vacancies List</title>
</head>
<body>
	<div style="width: 98%; height: 98%" class="generic-container">
	    <%@include file="../authheader.jsp" %>
		<%@include file="../menu.jsp" %>
		<div class="row"style="width: 100%; height: 5%;">
			<div class="form-actions floatRight">
				<a href="<c:url value='/rdg/newvacancy' />"
					class="btn btn-primary btn-sm">Add New Vacancy</a>
			</div>
		</div>
		<div class="panel panel-default"
			style="overflow-y: scroll; height: 75%; width: 100%;">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead"><i class="fa fa-address-card" aria-hidden="true"></i> List of Vacancies </span>
			</div>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th><i class="fa fa-id-badge" aria-hidden="true"></i> Vacancy Name</th>
							<th><i class="fa fa-code" aria-hidden="true"></i> Vacancy Code</th>
							<th width="100"></th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${vacancies}" var="vacancy">
							<tr>
								<td>${vacancy.vacancyName}</td>
								<td>${vacancy.vacancyCode}</td>
								<td><a href="<c:url value='/rdg/edit-vacancy-${vacancy.id}' />"
									class="btn btn-success custom-width">edit</a></td>
								<td><a
									href="<c:url value='/rdg/delete-vacancy-${vacancy.id}' />"
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