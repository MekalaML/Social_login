package com.mekala.mekalademo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import com.loginradius.sdk.raas.models.RaaSClientConfig;
import com.loginradius.sdk.raas.models.RaaSConfiguration;
import com.loginradius.sdk.social.api.LoginRadiusAPI;
import com.loginradius.sdk.social.api.LoginRadiusGetAPI;
import com.loginradius.sdk.social.core.LoginRadiusCallbackHelper;
import com.loginradius.sdk.social.core.LoginRadiusClient;
import com.loginradius.sdk.social.core.LoginRadiusException;
import com.loginradius.sdk.social.models.AccessToken;
import com.loginradius.sdk.social.models.userprofile.LoginRadiusUltimateUserProfile;
import com.loginradius.sdk.util.LoginRadiusRestErrorResponse;

@Path("/callback")
public class callback extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public callback() {
		super();
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LoginRadiusRestErrorResponse errorResponse=null;
		
		LoginRadiusCallbackHelper callbackhelper = new LoginRadiusCallbackHelper(LRDemoCredentials.apiSecret);
		AccessToken accessToken = callbackhelper.GetLoginRadiusToken(request);

		if(accessToken != null && !accessToken.toString().isEmpty())
		{
			ServletContext sc = getServletContext();
			String url = "/profile.jsp";
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			
			RaaSConfiguration configuration = new RaaSConfiguration(LRDemoCredentials.apiKey,LRDemoCredentials.apiSecret);
			new RaaSClientConfig(configuration);
		
		  
			LoginRadiusClient client = new LoginRadiusClient(accessToken);				
			LoginRadiusAPI userprofileapi=new LoginRadiusGetAPI("userprofile");
			
			try {
				LoginRadiusUltimateUserProfile userProfileData = client.getResponse(userprofileapi,LoginRadiusUltimateUserProfile.class);
				request.setAttribute("userProfile", userProfileData);
				rd.forward(request, response);
//				response.sendRedirect("profile.jsp?userid=" + request.getParameter("userid").toString() + userProfileData);
//				responseString = "Welcome " + userProfileData.FullName + " to Login RadiusDemo by -Mekala";
			}catch (IllegalArgumentException lre){
				response.sendRedirect("profile.jsp" + "cannot access the social profile");
			}catch (LoginRadiusException lre){
				errorResponse = lre.getErrorResponse();
				response.sendRedirect("profile.jsp" + errorResponse.getDescription());
			}
		}else {
			response.sendRedirect("profile.jsp" + "Access Token error");
		}
	}
}
