package cn.e3mall.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.easyui.EasyUITreeNode;
import cn.e3mall.content.interfaces.ContentCatService;

@Controller
public class ContentCatController {
	
	@Autowired
	private ContentCatService ccs;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public Object categoryTree(
			@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		
		List<EasyUITreeNode> nodeList = ccs.findContentCatByParentId(parentId);
		
		
		return nodeList;
		
	}
	
	
}
