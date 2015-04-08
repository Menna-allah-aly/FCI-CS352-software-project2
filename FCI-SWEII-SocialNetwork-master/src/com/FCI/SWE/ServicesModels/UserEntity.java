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
	public Boolean saveUser() {
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
		}
		finally{
			if (txn.isActive()) {
		        txn.rollback();
		    }
		}
		return true;

	}
	/**
	 * 
	 * @param friendEmail
	 * for sending request
	 */
	
	public static void sendrequest(String friendEmail) {
		
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		
		
		Entity employee = new Entity("friends", list.size() + 2);
		employee.setProperty("friendEmail",friendEmail );
		
		employee.setProperty("myEmail", User.getCurrentActiveUser().getEmail().toString());
		employee.setProperty("status", "pending");
		
		
		datastore.put(employee);
		
		
		//return true;
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
if (entity.getProperty("friendEmail").toString().equals(myEmail)&&entity.getProperty("myEmail").toString().equals(fname)) {
				
			entity.setProperty("status", "accept");
						
			datastore.put(entity);
				
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
		}

		return null;
	}

	/**
	 * used for person to accept 
	 * @return array list of string 
	 * 
	 */
	public static ArrayList<String> SearchOnPeopleAdd() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		ArrayList<String> requests= new ArrayList<String>();
		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
	for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("friendEmail").toString().equals(User.getCurrentActiveUser().getEmail().toString())&&
					!entity.getProperty("status").toString().equals("accept")) {
				requests.add(entity.getProperty(
						"myEmail").toString());
				
			}
		}
		return requests;
		//return null;
	}
	/**
	 * 
	 * @param friendemail
	 * @param mess
	 * for send mess
	 * 
	 */
	public static void sendmsg(String friendemail,String mess) {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			
			Query gaeQuery = new Query("msg");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			
			
			Entity employee = new Entity("msg", list.size() + 1);
			employee.setProperty("friendEmail",friendemail );
			
			employee.setProperty("myEmail", User.getCurrentActiveUser().getEmail().toString());
			//employee.setProperty("status", "pending");
			System.out.println(mess);
			employee.setProperty("message", mess);
		
			
			datastore.put(employee);
			
			
			//return true;
		}
	/**
	 * 
	 * @param membername
	 * @param convID
	 * send group meaasge
	 */
	
	public static void sendgroup_mesg(String membername,int convID) {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			
			Query gaeQuery = new Query("group");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			
			
			Entity employee = new Entity("group", list.size() + 1);
			employee.setProperty("membername", membername);
			
			employee.setProperty("group_ID", convID);
		
			
			datastore.put(employee);
			
			
			//return true;
		}
	
	/**
	 * 
	 * @param message
	 * @param chat_id
	 * for sending mess
	 */
	
public static void chat_message(String message,int chat_id) {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			
			Query gaeQuery = new Query("group_chat");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			Entity employee = new Entity("group_chat", list.size() + 1);
			employee.setProperty("Message", message);
			
			employee.setProperty("group_ID", chat_id);
			employee.setProperty("sender",User.getCurrentActiveUser().getName().toString());
		
			
			datastore.put(employee);
			
			
			//return true;
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

	
}




