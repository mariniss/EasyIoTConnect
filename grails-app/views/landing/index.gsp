<!DOCTYPE html>
<html lang="en">

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>Easy IoT Connect</title>

	<!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
	<asset:stylesheet src="bootstrap.css"/>

	<!-- Custom CSS -->
	<asset:stylesheet src="freelancer.css"/>
	<asset:stylesheet src="landing.css"/>

	<!-- Custom Fonts -->
	<asset:stylesheet src="font-awesome.css"/>
	<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
	<link href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

	<asset:javascript src="sweet-alert.min.js"/>
	<asset:stylesheet src="sweet-alert.css" />
</head>

<body id="page-top" class="index">

<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top header-navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div>
				<asset:image src="eiotc-ico.png" />
				<a class="navbar-brand" href="#page-top">Easy IoT Connect</a>
			</div>

		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="hidden">
					<a href="#page-top"></a>
				</li>
				<li class="page-scroll">
					<a href="#howWorks">How works</a>
				</li>
				<li class="page-scroll">
					<a href="#start">Start</a>
				</li>
				<li class="page-scroll">
					<a href="#contact" id="btnContactMe">Contact</a>
				</li>
				<li class="page-scroll">
					<div class="login-menu">
						<a href="#loginModal" class="btn btn-xs" id="menuLoginButton"  data-toggle="modal">
							<i class="fa fa-user"></i> Login
						</a>
					</div>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>

<!-- Header -->
<header>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="intro-text">
					<span class="name">Easy IoT Connect</span>
					<hr class="star-light cloud">
					<span class="skills">Connect your home to Internet</span>
				</div>
			</div>

			<div class="col-md-12">
				<div class="col-md-4">
					<asset:image src="sweet-home.png"/>
				</div>

				<div class="col-md-4">
					<br />
					<asset:image src="jack-l.png" />
				</div>

				<div class="col-md-4">
					<asset:image src="Internet.png"/>
				</div>
			</div>

			<div class="col-lg-12">
				<p>With this service you can connect your raspberry and manage it from the web by your computer, tablet or smartphone</p>
			</div>

			<div class="col-lg-12">
				<h4>no programming skills are necessary!</h4>
			</div>
		</div>
	</div>
</header>

<!-- How works Section -->
<section id="howWorks">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>How works</h2>
				<hr class="star-primary star">
			</div>
		</div>

		<div class="col-lg-4">
			<p>Get your Raspberry Pi and connect it to the internet by your home network</p>
		</div>
		<div class="col-lg-4">
			<p>Sign-Up on EIoTC, download the client and install on your Raspberry Pi</p>
		</div>
		<div class="col-lg-4">
			<p>Go to the EIoTC dashboard, give some labels and that's it</p>
		</div>

		<div class="row text-center">
			<a href="http://www.raspberrypi.org/">
				<asset:image src="raspberry_pi.jpg" />
			</a>
		</div>

		<div class="row text-center">
			<a href="#noRaspberryModal" class="portfolio-link" data-toggle="modal">I don't have a Raspberry</a>
		</div>

	</div>
</section>

<!-- Start Section -->
<section class="success" id="start">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Start</h2>
				<hr class="star-light world">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-4 col-lg-offset-2">
				<p>The service is completely free! You can connect 3 Raspberry Pi without costs, forever</p>
			</div>

			<div class="col-lg-4">
				<p>For the registration we need only your name, email and country</p>
			</div>

			<div class="col-lg-8 col-lg-offset-2 text-center">
				<a href="#singupModal" class="btn btn-lg btn-outline" data-toggle="modal">
					<i class="fa fa-sign-in"></i> Sign-Up
				</a>
			</div>

			<div class="col-lg-8 col-lg-offset-2 text-center">
				<br />
				<p>Or</p>
			</div>

			<div class="col-lg-8 col-lg-offset-2 text-center">
				<a href="#loginModal" class="btn btn-lg btn-outline" id="loginButton"  data-toggle="modal">
					<i class="fa fa-user"></i> Login
				</a>
			</div>

		</div>
	</div>
</section>

<!-- Contact Section -->
<section id="contact">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Contact Me</h2>
				<hr class="star-primary home">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<g:form method="POST" action="askQuestion"
					  	id='askQuestion'  autocomplete='off' class="form-horizontal" role="form" >

					<div class="row control-group">
						<div class="form-group col-xs-12 floating-label-form-group controls">
							<label>Name</label>
							<input type="text" class="form-control" placeholder="Name" id="name" name="name" required data-validation-required-message="Please enter your name.">
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<div class="row control-group">
						<div class="form-group col-xs-12 floating-label-form-group controls">
							<label>Email Address</label>
							<input type="email" class="form-control" placeholder="Email Address" id="sender" name="sender" required data-validation-required-message="Please enter your email address.">
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<div class="row control-group">
						<div class="form-group col-xs-12 floating-label-form-group controls">
							<label>Message</label>
							<textarea rows="5" class="form-control" placeholder="Message" id="message" name="message" required data-validation-required-message="Please enter a message."></textarea>
							<p class="help-block text-danger"></p>
						</div>
					</div>

					<br>
					<!-- <div id="success"></div> -->
					<div class="row">
						<div class="form-group col-xs-12">
							<g:submitButton name="send" value="Send"  class="btn btn-success btn-lg"/>
						</div>
					</div>
				</g:form>
			</div>
		</div>
	</div>
</section>


<!-- Footer -->
<footer class="text-center">
	<div class="footer-above">
		<div class="container">
			<div class="row">

				<div class="footer-col col-md-4">
					<h3>The basic idea</h3>
					<p>EIoTC is born and grows with a simple idea: help people to improve their quality life with the low cost technology</p>
				</div>

				<div class="footer-col col-md-4">
					<h3>Around the Web</h3>
					<ul class="list-inline">
						<li>
							<a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-facebook"></i></a>
						</li>
						<li>
							<a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-google-plus"></i></a>
						</li>
						<li>
							<a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-twitter"></i></a>
						</li>
					</ul>
				</div>

				<div class="footer-col col-md-4">
					<h3>Work in progress</h3>
					<p>EIoTC is always evolving, if your are interested to contribute don't hesitate to contact us!</p>
				</div>
			</div>
		</div>
	</div>
	<div class="footer-below">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					Copyright &copy;, EasyIoTConnect 2015.
				</div>
			</div>
		</div>
	</div>
</footer>

<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
<div class="scroll-top page-scroll visible-xs visble-sm">
	<a class="btn btn-primary" href="#page-top">
		<i class="fa fa-chevron-up"></i>
	</a>
</div>


<!-- Modals -->

<!-- Sign-Ups -->
<div class="portfolio-modal modal fade" id="singupModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-content">
		<div class="close-modal" data-dismiss="modal">
			<div class="lr">
				<div class="rl">
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
					<h2>Sign-Up</h2>
					<hr class="star-primary sign-in">

					<g:form method="POST" action="singUp"
							id='singUp'  autocomplete='off' class="form-horizontal" role="form" >

						<div class="row control-group">
							<div class="form-group col-xs-12 floating-label-form-group controls">
								<label>Name</label>
								<input type="text" class="form-control" placeholder="Your complete name" id="fullname" name="fullname" required data-validation-required-message="Please enter your name.">
								<p class="help-block text-danger"></p>
							</div>
						</div>

						<div class="row control-group">
							<div class="form-group col-xs-12 floating-label-form-group controls">
								<label>Country</label>
								<input type="text" class="form-control" placeholder="Country where yuo live" id="country" name="country" required data-validation-required-message="Please enter your country.">
								<p class="help-block text-danger"></p>
							</div>
						</div>

						<div class="row control-group">
							<div class="form-group col-xs-12 floating-label-form-group controls">
								<label>Email</label>
								<input type="email" class="form-control" placeholder="Email Address" id="email" name="email" required data-validation-required-message="Please enter your email address.">
								<p class="help-block text-danger"></p>
							</div>
						</div>

						<div class="row control-group">
							<div class="form-group col-xs-12 floating-label-form-group controls">
								<label>Password</label>
								<input type="password" class="form-control" placeholder="Insert a strong password" id="password" name="password" required data-validation-required-message="Please enter a password."></textarea>
								<p class="help-block text-danger"></p>
							</div>
						</div>

						<br>
						<!-- <div id="success"></div> -->
						<div class="row">
							<div class="form-group col-xs-12">
								<g:submitButton name="singUp" value="Sign-Up"  class="btn btn-success btn-lg"/>
							</div>
						</div>
					</g:form>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- Login -->
<div class="portfolio-modal modal fade" id="loginModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-content">
		<div class="close-modal" data-dismiss="modal">
			<div class="lr">
				<div class="rl">
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
					<h2>Login</h2>
					<hr class="star-primary user">

					<form method="POST" action="${createLink(uri: '/j_spring_security_check')}"
						  id='loginForm'  autocomplete='off' class="form-horizontal" role="form">

						<div class="row control-group">
							<div class="form-group col-xs-12 floating-label-form-group controls">
								<label>Email</label>
								<input type="email" class="form-control" placeholder="Email Address" name='j_username' id='j_username' required data-validation-required-message="Please enter your email address." />
								<p class="help-block text-danger"></p>
							</div>
						</div>

						<div class="row control-group">
							<div class="form-group col-xs-12 floating-label-form-group controls">
								<label>Password</label>
								<input type="password" class="form-control" placeholder="Password" name='j_password' id='j_password' required data-validation-required-message="Please enter your password." />
								<p class="help-block text-danger"></p>
							</div>
						</div>

						<!--
						<div class="row control-group">
							<div class="form-group col-xs-12 floating-label-form-group controls">
								<label>Remember me</label>
								<input type="checkbox" class="form-control" placeholder="Remember me" name='_spring_security_remember_me' id='remember_me' />
								<p class="help-block text-danger"></p>
							</div>
						</div>
						-->

						<br />
						<!-- <div id="success"></div> -->
						<div class="row">
							<div class="form-group col-xs-12">
								<button type="submit" class="btn btn-success btn-lg">Login</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- No Raspbbery -->
<div class="portfolio-modal modal fade" id="noRaspberryModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-content">
		<div class="close-modal" data-dismiss="modal">
			<div class="lr">
				<div class="rl">
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
					<div class="modal-body">
						<h2>I'don't have a Raspberry Pi</h2>
						<hr class="star-primary question">
						<p>You have three options to use EIoTC</p>
						<br /><br />
						<dl class="dl-horizontal" style="align-content: center">
							<dt>Contact us</dt>
							<dd>Will be a pleasure extend EIoTC to support other devices</dd>
							<br />

							<dt>Do it  yourself</dt>
							<dd>EIoTC is OpenSource, got to our <a href="https://github.com/mariniss">GitHub</a>, you will found the base libraries to develop or extends the client</dd>
							<br />

							<dt>Buy a Raspberry Pi</dt>
							<dd>It is a great device for home automation</dd>
							<br />
						</dl>
						<a href="#" type="button" class="btn btn-success btn-mg" data-dismiss="modal"><i class="fa fa-check"></i>Ok</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- jQuery -->
<asset:javascript src="jquery-1.11.1.js"/>

<!-- Bootstrap Core JavaScript -->
<asset:javascript src="bootstrap.min.js"/>

<!-- Plugin JavaScript -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
<asset:javascript src="classie.js"/>
<asset:javascript src="cbpAnimatedHeader.js"/>

<!-- Custom Theme JavaScript -->
<asset:javascript src="freelancer.js"/>

<script>
	if (location.hash == '#loginModal') {
		$('#loginButton').click();
	} else if (location.hash == '#askQuestion') {
		window.location.href = '#contact'
	}
</script>

<!-- Flash message -->
<g:if test="${flash.alert}">
	<script>
		$(function(){
			swal("${flash.alert.title}", "${flash.alert.message}", "${flash.alert.type}");
		});
	</script>
</g:if>

</body>

</html>
