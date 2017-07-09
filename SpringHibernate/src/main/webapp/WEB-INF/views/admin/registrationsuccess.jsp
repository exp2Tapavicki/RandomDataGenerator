<html>
<head>
	<title>Registration Confirmation Page</title>
</head>
<body>
	<div class="generic-container">
		<%@include file="../authheader.jsp" %>
		
		<div class="alert alert-success lead">
	    	${success}
		</div>
		
		<span class="well floatRight">
			Go to <a href="<c:url value='list' />"><i class="fa fa-user-secret" aria-hidden="true"></i> Users List</a>
		</span>
	</div>
</body>

</html>