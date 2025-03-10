package org.auction.module.admin.commodity.dwr;

import java.util.List; 

import org.auction.module.admin.commodity.data.BidingData;
import org.auction.module.admin.commodity.service.BidingService;
import org.mobile.common.exception.GeneralException;
import org.mobile.common.util.StringUtil;

/**
 * 前台定时进行价格变化的查询
 * 
 * @author ThinkPad
 * 
 */
public class BidingHandler {

	private BidingService bidingService;

	/**
	 * 前台异步实时交互，通过json获得当前商品竞拍信息
	 * @param commpityId
	 * @param json 
	 * @param did
	 * @return
	 * @throws GeneralException
	 */
	public String find(String commpityId, String json, String did)
			throws GeneralException {
		
		//这是之前实现的算法， 根据产品编号， 用户编号和前台的序号， 查询指定的产品。由于前台的产品数量多， 导致竞争，会造成页面的阻塞。
		//所以修改为每次请求只将发生变化的产品返回
//		String returnStr = "no";
//		BidingData model = new BidingData();
//		model.setCommpityId(commpityId);
//		model.setUserId(userId);
//		List<BidingData> list = bidingService.findNewPeople(model);
//		if (list != null && list.size() > 0) {
//			BidingData data = list.get(0);
//			if (data.getUserId() != null) {
//				returnStr = "index:" + did;
//				returnStr += ",userId:" + data.getUserId();
//				returnStr += ",username:" + data.getUsername();
//				returnStr += ",price:" + data.getPrice();
//				returnStr += ",add:" + data.getTime();
//			}
//		}
		
		/**
		 * 根据后台的所有竞拍商品的信息， 每秒钟将所有的信息向前台输出一次
		 */
		StringBuffer buffer = new StringBuffer();
		
		List<BidingData> list = bidingService.getAll(commpityId);
		if(list != null && list.size() > 0){
			buffer.append("[");
			for(BidingData bean : list){
				if(bean != null){
					buffer.append("{");
					buffer.append("'id':'" + bean.getId());
					buffer.append("','userId':'"+StringUtil.wrap(bean.getUserId()));
					buffer.append("','username':'"+StringUtil.wrap(bean.getUsername()));
					buffer.append("','price':"+StringUtil.wrapDecimal(bean.getPrice()));
					buffer.append(",'time':'" + StringUtil.wrap(bean.getDisplayTime()));
					buffer.append("','isFinish':"+StringUtil.wrap(""+bean.isFinish));
					buffer.append("},");
				}
			}
			buffer.append("{}]");
			
		}else{
			buffer.append("[{}]");
		}
		return buffer.toString();
	}

	/**
	 * 商品是否真的结束
	 * 
	 * @param commpityId
	 * @param userId
	 * @param did
	 * @return
	 * @throws GeneralException
	 */
	@SuppressWarnings("unchecked")
	public String finish(String commpityId, String userId, String did)
			throws GeneralException {
		String resultStr = "";
		BidingData model = new BidingData();
		model.setCommpityId(commpityId);
		model.setUserId(userId);
		List<BidingData> list = bidingService.findNewPeople(model);
		if (list != null && list.size() > 0) {
			BidingData data = list.get(0);
			if (data.getUserId() != null) {
				resultStr = "index:" + did;
				resultStr += ",userId:" + data.getUserId();
				resultStr += ",username:" + data.getUsername();
				resultStr += ",price:" + data.getPrice();
				resultStr += ",add:" + data.getTime();
			}
		} else {
			resultStr = "over";
		}
		return resultStr;
	}

	public void setBidingService(BidingService bidingService) {
		this.bidingService = bidingService;
	}
}
