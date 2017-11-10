package cn.e3mall.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.easyui.EasyUIPageBean;
import cn.e3mall.common.utils.E3mallResult;
import cn.e3mall.manager.interfaces.ItemService;
import cn.e3mall.manager.pojo.TbItem;
import cn.e3mall.manager.pojo.TbItemDesc;

@Controller
public class ItemController {
	
	@Autowired
	ItemService is ;
	
	@RequestMapping("/item/save")
	@ResponseBody
	public Object saveItem(TbItem item, TbItemDesc itemDesc) {
		
		E3mallResult result = is.saveItem(item, itemDesc);
		
		
		return result;
	}
	
	@RequestMapping("/item/findById/{itemId}")
	@ResponseBody
	public Object findItemById(@PathVariable Long itemId) {
		TbItem result = is.findItemById(itemId);
		
		return result;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIPageBean itemList(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "20") Integer rows) {
		 
		 EasyUIPageBean resultPage = is.findItemByPage(page, rows);
		 
		return resultPage;
	}
}
