package com.mekala.mekalademo;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.google.gson.JsonObject;
import com.loginradius.sdk.raas.api.UserProfileAPI;
import com.loginradius.sdk.raas.models.RaaSClientConfig;
import com.loginradius.sdk.raas.models.RaaSConfiguration;
import com.loginradius.sdk.raas.models.RaaSResponse;
import com.loginradius.sdk.raas.models.RaaSUserDetails;
import com.loginradius.sdk.social.core.LoginRadiusException;
import com.loginradius.sdk.util.LoginRadiusRestErrorResponse;

@Path("/profile")
public class MyResource {

	
	String responseString = null;
	LoginRadiusRestErrorResponse errorResponse=null;
	RaaSResponse successresponse=null;

	//gets the user profile details using username and password
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getProfile(@QueryParam ("username") String userName,
    						   @QueryParam ("password") String password){
    	
		RaaSConfiguration configuration = new RaaSConfiguration(LRDemoCredentials.apiKey,LRDemoCredentials.apiSecret);
		new RaaSClientConfig(configuration);
		
//		// user SDK to manage user API request
		UserProfileAPI userAPI = new UserProfileAPI();
		try{
			RaaSUserDetails userdetails = userAPI.getUserAfterAuthentication(userName, password);
			System.out.print(userdetails.getVerified());
			responseString = userdetails.getFirstName() + "successful LogIn" + userdetails.getVerified();
			
		}catch (IllegalArgumentException lre){
			responseString = lre.getMessage();
		}catch (LoginRadiusException lre){
			errorResponse = lre.getErrorResponse();
			responseString = errorResponse.getDescription();
		}
		
		return responseString;
    }
    
// register new user with the details from register form	
	@POST
	@Path("/register")
    @Produces(MediaType.TEXT_PLAIN)
    public String registerUser(@FormParam ("firstname") String firstName, 
    				  @FormParam ("lastname")   String lastName,
    				  @FormParam ("emailid")   String emailId,
    				  @FormParam ("password")  String passWord) {

		String responseString;

		RaaSConfiguration configuration = new RaaSConfiguration(LRDemoCredentials.apiKey,LRDemoCredentials.apiSecret);
		new RaaSClientConfig(configuration);
		
//		// user SDK to manage user API request
		UserProfileAPI userAPI = new UserProfileAPI();
    		
		//getting user details to register from the registration form 
		JsonObject userdet = new JsonObject();
		userdet.addProperty("firstname", firstName);
		userdet.addProperty("lastname", lastName);
		userdet.addProperty("emailid", emailId);
		userdet.addProperty("password", passWord);
		System.out.println(userdet.toString());
		try
		{
			RaaSResponse regres = userAPI.registerUser(userdet.toString());
			if (regres.isPosted)
			{
				System.out.println("inside posted");
				responseString = regres.getDescription();
			}else {
				System.out.println("inside not posted");
				responseString = regres.getDescription();
			}
		}catch (IllegalArgumentException lre){
			responseString = lre.getMessage();
		}catch (LoginRadiusException lre){
			errorResponse = lre.getErrorResponse();
			responseString = errorResponse.getDescription();
		}
		System.out.println(responseString);
		return responseString;			
	}
}
