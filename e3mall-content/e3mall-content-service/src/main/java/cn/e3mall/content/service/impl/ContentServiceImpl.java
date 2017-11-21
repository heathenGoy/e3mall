package cn.e3mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.easyui.EasyUIPageBean;
import cn.e3mall.common.utils.ADItem;
import cn.e3mall.common.utils.E3mallResult;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.content.interfaces.ContentService;
import cn.e3mall.content.interfaces.JedisService;
import cn.e3mall.manager.dao.TbContentMapper;
import cn.e3mall.manager.pojo.TbContent;
import cn.e3mall.manager.pojo.TbContentExample;
import cn.e3mall.manager.pojo.TbContentExample.Criteria;

@Service
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	TbContentMapper cm;
	
	@Autowired
	JedisService js;
	
	private static final String REDIS_CACHE_AD = "REDIS_CACHE_AD"; 
	
	@Override
	public EasyUIPageBean findPageContentByCatId(Long catId, Integer pageNum, Integer size) {
		TbContentExample example = new TbContentExample();
		
		Criteria criteria = example.createCriteria();
		
		criteria.andCategoryIdEqualTo(catId);
		
		PageHelper.startPage(pageNum, size);
		
		List<TbContent> resultList = cm.selectByExample(example);
		
		PageInfo<TbContent> pageinfo = new PageInfo<>(resultList);
		
		EasyUIPageBean page = new EasyUIPageBean();
		page.setRows(resultList);
		page.setTotal(pageinfo.getTotal());
		
		return page;
	}

	@Override
	public E3mallResult addContent(TbContent content) {
		js.hdel(REDIS_CACHE_AD, content.getCategoryId() + "");
		
		Date date = new Date();
		content.setUpdated(date);
		content.setCreated(date);
		
		cm.insert(content);
		
		return E3mallResult.ok();
	}

	@Override
	public List<ADItem> findADContentByCatId(Long catId) {
		String jedisResult = js.hget(REDIS_CACHE_AD, catId + "");
		
		//如果没有查到, 从数据库中查询
		if(StringUtils.isEmpty(jedisResult)) {
			TbContentExample example = new TbContentExample();
			Criteria criteria = example.createCriteria();
			criteria.andCategoryIdEqualTo(catId);
			
			List<TbContent> resultList = cm.selectByExample(example);
			List<ADItem> adList = new ArrayList<>();
			
			for (TbContent eachTbContent : resultList) {
				ADItem eachADItem = new ADItem();
				eachADItem.setSrc(eachTbContent.getPic());
				eachADItem.setSrcB(eachTbContent.getPic2());
				eachADItem.setHref(eachTbContent.getUrl());
				eachADItem.setAlt(eachTbContent.getTitle());
				
				adList.add(eachADItem);
			}
			
			//把这个对象以json的形式存储到redis 中
			js.hset(REDIS_CACHE_AD, catId + "", JsonUtils.objectToJson(adList));
			
			return adList;
			
			//如果redis查到了, 把存储的字符串转换为对象返回
		}else{
			return JsonUtils.jsonToList(jedisResult, ADItem.class);
		}
		
	}

	
}

