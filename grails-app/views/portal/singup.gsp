<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="portal" />
		<title>Sing-Up</title>
	</head>

	<body>
		<div class="container">
			<div class="starter-template">
				<div class="jumbotron">
					<form class="form-horizontal">
						<fieldset>
	
							<!-- Form Name -->
							<legend>Sing-Up</legend>
	
							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="name">Name</label>
								<div class="controls">
									<input id="name" name="name" type="text"
										placeholder="placeholder" class="input-xlarge" required="">
									<p class="help-block">Your first name</p>
								</div>
							</div>
	
							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="lastName">Last Name</label>
								<div class="controls">
									<input id="lastName" name="lastName" type="text"
										placeholder="placeholder" class="input-xlarge" required="">
									<p class="help-block">Your last name</p>
								</div>
							</div>
	
							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="borndate">Bird Date</label>
								<div class="controls">
									<input id="borndate" name="borndate" type="text"
										placeholder="placeholder" class="input-xlarge">
									<p class="help-block">Your bird date</p>
								</div>
							</div>
	
							<!-- Multiple Radios -->
							<div class="control-group">
								<label class="control-label" for="gender">Gender</label>
								<div class="controls">
									<label class="radio" for="gender-0"> <input type="radio"
										name="gender" id="gender-0" value="Male" checked="checked">
										Male
									</label> <label class="radio" for="gender-1"> <input
										type="radio" name="gender" id="gender-1" value="Female">
										Female
									</label>
								</div>
							</div>
	
							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="adress">Adress</label>
								<div class="controls">
									<input id="adress" name="adress" type="text"
										placeholder="placeholder" class="input-xlarge">
									<p class="help-block">Your Adress</p>
								</div>
							</div>
	
							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="phone">Phone</label>
								<div class="controls">
									<input id="phone" name="phone" type="text"
										placeholder="placeholder" class="input-xlarge" required="">
									<p class="help-block">Your phone or mobile</p>
								</div>
							</div>
	
							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="email">Email</label>
								<div class="controls">
									<input id="email" name="email" type="text"
										placeholder="placeholder" class="input-xlarge" required="">
									<p class="help-block">Your Email</p>
								</div>
							</div>
	
							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="username">Username</label>
								<div class="controls">
									<input id="username" name="username" type="text"
										placeholder="placeholder" class="input-xlarge" required="">
									<p class="help-block">Your Easy IoT Connect username</p>
								</div>
							</div>
	
							<!-- Password input-->
							<div class="control-group">
								<label class="control-label" for="password">Password</label>
								<div class="controls">
									<input id="password" name="password" type="password"
										placeholder="placeholder" class="input-xlarge" required="">
									<p class="help-block">Your Easy IoT Connect password</p>
								</div>
							</div>
	
							<!-- Button (Double) -->
							<div class="control-group">
								<label class="control-label" for="singup"></label>
								<div class="controls">
									<button id="singup" name="singup" class="btn btn-success">Sing-Up</button>
									<button id="cancel" name="cancel" class="btn btn-danger">Cancel</button>
								</div>
							</div>
	
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
