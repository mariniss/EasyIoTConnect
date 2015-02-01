<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="portal" />
<title>Sing-Up</title>
</head>

<body>
	<div class="container-fluid">
		<br />
		<h2 class="big-message">Great! We need only few informations</h2>
		<br /> <br />

		<div class="panel panel-default">
			<div class="panel-body">

				<g:form controller="portal" action="singUpCheck" method='POST'
					class="form-horizontal" role="form">

					<legend class="text-center">Sing-Up</legend>

					<div class="form-group">
						<label class="col-sm-2 control-label" for="name">Name</label>
						<div class="col-sm-10">
							<input id="name" name="name" type="text"
								placeholder="Your complete name" class="form-control">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label" for="country">Country</label>
						<div class="col-sm-10">
							<input id="country" name="country" type="text"
								placeholder="Country where you live" class="form-control">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label" for="email">Email</label>
						<div class="col-sm-10">
							<input id="email" name="email" type="email"
								placeholder="Email to identify you" class="form-control">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label" for="password">Password</label>
						<div class="col-sm-10">
							<input id="password" name="password" type="password"
								placeholder="<div class="row control-group">
							<div class="form-group col-xs-12 floating-label-form-group controls">
								<label>Name</label>
								<input type="text" class="form-control" placeholder="Your complete nam3" id="fullname" name="fullname" required data-validation-required-message="Please enter your name.">
								<p class="help-block text-danger"></p>
							</div>
						</div>" class="form-control">
						</div>
					</div>
					
					
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary btn-lg">Sign in</button>
						<a href="${createLink(controller: 'portal', action: 'index')}"
                     id="cancel" name="cancel" class="btn btn-danger btn-lg">Cancel</a>
					</div>
				</g:form>
			</div>
		</div>
	</div>
</body>
</html>
