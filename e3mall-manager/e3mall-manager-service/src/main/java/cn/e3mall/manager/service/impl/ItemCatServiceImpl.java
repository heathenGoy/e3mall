package cn.e3mall.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.easyui.EasyUIPageBean;
import cn.e3mall.common.easyui.EasyUITreeNode;
import cn.e3mall.manager.dao.TbItemCatMapper;
import cn.e3mall.manager.dao.TbItemMapper;
import cn.e3mall.manager.interfaces.ItemCatService;
import cn.e3mall.manager.interfaces.ItemService;
import cn.e3mall.manager.pojo.TbItem;
import cn.e3mall.manager.pojo.TbItemCat;
import cn.e3mall.manager.pojo.TbItemCatExample;
import cn.e3mall.manager.pojo.TbItemCatExample.Criteria;
import cn.e3mall.manager.pojo.TbItemExample;


@Service
public class ItemCatServiceImpl implements ItemCatService{
	
	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EasyUITreeNode> findItemCatByParentId(Long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria findByParentId = example.createCriteria();
		findByParentId.andParentIdEqualTo(parentId);
		
		
		List<TbItemCat> resultList = itemCatMapper.selectByExample(example);
		
		
		List<EasyUITreeNode> nodeList = new ArrayList<>();
		
		for (TbItemCat eachItemCat : resultList) {
			
			EasyUITreeNode eachNode = new EasyUITreeNode();
			
			eachNode.setId(eachItemCat.getId().intValue());
			eachNode.setState(eachItemCat.getIsParent()?"closed":"open");
			eachNode.setText(eachItemCat.getName());
			
			nodeList.add(eachNode);
			
		}
		
		return nodeList;
	}
	
	
}
