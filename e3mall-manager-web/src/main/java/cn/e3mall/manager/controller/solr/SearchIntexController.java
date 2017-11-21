package cn.e3mall.manager.controller.solr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.E3mallResult;
import cn.e3mall.search.interfaces.SearchItemService;

@Controller
public class SearchIntexController {
	
	@Autowired
	SearchItemService sis;
	
	@RequestMapping("/item/dataImport")
	@ResponseBody
	public E3mallResult loadItemIndex() {
		E3mallResult result = sis.loadItemIndex();
		return result;
	}
}
