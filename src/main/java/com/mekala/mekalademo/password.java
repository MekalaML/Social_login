package com.mekala.mekalademo;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.loginradius.sdk.raas.api.AccountAPI;
import com.loginradius.sdk.raas.api.UserProfileAPI;
import com.loginradius.sdk.raas.models.RaaSClientConfig;
import com.loginradius.sdk.raas.models.RaaSConfiguration;
import com.loginradius.sdk.raas.models.RaaSResponse;
import com.loginradius.sdk.raas.models.RaaSUserDetails;
import com.loginradius.sdk.social.core.LoginRadiusException;
import com.loginradius.sdk.util.LoginRadiusRestErrorResponse;

@Path("/password")
public class password {

	String responseString = null;
	LoginRadiusRestErrorResponse errorResponse=null;
	
	@POST
	@Path("/reset")
    @Produces(MediaType.TEXT_PLAIN)
    public String sendpasswordForTok(@FormParam ("emailid") String emailid,
    								 @FormParam ("confirmpassword") String password) {
		
		RaaSConfiguration configuration = new RaaSConfiguration(LRDemoCredentials.apiKey,LRDemoCredentials.apiSecret);
		new RaaSClientConfig(configuration);
		
//		// user SDK to manage Account API request
		UserProfileAPI userAPI = new UserProfileAPI();
		AccountAPI accountAPI = new AccountAPI();
		
		try{
			RaaSUserDetails userdetails = userAPI.getUserByUserId(emailid);
			RaaSResponse resp = accountAPI.setAccountPassword(userdetails.getUid(), password);
			
			if (resp.isPosted()){
				
				responseString = userdetails.getFirstName() + "successful Password reset for account " + userdetails.getFullName();
			}else{
				responseString = userdetails.getFirstName() + "Password not reset for account " + userdetails.getFirstName();
			}
			
		}catch (IllegalArgumentException lre){
			responseString = lre.getMessage();
		}catch (LoginRadiusException lre){
			errorResponse = lre.getErrorResponse();
			responseString = errorResponse.getDescription();
		}
		
		return responseString;
    }
		
}

