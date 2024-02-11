<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<div class="card mb-12" style="opacity: 0.9;">
		<div class="card-body row">
			<div class="col-6">
				<a class="btn btn-outline-secondary" href="/admin/team">Previous</a>
			</div>
		</div>
		<form method="post">
			<div class="card-body">
				<h3 class="card-title">Team Edit</h3>
				<input type="hidden" value="${teamForm.teamId }">
				<div class="row" style="padding: 10px;">
					<div class="col-6">
						<label for="name" class="form-label">Name </label> <input
							type="text" class="form-control" id="name" name="name"
							value="${teamForm.name }">
					</div>

				</div>

				<div class="row" style="padding: 10px;">
					<div class="col-6">
						<label for="memberList" class="form-label">Member </label> <select
							class="form-select" multiple id="memberList" size="8"
							aria-label="Multiple select example">
							<c:forEach items="${memberList }" var="member">
								<option id="${member.memberId }" value="${member.memberId }">${member.name }</option>
							</c:forEach>
						</select>
						<div class="col-12" style="padding-top: 10px;">
							<button class="btn btn-outline-secondary" type="button"
								onclick="addMember()">Add Member</button>
						</div>
					</div>
				</div>
				<div style="padding: 10px;">
					<div class=""  id="memberInTeam"></div>
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
			<c:if test="${teamForm.memberIdList != null}">
				let memberIdList = ${teamForm.memberIdList};
			</c:if>
			<c:if test="${teamForm.memberIdList == null}">
				let memberIdList = [];
			</c:if>
			
			if (memberIdList) {
				$.each(memberIdList,
						function(index, memberId) {
							let name = $('#memberList')
									.find('#' + memberId).text();
							addMemberAppend(memberId, name);
						});
			}
			$("form").submit(function(event) {
				event.preventDefault();
				$("button[type='submit']").prop("disabled", true);
				this.submit();
			});

		});
		function addMember() {
			let name = $('#memberList option:selected').text();
			let memberId = $('#memberList option:selected').val();
			if($('#'+memberId+'-c').length){
				return;
			}
			addMemberAppend(memberId, name);
		}
		function addMemberAppend(memberId, name) {
			let appendHtml = '<input type="checkbox" name="memberIdList" value="'+memberId+'" class="btn-check" id="'+memberId+'-c" checked autocomplete="off">'
					+ '<label class="btn btn-outline-secondary btn-sm" id="'
					+ memberId
					+ '-l" for="btn-check-3-outlined" onclick="removeMember('
					+ memberId + ')">' + name + '</label> '
			$('#memberInTeam').append(appendHtml);
		}
		function removeMember(memberId) {
			$('#' + memberId + '-c').remove();
			$('#' + memberId + '-l').remove();
		}
	</script>
</body>