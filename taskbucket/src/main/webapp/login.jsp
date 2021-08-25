<!DOCTYPE html>
<%@page import="com.taskBucket.helpers.AlertMessage"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>

	<%
	AlertMessage alertContent = (AlertMessage) session.getAttribute("alertMessage");
	if (alertContent != null) {
	%>
	<div class="alert <%=alertContent.getCssClass()%>" role="alert">
		<p>
			<%=alertContent.getMessage()%></p>
	</div>
	<%
	session.removeAttribute("alertMessage");
	}
	%>


	<div
		class=" mt-4 container p-3  card  d-flex align-items-center justify-content-center"
		style="width: 30rem;">
		<h5 class="mb-5 bg-warning p-3">Task Bucket Login</h5>
		<form action="LoginServlet" method='post' style="width: 100%;">
			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Email
					address</label> <input required type="email" name='email'
					class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp">
			</div>
			<div class="mb-3">
				<label for="exampleInputPassword1" class="form-label">Password</label>
				<input required type="password" name='password' class="form-control"
					id="exampleInputPassword1">
			</div>

			<button type="submit" class="btn btn-primary">Login</button>
		</form>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>