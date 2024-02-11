<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<div class="card mb-12" style="opacity: 0.9; overflow-x: auto;">
		<div class="card-body">
			<h3 class="card-title">ADR List</h3>
			<a class="btn btn-outline-secondary" href="/admin/adrCreate?teamId=${param.teamId }">ADD</a>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">ADR ID</th>
						<th scope="col">Title</th>
						<th scope="col">Progress Status</th>
						<th scope="col">Edit Name</th>
						<th scope="col">Create Time</th>
						<th scope="col">Update Time</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${adrRecordItemListItemList }" var="adrRecordItemListItem">
						<tr>
							<th scope="row">${adrRecordItemListItem.adrId }</th>
							<td><a href="/admin/adrContent/${adrRecordItemListItem.adrId }">${adrRecordItemListItem.title }</a></td>
							<td>
							<c:if test="${adrRecordItemListItem.progressStatus != null }"></c:if>
								${adrRecordItemListItem.progressStatus.name }
							</td>
							<td>${adrRecordItemListItem.editorMemberName }</td>
							<td>${adrRecordItemListItem.createTime }</td>
							<td>${adrRecordItemListItem.updateTime }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>