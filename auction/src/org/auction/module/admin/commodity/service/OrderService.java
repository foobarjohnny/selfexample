package org.auction.module.admin.commodity.service;

import org.auction.module.admin.commodity.data.OrderData;
import org.mobile.common.exception.GeneralException;

public interface OrderService {

	public void forward(OrderData model) throws GeneralException;

	public void save(OrderData model) throws GeneralException;

	public void delete(OrderData model) throws GeneralException;

	public void search(OrderData model) throws GeneralException;

	public void searchChannel(OrderData model) throws GeneralException;

	public String createOrder(String id, String sessionId)
			throws GeneralException;

	public void pay(OrderData model) throws GeneralException;

	public void channel(OrderData model) throws GeneralException;

	public void view(OrderData model) throws GeneralException;

	public void orderView(OrderData model) throws GeneralException;

	public String createOrders(String id, String userId)
			throws GeneralException;
}
