package com.FCI.SWE.ServicesModels;

import java.util.List;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class reciever {
	
	
	
public static void savenotification(String notification) {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			
			Query gaeQuery = new Query("notifications");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			Entity employee = new Entity("notifications", list.size() + 1);
			employee.setProperty("notifications",notification );
			
			//employee.setProperty("group_ID", chat_id);
			employee.setProperty("sender",User.getCurrentActiveUser().getName().toString());
		
			
			datastore.put(employee);
			
			
			//return true;
		}
	
	
	

}
