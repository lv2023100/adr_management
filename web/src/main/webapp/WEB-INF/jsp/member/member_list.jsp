<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<div class="card mb-12" style="opacity: 0.9; overflow-x: auto;">
		<div class="card-body">
			<h3 class="card-title">Member List</h3>
			<a class="btn btn-outline-secondary" href="/admin/memberCreate">ADD</a>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Member ID</th>
						<th scope="col">Name</th>
						<th scope="col">Create Time</th>
						<th scope="col">Update Time</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${memberListItemList }" var="memberListItem">
						<tr>
							<td>${memberListItem.memberId }</td>
							<td scope="row"><a href="/admin/memberUpdate/${memberListItem.memberId }">${memberListItem.name }</a></td>
							<td>${memberListItem.createTime }</td>
							<td>${memberListItem.updateTime }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>