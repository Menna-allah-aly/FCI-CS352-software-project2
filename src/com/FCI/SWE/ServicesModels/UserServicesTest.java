package com.FCI.SWE.ServicesModels;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.FCI.SWE.Controller.Connection;
import com.FCI.SWE.Models.User;

public class UserServicesTest {
@Test
 public void registrationService()
{
	 String serviceUrl = "http://localhost:8888/rest/RegistrationService";
		String urlParameters = "uname=" + "noran"+ "&email=" + "nonna@yahoo.com"+ "&password=" + "123";
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		Assert.assertEquals(retJson, "{\"Status\":\"OK\"}");

}
@Test 
public void loginService(){ 
	String serviceUrl = "http://localhost:8888/rest/LoginService";
String urlParameters = "uname=" + "noran"+ "&password=" + "123";
String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
		"application/x-www-form-urlencoded;charset=UTF-8");
Assert.assertEquals(retJson,"{\"id\":3,\"Status\":\"OK\",\"email\":\"noran@yahoo.com\",\"name\":\"noran\",\"password\":\"123\"}");}


@Test
public void signoutService(){
	String serviceUrl = "http://localhost:8888/rest/signoutService";
	//String urlParameters = "uname=" + "noran"+ "&password=" + "123";
	String retJson = Connection.connect(serviceUrl, "", "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	Assert.assertEquals(retJson, "{\"status\":\" success\"}");

}
@Test
public void sendrequestService(){
	String serviceUrl = "http://localhost:8888/rest/sendrequest";
	String urlParameters = "friendemail=" + "bal7a" ;
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	Assert.assertEquals(retJson,"{\"status\":\" success\"}");}
	

 @Test
 public void acceptrequestService(){
	 String serviceUrl = "http://localhost:8888/rest/acceptrequest";
	String urlParameters = "acceptfriend=" + "noran" ;
	String retJson = Connection.connect(serviceUrl ,urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	Assert.assertEquals(retJson,"{\"status\":\" accept\"}");
 }
 
@Test
public void searchService(){
	String serviceUrl = "http://localhost:8888/rest/search";
	String urlParameters = "searchemail=" + "noran@yahoo.com";
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	Assert.assertEquals(retJson, "{\"Status\":\"OK\",\"email\":\"noran@yahoo.com\",\"name\":\"noran\"}");
	
	
}

@Test
public void sendmsgService()
{String serviceUrl = "http://localhost:8888/rest/sendmsg";
String urlParameters = "friendemail=" + "noran@yahoo.com"+"&sendmsg=" + "hello" ;
String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
		"application/x-www-form-urlencoded;charset=UTF-8");
Assert.assertEquals(retJson, "{\"Status\":\" success\"}");
	}

@Test 
public void sendgroup(){
	String serviceUrl = "http://localhost:8888/rest/group";
	String urlParameters = "membername=" + "menna"+"&ID=" + "1" ;
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	Assert.assertEquals(retJson, "{\"Status\":\" success\"}");
}

@Test
public void chat(){
	
	String serviceUrl = "http://localhost:8888/rest/sendgroupmsg";
	
	String urlParameters = "message_of_group=" + "hii"+"&chat_id=" + "1" ;
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
  Assert.assertEquals(retJson, "{\"Status\":\" success\"}");
}

@Test
public void create_pageService(){
	String urlParameters ="name=" + "shoes" + "&category=" + "shopping"+ "&type=" + "public";
    String retJson = Connection.connect(
			"http://localhost:8888/rest/create_pageService", urlParameters,
			"POST", "application/x-www-form-urlencoded;charset=UTF-8");
	
	Assert.assertEquals(retJson, "{\"Status\":\" success\"}");
	
}

@Test
public void activepageservice(){
	String serviceUrl = "http://localhost:8888/rest/activepageservice";
	String urlParameters ="page_name=" + "shoes";
	String retJson = Connection.connect(serviceUrl, urlParameters , "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	
	Assert.assertEquals(retJson, "{\"Status\":\" success\"}");}
	
@Test
public void postservice(){
	String serviceUrl = "http://localhost:8888/rest/postservice";
	String urlParameter = "post=" + "kkk" + "&Post_To= " + "menna";
			
	String retJson2 = Connection.connect(serviceUrl, urlParameter, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	
	Assert.assertEquals(retJson2, "{\"status\":\" success\"}");
	
}

@Test
public void TimelinePostService(){
	String serviceUrl = "http://localhost:8888/rest/TimelinePostService";
	String urlParameters = "PostType="+ "public" +"&TimelineName=" + "shoes" +
			"&Post=" + "hhhh" +"&Feeling=" + "happy"+ "&Privacy=" + "public";
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	Assert.assertEquals(retJson,"{\"status\":\" accept\"}");}

	
@Test 
public void PageService(){
	String serviceUrl = "http://localhost:8888/rest/PageService";
	String urlParameters = "PostType="+ "public" +"&TimelineName=" + "menna" +
			"&Post=" + "hhhh" +"&Feeling=" + "happy"+ "&Privacy=" + "public";
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	Assert.assertEquals(retJson,"{\"status\":\" accept\"}");}

@Test
public void seenservicee()
{String serviceUrl = "http://localhost:8888/rest/seenservice";
String urlParameters ="page_name=" + "shoes";
String retJson = Connection.connect(serviceUrl, urlParameters , "POST",
		"application/x-www-form-urlencoded;charset=UTF-8");

Assert.assertEquals(retJson, "{\"Status\":\" success\"}");}

	
	@Test
public void hashservice(){
		String urlParameters ="hashtag=" + "#" + "&hashpost=" + "hvxfv";
	     String retJson = Connection.connect(
				"http://localhost:8888/rest/hashtagservice", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

	     Assert.assertEquals(retJson, "{\"Status\":\" success\"}");}

		
		
	}








