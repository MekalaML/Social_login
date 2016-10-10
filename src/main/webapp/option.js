
var option = {};
option.apikey = "9489e32b-da63-4b10-8884-6e2ef036e828";
option.appName = "1354df3d-f346-4731-94c4-e2fa11a2b107";
option.templatename = "loginradius_customtmpl"; 
option.hashTemplate = true;
option.V2Recaptcha = true;
option.inFormvalidationMessage = true;
option.enableLoginOnEmailVerification = true;
var path = window.location.href;
option.emailVerificationUrl = path.replace(path.substr(path.lastIndexOf('/')), "/verify.jsp");
option.forgotPasswordUrl = path.replace(path.substr(path.lastIndexOf('/')), "/resetpassword.html");