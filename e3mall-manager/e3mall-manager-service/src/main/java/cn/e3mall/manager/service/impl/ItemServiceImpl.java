package cn.e3mall.manager.service.impl;

import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.easyui.EasyUIPageBean;
import cn.e3mall.common.utils.E3mallResult;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.content.interfaces.JedisService;
import cn.e3mall.manager.dao.TbItemDescMapper;
import cn.e3mall.manager.dao.TbItemMapper;
import cn.e3mall.manager.interfaces.ItemService;
import cn.e3mall.manager.pojo.TbItem;
import cn.e3mall.manager.pojo.TbItemDesc;
import cn.e3mall.manager.pojo.TbItemExample;
import cn.e3mall.search.pojo.SearchItemObject;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private JmsTemplate template;

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private Topic topic;

	@Autowired
	private JedisService jedisService;

	@Value("${ITEM_CACHE}")
	private String ITEM_CACHE;
	@Value("${iTEM_EXPIRE}")
	private Integer iTEM_EXPIRE;

	@Override
	public TbItem findItemById(Long id) {
		String resultJson = jedisService.get(ITEM_CACHE + id + ":BASE");

		if (resultJson != null && !"".equals(resultJson)) {
			TbItem resultItem = JsonUtils.jsonToPojo(resultJson, TbItem.class);
			return resultItem;
		}

		TbItem result = itemMapper.selectByPrimaryKey(id);

		jedisService.set(ITEM_CACHE + id + ":BASE", JsonUtils.objectToJson(result));
		jedisService.expire(ITEM_CACHE + id + ":BASE", iTEM_EXPIRE);
		return result;
	}

	@Override
	public EasyUIPageBean findItemByPage(int pageNum, int size) {
		TbItemExample example = new TbItemExample();
		// 自动进行分页查询
		PageHelper.startPage(pageNum, size);

		List<TbItem> resultList = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<>(resultList);

		EasyUIPageBean pageBean = new EasyUIPageBean();
		pageBean.setTotal(pageInfo.getTotal());
		pageBean.setRows(resultList);

		return pageBean;
	}

	@Override
	public E3mallResult saveItem(final TbItem item, final TbItemDesc itemDesc) {

		long itemID = IDUtils.genItemId();
		Date date = new Date();

		item.setId(itemID);
		item.setCreated(date);
		item.setUpdated(date);
		item.setStatus((byte) 1);

		itemDesc.setItemId(itemID);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);

		itemMapper.insert(item);
		itemDescMapper.insert(itemDesc);

		template.send(topic, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {

				return session.createTextMessage(item.getId() + "");
			}
		});
		return E3mallResult.ok();
	}

	@Override
	public TbItemDesc findItemDescById(Long id) {
		String resultJson = jedisService.get(ITEM_CACHE + id + ":DESC");

		if (resultJson != null && !"".equals(resultJson)) {
			TbItemDesc resultItemDesc = JsonUtils.jsonToPojo(resultJson, TbItemDesc.class);
			return resultItemDesc;
		}

		TbItemDesc resultItemDesc = itemDescMapper.selectByPrimaryKey(id);
		
		jedisService.set(ITEM_CACHE + id + ":DESC", JsonUtils.objectToJson(resultItemDesc));
		jedisService.expire(ITEM_CACHE + id + ":DESC", iTEM_EXPIRE);
		return resultItemDesc;
	}

}
