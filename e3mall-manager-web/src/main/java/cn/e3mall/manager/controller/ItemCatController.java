package cn.e3mall.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.easyui.EasyUITreeNode;
import cn.e3mall.manager.interfaces.ItemCatService;

@Controller
public class ItemCatController {
	
	@Autowired
	ItemCatService is ;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public Object treeNode(@RequestParam(defaultValue = "0", value = "id")Long parentId) {
		
		List<EasyUITreeNode> nodeList = is.findItemCatByParentId(parentId);
		
		return nodeList;
		
	}
}
