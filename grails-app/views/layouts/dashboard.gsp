<!DOCTYPE html>
<html lang="en">

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">

	<title><g:layoutTitle default="EIoTC" /></title>

	<!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
	<asset:stylesheet src="bootstrap.css"/>

	<!-- Custom CSS -->
	<asset:stylesheet src="freelancer.css"/>
	<asset:stylesheet src="landing.css"/>
	<asset:stylesheet src="dashboard.css"/>

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

	<g:layoutHead />

	<script>
		(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
			(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
				m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		})(window,document,'script','//www.google-analytics.com/analytics.js','ga');

		ga('create', 'UA-59646328-1', 'auto');
		ga('send', 'pageview');

	</script>
</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-fixed-top header-navigation navbar-shrink">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header page-scroll">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<div style="margin-right: 10px">
					<asset:image src="eiotc-ico.png"/>
					<a class="navbar-brand" href="#page-top"></a>
				</div>

			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-left">
					<li class="hidden">
						<a href="#page-top"></a>
					</li>
					<li>
						<a href="${createLink(controller:'dashboard', action: 'index')}">Dashboard</a>
					</li>

					<li>
						<a href="#createDevice" data-toggle="modal" class="btn-outline btn-navbar">Add device</a>
					</li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li>
						<a href="#personal" data-toggle="modal"  >Personal</a>
					</li>
					<li class="page-scroll">
						<div class="login-menu">
							<a href="${createLink(uri:'/j_spring_security_logout')}">
								<i class="fa fa-user"></i> Logout
							</a>
						</div>
					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<!-- Body -->
	<div id="page-top" class="index">
		<!-- Flash message -->
		<g:if test="${flash.message}">
			<div class="alert alert-danger" role="alert">
				<strong> ${flash.message}
				</strong>
			</div>
		</g:if>

		<!-- Container -->
		<g:layoutBody />
	</div>


	<!-- Footer -->
	<footer class="footer">
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

</body>
</html>
