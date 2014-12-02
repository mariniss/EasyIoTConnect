<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dashboard" />
		<title>Create Device</title>
	</head>
	
	<body>
		<div class="container">
			
			<div class="panel panel-default">
         <div class="panel-body">
            <g:form controller="dashboard" action="updatePersonal" method='POST'
               class="form-horizontal" role="form">
               <input type="hidden" name="id" value="${user.id}" />

               <legend class="text-center">Change your personal information</legend>
               
               <div class="form-group">
                  <label class="col-sm-2 control-label" for="email">Email</label>
                  <div class="col-sm-10">
                     <input id="email" name="email" type="email" readonly="readonly"
                           class="form-control"  value="${user.email}">
                  </div>
               </div>
               
               <div class="form-group">
                  <label class="col-sm-2 control-label" for="password">Password</label>
                  <div class="col-sm-5">
                     <input id="password" name="password" type="password" 
                            class="form-control" placeholder="New password">
                  </div>
                  <div class="col-sm-5">
                     <input id="repeatPassword" name="repeatPassword" type="password" 
                            class="form-control" placeholder="Repeat password">
                  </div>
               </div>
               
               <div class="form-group">
                  <label class="col-sm-2 control-label" for="name">Name</label>
                  <div class="col-sm-10">
                     <input id="name" name="name" type="text" 
                            class="form-control"  value="${user.name}">
                  </div>
               </div>
               
               <div class="form-group">
                  <label class="col-sm-2 control-label" for="country">Country</label>
                  <div class="col-sm-10">
                     <input id="country" name="country" type="text" readonly="readonly"
                            class="form-control"  value="${user.state}">
                  </div>
               </div>

               <div class="col-sm-offset-2 col-sm-10">
                  <button type="submit" class="btn btn-primary btn-lg">Update</button>
                  <a href="${createLink(controller: 'dashboard', action: 'index')}"
                     id="cancel" name="cancel" class="btn btn-danger btn-lg">Cancel</a>
               </div>
            </g:form>

         </div>
		   </div>
		 </div>
	</body>
</html>
