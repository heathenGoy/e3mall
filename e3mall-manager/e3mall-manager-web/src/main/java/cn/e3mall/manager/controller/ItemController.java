package cn.e3mall.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.manager.pojo.TbItem;
import cn.e3mall.manager.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	ItemService is ;
	
	@RequestMapping("/item/findById/{itemId}")
	@ResponseBody
	public Object findItemById(@PathVariable Long itemId) {
		TbItem result = is.findItemById(itemId);
		
		return result;
	}
}
