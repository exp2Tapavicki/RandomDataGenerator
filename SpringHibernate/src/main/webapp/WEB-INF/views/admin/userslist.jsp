<html>

<head>
<title>Users List</title>
</head>

<body>
	<div style="width: 98%; height: 98%"  class="generic-container">
	    <%@include file="../authheader.jsp" %>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead"><i class="fa fa-user-secret" aria-hidden="true"></i> List of Users 
				<sec:authorize access="hasRole('ADMIN')">
						<span class="floatRight"> <a href="<c:url value='newuser' />" class="btn btn-primary btn-sm">Add New User</a>
						</span>
					</sec:authorize>
				</span>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th><i class="fa fa-user-o" aria-hidden="true"></i> Firstname</th>
						<th><i class="fa fa-user-o" aria-hidden="true"></i> Lastname</th>
						<th><i class="fa fa-envelope" aria-hidden="true"></i> Email</th>
						<th><i class="fa fa-code" aria-hidden="true"></i> SSO ID</th>
						<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
							<th width="100"></th>
						</sec:authorize>
						<sec:authorize access="hasRole('ADMIN')">
							<th width="100"></th>
						</sec:authorize>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.email}</td>
							<td>${user.ssoId}</td>
							<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
								<td><a href="<c:url value='edit-user-${user.ssoId}' />" class="btn btn-success custom-width">edit</a></td>
							</sec:authorize>
							<sec:authorize access="hasRole('ADMIN')">
								<td><a href="<c:url value='delete-user-${user.ssoId}' />" class="btn btn-danger custom-width">delete</a></td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>