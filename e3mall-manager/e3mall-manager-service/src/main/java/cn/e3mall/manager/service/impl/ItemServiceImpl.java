package cn.e3mall.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.manager.dao.TbItemMapper;
import cn.e3mall.manager.pojo.TbItem;
import cn.e3mall.manager.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	TbItemMapper itemMapper;
	
	@Override
	public TbItem findItemById(Long id) {
		TbItem result = itemMapper.selectByPrimaryKey(id);
		return result;
	}

}
