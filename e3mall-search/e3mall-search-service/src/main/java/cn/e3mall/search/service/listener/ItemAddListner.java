package cn.e3mall.search.service.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.search.mapper.SearchItemMapper;
import cn.e3mall.search.pojo.SearchItemObject;

public class ItemAddListner implements MessageListener{
	
	@Autowired
	private SearchItemMapper itemMapper;
	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public void onMessage(Message message) {
		if(message instanceof TextMessage) {
			try {
				String itemId = ((TextMessage) message).getText();
				SearchItemObject resultItem = itemMapper.selectItemById(Long.parseLong(itemId));
				
				
				if(resultItem != null) {
					
					SolrInputDocument doc = new SolrInputDocument();
					//封装Id
					doc.addField("id", resultItem.getId());
					//封装商品标题
					doc.addField("item_title", resultItem.getTitle());
					//封装商品买点
					doc.addField("item_sell_point",resultItem.getSell_point());
					//封装商品价格
					doc.addField("item_price", resultItem.getPrice());
					//封装商品图片地址
					doc.addField("item_image", resultItem.getImage());
					//商品类别
					doc.addField("item_category_name",
					resultItem.getCategory_name());
					//商品描述
					doc.addField("item_desc", resultItem.getItem_desc());
					
					solrServer.add(doc);
					solrServer.commit();
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
