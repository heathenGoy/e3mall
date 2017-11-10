package cn.e3mall.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.easyui.EasyUIPageBean;
import cn.e3mall.manager.interfaces.ItemService;

@Controller
public class JspController {


	@RequestMapping("/index/{jsp}")
	public String toJsp(@PathVariable String jsp) {
		
		return jsp;
	}
	
	
}
