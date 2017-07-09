<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Home</title>
</head>
<body>
	<div style="width:98%; height:98%" class="generic-container">
	   <%@include file="../authheader.jsp" %>
	   <%@include file="../menu.jsp" %>
	   <div style="overflow-y: scroll; height: 78%; width: 100%;" class="panel panel-default">
	       <div id="container">
	           <c:import url="https://github.com/exp2Tapavicki/RandomDataGenerator/" />
	       </div>
	   </div>
   	</div>
</body>
<script type="text/javascript">
    $('#container').on('click', function (event) {
	  if (event.target.href !== undefined && event.target.href.indexOf(location.origin) !== -1){
		  event.target.href = event.target.href.replace(location.origin, "https://github.com/");
		  $('#import').each(function() {
			  $('#import').clone().attr({url:event.target.href}).insertBefore(this);
           }).remove();
		  location.reload();
	  }
	});
</script>
</html>