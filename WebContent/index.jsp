<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="bootstrapIncludes.jsp" />
<title>MediCare</title>
<style type="text/css">
	.set-background{
		background-image: url("./static/index.jpg");
		background-repeat: no-repeat, repeat;
		background-size: cover;
	}
</style>
</head>
<body>
	<div>
		<jsp:include page="commonHeader.jsp" />
	</div>
	<jsp:include page="errorMessages.jsp" />
	<div>
		<div class="container  mt-4 shadow-lg p-4 mb-5 bg-white rounded">
			<div class="row">
				<div class="col-lg-8"></div>
				<div class="col-lg-4">
					<div class="card">
						<div class="card-header text-center bg-info text-light">User
							Login</div>
						<div class="card-body">
							<form action="login" method="post">
								<div class="form-group row">
									<label for="username" class="col-sm-4 col-form-label-sm">Username</label>
									<div class="col-sm-8">
										<input type="text" class="form-control form-control-sm"
											name="username" id="username" placeholder="Username">
									</div>
								</div>
								<div class="form-group row">
									<label for="password" class="col-sm-4 col-form-label-sm">Password</label>
									<div class="col-sm-8">
										<input type="password" class="form-control form-control-sm"
											name="password" id="password" placeholder="Password">
									</div>
								</div>
								<input class="btn btn-info btn-sm" type="submit" value="Login" />
							</form>
							<div class="mt-1 text-center">
								<small>Don't have an account? <a href="signup.jsp">Sign
										Up</a></small>
							</div>

						</div>
					</div>
					<div class="card mt-3">
						<div class="card-header text-center bg-info text-light">Doctor
							Login</div>
						<div class="card-body">
							<form action="doctorlogin" method="post">
								<div class="form-group row">
									<label for="Dusername" class="col-sm-4 col-form-label-sm">Username</label>
									<div class="col-sm-8">
										<input type="text" class="form-control form-control-sm"
											name="username" id="Dusername" placeholder="Username">
									</div>
								</div>
								<div class="form-group row">
									<label for="Dpassword" class="col-sm-4 col-form-label-sm">Password</label>
									<div class="col-sm-8">
										<input type="password" class="form-control form-control-sm"
											name="password" id="Dpassword" placeholder="Password">
									</div>
								</div>
								<input class="btn btn-info btn-sm" type="submit" value="Login">
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>



</body>
</html>