<!DOCTYPE html>
<html>
   <head>
      <meta name="layout" content="portal" />
      <title>Easy IoT Connect</title>
   </head>
   
   <body>
      <div class="container">
         <h2>Do you have questions? I'm really enjoy to help you</h2>
 
         <form method="POST" action="askQuestion"
               id='askQuestion'  autocomplete='off' class="form-horizontal" role="form">

               <div class="form-group">
                  <label class="col-sm-2 control-label" for="email">Email</label>
                  <div class="col-sm-10">
                     <input name='email' id='email' type="email" class="form-control">
                  </div>
               </div>

               <div class="form-group">
                  <label class="col-sm-2 control-label" for="password">Text</label>
                  <div class="col-sm-10">
                     <textarea class="form-control" rows="10" id="text" class="form-control">
                     </textarea>
                  </div>
               </div>
             
               <div class="col-sm-offset-2 col-sm-10">
                  <button type="submit" class="btn btn-primary btn-lg">Ask</button>
               </div>
            </form>
      </div>         
   </body>
</html>
