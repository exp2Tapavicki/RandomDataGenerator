<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Applicants List</title>
</head>
<body>
	<div style="overflow-y: scroll; width:98%; height:98%" class="generic-container">
	    <%@include file="../authheader.jsp" %>
		<%@include file="../menu.jsp" %>
	 	<div class="well" style="overflow-y: scroll; height: 40%; width: 100%;">
            <form:form method="POST" modelAttribute="applicant" class="form-horizontal">
            <form:input type="hidden" path="id" id="id"/>
                <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="firstName"><i class="fa fa-user-o" aria-hidden="true"></i> First Name</label>
                    <div class="col-md-7">
                        <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                    </div>
                </div>
                </div>
                <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="lastName"><i class="fa fa-user-o" aria-hidden="true"></i> Last Name</label>
                    <div class="col-md-7">
                        <form:input type="text" path="lastName" id="lastName" class="form-control input-sm" />
                    </div>
                </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="jmbg"><i class="fa fa-id-card-o" aria-hidden="true"></i> JMBG</label>
                        <div class="col-md-7">
                            <form:input type="text" path="jmbg" id="jmbg" class="form-control input-sm"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="vacancies"><i class="fa fa-address-card" aria-hidden="true"></i>  Vacancy</label>
                    <div class="col-md-7">
                        <form:select path="vacancies" items="${vacancies}" multiple="true" itemValue="id" itemLabel="vacancyName" class="form-control input-sm">
                        </form:select>
                    </div>
                </div>
                </div>
                <div class="row">
                    <div class="form-actions floatRight">
                        <a href="<c:url value='/rdg/newapplicant' />" class="btn btn-primary btn-sm">Add New Applicant</a>
                        <input type="submit" value="Search" class="btn btn-primary btn-sm"/>
                    </div>
                </div>
            </form:form>
	 	</div>
		<div class="panel panel-default" style="overflow-y: scroll; height: 120%; width: 100%;">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead"><i class="fa fa-users" aria-hidden="true"></i>  List of Applicants </span></div>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Firstname</th>
							<th>Lastname</th>
							<th>JMBG</th>
							<th>Year Of Birth</th>
							<th>Email</th>
							<th>Phone</th>
							<!--<th>Remark</th>-->
							<th>Hired After</th>
							<th>Update Time</th>
							<th>Vacancies</th>
							<th width="100"></th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${applicants}" var="applicant">
							<tr>
								<td>${applicant.firstName}</td>
								<td>${applicant.lastName}</td>
								<td>${applicant.jmbg}</td>
								<td>${applicant.yearOfBirth}</td>
								<td>${applicant.email}</td>
								<td>${applicant.phone}</td>
								<!-- <td>${applicant.remark}</td> -->
								<td>${applicant.hiredAfter}</td>
								<td>${applicant.updateTime}</td>
								<td><c:forEach items="${applicant.vacancies}" var="vacancy"> ${vacancy.vacancyName} </c:forEach></td>
								<td><a
									href="<c:url value='/rdg/edit-applicant-${applicant.id}' />"
									class="btn btn-success custom-width">edit</a></td>
								<td><a
									href="<c:url value='/rdg/delete-applicant-${applicant.id}' />"
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