<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<div class="card mb-12" style="opacity: 0.9;">
		<div class="card-body row">
			<div class="col-6">
				<c:if test="${adrRecord.adrId != null}">
				<a class="btn btn-outline-secondary"
					href="/admin/adrContent/${adrRecord.adrId }">Previous</a>
				</c:if>
				<c:if test="${adrRecord.adrId == null }">
				<a class="btn btn-outline-secondary"
					href="/admin/adr?teamId=${adrRecord.teamId }">Previous</a>
				</c:if>

			</div>
		</div>
		<form method="post">
			<div class="card-body">
				<h3 class="card-title">ADR Edit</h3>
				<input type="hidden" class="" name="adrId"
					value="${adrRecord.adrId }"> <input type="hidden" class=""
					name="teamId" value="${adrRecord.teamId }">
				<div class="mb-12" style="padding: 10px;">
					<label for="title" class="form-label">Title</label> <input
						type="text" class="form-control" id="title" name="title"
						value="${adrRecord.title }">
				</div>
				<div class="mb-12" style="padding: 10px;">
					<label for="progressStatus" class="form-label">Pogress
						Status</label>
					<div class="input-group">
						<select class="form-select form-select-sm" id="progressStatus"
							name="progressStatus" aria-label="Default  select example">
							<c:forEach items="${adrRecordProgressStatusList }"
								var="adrRecordProgressStatus">
								<option value="${adrRecordProgressStatus }"
									<c:if test="${adrRecordProgressStatus.name == adrRecord.progressStatus.name }">selected</c:if>>${adrRecordProgressStatus.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="mb-12" style="padding: 10px;">
					<label for="context" class="form-label">Context</label>
					<textarea class="form-control" id="context" name="context" rows="8">${adrRecord.context }</textarea>
				</div>
				<div class="mb-12" style="padding: 10px;">
					<label for="decision" class="form-label">Decision</label>
					<textarea class="form-control" id="decision" name="decision"
						rows="8">${adrRecord.decision }</textarea>
				</div>
				<div class="mb-12" style="padding: 10px;">
					<label for="rationale" class="form-label">Rationale</label>
					<textarea class="form-control" id="rationale" name="rationale"
						rows="8">${adrRecord.rationale }</textarea>
				</div>
				<div class="mb-12" style="padding: 10px;">
					<label for="consequences" class="form-label">Consequences</label>
					<textarea class="form-control" id="consequences"
						name="consequences" rows="8">${adrRecord.consequences }</textarea>
				</div>
				<div class="mb-10" style="padding: 10px;">
					<label for="decisionMember" class="form-label">Decision
						member</label>
					<div class="input-group">
						<select class="form-select form-select-sm" id="decisionMember">
							<c:forEach items="${memberList }" var="member">
								<option id="${member.memberId }" value="${member.memberId }">${member.name }</option>
							</c:forEach>
						</select>
						<button class="btn btn-outline-secondary" type="button"
							onclick="addMember()">+</button>
					</div>
				</div>
				<div class="mb-10" style="padding: 10px;" id="decisionMemberList">

				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</form>
	</div>
	<script>
		$(function() {
			
			<c:if test="${adrRecord.decisionMemberIdList!= null}">
				let decisionMemberIdList = ${adrRecord.decisionMemberIdList};
			</c:if>
			<c:if test="${adrRecord.decisionMemberIdList == null}">
				let decisionMemberIdLis = [];
			</c:if>
			
			if (decisionMemberIdList) {
				$.each(decisionMemberIdList,
						function(index, memberId) {
							let name = $('#decisionMember')
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
			let name = $('#decisionMember option:selected').text();
			let memberId = $('#decisionMember option:selected').val();
			if($('#'+memberId+'-c').length){
				return;
			}
			addMemberAppend(memberId, name);
		}
		function addMemberAppend(memberId, name) {
			let appendHtml = '<input type="checkbox" name="decisionMemberIdList" value="'+memberId+'" class="btn-check" id="'+memberId+'-c" checked autocomplete="off">'
					+ '<label class="btn btn-outline-secondary btn-sm" id="'
					+ memberId
					+ '-l" for="btn-check-3-outlined" onclick="removeMember('
					+ memberId + ')">' + name + '</label> '
			$('#decisionMemberList').append(appendHtml);
		}
		function removeMember(memberId) {
			$('#' + memberId + '-c').remove();
			$('#' + memberId + '-l').remove();
		}
	</script>
</body>
