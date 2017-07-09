<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Random Data Generator</title>
</head>
<body>
	<div style="width: 98%; height: 98%" class="generic-container">
	    <%@include file="../authheader.jsp" %>
		<%@include file="../menu.jsp" %>
		<div class="row"style="width: 100%; height: 5%;">
		 	<form:form method="POST" modelAttribute="randomData" class="form-horizontal">
		 	    <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="modelName"><i class="fa fa-file-code-o" aria-hidden="true"></i> Model name</label>
                    <div class="col-md-7">
                       <form:select path="randomDataGenerationModels" items="${randomData.randomDataGenerationModels}" multiple="false" itemValue="id" itemLabel="modelNameSelect" class="form-control input-sm">
                        </form:select>
                    </div>
                    <input type="submit" value="Generate Random Data in JSON" class="btn btn-primary btn-sm"/>
                </div>
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="numberOfDataApplicant"><i class="fa fa-line-chart" aria-hidden="true"></i> Number of data </label>
					<div class="col-md-7">
						<form:input type="number" path="numberOfDataApplicant"
							id="numberOfDataApplicant" class="form-control input-sm" value="10" />
						<div class="has-error">
							<form:errors path="numberOfDataApplicant" class="help-inline" />
						</div>
					</div>
                    <form:input type="hidden" path="numberOfDataVacancies" id="numberOfDataVacancies" value="0"/>
                </div>
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="downloadFile"><i class="fa fa-file" aria-hidden="true"></i> Download file</label>
                    <div class="col-md-7">
                        <form:checkbox path="downloadFile" class="form-control input-sm" />
                    </div>
                    <div class="form-actions floatRight">
                        <a href="<c:url value='/rdg/newRandomDataGenerationModel'/>" class="btn btn-primary btn-sm">Add New Model</a>
                    </div>
                </div>
            </form:form>
		</div>
		<div class="panel panel-default"
			style="overflow-y: scroll; height: 58%; width: 100%;">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead"><i class="fa fa-database fa-1x" aria-hidden="true"></i> List of Fields </span>
			</div>
			<div class="table-responsive">
				<table class="table table-hover">
				    <c:forEach items="${randomDataGenerationModelList}" var="randomDataGenerationModel">
				    <thead>
				        <th><i class="fa fa-file-code-o" aria-hidden="true"></i> Model Name</th>
                        <th><i class="fa fa-user-o" aria-hidden="true"></i> User First Name</th>
                        <th><<i class="fa fa-user-o" aria-hidden="true"></i> User Last Name</th>
                        <th><i class="fa fa-user-secret" aria-hidden="true"></i> User role</th>
                        <tr>
                                <td>${randomDataGenerationModel.modelName}</td>
                                <td>${randomDataGenerationModel.user.firstName}</td>
                                <td>${randomDataGenerationModel.user.lastName}</td>
                                <td>${randomDataGenerationModel.user.getRoles()}</td>
	                            <td><a href="<c:url value='/rdg/edit-randomDataGenerationModel-${randomDataGenerationModel.id}' />"
	                                class="btn btn-success custom-width">edit</a></td>
	                            <td><a
	                                href="<c:url value='/rdg/delete-randomDataGenerationModel-${randomDataGenerationModel.id}' />"
	                                class="btn btn-danger custom-width">delete</a></td>
                                <td><a href="<c:url value='/rdg/newRandomDataGeneration-${randomDataGenerationModel.id}-${randomDataGenerationModel.randomDataGenerations[fn:length(randomDataGenerationModel.randomDataGenerations)-1].ordinalNumber + 1}' />"
                                    class="btn btn-primary btn-block">Add New Field</a></td>
                        </tr>
                    </thead>
					<thead>
						<tr>
						    <td></td>
						    <td></td>
							<th><i class="fa fa-file-code-o" aria-hidden="true"></i> Type of field</th>
							<th><i class="fa fa-lightbulb-o" aria-hidden="true"></i> Field name</th>
							<th><i class="fa fa-minus-circle" aria-hidden="true"></i> Minimum value</th>
							<th><i class="fa fa-plus-circle" aria-hidden="true"></i> Maximum value</th>
							<th><i class="fa fa-bullseye" aria-hidden="true"></i> Precision</th>
							<th><i class="fa fa-check" aria-hidden="true"></i> Allow nulls for value</th>
							<th><i class="fa fa-check" aria-hidden="true"></i> Allowed values splitted with comma [,] </th>
							<th width="100"></th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
				        
							<c:forEach items="${randomDataGenerationModel.randomDataGenerations}" var="randomDataGeneration">
								<tr>
								        <td></td>
								        <td></td>
										<td>${randomDataGeneration.basicClassConstants}</td>
										<td>${randomDataGeneration.propertyName}</td>
										<td>${randomDataGeneration.objMin}</td>
										<td>${randomDataGeneration.objMax}</td>
										<td>${randomDataGeneration.objPrecision}</td>
										<td>${randomDataGeneration.bAllowNulls}</td>
										<td>${randomDataGeneration.objEnum}</td>
									<td><a href="<c:url value='/rdg/edit-randomDataGeneration-${randomDataGeneration.id}-${randomDataGeneration.ordinalNumber}' />"
										class="btn btn-success custom-width">edit</a></td>
									<td><a
										href="<c:url value='/rdg/delete-randomDataGeneration-${randomDataGeneration.id}-${randomDataGeneration.ordinalNumber}' />"
										class="btn btn-danger custom-width">delete</a></td>
								</tr>
							</c:forEach>
					</tbody>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>