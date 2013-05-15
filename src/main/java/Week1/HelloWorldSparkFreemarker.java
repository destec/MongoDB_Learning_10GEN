package Week1;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldSparkFreemarker {
	public static void main(String[] args) {

		final Configuration conf = new Configuration();
		conf.setClassForTemplateLoading(HelloWorldSparkFreemarker.class, "/");

		Spark.get(new Route("/") {

			@Override
			public Object handle(Request arg0, Response arg1) {
				StringWriter writer = new StringWriter();
				try {
					Template helloTemplate = conf.getTemplate("views/Hello.ftl");
					
					Map<String, Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name", "Spark & Freemarker");
					
					helloTemplate.process(helloMap, writer);
					
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
