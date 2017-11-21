package cn.e3mall.content.interfaces;

import java.util.List;

import cn.e3mall.common.easyui.EasyUITreeNode;
import cn.e3mall.common.utils.E3mallResult;

public interface ContentCatService {
	
	List<EasyUITreeNode> findContentCatByParentId(Long parentId);
	
	E3mallResult addContentCat(Long parentId, String name);
	
	E3mallResult deleteContentCat(Long parentId, Long id);
}
