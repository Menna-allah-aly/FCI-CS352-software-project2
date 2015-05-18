package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.memcache.MemcacheSerialization.Flag;

/**
 * <h1>User Entity class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 */
public class UserEntity {
	private String name;
	private String email;
	private String password;
	private long id;

	/**
	 * Constructor accepts user data
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            user email
	 * @param password
	 *            user provided password
	 */
	
	
	public UserEntity(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	 public UserEntity (String name, String email)
	 {this.name = name;
		this.email = email;}
	 
	 public UserEntity ()
	 {}
	 
	 public UserEntity (String name)
	 {
		 this.name=name;
	 }
	 
 
	 
	 
	 private void setId(long id){
		this.id = id;
	}
	
	public long getId(){
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return password;
	}

	
	/**
	 * 
	 * This static method will form UserEntity class using user name and
	 * password This method will serach for user in datastore
	 * 
	 * @param name
	 *            user name
	 * @param pass
	 *            user password
	 * @return Constructed user entity
	 */
/**
 * 
 * @param name
 * @param pass
 * @return null
 */
	
	public static UserEntity getUser(String name, String pass) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("name").toString().equals(name)
					&& entity.getProperty("password").toString().equals(pass)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				returnedUser.setId(entity.getKey().getId());
				return returnedUser;
			}
			else{
				
			}
		}

		return null;
	}

	/**
	 * This method will be used to save user object in datastore
	 * 
	 * @return boolean if user is saved correctly or not
	 */
	/**
	 * 
	 * @return null
	 */
	
	public boolean saveUser() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		System.out.println("Size = " + list.size());
		
		try {
		Entity employee = new Entity("users", list.size() + 2);

		employee.setProperty("name", this.name);
		employee.setProperty("email", this.email);
		employee.setProperty("password", this.password);
		
		datastore.put(employee);
		txn.commit();
		return true;
		}catch(Exception e)
		{
			return false;		
		}
		
		finally{			
			     if (txn.isActive()) {
		        txn.rollback();
		    }
			
		}
		

	}
	
	/**
	 * 
	 * @param friendEmail
	 * for sending request
	 */
	
	public static boolean sendrequest(String friendEmail) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("friends");
		Query gaeQuery1 = new Query("users");

		PreparedQuery pq = datastore.prepare(gaeQuery);
		PreparedQuery pq1 = datastore.prepare(gaeQuery1);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity employee = new Entity("friends", list.size() + 2);
			
		for (Entity entity : pq1.asIterable()) {
				if (entity.getProperty("email").toString().equals(friendEmail))

				{
					employee.setProperty("friendEmail", friendEmail);

					employee.setProperty("myEmail", User.getCurrentActiveUser()
							.getEmail().toString());
					employee.setProperty("status", "pending");

					datastore.put(employee);
					return true;
				} 
				  else {

				}
			}

		return false;
	}
	
	/**
	 * 
	 * @param fname
	 * for accepting friend request
	 * @return null
	 */
	
	public static UserEntity getrequest(String fname) {
		String myEmail=User.getCurrentActiveUser().getEmail().toString();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
         if (entity.getProperty("friendEmail").toString().equals(fname)&&entity.getProperty("myEmail").toString().equals(myEmail)) 
           {
				
			entity.setProperty("status", "accept");
						
			datastore.put(entity);
				
			}
       else{
	
              }
		}

		return null;
	}

	
	/**
	 * for search on person
	 * @param email
	 * @return null
	 */
	
	public static UserEntity search(String email) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		
		for (Entity entity : pq.asIterable()) {
			    
			if (entity.getProperty("email").toString().equals(email)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString());
				returnedUser.setId(entity.getKey().getId());
				return returnedUser;
			                                                          }
else{
				
     }
		}

		return null;
	}

	
	/**
	 * used for person to accept 
	 * @return array list of string 
	 * 
	 */
	
	public static ArrayList<String> Searching_OnPeople_who_AddMe() {
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		ArrayList<String> requests= new ArrayList<String>();
		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);

		for (Entity entity : pq.asIterable()) {
			
			if (entity.getProperty("friendEmail").toString().equals(User.getCurrentActiveUser().getEmail().toString())&&
					!entity.getProperty("status").toString().equals("accept")) {
				
				requests.add(entity.getProperty("myEmail").toString());
				
			                                     }
else{
				 }
		}
		return requests;
	
	}
	
	/**
	 * 
	 * @param friendemail
	 * @param mess
	 * for send mess
	 * 
	 */
	
	public static boolean sendmsg(String friendemail,String message) {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			
			Query gaeQuery = new Query("msg");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			try{
			
			Entity employee = new Entity("msg", list.size() + 1);
			employee.setProperty("friendEmail",friendemail );
			
			employee.setProperty("myEmail", User.getCurrentActiveUser().getEmail().toString());
			//employee.setProperty("status", "pending");
			System.out.println(message);
			employee.setProperty("message", message);
		
		datastore.put(employee);
			return true;
			}
			catch(Exception e){
			
			return false;}
		}
	
	/**
	 * 
	 * @param membername
	 * @param convID
	 * send group meaasge
	 */
	
	public static boolean sendgroup_mesg(String membername,int convID) {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			
			Query gaeQuery = new Query("group");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			
			try{
			
			Entity employee = new Entity("group", list.size() + 1);
			employee.setProperty("membername", membername);
			
			employee.setProperty("group_ID", convID);
		
			
			datastore.put(employee);
				return true;}
			catch(Exception e){
			
			return false;
			}
			
		     }
	
	/**
	 * 
	 * @param message
	 * @param chat_id
	 * for sending mess
	 */
	
public static boolean chat_message(String message,int chat_id) {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			
			Query gaeQuery = new Query("group_chat");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			try{
			
				Entity employee = new Entity("group_chat", list.size() + 1);
			    employee.setProperty("Message", message);
			
			    employee.setProperty("group_ID", chat_id);
			    employee.setProperty("sender",User.getCurrentActiveUser().getName().toString());
		         datastore.put(employee);
			
		         return true;
		         }
			catch(Exception e){
			
				return false;
			}		
			}
	
	
	
	/*public static void conversation(String myemail,String mess,int convID) {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			
			Query gaeQuery = new Query("conversation");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			
			
			Entity employee = new Entity("conversation", list.size() + 1);
			employee.setProperty("conversationID",convID );
			
			employee.setProperty("Sender", User.getCurrentActiveUser().getName().toString());
			//employee.setProperty("status", "pending");
			//System.out.println(mess);
			employee.setProperty("message", mess);
		
			
			datastore.put(employee);
			
			
			//return true;
		}*/

/**********************************************************************************************/

public static String activepage(String name) {
	
	System.out.println(name);
	     DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Query gaeQuery = new Query("page_like");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		
		try{
		Entity employee = new Entity("page_like", list.size() + 1);
		
		employee.setProperty("page_name",name );
		employee.setProperty("who_like_page",User.getCurrentActiveUser().getName().toString() );
		datastore.put(employee);
		
		DatastoreService datastore1 = DatastoreServiceFactory.getDatastoreService();
		 
		boolean flag=false;
		Query gaeQuery1 = new Query("page");
		PreparedQuery pq1 = datastore1.prepare(gaeQuery1);
		
		List<Entity> list1 = pq1.asList(FetchOptions.Builder.withDefaults());
		
		for (Entity entity : pq1.asIterable()) {
			
			if (entity.getProperty("page_name").toString().equals(name)) {
				
				 Entity employee1 = new Entity("page");
				employee1.setProperty("numberOflike",Integer.parseInt(entity.getProperty("numberOflike").toString())+1 );
				employee1.setProperty("category", entity.getProperty("category"));
				employee1.setProperty("type",entity.getProperty("type") );
				employee1.setProperty("page_name",entity.getProperty("page_name"));
				datastore1.delete(entity.getKey());
				datastore1.put(employee1);
				UserEntity returnedUser = new UserEntity(entity.getProperty("page_name").toString());
		//		flag=true;
				returnedUser.setId(entity.getKey().getId());
		                                                                  }
else{
				
		}
		}
	     
		return "el7"; }
		
		catch(Exception e){
			
			return "fail";
		                   }
		                   };
/*public static UserEntity activepage(String name)
{
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();

	Query gaeQuery = new Query("page likes");
	PreparedQuery pq = datastore.prepare(gaeQuery);
	for (Entity entity : pq.asIterable()) {
if (entity.getProperty("page name").toString().equals(name)) 
        { int numberOfLikes=0;			
		entity.setProperty("numberOfLike",numberOfLikes+1);
		
		//ezay nezowed 1 fe el data store 3ashan el like	
		}
	}

	return null;

	
}*/


public static boolean create_page(String name,String category,String type)
{
	
	System.out.println(name);
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	try {
	boolean flag=false;
	Query gaeQuery = new Query("page");
	PreparedQuery pq = datastore.prepare(gaeQuery);
	
	if(!flag)
	{
		Entity employee1 = new Entity("page");
		employee1.setProperty("numberOflike",0 );
		employee1.setProperty("category", category);
		employee1.setProperty("type",type );
	//	employee1.setProperty("ID/Name",entity.getProperty("ID/Name") );
		employee1.setProperty("page_name",name);
		
		datastore.put(employee1);
		
	         }
	else{
		
	     }
	return true;}
	
	catch (Exception e){
		return false;
	                      }
}

public static boolean savepost(String post,String Post_To) {
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();

	Query gaeQuery = new Query("Posts");
	PreparedQuery pq = datastore.prepare(gaeQuery);
	List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
         
	try {
	
		Entity employee = new Entity("Posts", list.size() + 2);
	employee.setProperty("Post_From", User.getCurrentActiveUser().getName()
			.toString());
	
	employee.setProperty("Post", post);

	employee.setProperty("Post_To", Post_To);
	

	datastore.put(employee);

	 return true;
	     }
catch (Exception e){
	return false;
}
}

public static boolean createTimelinePost (String PostType,String TimelineName,String Post,String Feeling,String Privacy) 
		//throws InstantiationException, IllegalAccessException, ClassNotFoundException  
{
System.out.println(PostType + " " + TimelineName + " " + Post + " " + Feeling+ " " + Privacy);
try{

Builder b = new Builder();

//b.setPost(PostType);
b.setTimelineName(TimelineName);
b.setPost(Post);
b.setFeeling(Feeling);
b.setPrivacy(Privacy);

b.checkType (PostType);

return true;
}

catch(Exception e){
	return false;
                  }}

public static UserEntity seen_page(String name) {
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();

	Query gaeQuery = new Query("PagePosts");
	PreparedQuery pq = datastore.prepare(gaeQuery);


List<Entity> list1 = pq.asList(FetchOptions.Builder.withDefaults());
for (Entity entity : pq.asIterable()) {
	
	if (entity.getProperty("TimelineName").toString().equals(name)) {
		
		Entity employee1 = new Entity("PagePosts");
		employee1.setProperty("numofseen",Integer.parseInt(entity.getProperty("numofseen").toString())+1 );
		employee1.setProperty("Feeling", entity.getProperty("Feeling"));
		employee1.setProperty("Post",entity.getProperty("Post") );
		employee1.setProperty("Privacy",entity.getProperty("Privacy"));
		employee1.setProperty("TimelineName",entity.getProperty("TimelineName"));
		datastore.delete(entity.getKey());
		datastore.put(employee1);
		UserEntity returnedUser = new UserEntity(entity.getProperty("TimelineName").toString());
//		flag=true;
		returnedUser.setId(entity.getKey().getId());
}
}

	return null;
}




public static String create_hashtag(String hashtag,String hash_tag_post) {
	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	try{	
		Query gaeQuery = new Query("hashtag");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		
		
		Entity employee = new Entity("hashtag", list.size() + 1);
		employee.setProperty("hashtag",hashtag);
		employee.setProperty("post",hash_tag_post);
		employee.setProperty("myEmail", User.getCurrentActiveUser().getEmail().toString());
		datastore.put(employee);
		
		return "created";}
	
	catch(Exception e){
		
		return "failed";}
	}
	}


