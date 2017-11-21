package cn.e3mall.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import cn.e3mall.search.pojo.SearchResult;

public interface SearchItemBySolrDao {
	
	SearchResult searchItem(SolrQuery query);
}
