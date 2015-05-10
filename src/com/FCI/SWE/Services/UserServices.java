package com.FCI.SWE.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.User;
import com.FCI.SWE.ServicesModels.UserEntity;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class UserServices {
	
	
	/*@GET
	@Path("/index")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}*/
	
	/*@POST
	@Path("/SearchService")
	public String searchFriend(@FormParam("uname") String uname){
		
	}*/


		/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided password
	 * @return Status json
	 */
	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity(uname, email, pass);
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}

	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 * @param pass provided user password
	 * @return user in json format
	 */
	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
			object.put("id", user.getId());
		}
		return object.toString();

	}
	@POST
	@Path("/signoutService")
	public String signoutService() {
		User.setCurrentActiveUser();
		JSONObject object = new JSONObject();
		object.put("status"," success");
		
			return object.toString();

	}
	@POST
	@Path("/sendrequest")
	public String sendrequestService(@FormParam("friendemail") String uemail)
			{
		JSONObject object = new JSONObject();
		UserEntity.sendrequest(uemail);
		
		object.put("status"," success");
		//System.out.print(uemail);
		//object.put("status", "accept");
		return object.toString();

	}
	
	@POST
	@Path("/acceptrequest")
	public String acceptrequestService(@FormParam("acceptfriend") String femail)
			{
		JSONObject object = new JSONObject();
		UserEntity.getrequest(femail);
		
     object.put("status"," accept");
		return object.toString();

	}

	@POST
	@Path("/search")
	public String searchService(@FormParam("searchemail") String email) {
		JSONObject object = new JSONObject();
	UserEntity user = UserEntity.search(email);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			//object.put("password", user.getPass());
			//object.put("id", user.getId());
		}
		return object.toJSONString();

	}

	
	@POST
	@Path("/SearchOnPeopleAdd")
	public String SearchOnPeopleAdd() {
		JSONObject object = new JSONObject();
	    ArrayList<String> request = UserEntity.SearchOnPeopleAdd();
	    JSONArray arr= new JSONArray();
	    
		if (request == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			for(int i=0;i<request.size();i++)
			{
				arr.add(request.get(i));
			}
			object.put("request", arr);
		}
		return object.toJSONString();

	}

	@POST
	@Path("/sendmsg")
	public String sendmsgService(@FormParam("friendemail") String uemail,@FormParam("sendmsg")String message)
			{
		JSONObject object = new JSONObject();
		UserEntity.sendmsg(uemail,message);
		
		object.put("Status"," success");
	
		return object.toString();

	}
	
	
	
	
	@POST
	@Path("/group")
	public String sendgroup(@FormParam("membername") String memebr_name,@FormParam("ID")int id)
			{
		JSONObject object = new JSONObject();
		UserEntity.sendgroup_mesg(memebr_name,id);
		
		object.put("Status"," success");
	
		return object.toString();

	}	
	
	@POST
	@Path("/sendgroupmsg")
	public String chat(@FormParam("message_of_group") String messagegroup,@FormParam("chat_id") int chat_id)
			{
		JSONObject object = new JSONObject();
		UserEntity.chat_message(messagegroup,chat_id);
		
		object.put("Status"," success");
	
		return object.toString();

	}	
	
	
	@POST
	@Path("/create_pageService")
public String create_pageService(@FormParam("name") String name,@FormParam("category") String category,@FormParam("type") String type)
			{System.out.println(name);
		JSONObject object = new JSONObject();
		UserEntity.create_page(name,category,type);
		
		object.put("Status"," success");
	
		return object.toString();

	}		
	


@POST
	@Path("/activepageservice")
	public String activepageservice(@FormParam("page_name") String pname)
			{System.out.println(pname);
		JSONObject object = new JSONObject();
		UserEntity.activepage(pname);
		
		object.put("Status"," success");
		System.out.println("Service"+ pname);
		return object.toString();

	}
	
@POST
@Path("/postservice")
public String postservice(@FormParam("post") String post,@FormParam("Post_To") String Post_To) {
	JSONObject object = new JSONObject();
	UserEntity.savepost(post,Post_To);

	object.put("status", " success");
	// System.out.print(uemail);
	// object.put("status", "accept");
	return object.toString();
	

}

@POST
@Path("/TimelinePostService")
public String TimelinePostService(@FormParam("PostType") String PostType , @FormParam("TimelineName") String TimelineName
		,@FormParam("Post") String Post
		,@FormParam("Feeling")String Feeling
		,@FormParam("Privacy") String Privacy ) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
	JSONObject object = new JSONObject();
	//Builder b =new Builder();
	//b.checkType (PostType);
	UserEntity.createTimelinePost(PostType,TimelineName,Post,Feeling,Privacy);
	//Builder b =new Builder();
	//b.checkType (PostType);
	object.put("status", " accept");
	return object.toString();
}

@POST
@Path("/PageService")
public String PageService(@FormParam("PostType") String PostType , @FormParam("TimelineName") String TimelineName
		,@FormParam("Post") String Post
		,@FormParam("Feeling")String Feeling
		,@FormParam("Privacy") String Privacy ) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
	JSONObject object = new JSONObject();
	//Builder b =new Builder();
	//b.checkType (PostType);
	UserEntity.createTimelinePost(PostType,TimelineName,Post,Feeling,Privacy);
	//Builder b =new Builder();
	//b.checkType (PostType);
	object.put("status", " accept");
	return object.toString();
}

@POST
@Path("/seenservice")
public String seenservicee(@FormParam("seenpage") String name)
		{
	JSONObject object = new JSONObject();
	UserEntity.seen(name);
	
	object.put("Status"," success");
	
	return object.toString();
}


@POST
@Path("/hashtagservice")
public String hashservice(@FormParam("hashtag") String htag,@FormParam("hashpost") String hpost)
		{
	JSONObject object = new JSONObject();
	UserEntity.create_hashtag(htag,hpost);
	
	object.put("Status"," success");

	return object.toString();

}
}