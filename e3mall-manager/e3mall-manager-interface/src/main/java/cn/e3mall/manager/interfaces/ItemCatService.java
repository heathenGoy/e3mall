package cn.e3mall.manager.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.e3mall.common.easyui.EasyUITreeNode;


public interface ItemCatService {
	
	List<EasyUITreeNode> findItemCatByParentId(Long parentId);
	
	
}
