package cn.e3mall.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.e3mall.search.pojo.SearchItemObject;
import cn.e3mall.search.pojo.SearchResult;

@Repository
public class SearchItemBySolrDaoImpl implements SearchItemBySolrDao{
	
	@Autowired
	private SolrServer server;
	
	@Override
	public SearchResult searchItem(SolrQuery query) {
		SearchResult result = new SearchResult();
		List<SearchItemObject> itemList = new ArrayList<>();
		
		try {
			
			QueryResponse response = server.query(query);
			
			SolrDocumentList  resultDocs = response.getResults();
			
			//设置总记录数	
			long numFound = resultDocs.getNumFound();
			result.setRecordCount(numFound);
			
			
			for (SolrDocument eachDoc : resultDocs) {
				SearchItemObject eachItem = new SearchItemObject();
				
				String id = (String)eachDoc.getFieldValue("id");
				eachItem.setId(Long.parseLong(id));
				
				//获取商品标题, 有可能高亮里面没有, 因为查询的关键字在买点中而不在标题中
				String title = (String) eachDoc.get("eachItem_title");
				
				
				//获取高亮
				Map<String, Map<String, List<String>>> hlItemMap = response.getHighlighting();
				//通过id获取目标商品的高亮信息
				Map<String, List<String>> hl_item = hlItemMap.get(id);
				//通过fieldName获取目标商品的一个高亮字段, 注意可能为空, 因为关键字没有 出现在这个字段中
				List<String> field = hl_item.get("item_title");
				//如果有高亮标题, 那么替换成高亮标题
				if(field != null && field.size() > 0 ) {
					title = field.get(0);
				}
				
				eachItem.setTitle(title);
				
				//获取索引库中商品买点
				String sellPoint = (String) eachDoc.get("item_sell_point");
				eachItem.setSell_point(sellPoint);
				//获取索引库商品价格
				Long price = (Long) eachDoc.get("item_price");
				eachItem.setPrice(price);
				//获取图片地址
				String image = (String) eachDoc.get("item_image");
				
				eachItem.setImage(image);
				//获取商品类别名称
				String categoryName = (String) eachDoc.get("item_category_name");
				eachItem.setCategory_name(categoryName);
				//获取商品描述信息
				String desc = (String) eachDoc.get("item_desc");
				eachItem.setItem_desc(desc);
				
				
				
				//把索引库数据放入 pojo， 把 pojo 放入集合
				itemList.add(eachItem);
			}
			
			
			result.setItemList(itemList);
			return result;
			
			
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
