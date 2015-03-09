package com.FCI.SWE.ServicesModels;

import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import com.FCI.SWE.Models.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

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
	public UserEntity( String email) {
		//this.name = name;
		this.email = email;
		}
	
	public UserEntity(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
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
	
	

	
	/*public static UserEntity getrequest( String Email) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if ( entity.getProperty("email").toString().equals(Email)) {
				UserEntity returnedUser = new UserEntity( entity.getProperty("email")
						.toString());
			  returnedUser.setId(entity.getKey().getId());
				return returnedUser;
			}
		}

		return null;
	}*/
	
	public static void  getrequest( String name) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();	
		
			Query gaeQuery = new Query("friend");
			PreparedQuery pq = datastore.prepare(gaeQuery);
		List <Entity> list =pq.asList(FetchOptions.Builder.withDefaults())	;
		Entity employee =new Entity ("friend",list.size()+1);
		employee.setProperty("fname", name);
		employee.setProperty("myname", User.getCurrentActiveUser().getName().toString());
		employee.setProperty("status", "pending");
		datastore.put(employee);
		
	}

		
		//return null;
	



	/*public static UserEntity addfriend( String Email) {//function number 3
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("friend");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if ( entity.getProperty("email").toString().equals(Email)) {
				entity.setProperty("status", "active");
			}
		}

		return null;
	}
*/
	public static UserEntity acceptfriend() {
		String name=User.getCurrentActiveUser().getName().toString();
		System.out.println("Name is = " + name);
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("friend");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("myname").toString().equals(name)) {
				System.out.println("accept");
				entity.setProperty("status", "active");
				datastore.put(entity);
			}
		}

		return null;
	}
	
	
	/**
	 * This method will be used to save user object in datastore
	 * 
	 * @return boolean if user is saved correctly or not
	 */
	public Boolean saveUser() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("users", list.size() + 1);

		employee.setProperty("name", this.name);
		employee.setProperty("email", this.email);
		employee.setProperty("password", this.password);
		datastore.put(employee);

		return true;

	}
	/*public static void savefriend(String uname) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
	//	System.out.print("aaaaaaaaaaaaaaa");
		Query gaeQuery = new Query("friend");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("friend",  list.size()+1);

		employee.setProperty("name",uname);
		employee.setProperty("Myname",User.getCurrentActiveUser().getName().toString());
		//employee.setProperty("email", uname);
		employee.setProperty("status","pending");
		datastore.put(employee);

	//	return true;

	}*/
}
