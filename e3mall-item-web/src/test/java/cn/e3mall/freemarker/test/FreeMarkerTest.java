package cn.e3mall.freemarker.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.ConfigurationException;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerTest {
	/*
	public static void userFreeMarker(String fileName, Map<String, Object> data) {
		try {
		
		Configuration conf = new Configuration(Configuration.getVersion());
		conf.setDefaultEncoding("utf-8");
		conf.setDirectoryForTemplateLoading(new File("E:\\template"));
		
		Template template = conf.getTemplate(fileName + ".flt");
		
		
		FileWriter writer = new FileWriter(new File("E://template//out//" + fileName + ".html"));
		template.process(data, writer);
		
		writer.close();
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void time() {
		String fileName = "time";
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("today", new Date());
		
		userFreeMarker(fileName, dataMap);
		
	}
	
	@Test
	public void list() {
		String fileName = "list";
		List<Person> persons = new ArrayList<>();
		Map<String, Object> dataMap = new HashMap<>();
		
		Person person = new Person();
		//person.setAddress("Beijing");
		person.setAge(44);
		person.setName("小方");
		persons.add(person);
		
		Person person2 = new Person();
		person2.setAddress("Nanjing");
		person2.setAge(43);
		person2.setName("小芳");
		persons.add(person2);
		
		Person person3 = new Person();
		person3.setAddress("Nanjing");
		//person3.setAge(43);
		person3.setName("小芳");
		persons.add(person3);
		
		dataMap.put("personList", persons);
		
		userFreeMarker(fileName, dataMap);
	}
	
	@Test
	public void pojo() {
		String fileName  = "pojo";
		Map <String, Object> dataMap = new HashMap<>();
		
		Person person = new Person();
		person.setAddress("Beijing");
		person.setAge(44);
		person.setName("小方");
		dataMap.put("person", person);
		
		userFreeMarker(fileName, dataMap);
		
	}
	
	@Test
	public void  hello() throws IOException, TemplateException {
		Configuration conf = new Configuration(Configuration.getVersion());
		//获取模板文件, 及编码格式
		conf.setDirectoryForTemplateLoading(new File("E:\\template"));
		conf.setDefaultEncoding("utf-8");
		
		//获取模板对象
		Template template = conf.getTemplate("test.flt");
		Map<String, Object> map = new HashMap<>();
		
		map.put("hello",false);
		Writer writer = new FileWriter(new File("E://template//out//hello.html"));
		
		template.process(map, writer);
		writer.close();
	}*/
}
