package cn.e3mall.search.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索结果的返回对象, 包含了总记录数, 当前页数, 商品信息, 总页数
 * @author 15161
 *
 */
public class SearchResult implements Serializable {
	
	
	private Long recordCount;
	private List<SearchItemObject> itemList;
	private Integer pageCount;
	private Integer curPage;
	
	public Long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
	public List<SearchItemObject> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchItemObject> itemList) {
		this.itemList = itemList;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
}
