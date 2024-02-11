<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<security:authentication property="principal.member.name"
					var="memberName" />
<body>
<div class="card mb-12 text-bg-dark" style="opacity: 0.9; margin-top:100px">
		<div class="card-body">
			
			<div class="col-12 d-flex justify-content-center"><h1>403 Forbidden</h1></div>
			<div class="col-12 d-flex justify-content-center"><h5>Sorry, You don't have permission to access this resource</h5></div>
			<div class="col-12 d-flex justify-content-center"><img src="/static/images/403error.jpg"></div>
		</div>		
</div>
</body>