package org.auction.module.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.auction.module.manager.data.TradeComparator;
import org.auction.module.manager.data.TradeData;

public class TradeManager {

	private static Map<String, TradeData> tradeDataMap = new HashMap<String, TradeData>();

	/**
	 * 添加正在商品
	 * 
	 * @param tradeData
	 */
	public static void add(TradeData tradeData) {
		tradeDataMap.put(tradeData.getId(), tradeData);
	}

	/**
	 * 获得指定商品
	 * 
	 * @param key
	 * @return
	 */
	public static TradeData get(String key) {
		return tradeDataMap.get(key);
	}

	/**
	 * 删除已经竞拍的商品
	 * 
	 * @param tradeData
	 */
	public static void remove(String id) {
		tradeDataMap.remove(id);
	}
	
	/**
	 * 已经竞拍的商品
	 * 
	 * @param tradeData
	 */
	public static void finshed(String id) {
		tradeDataMap.get(id).setFinshed(true);
		tradeDataMap.get(id).setOrder(true);
	}

	/**
	 * 查询缓存中的竞拍商品
	 * 
	 * @param id
	 * @return
	 */
	public static List<TradeData> getTradeData(String id) {
		List<TradeData> list = new ArrayList<TradeData>();
		if (id != null && !id.equals("")) {
			TradeData data = tradeDataMap.get(id);
			if (data != null) {				
				list.add(data);
			}
		} else {
			list.addAll(tradeDataMap.values());
		}
		//对商品信息按结束时间排序
		Collections.sort(list,new TradeComparator());
		return list;
	}

	/**
	 * 获得存在商品ID
	 * 
	 * @return
	 */
	public static String[] getId() {
		if (tradeDataMap != null && tradeDataMap.size() > 0) {
			String[] strs = new String[tradeDataMap.size()];
			return tradeDataMap.keySet().toArray(strs);
		} else {
			return new String[0];
		}
	}
}
