package cn.e3mall.manager.interfaces;

import java.util.List;

import cn.e3mall.common.easyui.EasyUIPageBean;
import cn.e3mall.common.easyui.EasyUITreeNode;
import cn.e3mall.common.utils.E3mallResult;
import cn.e3mall.manager.pojo.TbItem;
import cn.e3mall.manager.pojo.TbItemDesc;

public interface ItemService {
	
	TbItem findItemById(Long id);
	
	EasyUIPageBean findItemByPage(int pageNum, int size);
	
	E3mallResult saveItem(TbItem item , TbItemDesc itemDesc);
}
