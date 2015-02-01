<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="portal" />
<title>Sing-Up</title>
</head>

<body>
	<div class="container-fluid">
		<br />
		<h2 class="big-message">Your credential please</h2>
		<br /> <br />

		<div class="panel panel-default">
			<div class="panel-body">

				<form method="POST" action="${createLink(uri: '/j_spring_security_check')}"
				   id='loginForm'  autocomplete='off' class="form-horizontal" role="form">

					<legend class="text-center">Login</legend>

					<div class="form-group">
						<label class="col-sm-2 control-label" for="username">Email</label>
						<div class="col-sm-10">
							<input name='j_username' id='username' type="email" class="form-control">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label" for="password">Password</label>
						<div class="col-sm-10">
							<input name='j_password' id='password' type="password" class="form-control">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label" for="remember_me">Remember me</label>
					  	<div class="col-sm-10">
							<input name='_spring_security_remember_me' id='remember_me' type='checkbox' class="form-control" />
					  	</div>
					</div>
					
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary btn-lg">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
