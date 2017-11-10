package cn.e3mall.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.easyui.EasyUIPageBean;
import cn.e3mall.common.utils.E3mallResult;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.manager.dao.TbItemDescMapper;
import cn.e3mall.manager.dao.TbItemMapper;
import cn.e3mall.manager.interfaces.ItemService;
import cn.e3mall.manager.pojo.TbItem;
import cn.e3mall.manager.pojo.TbItemDesc;
import cn.e3mall.manager.pojo.TbItemExample;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	TbItemMapper itemMapper;
	
	@Autowired
	TbItemDescMapper itemDescMapper;
	
	@Override
	public TbItem findItemById(Long id) {
		TbItem result = itemMapper.selectByPrimaryKey(id);
		return result;
	}

	@Override
	public EasyUIPageBean findItemByPage(int pageNum, int size) {
		TbItemExample example = new TbItemExample();
		//自动进行分页查询
		PageHelper.startPage(pageNum, size);
		
		
		List<TbItem> resultList = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<>(resultList);
		
		EasyUIPageBean pageBean = new  EasyUIPageBean();
		pageBean.setTotal(pageInfo.getTotal());
		pageBean.setRows(resultList);
		
		
		return pageBean;
	}

	@Override
	public E3mallResult saveItem(TbItem item, TbItemDesc itemDesc) {
		
		long itemID = IDUtils.genItemId();
		Date date = new Date();
		
		item.setId(itemID);
		item.setCreated(date);
		item.setUpdated(date);
		item.setStatus((byte)1);
		
		
		itemDesc.setItemId(itemID);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		
		itemMapper.insert(item);
		itemDescMapper.insert(itemDesc);
		
		return E3mallResult.ok();
	}


}
