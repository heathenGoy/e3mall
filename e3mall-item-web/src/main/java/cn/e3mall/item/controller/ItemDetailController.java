package cn.e3mall.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.e3mall.manager.interfaces.ItemService;
import cn.e3mall.manager.pojo.TbItem;
import cn.e3mall.manager.pojo.TbItemDesc;

@Controller
public class ItemDetailController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("{itemId}")
	public String toItemDetail(@PathVariable Long itemId, Model model) {
		
		TbItem resultItem = itemService.findItemById(itemId);
		TbItemDesc resultItemDesc = itemService.findItemDescById(itemId);
		
		model.addAttribute("item",resultItem);
		model.addAttribute("itemDesc",resultItemDesc);
		
		return "item";
	}
}
