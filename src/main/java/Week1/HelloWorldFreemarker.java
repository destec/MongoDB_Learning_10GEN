package Week1;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldFreemarker {
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		conf.setClassForTemplateLoading(HelloWorldFreemarker.class, "/");
		
		try {
			Template helloTemplate = conf.getTemplate("views/Hello.ftl");
			StringWriter writer = new StringWriter();
			HashMap<String, Object> helloMap = new HashMap<String, Object>();
			helloMap.put("name", "Freemarker");
			
			helloTemplate.process(helloMap, writer);
			
			System.out.println(writer);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
