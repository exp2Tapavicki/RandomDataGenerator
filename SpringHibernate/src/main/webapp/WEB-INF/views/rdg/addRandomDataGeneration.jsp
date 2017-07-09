<html>
<head>
<title>Random Data Generation Form</title>
</head>
<body>
	<div style="overflow-y: scroll; width: 98%; height: 98%" class="generic-container">
	    <%@include file="../authheader.jsp" %>
        <%@include file="../menu.jsp" %>
		<div class="well lead"><i class="fa fa-database fa-1x" aria-hidden="true"></i> Random Data Generation Form</div>
		<form:form method="POST" modelAttribute="randomDataGeneration" class="form-horizontal" onsubmit="return checkform()">
			<form:input type="hidden" path="id" id="id" />
			<div class="row" id="basicClassConstantsId">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="basicClassConstants"><i class="fa fa-file-code-o" aria-hidden="true"></i> Type of field</label>
					<div class="col-md-7">
						<form:select class="form-control" path="basicClassConstants" id="basicClassConstants" items="${classTypes}" />
						<div class="has-error">
							<form:errors path="basicClassConstants" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row" id="propertyNameId">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="propertyName"><i class="fa fa-lightbulb-o" aria-hidden="true"></i> Field name</label>
					<div class="col-md-7">
						<form:input type="text" path="propertyName" id="propertyName" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="propertyName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row" id="objMinId">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="objMin"><i class="fa fa-minus-circle" aria-hidden="true"></i> Minimum value</label>
					<div class="col-md-7">
						<form:input type="text" path="objMin" id="objMin" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="objMin" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row" id="objMaxId">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="objMax"><i class="fa fa-plus-circle" aria-hidden="true"></i> Maximum value</label>
					<div class="col-md-7">
						<form:input type="text" path="objMax" id="objMax" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="objMax" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row" id="objPrecisionId">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="objPrecision"><i class="fa fa-bullseye" aria-hidden="true"></i> Precision</label>
					<div class="col-md-7">
						<form:input type="number" path="objPrecision" id="objPrecision" class="form-control input-sm" value="5" />
						<div class="has-error">
							<form:errors path="objPrecision" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row" id="bAllowNullsId">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="bAllowNulls"><i class="fa fa-check" aria-hidden="true"></i> Allow nulls for value</label>
					<div class="col-md-7">
						<form:checkbox path="bAllowNulls" id="bAllowNulls" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="bAllowNulls" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row" id="objEnumId">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="objEnum"><i class="fa fa-check" aria-hidden="true"></i> Allowed values splitted with comma [,] </label>
					<div class="col-md-7">
						<form:input type="text" path="objEnum" id="objEnum" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="objEnum" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="form-actions floatRight">
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="Update" class="btn btn-primary btn-sm" /> or <a href="<c:url value='/rdg/randomDataGeneration' />">Cancel</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="Add" class="btn btn-primary btn-sm" /> or <a href="<c:url value='/rdg/randomDataGeneration' />">Cancel</a>
					</c:otherwise>
				</c:choose>
			</div>
		</form:form>
	</div>
</body>
<script type="text/javascript">
    var basicClassConstants;
    var precision;
    function setHiddenFields(){
    	var propertyNameShow = "${visableFields['propertyName']}";
        var objMinShow = "${visableFields['objMin']}";
        var objMaxShow = "${visableFields['objMax']}";
        var objPrecisionShow = "${visableFields['objPrecision']}";
        var bAllowNullsShow = "${visableFields['bAllowNulls']}";
        var objEnumShow = "${visableFields['objEnum']}";
        
        if (propertyNameShow.indexOf(basicClassConstants) !== -1){
            $('#propertyNameId').show();  
        } else {
            $('#propertyNameId').hide();
        }
        
        if (objMinShow.indexOf(basicClassConstants) !== -1){
            $('#objMinId').show();  
        } else {
            $('#objMinId').hide();
        }
        
        if (objMaxShow.indexOf(basicClassConstants) !== -1){
            $('#objMaxId').show();  
        } else {
            $('#objMaxId').hide();
        }
        
        if (objPrecisionShow.indexOf(basicClassConstants) !== -1){
            $('#objPrecisionId').show();  
        } else {
            $('#objPrecisionId').hide();
        }
        
        if (bAllowNullsShow.indexOf(basicClassConstants) !== -1){
            $('#bAllowNullsId').show();  
        } else {
            $('#bAllowNullsId').hide();
        }
        
        if (objEnumShow.indexOf(basicClassConstants) !== -1){
            $('#objEnumId').show();  
        } else {
            $('#objEnumId').hide();
        }
    }
    
  
    
    function changeInputFieldType(){
        if (basicClassConstants.localeCompare('java.util.Date') == 0 ){
            $('#objMinId').find('#objMin').each(function() {
            	$('#objMinId').find('#objMin').clone().attr({type:"text"}).insertBefore(this);
             }).remove();
            $('#objMaxId').find('#objMax').each(function() {
            	$('#objMaxId').find('#objMax').clone().attr({type:"text"}).insertBefore(this);
             }).remove();
            
//            $("#objMin").datetimepicker({ dateFormat: 'dd/mm/yy h:m:s' });
//            $("#objMax").datetimepicker({ dateFormat: 'dd/mm/yy h:m:s' });
            $("#objMin").datetimepicker();
            $("#objMax").datetimepicker();
        } else if (basicClassConstants.localeCompare('java.lang.Integer') == 0 ||
        		basicClassConstants.localeCompare('java.lang.Long') == 0 ||
        		basicClassConstants.localeCompare('java.lang.Short') == 0 ||
        		basicClassConstants.localeCompare('java.lang.Byte') == 0 ||
        		basicClassConstants.localeCompare('java.lang.Character') == 0 ||
        		basicClassConstants.localeCompare('java.math.BigInteger') == 0 ||
        		basicClassConstants.localeCompare('java.lang.Enum') == 0 ||
        		basicClassConstants.localeCompare('int') == 0 ||
        		basicClassConstants.localeCompare('long') == 0 ||
        		basicClassConstants.localeCompare('short') == 0 ||
        		basicClassConstants.localeCompare('byte') == 0 ||
        		basicClassConstants.localeCompare('char') == 0
        	    ){
        	$('#objMinId').find('#objMin').each(function() {
        		$('#objMinId').find('#objMin').clone().attr({type:"number", step:"1"}).insertBefore(this);
       		}).remove();
            $('#objMaxId').find('#objMax').each(function() {
            	$('#objMaxId').find('#objMax').clone().attr({type:"number", step:"1"}).insertBefore(this);
            }).remove();
    	}  else if (basicClassConstants.localeCompare('java.lang.Double') == 0 ||
                basicClassConstants.localeCompare('java.lang.Float') == 0 ||
                basicClassConstants.localeCompare('java.math.BigDecimal') == 0 ||
                basicClassConstants.localeCompare('double') == 0 ||
                basicClassConstants.localeCompare('float') == 0
                ){
    		steps = "0.";
    		for(count = 1; count < precision; count++){
    			steps = steps + '0';
             }
    		steps = steps + "1";
            $('#objMinId').find('#objMin').each(function() {
            	$('#objMinId').find('#objMin').clone().attr({type:"number", step:steps}).insertBefore(this);
            }).remove();
            $('#objMaxId').find('#objMax').each(function() {
            	$('#objMaxId').find('#objMax').clone().attr({type:"number", step:steps}).insertBefore(this);
            }).remove();
        }
    }
    
	$(document).ready(function() {
		basicClassConstants = "${randomDataGeneration.basicClassConstants}";
		precision = $('#objPrecisionId').find('#objPrecision').val();
		setHiddenFields();
		changeInputFieldType();
	});
	$("#basicClassConstants").change(function() {
		basicClassConstants = $(this).val();
		precision = $('#objPrecisionId').find('#objPrecision').val();
		setHiddenFields();
		changeInputFieldType();
	})
	
	$("#objPrecision").change(function() {
		precision = $('#objPrecisionId').find('#objPrecision').val();
		changeInputFieldType();
	})
	
	function checkform(){
		$(this);
		$('form').serialize();
		basicClassConstants = $(this).val();
	}
</script>
</html>