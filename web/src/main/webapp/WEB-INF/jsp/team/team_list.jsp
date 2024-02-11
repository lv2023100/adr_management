<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<div class="card mb-12" style="opacity: 0.9; overflow-x: auto;">
		<div class="card-body">
			<h3 class="card-title">Team List</h3>
			<a class="btn btn-outline-secondary" href="/admin/teamCreate">ADD</a>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Team ID</th>
						<th scope="col">Name</th>
						<th scope="col">Create Time</th>
						<th scope="col">Update Time</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${teamListItemList }" var="teamListItem">
						<tr>
							<th>${teamListItem.teamId }</th>
							<td scope="row"><a href="/admin/teamUpdate/${teamListItem.teamId }">${teamListItem.name }</a></td>
							<td>${teamListItem.createTime }</td>
							<td>${teamListItem.updateTime }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>