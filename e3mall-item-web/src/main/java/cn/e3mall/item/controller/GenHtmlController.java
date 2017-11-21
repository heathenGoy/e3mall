package cn.e3mall.item.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Controller
public class GenHtmlController {
	
	@Autowired
	private FreeMarkerConfigurer config;
	
	@RequestMapping("genHtml")
	@ResponseBody
	public String genHtml() throws Exception{
		Configuration conf = config.getConfiguration();
		Template template = conf.getTemplate("time.flt");
		
		
		Map<String, Object> data = new HashMap<>();
		data.put("today", new Date());
		
		Writer writer = new FileWriter(new File("E:\\template\\out\\time.html"));
		template.process(data, writer);
		
		return "ok";
	}
}
