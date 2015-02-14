<!DOCTYPE html>
<html>
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

	<g:if env="development">
		<asset:stylesheet src="error.css" />
	</g:if>
</head>

<body>

	<header>
		<div>
			<div class="row">
				<div class="col-lg-12">
					<br />
					<asset:image src="jack-l.png" />
				</div>

				<div class="col-lg-12">
					<div class="intro-text">
						<span class="name">Ops!</span>
						<hr class="star-light exclamation-triangle">
						<span class="skills">An error has occurred</span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<section id="howWorks">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h4>don't worry, we are already working on it</h4>
			</div>

			<div class="col-lg-12 text-center">
				<p>Don't hesitate to
					<a href="${g.createLink(controller: 'landing', action: 'index')}#askQuestion">contact me</a>
					for any questions</p>
			</div>
		</div>
	</section>


	<!-- Footer -->
	<footer class="navbar-fixed-bottom">
		<div class="footer-below text-center">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						Copyright &copy;, EasyIoTConnect 2015.
					</div>
				</div>
			</div>
		</div>
	</footer>


	<g:if env="development">
		<g:renderException exception="${exception}" />
	</g:if>
</body>

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

</html>
