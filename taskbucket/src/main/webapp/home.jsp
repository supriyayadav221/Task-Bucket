<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="com.taskBucket.entities.Todo"%>
<%@page import="java.util.List"%>
<%@page import="com.taskBucket.entities.User"%>
<%@page import="com.taskBucket.helpers.myConnection"%>
<%@page import="com.taskBucket.daos.TodoDao"%>
<%@page import="com.taskBucket.helpers.AlertMessage"%>


<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>Document</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<style>
.custom-div ul li {
	display: flex;
	justify-content: space-between;
}
</style>
</head>
<body>

	<%
	User user = (User) session.getAttribute("currentUser");
	if (user == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	%>


	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Task Bucket</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Home</a></li>
				</ul>
				<ul class="navbar-nav ml-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="LogoutServlet">Logout</a></li>
				</ul>
			</div>

		</div>
	</nav>
	<div
		class="custom-div mt-4 container p-3  card  d-flex align-items-center justify-content-center">
		<h5 class="mb-2 bg-warning p-3">Task Bucket Dashboard</h5>
		<%
		AlertMessage alertContent = (AlertMessage) session.getAttribute("alertMessage");
		if (alertContent != null) {
		%>
		<div
			class="alert text-white alert-dismissible fade show <%=alertContent.getCssClass()%>"
			role="alert">
			<p>
				<%=alertContent.getMessage()%></p>
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>

		</div>
		<%
		session.removeAttribute("alertMessage");
		}
		%>
		<%
		TodoDao todoDao = new TodoDao(myConnection.getConnection());

		ArrayList<Todo> list = todoDao.getUserTodos(user.getId());
		%>


		<div class="new m-3 p-4 bg-warning"
			style="width: 100%; text-align: center;">
			<form action="SaveTodo" method='post'>
				<div class="m-3">
					<label class="form-label">Add new task</label> <input type="text"
						name='title' required class="form-control">
				</div>
				<button class="btn btn-primary ">Add todo</button>
			</form>
		</div>

		<ul class="list-group" style="width: 100%;">

			<%
			if (list.size() > 0) {
				for (Todo todo : list) {
			%>
			<li class="list-group-item"><%=todo.getTitle()%> <span> <i
					data-target="#dt<%=todo.getTodo_id()%>" class="fas fa-edit"
					type="button" class="btn btn-primary" data-bs-toggle="modal"
					data-bs-target="#dt<%=todo.getTodo_id()%>"> &nbsp; </i> <a
					href="DeleteServlet?q=<%=todo.getTodo_id()%>"> <i
						class="fas fa-trash"></i>
				</a>
			</span></li>
			<div class="modal fade" id="dt<%=todo.getTodo_id()%>" tabindex="-1"
				aria-labelledby="dt<%=todo.getTodo_id()%>" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Update TODO</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<form action="UpdateTodoServlet" method='post'>
								<input class="form-control" value="<%=todo.getTitle()%>"
									name='title'> <input class="form-control"
									value="<%=todo.getTodo_id()%>" name='todo_id' type="hidden">
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-primary">Update
										changes</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			%>
			<h1>No todos found! Add now!</h1>
			<%
			}
			%>
		</ul>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>