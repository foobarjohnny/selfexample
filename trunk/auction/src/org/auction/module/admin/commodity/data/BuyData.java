package org.auction.module.admin.commodity.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mobile.common.action.GeneralData;

public class BuyData extends GeneralData {

	private String id;
	private Date buytime;
	private String userId;
	private String username;
	private String remark;
	private String buytype;
	private Integer amount;

	private List<BuyData> dataList = new ArrayList<BuyData>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBuytime() {
		return buytime;
	}

	public void setBuytime(Date buytime) {
		this.buytime = buytime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBuytype() {
		return buytype;
	}

	public void setBuytype(String buytype) {
		this.buytype = buytype;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public List<BuyData> getDataList() {
		return dataList;
	}

	public void setDataList(List<BuyData> dataList) {
		this.dataList = dataList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
