<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="bootstrapIncludes.jsp" />
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<jsp:include page="commonHeader.jsp" />
	</div>
	<div>
		<div class="container">
			<div class="card mt-4">
				<div class="card-header  text-center bg-info text-light">User
					Signup</div>
				<div class="card-body">
					<form action="signup" method="post">
						<div class="form-row">
						    <div class="form-group col-md-6">
						      <label for="name">Name</label>
						      <input type="text" name="name" class="form-control" id="name" placeholder="Name">
						    </div>
						    <div class="form-group col-md-6">
						      <label for="username">Username</label>
						      <input type="text" name="username" class="form-control" id="username" placeholder="Username">
						    </div>
						</div>
						<div class="form-row">
						    <div class="form-group col-md-6">
						      <label for="password">Password</label>
						      <input type="password" name="password" class="form-control" id="password" placeholder="Password">
						    </div>
						    <div class="form-group col-md-4">
						      <label for="phone">Phone Number</label>
						      <input type="text" name="phone" class="form-control" id="phone" placeholder="Phone Number">
						    </div>
						    <div class="form-group col-md-2">
						      <label for="age">Age</label>
						      <input type="number" name="age" class="form-control" id="age" placeholder="Age">
						    </div>
						</div>
						<div class="form-row">
						    <div class="form-group col-md-6">
						      <label for="email">Email</label>
						      <input type="email" name="email" class="form-control" id="email" placeholder="Email">
						    </div>
						    <div class="form-group col-md-6">
						      <label for="city">City</label>
						      <input type="text" name="city" class="form-control" id="city" placeholder="City">
						    </div>
						</div>
						<div class="form-group">
						    <label for="address">Address</label>
						    <input type="text" class="form-control" name="address" id="address" placeholder="Address">
						</div>
						<!-- Name : <input type="text" name="name"><br> 
						Username :<input type="text" name="username"><br> 
						Password : <input type="text" name="password"><br> 
						Age : <input type="number" name="age"><br> 
						Phone : <input type="text" name="phone"><br> 
						Email : <input type="text" name="email"><br> 
						Address : <input type="text" name="address"><br> 
						City : <input type="text" name="city"><br>  -->
						<input type="submit" class="btn btn-info" value="Signup">
					</form>
					<div class="mt-1 text-center">
						<small>Already a user? <a href="index.jsp">Login here</a></small>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Already a user?
	<a href="index.jsp">Login here</a> -->
</body>
</html>