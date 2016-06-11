<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.awt.datatransfer.Clipboard" %>
<%@ page import="java.awt.datatransfer.StringSelection" %>
<%@ page import="java.awt.Toolkit" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success Confirmation Page</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
	<div style="width: 98%; height: 98%" class="generic-container">
		<div class="alert alert-success lead">${success}</div>
		<span class="well floatRight"> 
			<c:choose>
			<c:when test="${randomDataGenerated != null}">
				<button class="btn btn-success " onclick="
		 		<%
		 		Toolkit.getDefaultToolkit ().getSystemClipboard().setContents(new StringSelection ((String)request.getAttribute("randomDataGenerated")), null);
				%>">Copy to clipboard</button> 
			</c:when>
			</c:choose>
			Go to <a
			href="<c:url value='/applicantslist' />">Application List</a> Go to <a
			href="<c:url value='/vacancieslist' />">Vacancies List</a> Go to <a
			href="<c:url value='/randomDataGeneration' />">Random Data
				Generation List</a>
		</span>
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