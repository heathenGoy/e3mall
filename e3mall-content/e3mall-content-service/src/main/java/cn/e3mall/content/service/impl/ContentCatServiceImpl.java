package cn.e3mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.easyui.EasyUITreeNode;
import cn.e3mall.common.utils.E3mallResult;
import cn.e3mall.content.interfaces.ContentCatService;
import cn.e3mall.manager.dao.TbContentCategoryMapper;
import cn.e3mall.manager.pojo.TbContentCategory;
import cn.e3mall.manager.pojo.TbContentCategoryExample;
import cn.e3mall.manager.pojo.TbContentCategoryExample.Criteria;

@Service
public class ContentCatServiceImpl implements ContentCatService{
	@Autowired
	private TbContentCategoryMapper contentCatMapper;
	
	@Override
	public List<EasyUITreeNode> findContentCatByParentId(Long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andParentIdEqualTo(parentId);
		
		List<TbContentCategory> resultList = contentCatMapper.selectByExample(example);
		
		List<EasyUITreeNode> nodeList  = new ArrayList<>();
		
		for (TbContentCategory eachContentCat : resultList) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(eachContentCat.getId().intValue());
			node.setState(eachContentCat.getIsParent()?"closed":"open");
			node.setText(eachContentCat.getName());
			
			nodeList.add(node);
		}
		return nodeList;
	}

	@Override
	public E3mallResult addContentCat(Long parentId, String name) {
		TbContentCategory target  = new TbContentCategory();
		target.setIsParent(false);
		target.setName(name);
		target.setParentId(parentId);
		
		Date date  = new Date();
		target.setCreated(date);
		target.setUpdated(date);
		
		target.setStatus(1);
		target.setSortOrder(1);
		
		contentCatMapper.insert(target);
		
		TbContentCategory parentNode = contentCatMapper.selectByPrimaryKey(parentId);
		if( ! parentNode.getIsParent()) {
			
			parentNode.setIsParent(true);
			contentCatMapper.updateByPrimaryKey(parentNode);
		}
		
		return E3mallResult.ok(target);
	}

	@Override
	public E3mallResult deleteContentCat(Long parentId, Long id) {
		contentCatMapper.deleteByPrimaryKey(id);
		
		List<EasyUITreeNode> resultList = findContentCatByParentId(parentId);
		if(resultList.size() == 0) {
			TbContentCategory parentNode = contentCatMapper.selectByPrimaryKey(parentId);
			parentNode.setIsParent(false);
			
			contentCatMapper.updateByPrimaryKey(parentNode);
		}
		
		return E3mallResult.ok();
	}

}
