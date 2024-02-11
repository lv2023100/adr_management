<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<security:authentication property="principal.member.teamInfoList"
					var="teamInfoList" />
<security:authentication property="principal.member.name"
					var="memberName" />
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="/static/css/mycss.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>ADR management</title>
</head>

<body>

	<header>
		<nav class="navbar bg-body-tertiary fixed-top" style="opacity: 0.9;"
			data-bs-theme="dark">
			<div class="container-fluid">
				<a class="navbar-brand" href="/admin">ADR management</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
					aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="offcanvas offcanvas-end" tabindex="-1"
					id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
					<div class="offcanvas-header">
						<h5 class="offcanvas-title" id="offcanvasNavbarLabel">ADR</h5>
						<button type="button" class="btn-close"
							data-bs-dismiss="offcanvas" aria-label="Close"></button>
					</div>
					<div class="offcanvas-body">
						<h5 class="offcanvas-title">Hi ${memberName }</h5>
						<hr>
						<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">							
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">ADR</a>
								<ul class="dropdown-menu">
									<c:forEach items="${teamInfoList }" var="teamInfo">
										<li><a class="dropdown-item" href="/admin/adr?teamId=${teamInfo.teamId }">${teamInfo.name }</a></li>
									</c:forEach>
									
								</ul></li>
							<li class="nav-item"><a class="nav-link" href="/admin/team">Team</a></li>
							<li class="nav-item"><a class="nav-link" href="/admin/member">Member</a></li>
							<li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
						</ul>
					</div>
				</div>
			</div>
		</nav>
	</header>
	
	<div class="container-xxl bd-gutter mt-3 my-md-4 bd-layout" style="padding-top: 80px;">
		<main class="bd-main order-1">
			<sitemesh:write property='body' />
		</main>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	
</body>
</html>