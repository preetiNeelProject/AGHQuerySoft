

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
		<title><tiles:getAsString name="title" /></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 		<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/dms.css'/>"/>-->
		<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/base.css'/>"/> 
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<c:url value='staticResources/styleSheets/bootstrap.min.css'/>"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<c:url value='staticResources/styleSheets/font-awesome.min.css'/>"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<c:url value='staticResources/styleSheets/sub.css'/>"/>

	<link rel="stylesheet" type="text/css" href="<c:url value='staticResources/styleSheets/cform.css'/>"/>
	
	<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/tableManager.css'/>"/>
<%-- <script type="text/javascript" src="<c:url value='/staticResources/scripts/retrieval.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.min.js'/>"></script>
<!--===============================================================================================-->
		<!-- <link rel="stylesheet" href="<c:url value='staticResources/styleSheets/bootstrap.min.css'/>"/> -->
<!-- 		<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/bootstrap-theme.min.css'/>"/>
	-->
		
		<style>
			.confBtn {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 8px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 10px;
  margin: 3px 1px;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
  cursor: pointer;
  border-radius: 12%;
  background-color: white;
  color: black;
  border: 2px solid #e7e7e7;
}
.confBtn:hover {background-color: #e7e7e7;}
[type="date"]::-webkit-inner-spin-button {
  display: none;
}
		</style>
	</head>
	<body class="bdy-bg">
		<section id="header">
			<tiles:insertAttribute name="menu"/>
		</section>
		<section id="body">
			<tiles:insertAttribute name="body"/>
		</section>
		<section id="footer">
			<tiles:insertAttribute name="footer"/>
		</section>

<!--===============================================================================================-->
	<script language="javascript">
		$('.dropdown-menu a.dropdown-toggle').on('click', function(e) {
		  if (!$(this).next().hasClass('show')) {
			$(this).parents('.dropdown-menu').first().find('.show').removeClass("show");
		  }
		  var $subMenu = $(this).next(".dropdown-menu");
		  $subMenu.toggleClass('show');

		  $(this).parents('li.nav-item.dropdown.show').on('hidden.bs.dropdown', function(e) {
			$('.dropdown-submenu .show').removeClass("show");
		  });
		  return false;
		});
	</script>
<!--===============================================================================================-->
	<script src="<c:url value='/staticResources/scripts/popper.js' />"></script>
	<script src="<c:url value='/staticResources/scripts/bootstrap.min.js' />"></script>
<!--===============================================================================================-->

	</body>
</html>