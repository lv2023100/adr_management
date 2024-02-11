<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<div class="card mb-12" style="opacity: 0.9;">
		<div class="card-body row">
			<div class="col-6">
				<a class="btn btn-outline-secondary" href="/admin/member">Previous</a>
			</div>
		</div>
		<form method="post">
			<div class="card-body">
				<h3 class="card-title">Member Edit</h3>
				<c:if test="${failMessage != null }">
					<div class="alert alert-danger" role="alert">
					${failMessage }
					</div>
				</c:if>
				<div class="row" style="padding: 10px;">
					<div class="col-6">
						<label for="name" class="form-label">Name </label> <input
							type="text" class="form-control" id="name" name="name"
							value="${memberForm.name }">
					</div>
					<div class="col-6">
						<label for="phone_number" class="form-label">Phone Number
						</label> <input type="text" class="form-control" id="phone_number"
							name="phoneNumber" value="${memberForm.phoneNumber }">
					</div>

				</div>
				<div class="row" style="padding: 10px;">
					<div class="col-6">
						<label for="account" class="form-label">Account </label> <input
							type="text" class="form-control" id="account" name="account"
							value="${memberForm.account }">
					</div>
					<div class="col-6">
						<label for="email" class="form-label">Email </label> <input
							type="email" class="form-control" id="email" name="email"
							value="${memberForm.email }">
					</div>

				</div>
				<div class="row" style="padding: 10px;">
					<div class="col-6">
						<label for="password" class="form-label">Password </label> <input
							type="password" class="form-control" id="password"
							name="password">
					</div>
					<div class="col-6">
						<label for="password2" class="form-label">Confirm Password
						</label> <input type="password" class="form-control" id="password2"
							name="password2">
					</div>
				</div>
				<div class="row" style="padding: 10px;">
					<div class="col-12">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</div>

			</div>
		</form>
	</div>
	<script>
	$(function() {

		$("form").submit(function(event) {
			event.preventDefault();
			$("button[type='submit']").prop("disabled", true);
			this.submit();
		});
	});
	</script>
</body>