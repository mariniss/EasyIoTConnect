<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <title><g:layoutTitle default="Easy IoT Connect"/></title>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
      <link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
      <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
      <asset:stylesheet src="devel.css"/>
      <asset:javascript src="devel.js"/>
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
      <div id="grailsLogo" role="banner"><a href=#"><asset:image src="grails_logo.png" alt="Grails"/></a></div>
      <g:layoutBody/>
      <div class="footer" role="contentinfo"></div>
      <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
   </body>
</html>
