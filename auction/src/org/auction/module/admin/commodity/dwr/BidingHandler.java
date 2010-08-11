package org.auction.module.admin.commodity.dwr;

import java.util.List;

import org.auction.module.admin.commodity.data.BidingData;
import org.auction.module.admin.commodity.service.BidingService;
import org.mobile.common.exception.GeneralException;

/**
 * 
 * @author ThinkPad
 * 
 */
public class BidingHandler {

	private BidingService bidingService;

	@SuppressWarnings("unchecked")
	public String find(String commpityId, String userId)
			throws GeneralException {
		String returnStr = "";
		BidingData model = new BidingData();
		model.setCommpityId(commpityId);
		model.setUserId(userId);
		List<BidingData> list = bidingService.findNewPeople(model);
		if (list != null && list.size() > 0) {
			BidingData data = list.get(0);
			if (data.getUserId() != null) {
				returnStr = "userId:" + data.getUserId();
				returnStr += ",username:" + data.getUsername();
				returnStr += ",price:" + data.getPrice();
			}
		}
		return returnStr;
	}

	public void setBidingService(BidingService bidingService) {
		this.bidingService = bidingService;
	}
}
