package cn.e3mall.content.interfaces;

import java.util.List;

import cn.e3mall.common.easyui.EasyUIPageBean;
import cn.e3mall.common.easyui.EasyUITreeNode;
import cn.e3mall.common.utils.ADItem;
import cn.e3mall.common.utils.E3mallResult;
import cn.e3mall.manager.pojo.TbContent;

public interface ContentService {

	EasyUIPageBean findPageContentByCatId(Long catId, Integer pageNum, Integer size);
	
	E3mallResult addContent(TbContent content);
	
	List<ADItem> findADContentByCatId(Long catId);
}
