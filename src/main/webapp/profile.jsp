
<%@page import="java.util.HashMap, java.io.*"%>
<%@page import="java.util.Map, com.mekala.mekalademo.callback"%>
<%@page
  import="com.loginradius.sdk.social.models.LoginRadiusContactCursorResponse"%>
<%@page
  import="com.loginradius.sdk.social.models.userprofile.LoginRadiusUltimateUserProfile"%>
<%@page import="com.loginradius.sdk.social.api.LoginRadiusAPI"%>
<%@page import="com.loginradius.sdk.social.api.LoginRadiusGetAPI"%>
<%@page import="com.loginradius.sdk.social.core.LoginRadiusClient"%>
<%@page import="com.loginradius.sdk.social.models.AccessToken"%>
<%@page
  import="com.loginradius.sdk.social.core.LoginRadiusCallbackHelper"%>
<%@page import="com.loginradius.sdk.social.core.LoginRadiusException"%>

<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>User Profile</title>
	<link rel="stylesheet" type="text/css" href="assets/css/lr-raas.css">
	<link rel="stylesheet" type="text/css" href="assets/css/customize.css">
	<link rel="stylesheet" type="text/css" href="assets/css/custom-social.css">
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
	<script src="/App/LoginRadiusService.js"></script>
	<script src="//cdn.loginradius.com/hub/prod/js/LoginRadiusSSO.js"></script> 
	
	<script type="text/javascript">
        LoginRadiusSSO.init('lr-candidate-demo2');
    </script>
	
	<script>
	<% LoginRadiusUltimateUserProfile userprofile = null;
	   userprofile = (LoginRadiusUltimateUserProfile) request.getAttribute("userProfile"); 
	%>
	</script>
</head>

<body>

	<h1>User Details - Welcome to LoginRadius demo </h1> 
	<h6><button type="button" onclick="LoginRadiusSSO.logout('/mekalademo/index.jsp');">Log Out</button></h6>
    <!-- Start Things -->
    <table style="width:50%; border:1px solid #ccc; box-shadow: 0px 0px 11px 0px #ccc" >
			<tr><%= userprofile.FirstName %></tr>
			<tr><%= userprofile.Email %></tr>
			<tr><%= userprofile.BirthDate %></tr>
			<tr><%= userprofile.City %></tr>
	</table>
</body>

</html>