<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="EIoTC"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'eiot.ico')}" type="image/x-icon">
		<!-- <link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">  -->
  		
  		<link href='http://fonts.googleapis.com/css?family=Merriweather' rel='stylesheet' type='text/css'>
  		    
  		<asset:stylesheet src="bootstrap.css"/>
  		<asset:stylesheet src="bootstrap-theme.css"/>
		
		<asset:stylesheet src="portal.css"/>
		
		<asset:javascript src="bootstrap.js"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
      
      <!-- font -->
	  <g:layoutHead/>

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
		<!-- Menu -->
		<div id="page-body" class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand">Easy IoT Connect</a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
					   
						<li class="${params.action=='index'?'active':''}">
						   <a href="${createLink(controller:'portal', action: 'index')}">Home</a>
						</li>
						
						<li class="${params.action=='howItWorks'?'active':''}">
						   <a href="${createLink(controller:'portal', action: 'howItWorks')}">How works</a>
						</li>
						
						<li class="${params.action=='contact'?'active':''}">
						   <a href="${createLink(controller:'portal', action: 'contact')}">Contact</a>
					   </li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
                  <li>
                     <a href="${createLink(controller:'dashboard', action: 'index')}">Login</a>
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
				        	<strong>${flash.message}</strong> 
				      </div>
				</g:if>
				
				<!-- Container -->
				<g:layoutBody/>
			
			</div>
		</div>
		
		<!-- Footer -->
		<div class="footer text-center text-primary" role="contentinfo">
			© Copyright 2015, EasyIoTConnect. All rights reserved.
		</div>
		
		<!-- Spinner -->
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
	</body>
</html>
