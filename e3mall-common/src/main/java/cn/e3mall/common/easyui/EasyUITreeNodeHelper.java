package cn.e3mall.common.easyui;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class EasyUITreeNodeHelper<T> {
	
	private List<T> resultList;
	
	public  List<EasyUITreeNode> getNodeList(BiConsumer<T, EasyUITreeNode> bi) {
		List<EasyUITreeNode> list  = new ArrayList<>();
		
		for (T target : resultList) {
			EasyUITreeNode node = new EasyUITreeNode();
			
			bi.accept(target, node);
			
			list.add(node);
		}
		
		
		return list; 
	}
}
