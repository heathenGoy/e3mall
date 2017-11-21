package cn.e3mall.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.utils.E3mallResult;
import cn.e3mall.search.dao.SearchItemBySolrDao;
import cn.e3mall.search.interfaces.SearchItemService;
import cn.e3mall.search.mapper.SearchItemMapper;
import cn.e3mall.search.pojo.SearchItemObject;
import cn.e3mall.search.pojo.SearchResult;

@Service
public class SearchItemServiceImpl implements SearchItemService {
	
	@Autowired
	SearchItemMapper sim;
	
	@Autowired
	SolrServer solrServer;
	
	@Autowired
	SearchItemBySolrDao itemDao;
	
	@Override
	public E3mallResult loadItemIndex() {
		try {
		//查询数据库的相关数据
		List<SearchItemObject> resultList = sim.loadItemIndex();
		
		
		for (SearchItemObject eachItem : resultList) {
			
			SolrInputDocument document = new SolrInputDocument();
			document.addField("id", eachItem.getId());
			document.addField("item_title",eachItem.getTitle());
			document.addField("item_image",eachItem.getImage());
			document.addField("item_sell_point",eachItem.getSell_point());
			document.addField("item_price",eachItem.getPrice());
			document.addField("item_desc",eachItem.getItem_desc());
			document.addField("item_category_name",eachItem.getCategory_name());
			
			solrServer.add(document);
		}
		
		solrServer.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
		
		return E3mallResult.ok();
	}

	@Override
	public SearchResult searchItem(String qName, Integer pageNum, Integer rows) {
		
		SolrQuery query = new SolrQuery();
		
		if(qName != null && ! qName.trim().equals("")) {
			//要配合后面设置的默认字段
			query.setQuery(qName);
			query.set("df", "item_keywords");
		}else {
			query.setQuery("*:*");
		}
		
		//设置分页
		query.setStart((pageNum - 1 ) * rows);
		query.setRows(rows);
		
		//设置高亮
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<font" + 
				"class=\"skcolor_ljg\">");
		query.setHighlightSimplePost("</font>");
		
		//把result对象补全 
		SearchResult result = itemDao.searchItem(query);
		
		long recordTotal = result.getRecordCount();
		
		result.setPageCount((int)Math.ceil(recordTotal / rows));
		result.setCurPage(pageNum);
		return result;
	}

}
