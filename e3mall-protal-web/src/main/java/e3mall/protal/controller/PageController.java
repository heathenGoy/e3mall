package e3mall.protal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.e3mall.common.utils.ADItem;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.content.interfaces.ContentService;

@Controller
public class PageController {
	
	@Value("${AD1_CATEGORY_ID}")
	private Long AD1_CATEGORY_ID;
	@Value("${AD1_WIDTH}")
	private Integer AD1_WIDTH;
	@Value("${AD1_WIDTHB}")
	private Integer AD1_WIDTHB;
	@Value("${AD1_HEIGHT}")
	private Integer AD1_HEIGHT;
	@Value("${AD1_HEIGHTB}")
	private Integer AD1_HEIGHTB;
	
	@Autowired
	ContentService sc;
	
	@RequestMapping("/index")
	public String toIndex(Model model) {
		List<ADItem> resultADs = sc.findADContentByCatId(AD1_CATEGORY_ID);
		
		for (ADItem eachAdItem : resultADs) {
			eachAdItem.setHeight(AD1_HEIGHT);
			eachAdItem.setHeightB(AD1_HEIGHTB);
			eachAdItem.setWidth(AD1_WIDTH);
			eachAdItem.setWidthB(AD1_WIDTHB);
		}
		
		
		String jsonADs = JsonUtils.objectToJson(resultADs);
		model.addAttribute("ad1", jsonADs);
		
		return "index";
	}
}
