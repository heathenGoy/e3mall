package cn.e3mall.manager.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.easyui.EasyUIPageBean;
import cn.e3mall.common.utils.E3mallResult;
import cn.e3mall.content.interfaces.ContentCatService;
import cn.e3mall.content.interfaces.ContentService;
import cn.e3mall.manager.pojo.TbContent;

@Controller
public class ContentController {
	
	@Autowired
	ContentService cs;
	
	@RequestMapping("/content/save")
	@ResponseBody
	public Object addContent(TbContent content) {
		
		E3mallResult result = cs.addContent(content);
		return result;
	}
	
	@RequestMapping("content/query/list")
	@ResponseBody
	public Object queryContent(Long categoryId, Integer page, Integer rows) {
		
		EasyUIPageBean resultPage = cs.findPageContentByCatId(categoryId, page, rows);
		
		return resultPage;
	}
}
