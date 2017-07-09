<%@include file="include.jsp" %>
<div class="well">
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li class="nav navbar-nav"><a href="<c:url value='/rdg/' />"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
	      </ul> 
	      <ul class="nav navbar-nav">
	        <li class="#"><a href="<c:url value='/rdg/generateRandomData' />"><i class="fa fa-database fa-1x" aria-hidden="true"></i> Generate Random Data</a></li>
	      </ul> 
	      <ul class="nav navbar-nav">
	        <li class="#"><a href="<c:url value='/rdg/randomDataGeneration' />"><i class="fa fa-database fa-1x" aria-hidden="true"></i> Random Data Generator</a></li>
	      </ul> 
  		  <ul class="nav navbar-nav">
		    <li class="#"><a href="<c:url value='/rdg/applicantslist' />"><i class="fa fa-users" aria-hidden="true"></i> Applicants</a></li>
		    <li class="#"><a href="<c:url value='/rdg/vacancieslist' />"><i class="fa fa-address-card" aria-hidden="true"></i> Vacancies</a></li>
		  </ul>
	    </div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
	</nav>
</div>
<script>
	var url = window.location;
	$('ul.nav a[href="'+ url +'"]').parent().addClass('active');
	$('ul.nav a').filter(function() {
	 return this.href == url;
	}).parent().addClass('active');
</script>