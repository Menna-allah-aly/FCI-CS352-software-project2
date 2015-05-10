package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalTaskQueueTestConfig;


public class Entitytest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(

			new LocalDatastoreServiceTestConfig(),
					new LocalMemcacheServiceTestConfig(),
					new LocalTaskQueueTestConfig());

			@BeforeClass
			public void setUp() {
				helper.setUp();

			}

			@AfterClass
			public void tearDown() {
				helper.tearDown();
			}
	UserEntity U=new UserEntity("");
  @Test
  public void getUser() {
	  
		Assert.assertNotNull(UserEntity.getUser("samah", "123"));//get user
  }
  
  @Test  
public void saveuser(){	
	boolean b=U.saveUser();
		Assert.assertEquals(true, b);//save user
}
  @Test
public void sendrequest(){
	boolean b=U.sendrequest("noran@yahoo.com");

		Assert.assertEquals(true, b);}
  @Test	
public void getrequest(){
		Assert.assertNotNull(U.getrequest("noran@yahoo.com"));
}
  @Test
public void search(){

		Assert.assertNotNull(U.search("samah@yahoo.com"));}
  
  @Test
public void SearchOnPeopleAdd(){
	ArrayList<String>actuall = new ArrayList();
	actuall.add("noran@yahoo.com");
	actuall.add("samah@yahoo.com");
	
	ArrayList<String>expects = new ArrayList();
//expects.add("noran@yahoo.com");
//expects.add("bal7a@yahoo.com");

ArrayList<String>m=U.SearchOnPeopleAdd();

Assert.assertEquals(actuall.get(0),m.get(0));

}

  @Test
  
public void sendmessage(){
	boolean b=U.sendmsg("noran@yahoo.com", "");
	Assert.assertEquals(true,b);
}

  @Test
  
public void sendgroup_mesg(){
	boolean b=U.sendgroup_mesg("noran", 1);
	Assert.assertEquals(true,b);
	}
  @Test
  
public void chat_message(){
	String message="";
	int chat_id=0;
	boolean b=U.chat_message("hello", 1);
	Assert.assertEquals(true,b);}
  
  @Test
 public void activepage(){
	String s=U.activepage("shoes");
	
	Assert.assertEquals("el7", s);
	}
  
  @Test
public void creat_page(){
	boolean B= U.create_page("bag","shopping", "public");
	Assert.assertEquals(true, B);
	}
  
  @Test
public void savepost(){
	  //User.setCurrentActiveUser("noran");
	boolean b=U.savepost("hello","noran");
	Assert.assertEquals(true, b);
	}
  
  
  @Test
 public void createTimelinePost(){
	  try{
boolean b=U.createTimelinePost("Timeline post", "shoes","hello", "happy", "public");
Assert.assertEquals(true, b);
	  }catch(Exception E)
	  {
		 E.printStackTrace();
	  }
  }
  
  @Test
  public void create_hashtag(){
	  String s= U.create_hashtag("#", "menna");
	  Assert.assertEquals("created", s);
	  
  }
 
}



