<%@include file="include.jsp" %>	
<c:set var="baseURL" value="${pageContext.request.localName}:8080/SpringHibernate"/>
<div style="width: 98%;" class="authbar well">
    <span>Dear <strong class="text-primary"> <i class="fa fa-user-secret" aria-hidden="true"></i> ${loggedinuser} </strong>, Welcome to Random Data Generator.</span>
    <span class="floatCenter"></span>
    <span class="floatCenter"><i class="fa fa-database" aria-hidden="true"></i> <a href="http://${baseURL}/rdg/randomDataGeneration">Random Data Generation</a></span>
    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
        <span class="floatCenter"><i class="fa fa-cogs" aria-hidden="true"></i> <a href="<c:url value="http://${pageContext.request.localName}:8080/SpringHibernate/admin/" />">Admin Panel</a></span>
    </sec:authorize>
    <span class="floatCenter"><i class="fa fa-sign-out" aria-hidden="true"></i> <a href="<c:url value="/logout"/>">Logout</a></span>
</div>