/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.demo;
import java.io.IOException;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.rest.model.User;

/**
 *
 * @author root
 */
@Path("/login")
public class Login {
    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/dologin")
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
    public String doLogin(@QueryParam("username") String uname, @QueryParam("password") String pwd){
        String response = "";
        if(checkCredentials(uname, pwd)){
            response = Utility.constructJSON("login",true);
        }else{
            response = Utility.constructJSON("login", false, "Incorrect Email or Password");
        }
    return response;        
    }
    
    @POST
    @Path("/dologin")
    @Produces(MediaType.APPLICATION_JSON) 
    //@Consumes(MediaType.APPLICATION_JSON) 
    public String doLoginPost( String string){
    	//string has all the payload data coming in 
    	String response = "";
    	ObjectMapper mapper = new ObjectMapper();
		JSONObject output = new JSONObject();
		JSONObject status = new JSONObject();
		JSONObject data = new JSONObject();
		JSONObject Authentication = new JSONObject();
		User user;
    	try 
    	{
    		JSONObject json = new JSONObject(string);
    		String username = (String) json.get("UserName");
    		String password = (String) json.get("Password");
    		if(checkCredentials(username, password))
    		{
    			//write a database function here to get user details 
    			
    			user = getUserDetails(username, password);
    			if(user != null){
    				status.put("code", 200);
    				status.put("message", "success");
    				Authentication.put("ClientUID", user.getUserID());
    				Authentication.put("Email", user.getEmailId());
    				Authentication.put("Password", "");
    				Authentication.put("SecurityToken", "sngfjklg");
    				Authentication.put("UserName", user.getUserName());
    				Authentication.put("UserId", user.getUserID());
    				data.put("Authentication",Authentication );
    				output.put("status", status);
    				output.put("data", data);
    			}
    			response = output.toString();
    		}else
    		{
    			response = Utility.constructJSON("login", false, "Incorrect Email or Password");
    		}
    	}catch (JSONException e) {
    		e.printStackTrace();
    	}
    return response;        
    }
    private User getUserDetails(String username, String password) {
    	
		try {
			return Database.getUserDetails(username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	/**
     * Method to check whether the entered credential is valid
     * 
     * @param uname
     * @param pwd
     * @return
     */
    private boolean checkCredentials(String uname, String pwd){
        System.out.println("Inside checkCredentials");
        boolean result = false;
        if(Utility.isNotNull(uname) && Utility.isNotNull(pwd)){
            try {
                result = Database.checkLogin(uname, pwd);
                //System.out.println("Inside checkCredentials try "+result);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                //System.out.println("Inside checkCredentials catch");
                result = false;
            }
        }else{
            //System.out.println("Inside checkCredentials else");
            result = false;
        }
 
        return result;
    }
}
/*
 * for dologinpost
 * http://localhost:8084/RestDemo/login/dologin
 * header data
 * username: jyoti
password:jyoti123
for dologin
http://localhost/RestDemo/login/dologin?username=jyoti&password=jyoti123
 */
