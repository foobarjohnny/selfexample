package org.auction.module.admin.commodity.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.auction.entity.TsBingcur;
import org.auction.entity.TsCommodity;
import org.auction.entity.TsConsume;
import org.auction.entity.TsUser;
import org.auction.module.admin.commodity.data.ConsumeData;
import org.auction.module.admin.commodity.service.ConsumeService;
import org.mobile.common.bean.LoginBean;
import org.mobile.common.bean.SearchBean;
import org.mobile.common.exception.GeneralException;
import org.mobile.common.manager.GeneralManager;
import org.mobile.common.service.GeneralService;
import org.mobile.common.session.SessionManager;
import org.mobile.common.util.BeanProcessUtils;
import org.mobile.common.util.Constant;

public class ConsumeServiceImpl extends GeneralService implements ConsumeService {

	public void delete(ConsumeData model) throws GeneralException {
		String hql = " update TsConsume t set t.state = '1' where t.tsCommodity.id='" + model.getComId() + "' and t.tsUser.id='" + model.getUserId() + "'";
		generalDao.executeHql(hql);
	}

	public void forward(ConsumeData model) throws GeneralException {
		if (model.getId() != null && !model.getId().equals("")) {
			TsConsume tsConsume = (TsConsume) generalDao.get(TsConsume.class, model.getId());
			BeanProcessUtils.copyProperties(model, tsConsume);
		}
	}

	public void save(ConsumeData model) throws GeneralException {
		TsConsume tsConsume = new TsConsume();
		BeanProcessUtils.copyProperties(tsConsume, model);
		if (model.getId() != null && !model.getId().equals("")) {
			generalDao.update(tsConsume);
		} else {
			generalDao.save(tsConsume);
		}
	}

	@SuppressWarnings("unchecked")
	public void search(ConsumeData model) throws GeneralException {
		String hql = "select c.tsCommodity,sum(c.amount),c.tsUser  from TsConsume c where c.state is null ";
		if (model.getSearchBeans() != null && model.getSearchBeans().size() > 0) {
			if (model.getSearchBeans().get(0) != null) {
				SearchBean s = model.getSearchBeans().get(0);
				hql += " and c.tsUser.username='" + s.getValue() + "'";
			}
		}
		hql += " group by c.tsCommodity,c.tsUser";
		String pageTotal = "select count(id) as id from TsConsume c where c.state is null";
		if (model.getSearchBeans() != null && model.getSearchBeans().size() > 0) {
			if (model.getSearchBeans().get(0) != null) {
				SearchBean s = model.getSearchBeans().get(0);
				pageTotal += " and c.tsUser.username='" + s.getValue() + "'";
			}
		}

		List list = generalDao.search(hql, pageTotal, model.getPageBean());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ConsumeData data = new ConsumeData();
				Object[] obj = (Object[]) list.get(i);
				TsCommodity tsCommodity = (TsCommodity) obj[0];
				data.setAmount((Integer) obj[1]);
				data.setComname(tsCommodity.getTradename());
				data.setDescript(tsCommodity.getSummary());
				data.setComId(tsCommodity.getId());
				data.setMarkPrice(tsCommodity.getPrices());
				TsUser tsUser = (TsUser) obj[2];
				data.setUsername(tsUser.getUsername());
				data.setUserId(tsUser.getId());
				Iterator it = tsCommodity.getTsBingcurs().iterator();
				if (it.hasNext()) {
					TsBingcur tsBingcur = (TsBingcur) it.next();
					data.setPrice(tsBingcur.getPrice());
					data.setBidId(tsBingcur.getId());
				}
				model.getDataList().add(data);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void searchCom(ConsumeData model) throws GeneralException {
		List<SearchBean> list = new ArrayList<SearchBean>();
		list.add(new SearchBean("state", "eq", "string", "3"));
		List dataList = generalDao.search(TsCommodity.class, list, model.getPageBean(), null);
		if (dataList != null && dataList.size() > 0) {
			for (int i = 0; i < dataList.size(); i++) {
				TsCommodity tsCommodity = (TsCommodity) dataList.get(i);
				// 免费查询
				String h1 = "select count(id) from TsBidding c where c.bidtype='1' and c.tsCommodity.id='" + tsCommodity.getId() + "'";
				if (model.getSearchBeans() != null && model.getSearchBeans().size() > 0) {
					if (model.getSearchBeans().get(0) != null) {
						SearchBean s = model.getSearchBeans().get(0);
						h1 += " and c.tsUser.username='" + s.getValue() + "'";
					}
				}
				// 收费查询
				String h2 = "select count(id) from TsBidding c where c.bidtype='2' and c.tsCommodity.id='" + tsCommodity.getId() + "'";
				if (model.getSearchBeans() != null && model.getSearchBeans().size() > 0) {
					if (model.getSearchBeans().get(0) != null) {
						SearchBean s = model.getSearchBeans().get(0);
						h2 += " and c.tsUser.username='" + s.getValue() + "'";
					}
				}
				// 免费数量
				Object o1 = generalDao.executeQuery(h1);
				// 收费数量
				Object o2 = generalDao.executeQuery(h2);
				ConsumeData data = new ConsumeData();
				data.setComname(tsCommodity.getTradename());
				data.setAmount(Integer.parseInt((String.valueOf(o2))));
				data.setFree(Integer.parseInt((String.valueOf(o1))));
				data.setComId(tsCommodity.getId());

				Iterator it = tsCommodity.getTsBingcurs().iterator();
				if (it.hasNext()) {
					TsBingcur tsBingcur = (TsBingcur) it.next();
					data.setPrice(tsBingcur.getPrice());
					data.setBidId(tsBingcur.getId());
					data.setBuytime(tsBingcur.getBinddate());
				}
				model.getDataList().add(data);
			}
		}
	}

	/**
	 * 用户E拍币消费情况
	 */
	@SuppressWarnings("unchecked")
	public void searchPayE(ConsumeData model) throws GeneralException {
		LoginBean loginBean = SessionManager.getLoginInfo(GeneralManager.getCurrentManager().getSessionId());
		if (loginBean != null) {
			// 分组统计用户商品E拍币使用数量
			String hql = "select c.tsCommodity,sum(c.amount) from TsConsume c where c.tsUser.id='" + loginBean.getId() + "' group by c.tsCommodity";
			String pageTotal = "select count(id) from TsConsume c where c.tsUser.id='" + loginBean.getId() + "' ";
			List list = generalDao.search(hql, pageTotal, model.getPageBean());
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ConsumeData data = new ConsumeData();
					Object[] obj = (Object[]) list.get(i);
					TsCommodity tsCommodity = (TsCommodity) obj[0];
					data.setAmount((Integer) obj[1]);
					data.setComname(tsCommodity.getTradename());
					data.setDescript(tsCommodity.getSummary());
					data.setComId(tsCommodity.getId());
					data.setMarkPrice(tsCommodity.getPrices());
					// 商品显示图片
					data.setImagesPath(generalDao.searchImages(tsCommodity.getId(), Constant.TRADE_IMAGES));
					Iterator it = tsCommodity.getTsBingcurs().iterator();
					if (it.hasNext()) {
						TsBingcur tsBingcur = (TsBingcur) it.next();
						data.setPrice(tsBingcur.getPrice());
					}
					model.getDataList().add(data);
				}
			}
		}

	}

}
