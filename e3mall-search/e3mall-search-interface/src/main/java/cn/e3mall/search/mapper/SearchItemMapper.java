package cn.e3mall.search.mapper;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;

import cn.e3mall.search.pojo.SearchItemObject;
import cn.e3mall.search.pojo.SearchResult;

public interface SearchItemMapper {
	
	List<SearchItemObject> loadItemIndex();
	
	SearchItemObject selectItemById(Long id);
	
}
