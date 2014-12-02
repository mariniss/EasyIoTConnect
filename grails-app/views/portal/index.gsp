<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="portal" />
<title>Easy IoT Connect</title>
</head>

<body>
	<div class="container-fluid">
		<br />
		<h2 class="big-message">Welcome to Easy IoT Connect</h2>
		<br /> <br />

		<div class="panel panel-default">
			<div class="panel-body">
			   <div class="row text-center">
					<h2 class="text-uppercase">
						<strong>Connect your home to internet!</strong>
					</h2>
			   </div>
		    </div>
		</div>

		<div class="panel panel-default">
			<div class="panel-body">

				<div class="row text-center">
					<h2 class="lead">With this service you can connect your
						raspberry and manage it from the web, by your computer, tablet or
						smartphone</h2>
				</div>
				<br /> <br />

				<div class="row text-center">
					<div class="col-md-3">
						<div class="panel panel-success">
							<div class="panel-heading">
								<h3 class="panel-title">Free</h3>
							</div>
							<div class="panel-body text-left">
								<ul class="list-unstyled">
									<li>You can connect 3 raspberry without costs forever</li>
								</ul>
							</div>
						</div>
					</div>

<%--					<div class="col-md-3">--%>
<%--						<div class="panel panel-success">--%>
<%--							<div class="panel-heading">--%>
<%--								<h3 class="panel-title">Fast</h3>--%>
<%--							</div>--%>
<%--							<div class="panel-body text-left">--%>
<%--								<ul class="list-unstyled">--%>
<%--									<li>Fast response from raspberry</li>--%>
<%--									<li>Dedicated server on each continent</li>--%>
<%--								</ul>--%>
<%--							</div>--%>
<%--						</div>--%>
<%--					</div>--%>

					<div class="col-md-3">
						<div class="panel panel-success">
							<div class="panel-heading">
								<h3 class="panel-title">Simple</h3>
							</div>
							<div class="panel-body text-left">
								<ul class="list-unstyled">
									<li>Simple to configure</li>
									<li>Simple to use</li>
								</ul>
							</div>
						</div>
					</div>

					<div class="col-md-3">
						<div class="panel panel-success">
							<div class="panel-heading">
								<h3 class="panel-title">Secure</h3>
							</div>
							<div class="panel-body text-left">
								<ul class="list-unstyled">
									<li>Each user works on own area</li>
									<li>https connections</li>
								</ul>
							</div>
						</div>
					</div>
				</div>

				<br /> <br />
				<div class="text-center">
					<p>
						<a href="${createLink(controller: 'portal', action: 'singUp')}"
							class="btn btn-primary btn-lg" role="button">Sing-Up &raquo;</a>
					</p>
					<p>Or</p>
					<p>
					   <a href="${createLink(controller: 'dashboard', action: 'index')}"
							class="btn btn-info" role="button">Login</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

