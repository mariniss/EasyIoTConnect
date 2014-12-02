<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<title><g:layoutTitle default="EIoTC" /></title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="shortcut icon" href="${assetPath(src: 'eiot.ico')}"
	type="image/x-icon">

<asset:javascript src="jquery-1.11.1.js" />
<asset:javascript src="bootstrap.js" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<link href='http://fonts.googleapis.com/css?family=Merriweather'
	rel='stylesheet' type='text/css'>
<asset:stylesheet src="bootstrap.css" />
<asset:stylesheet src="bootstrap-theme.css" />
<asset:stylesheet src="dashboard.css" />

<g:layoutHead />
</head>
<body>
	<!-- Menu -->
	<div id="page-body"
		class="page-body navbar navbar-inverse navbar-fixed-top"
		role="navigation">
		<div class="container">
			<div class="navbar-header">
				<asset:image src="eiot.ico" style="float:left; margin-right: 10px;" />
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Dashboard</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">

					<li class="${params.action=='index'?'active':''}"><a
						href="${createLink(controller:'dashboard', action: 'index')}">Overview</a>
					</li>

					<li class="${params.action=='manage'?'active':''}"><a
						href="${createLink(controller:'dashboard', action: 'manage')}">Manage</a>
					</li>

					<li class="${params.action=='remote'?'active':''}"><a
						href="${createLink(controller:'dashboard', action: 'remote')}">Remote</a>
					</li>

               <li class="${params.action=='configure'?'active':''}"><a
                  href="${createLink(controller:'dashboard', action: 'configure')}">Configure</a>
               </li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="${params.action=='personal'?'active':''}"><a
						href="${createLink(controller:'dashboard', action: 'personal')}">Personal</a>
					</li>
					<li><a href="${createLink(uri:'/j_spring_security_logout')}">Logout</a>
					</li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<!-- Body -->
	<div class="starter-template">
		<div class="jumbotron">

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
	</div>

	<!-- Footer -->
	<div class="footer" role="contentinfo"></div>

	<!-- Spinner -->
	<div id="spinner" class="spinner" style="display: none;">
		<g:message code="spinner.alt" default="Loading&hellip;" />
	</div>
</body>
</html>
