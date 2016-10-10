<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Mekala Demo Login Radius API</title>
		<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">	
		<script src="//hub.loginradius.com/include/js/LoginRadius.js"></script>
		<script src="//cdn.loginradius.com/hub/prod/js/LoginRadiusRaaS.js"></script>
		<script src="//cdn.loginradius.com/hub/prod/js/CustomInterface.2.js" type="text/javascript"></script>
		
		<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
		<script src="/Content/js/raasdemoscript.js"></script>
		
		
		<script src="option.js"></script>
		<script type="text/javascript">   
		    LoginRadiusRaaS.CustomInterface(".interfacecontainerdiv", option);
		</script>
	 <script type="text/html" id="loginradius_customtmpl">
        <li>
            <a onclick="return $SL.util.openWindow('<#= Endpoint #>&callback=http://localhost:8080/mekalademo/profile.jsp');"><span>Log in with</span> <strong><#= Name #></strong></a>
            
        </li>
    </script>
		
	</head>
	<body>
	<h1>		Welcome to LoginRadius Demo - Mekala		</h1>
	<button type="button" onclick="loginform()">LogIn</button>
	<button type="button" onclick="registerform()">Register</button>
	<h3> OR </h3>
	
	<div id="registration-container">
	<form id="registration-form" method="post" action="users/profile/register" style="display:none">
	<label for="loginradius-raas-registration-firstname">First name</label>
		<input type="text" name="firstname" placeholder="Johnson" required><br>
	<label for="loginradius-raas-registration-lastname">Last name</label>
		<input type="text" name="lastname" placeholder="David" required><br>
	<label for="loginradius-raas-registration-emailid">Email Id</label>
		<input type="text" name="emailid" placeholder="xyz@gmail.com" required><br>
	<label for="loginradius-raas-registration-password">Password</label>
		<input type="password" name="password" required><br>
		<input type="submit" value="Register" >
	</form>
	</div>
	
	<div id="login-container">
	<form id="login-form" method="get" action="users/profile" style="display:none">
	<label for="loginradius-raas-registration-username">E-mail</label>
	<input type="text" name="username" ><br>
	<label for="loginradius-raas-registration-password">Password</label>
	<input type="password" name="password"><br>
	<input type="submit" value="Login" > 
	<a href='forgotpassword.html'> Forgot Password </a>
	</form>
	</div>
	
	<div class="interfacecontainerdiv" id="interfacecontainerdiv"></div>
	<div class="social-login-container" id="social-login-container"></div>
	
	<script>
	function loginform() {
		document.getElementById('login-form').style.display='block';
		document.getElementById('registration-form').style.display='none';
	}
	function registerform() {
		document.getElementById('registration-form').style.display='block';
		document.getElementById('login-form').style.display='none';
	}
	</script>
	
	<script>
	function redirect(token) {
	    var form = document.createElement("form");
	    form.method = "POST";
	    form.action = "users/callback";
	    var _token = document.createElement("input");
	    _token.type = "hidden";
	    _token.name = "token";
	    _token.value = token;
	    form.appendChild(_token);
	    document.body.appendChild(form);
	    form.submit();
	}
	
	$SL.util.ready(function() {
		var raasoption = option;
		LoginRadiusRaaS.init(raasoption, 'sociallogin', function (response) {
		    // On Success this callback will call
		    // response will be string as token
			//    alert("social login success", response);
		    redirect(response);
		}, function (errors) {
		    // on failure this function will call ‘errors’ which is an array of             errors with message.
		    // every kind of error will be returned in this method
		    // you can run a loop on this array.
		
		    alert("social login error", errors);
		}, "social-login-container");
		}
	);
	</script>
</body>



</html>