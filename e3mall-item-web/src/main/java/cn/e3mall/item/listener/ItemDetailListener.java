package cn.e3mall.item.listener;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.e3mall.manager.interfaces.ItemService;
import cn.e3mall.manager.pojo.TbItem;
import cn.e3mall.manager.pojo.TbItemDesc;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

public class ItemDetailListener implements MessageListener {

	@Autowired
	private ItemService itemService;
	
	@Value("{HTML_GEN_PATH}")
	private String HTML_GEN_PATH;
	
	@Autowired
	private FreeMarkerConfigurer config;
	
	@Override
	public void onMessage(Message message) {
		try {
			
			Long itemId = null;
			
			if (message instanceof TextMessage) {
			
				String itemIdStr = ((TextMessage) message).getText();
				itemId = Long.parseLong(itemIdStr);
			}
			
			Configuration configuration = config.getConfiguration();
			Template template = configuration.getTemplate("item.flt");
			
			
			TbItem targetItem = itemService.findItemById(itemId);
			TbItemDesc targetItemDesc = itemService.findItemDescById(itemId);
			
			Map<String, Object> data = new HashMap<>();
			data.put("item", targetItem);
			data.put("itemDesc", targetItemDesc);
			
			FileWriter writer = new FileWriter(HTML_GEN_PATH + itemId + ".html" );
			
			template.process(data, writer);
			
			writer.close();
			
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
