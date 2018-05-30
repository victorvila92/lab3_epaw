<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="models.BeanUser" %>

<% 
BeanUser user = null;
if (request.getAttribute("user")!=null) {
	user = (BeanUser)request.getAttribute("user");
}
else {
	user = new BeanUser();
}
%>
<div class="container">
<form action="RegisterController" method="post" id="registerForm">


  <div id="login" class="login">
    <div class="login-form">
      <div class="username-row row">

        <input type="text" id="username_input" class="username-input" placeholder="Username" name="user" value="<%=user.getUser() %>" required minlength="5"></input>
        <%
        	if(user.getError()[1] == 1) {
        		out.println("The mail already exists in our DB!");
        	}
        	if ( user.getError()[0] == 1) {
        		out.println("The username already exists in our DB!");
        	}
        %>
      </div>
      
      <div class="password-row row">
        
        <input type="email" name="mail" id="mail" value="<%=user.getMail() %>" required email" class="password-input" class="input" placeholder="Email"></input>
      </div>
      

      <div class="password-row row">
        
        <input type="password" name="password" id="password_input" class="password-input" class="input" placeholder="Password" value="<%=user.getPassword() %>" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" title="Password must contain at least 6 characters, including UPPER/lowercase and numbers."></input>
      </div>
      
       <div class="password-row row">
     
        <input type="password" name="confirmPassword" id="mail" value="<%=user.getMail() %>" required email" class="password-input" id="confirmPassword" value="<%=user.getConfirmPassword() %>" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" title="Please enter the same Password as above." class="input" placeholder="Confirm Password"></input>
      </div>
      
	  </div>
  
      <button id="login-button" type="submit" value="Enviar" style="margin-top: 100px;">Register</button>
    </div>
    </form>
  </div>


 <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='https://cdn.jsdelivr.net/velocity/1.2.2/velocity.min.js'></script>
<script src='https://cdn.jsdelivr.net/velocity/1.2.2/velocity.ui.min.js'></script>



 <script>
    // jQuery & Velocity.js
    
   
		$(document).ready(function(){
		    $("#registerForm").validate({
		    	submitHandler: function(form) {
		    		$('#content').load('RegisterController',$("#registerForm").serialize());
		    }
		    });
		});

	
    function slideUpIn() {
      $("#login").velocity("transition.slideUpIn", 1250)
    };

    function slideLeftIn() {
      $(".row").delay(500).velocity("transition.slideLeftIn", {stagger: 500})
    }

    slideUpIn();
    slideLeftIn();
    $("button").on("click", function () {
      shake();
    });
    
    
	var password = document.getElementById("password")
	, confirm_password = document.getElementById("confirmPassword");
	
	function validatePassword(){
	if(password.value != confirm_password.value) {
	  confirm_password.setCustomValidity("Passwords Don't Match");
	} else {
	  confirm_password.setCustomValidity('');
	}
	}
	
	password.onchange = validatePassword;
	confirm_password.onkeyup = validatePassword;
    
</script>
</body>
</html>
