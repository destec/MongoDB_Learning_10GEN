package Week1;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class HelloWorldMongoDB {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
		
		DB database = client.getDB("test");
		DBCollection collection = database.getCollection("test");
		
		DBObject document = collection.findOne(); 
		
		System.out.println(document);
	}
}
