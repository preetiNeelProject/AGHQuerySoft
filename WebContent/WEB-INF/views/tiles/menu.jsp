<%--
    Document   : Menu
    Created on : 29 Mar, 2018, 11:45:32 AM
    Author     : Swapril Tyagi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid bg-white">
	<nav class="navbar navbar-expand-lg navbar-light">
		<a class="navbar-brand" href="#"><img width="70%" src="${pageContext.request.contextPath}/staticResources/images/indexLogo.jpg"></a>
	  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
		<div class="collapse navbar-collapse justify-content-center" id="navbarNavDropdown1">
			<ul class="navbar-nav">
				<li class="nav-item"><a href="${pageContext.request.contextPath}/home" class="nav-link btn btn-outline-light btn-m10">Home</a></li>
				<c:if test="${not empty admin}">
					<li class="nav-item dropdown">
						<a class="nav-link btn btn-outline-light btn-m10 dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Users</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="${pageContext.request.contextPath}/currentUsers">Current Users</a>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/addUser">Add New User</a>
						</div>
		  			</li>
				</c:if>
				<c:if test="${not empty upload}">
					<li class="nav-item dropdown">
						<a class="nav-link btn btn-outline-light btn-m10 dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">DSI's</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="${pageContext.request.contextPath}/dsi?obj=neel">Neel's Signatures</a>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/dsi?obj=ag">AG Office Signatures</a>
						</div>
			  		</li>
					<li class="nav-item dropdown">
						<a class="nav-link btn btn-outline-light btn-m10 dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Upload</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<c:forEach items="${departments}" var="department">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/upload?department=${department}">${department}</a>
							</c:forEach>
						</div>
			  		</li>
			  		<li class="nav-item"><a href="${pageContext.request.contextPath}/uploadBulk" class="nav-link btn btn-outline-light btn-m10">Bulk Upload</a></li>
			  	</c:if>
				<li class="nav-item dropdown">
					<a class="nav-link btn btn-outline-light btn-m10 dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Retrieve</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<c:forEach items="${departments}" var="department">
							<a class="dropdown-item" href="${pageContext.request.contextPath}/retrieval?department=${department}">${department}</a>
						</c:forEach>
					</div>
			  	</li>
				<c:if test="${not empty lg}"><li class="nav-item"><a href="${pageContext.request.contextPath}/logs" class="nav-link btn btn-outline-light btn-m10">Logs</a></li></c:if>
			</ul>
		</div>
		
		<ul class="navbar-nav ml-auto rgt-menu">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle hi-user" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Hi, ${user} <img src="${pageContext.request.contextPath}/staticResources/images/profile-dummy.jpg" class="user"></a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<c:if test="${not empty userModule}"><a class="dropdown-item" href="${pageContext.request.contextPath}/currentUsers">Profile</a></c:if>
					<a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Log Out</a>
				</div>
			</li>
		</ul>
	</nav>	  
</div>