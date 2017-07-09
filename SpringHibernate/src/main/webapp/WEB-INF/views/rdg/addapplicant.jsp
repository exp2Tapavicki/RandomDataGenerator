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
	<div class="well lead"><i class="fa fa-users" aria-hidden="true"></i> Applicant Registration Form</div>
 	<form:form method="POST" modelAttribute="applicant" class="form-horizontal">
		<form:input type="hidden" path="id" id="id"/>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="firstName"><i class="fa fa-user-o" aria-hidden="true"></i> First Name</label>
				<div class="col-md-7">
					<form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="firstName" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="lastName"><i class="fa fa-user-o" aria-hidden="true"></i> Last Name</label>
				<div class="col-md-7">
					<form:input type="text" path="lastName" id="lastName" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="lastName" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="jmbg"><i class="fa fa-id-card-o" aria-hidden="true"></i> JMBG</label>
				<div class="col-md-7">
					<form:input type="text" path="jmbg" id="jmbg" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="jmbg" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="yearOfBirth"><i class="fa fa-calendar" aria-hidden="true"></i> Year Of Birth</label>
				<div class="col-md-7">
					<form:input type="number" path="yearOfBirth" id="yearOfBirth" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="yearOfBirth" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="email"><i class="fa fa-envelope" aria-hidden="true"></i> Email</label>
				<div class="col-md-7">
					<form:input type="email" path="email" id="email" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="email" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="phone"><i class="fa fa-mobile" aria-hidden="true"></i> Phone</label>
				<div class="col-md-7">
					<form:input type="tel" path="phone" id="email" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="phone" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="remark"><i class="fa fa-file-text" aria-hidden="true"></i> Remark</label>
				<div class="col-md-7">
					<form:textarea type="text" path="remark" id="remark" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="remark" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="hiredAfter"><i class="fa fa-check" aria-hidden="true"></i> Hired After</label>
				<div class="col-md-7">
					<form:checkbox path="hiredAfter" id="hiredAfter" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="hiredAfter" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="updateTime"><i class="fa fa-calendar" aria-hidden="true"></i> Update Time</label>
				<div class="col-md-7">
				
					<form:input type="text" path="updateTime" id="updateTime" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="updateTime" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="vacancies"><i class="fa fa-address-card" aria-hidden="true"></i> Vacancy</label>
				<div class="col-md-7">
					<form:select path="vacancies" items="${vacancies}" multiple="true" itemValue="id" itemLabel="vacancyName" class="form-control input-sm">
					</form:select>
					<div class="has-error">
						<form:errors path="vacancies" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-actions floatRight">
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/rdg/applicantslist' />">Cancel</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="Add" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/rdg/applicantslist' />">Cancel</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
	</div>
</body>
</html>