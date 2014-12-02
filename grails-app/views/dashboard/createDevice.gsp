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
            <g:form controller="dashboard" action="saveDevice" method='POST'
               class="form-horizontal" role="form">

               <legend class="text-center">Create a new Device</legend>

               <div class="form-group">
                  <label class="col-sm-2 control-label" for="name">Name</label>
                  <div class="col-sm-10">
                     <input id="name" name="name" type="text"
                        placeholder="An identifier for the device" class="form-control">
                  </div>
               </div>

               <div class="form-group">
                  <label class="col-sm-2 control-label" for="country">Type</label>
                  <div class="col-sm-10">
                     <select id="type" name="type" type="text"
                        placeholder="For now no choice" class="form-control">
                        <option>Raspberry</option>
                     </select>
                  </div>
               </div>
               
               <div class="col-sm-offset-2 col-sm-10">
                  <button type="submit" class="btn btn-primary btn-lg">Sign in</button>
                  <a href="${createLink(controller: 'dashboard', action: 'index')}"
                     id="cancel" name="cancel" class="btn btn-danger btn-lg">Cancel</a>
               </div>
            </g:form>
         </div>
		   </div>
		 </div>
	</body>
</html>
