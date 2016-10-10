<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mekala Demo Email Verification</title>

<script src="//cdn.loginradius.com/hub/prod/js/LoginRadiusRaaS.js"></script>
</head>
<body>
<p id='message'>E-mail Verified</p>
<div id='response'> 
<h2></h2>
</div>
<script>
$LR.util.ready(function() {
    var raasoption = {};
    raasoption.apikey = "<%= application.getInitParameter("LoginRadiusApiKey") %>";
    LoginRadiusRaaS.init(raasoption, 'emailverification', function(response) {
      // succeed
    	document.getElementById('response').innerHtml = response;
    }, function(errors) {
      // error
    	document.getElementById('response').innerHtml = response;
    });
});
</script>

</body>
</html>