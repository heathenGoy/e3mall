package cn.e3mall.search.interfaces;

import cn.e3mall.common.utils.E3mallResult;
import cn.e3mall.search.pojo.SearchResult;

public interface SearchItemService {
	
	E3mallResult loadItemIndex();
	
	SearchResult searchItem(String qName, Integer pageNum, Integer rows);
}
