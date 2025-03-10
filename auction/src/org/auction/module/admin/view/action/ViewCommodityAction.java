package org.auction.module.admin.view.action;

import org.auction.module.admin.view.data.ViewData;
import org.auction.module.admin.view.service.ViewCommodityService;
import org.mobile.common.action.GeneralAction;
import org.mobile.common.exception.GeneralException;

/**
 * 获取竞拍信息
 * 
 * @author Administrator
 *
 */
public class ViewCommodityAction extends GeneralAction<ViewData> {

	private static final long serialVersionUID = 1L;

	private ViewData model = new ViewData();

	private ViewCommodityService viewCommodityService;

	/**
	 * 获取所有的可以竞拍的产品
	 * @return
	 * @throws GeneralException
	 */
	public String view() throws GeneralException {
		viewCommodityService.show(model);
		return SUCCESS;
	}

	public String showAll() throws GeneralException {
		this.setPage(model);
		viewCommodityService.showAll(model);
		return SUCCESS;
	}

	public void setViewCommodityService(
			ViewCommodityService viewCommodityService) {
		this.viewCommodityService = viewCommodityService;
	}

	public ViewData getModel() {
		return model;
	}
}
