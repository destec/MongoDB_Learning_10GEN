package Week1;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkFormHandling {
	public static void main(String[] args) {
		
		final Configuration conf = new Configuration();
		conf.setClassForTemplateLoading(SparkFormHandling.class, "/");

		Spark.get(new Route("/") {

			StringWriter writer = new StringWriter();

			@Override
			public Object handle(Request request, Response response) {
				// TODO Auto-generated method stub
				try {
					Map<String, Object> fruitMap = new HashMap<String, Object>();
					fruitMap.put("fruits",
							Arrays.asList("apple", "orange", "banana", "peach"));

					Template fruitPickerTemplate = conf
							.getTemplate("views/FruitPicker.ftl");

					fruitPickerTemplate.process(fruitMap, writer);
					return writer;

				} catch (Exception e) {
					//halt(500);
					e.printStackTrace();
					return null;
					
				}
			}
		});

		Spark.post(new Route("/favourite_fruit") {

			@Override
			public Object handle(Request request, Response response) {
				final String fruit = request.queryParams("fruit");
				if (fruit == null) {
					return "Why dont you pick one?";
				} else {
					return "Your favourite fruit is " + fruit;
				}
			}

		});
	}
}
