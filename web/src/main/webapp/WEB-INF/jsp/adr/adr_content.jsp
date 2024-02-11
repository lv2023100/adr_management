<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<body>
	<div class="card mb-12" style="opacity: 0.9; margin-bottom: 20px;">
		<div class="card-body row">
			<c:if test="${!isHistorical}">
			<div class="col-6">
				
					<a class="btn btn-outline-secondary"
					href="/admin/adr?teamId=${adrRecordForm.teamId }">Previous</a>
			</div>

			<div class="col-6 d-flex flex-row-reverse">
				<a class="btn btn-primary justify-content-center"
					style="margin-left: 12px"
					href="/admin/adrUpdate/${adrRecordForm.adrId }">Edit</a>
				<button type="button" class="btn btn-outline-primary"
					data-bs-toggle="modal" data-bs-target="#exampleModal">Historical</button>

			</div>
			</c:if>
			<c:if test="${isHistorical}">
				<div class="col-12 d-flex flex-row-reverse">
					<button type="button" onclick="window.close();" class="btn btn-outline-secondary">Close</button>
				</div>
			</c:if>
		</div>
		<div class="card-body">
			<h3 class="card-title">${adrRecordForm.title }</h3>
			<div class="mb-3 row">
				<label for="staticEmail" class="col-sm-2 col-form-label">Decision
					Member</label>
				<div class="col-sm-10">
					<c:forEach items="${decisionMemberNameList }"
						var="decisionMemberName">
						<button type="button" class="btn btn-secondary btn-sm">${decisionMemberName }</button>
					</c:forEach>
				</div>
			</div>
			<div class="mb-3 row">
				<label for="progressStatus" class="col-sm-2 col-form-label">Progress
					Status</label>
				<div class="col-sm-10">
					<c:if test="${adrRecordForm.progressStatus != null }">
						<input type="text" readonly class="form-control-plaintext"
							id="progressStatus" value="${adrRecordForm.progressStatus.name }">
					</c:if>

				</div>
			</div>
			<div class="mb-3 row">
				<label for="createTime" class="col-sm-2 col-form-label">Create
					Time</label>
				<div class="col-sm-10">
					<input type="text" readonly class="form-control-plaintext"
						id="createTime" value="${fn:replace(adrRecordForm.createTime ,  'T', ' ')}">
				</div>
			</div>
			<div class="mb-3 row">
				<label for="updateTime" class="col-sm-2 col-form-label">Update
					Time</label>
				<div class="col-sm-10">
					<input type="text" readonly class="form-control-plaintext"
						id="updateTime" value="${fn:replace(adrRecordForm.updateTime ,  'T', ' ')}">
				</div>
			</div>
		</div>
	</div>
	<div class="card mb-12" style="opacity: 0.9; margin-bottom: 20px;">
		<div class="card-body">
			<h4 class="card-title">Context</h4>
			<pre style="overflow-y: hidden; overflow-x: hidden;" class="card-text">${adrRecordForm.context }</pre>
		</div>
	</div>
	<div class="card mb-12" style="opacity: 0.9; margin-bottom: 20px;">
		<div class="card-body">
			<h4 class="card-title">Decision</h4>
			<pre style="overflow-y: hidden; overflow-x: hidden;" class="card-text">${adrRecordForm.decision }</pre>
		</div>
	</div>
	<div class="card mb-12" style="opacity: 0.9; margin-bottom: 20px;">
		<div class="card-body">
			<h4 class="card-title">Rationale</h4>
			<pre style="overflow-y: hidden; overflow-x: hidden;" class="card-text">${adrRecordForm.rationale }</pre>
		</div>
	</div>
	<div class="card mb-12" style="opacity: 0.9; margin-bottom: 20px;">
		<div class="card-body">
			<h4 class="card-title">Consequences</h4>
			<pre style="overflow-y: hidden; overflow-x: hidden;" class="card-text">${adrRecordForm.consequences }</pre>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Historical edited records</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body" style="overflow-x: auto;">
					<table class="table">
				<thead>
					<tr>
						<th scope="col">Event Name</th>
						<th scope="col">Title</th>
						<th scope="col">progressStatus</th>
						<th scope="col">Create Time</th>
						<th scope="col">Update Time</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${adrRecordEventStoreItemList }" var="adrRecordEventStoreItem">
						<tr>
							<th scope="row">${adrRecordEventStoreItem.eventName }</th>
							<td><a href="/admin/adrContent/${adrRecordEventStoreItem.adrId }">${adrRecordEventStoreItem.title }</a></td>
							<td>
							<c:if test="${adrRecordEventStoreItem.progressStatus != null }"></c:if>
								${adrRecordEventStoreItem.progressStatus.name }
							</td>
							<td>${fn:replace(adrRecordEventStoreItem.createTime ,  'T', ' ')}</td>
							<td>${fn:replace(adrRecordEventStoreItem.updateTime ,  'T', ' ')}</td>
							<td>
								<form action="/admin/adrContent/historical"  method="get"  target="_blank">
									<input type="hidden" name="title" value="${adrRecordEventStoreItem.title }">
									<input type="hidden" name="progressStatus" value="${adrRecordEventStoreItem.progressStatus }">
									<input type="hidden" name="context" value="${adrRecordEventStoreItem.context }">
									<input type="hidden" name="decision" value="${adrRecordEventStoreItem.decision }">
									<input type="hidden" name="rationale" value="${adrRecordEventStoreItem.rationale }">
									<input type="hidden" name="consequences" value="${adrRecordEventStoreItem.consequences }">
									<input type="hidden" name="createTime" value="${adrRecordEventStoreItem.createTime}">
									<input type="hidden" name="updateTime" value="${adrRecordEventStoreItem.updateTime}">
									<c:forEach items="${ adrRecordEventStoreItem.decisionMemberIdList}" var="decisionMemberId" varStatus="status">
										<input type="hidden" name="decisionMemberIdList[${status.index }]" value="${decisionMemberId }">
									</c:forEach>
									<button type="submit" class="btn btn-secondary btn-sm">Browse</button>
								</form>
								
							
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-outline-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</body>
