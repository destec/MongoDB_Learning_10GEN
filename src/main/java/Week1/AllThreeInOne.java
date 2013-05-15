package Week1;

import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class AllThreeInOne {
	public static void main(String[] args) throws UnknownHostException {
		final Configuration conf = new Configuration();
		conf.setClassForTemplateLoading(AllThreeInOne.class, "/");

		MongoClient client = new MongoClient(new ServerAddress(
				"localhost", 27017));
		DB database = client.getDB("test");
		final DBCollection collection = database.getCollection("test");
		
		Spark.get(new Route("/") {

			@Override
			public Object handle(Request arg0, Response arg1) {
				StringWriter writer = new StringWriter();
				try {
					Template helloTemplate = conf
							.getTemplate("views/Hello.ftl");

					//Map<String, Object> helloMap = new HashMap<String, Object>();
					//helloMap.put("name", "Spark & Freemarker");
					DBObject doc = collection.findOne();

					helloTemplate.process(doc, writer);

				} catch (Exception e) {
					halt(500);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return writer;
			}

		});
	}
}
