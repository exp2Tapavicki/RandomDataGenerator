<html>
<head>
<title>Success Confirmation Page</title>
</head>
<body>
	<div style="width: 98%; height: 98%" class="generic-container">
	    <%@include file="../authheader.jsp" %>
        <%@include file="../menu.jsp" %>
		<div class="alert alert-success lead"><i class="fa fa-database fa-1x" aria-hidden="true"></i> ${success}</div>
		<div class="well floatCenter"> 
			<c:choose>
			<c:when test="${randomDataGenerated != null}">
				<button class="btn btn-success " onclick="
		 		<%
		 		Toolkit.getDefaultToolkit ().getSystemClipboard().setContents(new StringSelection ((String)request.getAttribute("randomDataGenerated")), null);
				%>"><i class="fa fa-clipboard" aria-hidden="true"></i> Copy to clipboard</button> 
			</c:when>
			</c:choose>
			<span style="display:inline-block; width: 5em;"></span>
				Go to <a href="<c:url value='/rdg/applicantslist' />"><i class="fa fa-users" aria-hidden="true"></i> Application List</a> 
				Go to <a href="<c:url value='/rdg/vacancieslist' />"><i class="fa fa-address-card" aria-hidden="true"></i> Vacancies List</a> 
				Go to <a href="<c:url value='/rdg/randomDataGeneration' />"><i class="fa fa-database fa-1x" aria-hidden="true"></i> Random Data Generation List</a>
			</div>
			<c:choose>
			<c:when test="${randomDataGenerated != null}">
				<div class="panel panel-default" style="overflow-y: scroll; height: 80%; width: 100%;">
					${randomDataGenerated}
				</div>
			</c:when>
			<c:otherwise>
				<div style="display: none;" class="panel panel-default"
					style="overflow-y: scroll; height: 80%; width: 100%;">
					${randomDataGenerated}
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>