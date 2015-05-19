package com.FCI.SWE.Controller;

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




@Path("/")
@Produces("text/html")
public class UserController {
	
	@POST
	@Path("/doSearch")
	
	public Response usersList(@FormParam("uname") String uname){
		System.out.println(uname);
		String serviceUrl = "http://localhost:8888/rest/SearchService";
		String urlParameters = "uname=" + uname;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		return null;
	}
	
	
	@GET
	@Path("/signup")
	@Produces("text/html")
	public Response signUp() {
		return Response.ok(new Viewable("/jsp/register")).build();
	}

	
	/**
	 * 
	 * @param email 
	 * this function taking email for search to find specific person
	 * @return null 
	 */
	
	//search for specific user
	@POST
	@Path("/search")
	@Produces("text/html")
	public Response search(@FormParam("searchemail") String email){
	//	return Response.ok(new Viewable("/jsp/home")).build();
		String serviceUrl = "http://localhost:8888/rest/search";
		String urlParameters = "searchemail=" + email;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			
			Map<String, String> map = new HashMap<String, String>();
			//User user = User.getUser(object.toJSONString());
			if (object.get("Status").toString().equals("OK")){
			map.put("name", object.get("name").toString());
		//	map.put("email", user.getEmail());
			return Response.ok(new Viewable("/jsp/search", map)).build();}
			else{
			map.put("name", "the name not found :s");
			//	map.put("email", user.getEmail());
			return Response.ok(new Viewable("/jsp/notfounded", map)).build();}

			} 
		   
		catch (ParseException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return null;
	}
	
	
//search in people who added me to accept them	
	@POST
	@Path("/acceptsearch")
	@Produces("text/html")
	public String searching_On_requests(){
	//	return Response.ok(new Viewable("/jsp/home")).build();
		String serviceUrl = "http://localhost:8888/rest/SearchOnPeopleAdd";
		//String urlParameters = "searchemail=" + email;
		String retJson = Connection.connect(serviceUrl, "", "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		
		try {
		
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			
			Map<String, String> map = new HashMap<String, String>();
			//User user = User.getUser(object.toJSONString());
			JSONArray arr= (JSONArray) object.get("request");
			
			if (object.get("Status").toString().equals("OK")){
				
				 for(Integer i=0;i<arr.size();i++)
				map.put("name"+i.toString(), arr.get(i).toString());
		
				return arr.toJSONString(); 
				}
			else{
				map.put("name", "the name not found :s");
				
			return "ss"	;
			}

			
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		}
		return null;
	}
	
	
	@GET
	@Path("/")
	@Produces("text/html")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}

	
	@GET
	@Path("/login")
	public Response login() {
		return Response.ok(new Viewable("/jsp/login")).build();
	}
	

	
	@POST
	@Path("/response")
	@Produces(MediaType.TEXT_PLAIN)
	public String response(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {

		String serviceUrl = "http://localhost:8888/rest/RegistrationService";
		String urlParameters = "uname=" + uname + "&email=" + email+ "&password=" + pass;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Registered Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "Failed";
	}

	/**
	 * Action function to response to login request. This function will act as a
	 * controller part, it will calls login service to check user data and get
	 * user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return Home page view
	 */
	
	/**
	 * 
	 * @param uname
	 * @param pass
	 * this func taking name password for loging
	 * @return null
	 * 
	 */
	
	@POST
	@Path("/home")
	@Produces("text/html")
	public Response home(@FormParam("uname") String uname,@FormParam("password") String pass) {
		String urlParameters = "uname=" + uname + "&password=" + pass;

		String retJson = Connection.connect(
				"http://localhost:8888/rest/LoginService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			Map<String, String> map = new HashMap<String, String>();
			User user = User.getUser(object.toJSONString());
			map.put("name", user.getName());
			map.put("email", user.getEmail());
			return Response.ok(new Viewable("/jsp/home", map)).build();
		     } 
		
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;

	}
	
	/**
	 * this function for sign out current active user
	 * @return response
	 */
	@POST
	@Path("/signout")
	@Produces("text/html")
	public Response signout(){
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}
	
	/**
	 * 
	 * @param femail
	 * for sending request
	 * @return response
	 */
	@POST
	@Path("/sendrequest")
	@Produces("text/html")
	public Response sendrequest(@FormParam("friendemail") String femail){
		String serviceUrl = "http://localhost:8888/rest/sendrequest";
		String urlParameters = "friendemail=" + femail ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		return Response.ok(new Viewable("/jsp/home")).build();
	}
	
	/**
	 * 
	 * @param femail 
	 * fun for accept request of friend
	 * @return response
	 */
	
	@POST
	@Path("/acceptrequest")
	@Produces("text/html")
	public Response acceptrequest(@FormParam("acceptfriend") String femail){
		System.out.println(femail);
		String serviceUrl = "http://localhost:8888/rest/acceptrequest";
		String urlParameters = "acceptfriend=" + femail ;
		String retJson = Connection.connect(serviceUrl ,urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		return Response.ok(new Viewable("/jsp/accept")).build();
	}
	
	/**
	 * 
	 * @return reponse
	 */
	
	@POST
	@Path("/accept")
	@Produces("text/html")
	public Response accept(){
		return Response.ok(new Viewable("/jsp/home")).build();
			
	}
	
	/**
	 * 
	 * @param friendemail
	 * @param message
	 * for sending message to specifi person
	 * @return null
	 */
	
	@POST
	@Path("/sendmsg")
	@Produces("text/html")
	public Response sendmessage(@FormParam("friendemail") String friendemail,@FormParam("sendmsg")String message){
		System.out.println(message);
		String serviceUrl = "http://localhost:8888/rest/sendmsg";
		String urlParameters = "friendemail=" + friendemail+"&sendmsg=" + message ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		//return Response.ok(new Viewable("/jsp/home")).build();
		JSONParser parser = new JSONParser();
		Object obj;
		
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			
			if (object.get("Status").equals("success"))
				return Response.ok(new Viewable("/jsp/home")).build();

		          }
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @return response
	 */
	
	@POST
	@Path("/group_message")
	@Produces("text/html")
	public Response group(){
		return Response.ok(new Viewable("/jsp/group")).build();
			
	}
	
/**
 * botton for back to last page
 * @return response
 */
	@POST
	@Path("/back")
	@Produces("text/html")
	public Response back(){
		return Response.ok(new Viewable("/jsp/home")).build();
			
	}
	/**
	 * 
	 * @param member_name
	 * @param group_id
	 * this fun used for sadd memeber to specidic group
	 * @return null
	 */
	@POST
	@Path("/group")
	@Produces("text/html")
	public Response sendgroup(@FormParam("membername") String member_name,@FormParam("ID")String group_id){
		//System.out.println(message);
		String serviceUrl = "http://localhost:8888/rest/group";
		String urlParameters = "membername=" + member_name+"&ID=" + group_id ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		//return Response.ok(new Viewable("/jsp/home")).build();
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("success"))
				return Response.ok(new Viewable("/jsp/group")).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param chat_msg
	 * @param chat_id
	 * this fun for sending message for group
	 * @return null
	 */
	
	@POST
	@Path("/sendgroupmsg")
	@Produces("text/html")
	public Response chat_message(@FormParam("message_of_group") String chat_msg,@FormParam("chat_id")int chat_id){
		//System.out.println(message);
		String serviceUrl = "http://localhost:8888/rest/sendgroupmsg";
		
		String urlParameters = "message_of_group=" + chat_msg+"&chat_id=" + chat_id ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		//return Response.ok(new Viewable("/jsp/home")).build();
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("success"))
				return Response.ok(new Viewable("/jsp/group")).build();

		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@POST 
	@Path("/create_page")
	@Produces("text/html")
  public Response createpage(){
		return Response.ok(new Viewable("/jsp/create_page")).build();
	}
	

	// for creating page
	@POST 
	@Path("/create_page_data")
	@Produces("text/html")
public Response create_page(@FormParam("name") String name,@FormParam("category") String category,@FormParam("type") String type) {
		System.out.println(name);
		System.out.println(type);
		System.out.println(category);
			String urlParameters ="name=" + name + "&category=" + category+ "&type=" + type;
         String retJson = Connection.connect(
				"http://localhost:8888/rest/create_pageService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		
       JSONParser parser = new JSONParser();
		Object obj;
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("success"))
				return Response.ok(new Viewable("/jsp/active_page.jsp")).build();

		        }
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

@POST 
@Path("/active_page")
@Produces("text/html")
public Response activepage(){
	return Response.ok(new Viewable("/jsp/active_page")).build();
                              }


@POST
@Path("/active_page_")
@Produces("text/html")
public Response active_page(@FormParam("page_name") String name)
{
	System.out.println(name);
	String serviceUrl = "http://localhost:8888/rest/activepageservice";
	String urlParameters ="page_name=" + name;
	System.out.println("hController"+ name);
	String retJson = Connection.connect(serviceUrl, urlParameters , "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	return Response.ok(new Viewable("/jsp/active_page")).build();
}
 
//make seen for page
@POST
@Path("/seen")
@Produces("text/html")
public Response seen_page(@FormParam("seenpage") String name)

{	String serviceUrl = "http://localhost:8888/rest/seenservice";
     String urlParameters ="page_name=" + name;
	String retJson = Connection.connect(serviceUrl, urlParameters , "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	return Response.ok(new Viewable("/jsp/home")).build();
}
	
/*	@POST
	@Path("/create_page")
	@Produces("text/html")
	public Response create_pagee(@FormParam("name") String name,@FormParam("category") String category,@FormParam("type") String type){
		System.out.println(name);
		String serviceUrl = "http://localhost:8888/rest/create_pageService";
		
		String urlParameters = "name="+name+"&category="+category+"&type="+type;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		//return Response.ok(new Viewable("/jsp/home")).build();
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("success"))
				return Response.ok(new Viewable("/jsp/create_page")).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
	
@POST
@Path("/timeline")
@Produces("text/html")
public Response time() {
	return Response.ok(new Viewable("/jsp/timeline")).build();
}

//for making post

@POST
@Path("/post")
@Produces("text/html")
public Response post(@FormParam("post") String post,@FormParam("Post_To") String Post_To) {
	String serviceUrl = "http://localhost:8888/rest/postservice";
	String urlParameter = "post=" + post + "&Post_To= " + Post_To;
			
	String retJson2 = Connection.connect(serviceUrl, urlParameter, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	
	    return Response.ok(new Viewable("/jsp/timeline")).build();
	
}

@POST
@Path("tlpost")
public Response tlpost() {
	
	return Response.ok(new Viewable("/jsp/timelinePost")).build();
}

@POST
@Path("ppost")
public Response ppost() {
	
	return Response.ok(new Viewable("/jsp/PagePost")).build();
}


//formaking tmeline post
@POST
@Path("/TimelinePost")
@Produces("text/html")
public Response TimelinePost(@FormParam("PostType") String PostType ,@FormParam("TimelineName") String TimelineName
		,@FormParam("Post") String Post,@FormParam("Feeling") String Feeling,@FormParam("Privacy") String Privacy ) {
	
	String serviceUrl = "http://localhost:8888/rest/TimelinePostService";
	String urlParameters = "PostType="+ PostType +"&TimelineName=" + TimelineName +
			"&Post=" + Post +"&Feeling=" + Feeling+ "&Privacy=" + Privacy;
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	return Response.ok(new Viewable("/jsp/home")).build();
}

//for making page post
@POST
@Path("/PagePost")
@Produces("text/html")
public Response PagePost(@FormParam("PostType") String PostType ,@FormParam("TimelineName") String TimelineName
		,@FormParam("Post") String Post,@FormParam("Feeling") String Feeling,@FormParam("Privacy") String Privacy ) {
	
	System.out.println(PostType);
	System.out.println(TimelineName);
	String serviceUrl = "http://localhost:8888/rest/PageService";
	String urlParameters = "PostType="+ PostType +"&TimelineName=" + TimelineName +
			"&Post=" + Post +"&Feeling=" + Feeling+ "&Privacy=" + Privacy;
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	return Response.ok(new Viewable("/jsp/home")).build();
}

// for making hashtag

@POST 
@Path("/hashtag")
@Produces("text/html")
public Response create_hashtage(@FormParam("hashtag") String htag,@FormParam("hashpost") String hpost) {

		String urlParameters ="hashtag=" + htag + "&hashpost=" + hpost;
     String retJson = Connection.connect(
			"http://localhost:8888/rest/hashtagservice", urlParameters,
			"POST", "application/x-www-form-urlencoded;charset=UTF-8");
	
   JSONParser parser = new JSONParser();
	Object obj;
	try {
		// System.out.println(retJson);
		obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;
		if (object.get("Status").equals("success"))
			return Response.ok(new Viewable("/jsp/home.jsp")).build();

	} 
	catch (ParseException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

}