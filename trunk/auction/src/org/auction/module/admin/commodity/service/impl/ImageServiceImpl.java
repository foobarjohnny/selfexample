package org.auction.module.admin.commodity.service.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.auction.entity.TsImages;
import org.auction.module.admin.commodity.data.ImageData;
import org.auction.module.admin.commodity.service.ImageService;
import org.mobile.common.bean.SearchBean;
import org.mobile.common.manager.GeneralManager;
import org.mobile.common.service.GeneralService;

public class ImageServiceImpl extends GeneralService implements ImageService {

	@SuppressWarnings("unchecked")
	public void getImage(ImageData model) throws Exception {
		List<SearchBean> list = new ArrayList<SearchBean>();
		list.add(new SearchBean("tablename","eq","string","TS_COMMODITY"));
		list.add(new SearchBean("imageid","eq","string",model.getId()));
		List imageList = generalDao.search(TsImages.class, list, null, null);
		if (imageList != null && imageList.size() > 0) {
			TsImages tsImages = (TsImages) imageList.get(0);
			InputStream fis = new FileInputStream(tsImages.getFilepath());
			GeneralManager manager = GeneralManager.getCurrentManager();
			OutputStream os = manager.getResponse().getOutputStream();
			byte[] blobbytes = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = fis.read(blobbytes)) != -1) {
				os.write(blobbytes, 0, bytesRead);
			}
			fis.close();
			os.flush();
		}
	}

}
