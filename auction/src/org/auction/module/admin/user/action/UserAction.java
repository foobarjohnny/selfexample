package org.auction.module.admin.user.action;

import org.auction.module.admin.user.data.UserData;
import org.auction.module.admin.user.service.UserService;
import org.mobile.common.action.GeneralAction;
import org.mobile.common.bean.LoginBean;
import org.mobile.common.exception.GeneralException;
import org.mobile.common.manager.GeneralManager;
import org.mobile.common.session.SessionManager;

public class UserAction extends GeneralAction<UserData> {

	private static final long serialVersionUID = 1L;

	private UserData model = new UserData();

	private UserService userService;

	public String forward() throws GeneralException {
		userService.forward(model);
		return SUCCESS;
	}

	/**
	 * 后台用户管理用户添加界面
	 * 
	 * @return
	 * @throws GeneralException
	 */
	public String forwardAdmin() throws GeneralException {
		userService.forwardAdmin(model);
		return SUCCESS;
	}

	public String save() throws GeneralException {
		userService.save(model);
		if (model.getMethodName().equals("1")) {
			return SUCCESS;
		} else {
			return "page";
		}
	}

	public String delete() throws GeneralException {
		userService.delete(model);
		return SUCCESS;
	}

	public String search() throws GeneralException {
		this.setPage(model);
		userService.search(model);
		return SUCCESS;
	}

	public String login() throws GeneralException {
		if (userService.login(model)) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String regedit() throws GeneralException {
		userService.regedit(model);
		return SUCCESS;
	}

	public String regForward() throws Exception {
		userService.regForward(model);
		return SUCCESS;
	}

	public String reg() throws Exception {
		userService.reg(model);
		return SUCCESS;
	}

	public String view() throws GeneralException {
		userService.view(model);
		return SUCCESS;
	}

	public String update() throws GeneralException {
		userService.update(model);
		return SUCCESS;
	}

	public String viewInfo() throws GeneralException {
		GeneralManager manager = GeneralManager.getCurrentManager();
		LoginBean bean = SessionManager.getLoginInfo(manager.getSessionId());
		model.setId(bean.getId());
		userService.forward(model);
		return SUCCESS;
	}

	public String viewUser() throws GeneralException {
		if (!checkedLogin()) {
			return "login";
		}
		userService.forward(model);
		return SUCCESS;
	}

	public String viewSaveUser() throws GeneralException {
		userService.save(model);
		return SUCCESS;
	}

	public String viewPass() throws GeneralException {
		userService.forward(model);
		return SUCCESS;
	}

	/**
	 * 重新发送邮件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String send() throws Exception {
		userService.send(model);
		return SUCCESS;
	}

	/**
	 * 邮箱邀请用户界面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String invitePage() throws Exception {
		// 判断用户是否登录
		if (!userService.checkedUser(model)) {
			return "login";
		}
		userService.invitePage(model);
		return SUCCESS;
	}

	/**
	 * 发送邀请
	 * 
	 * @return
	 * @throws Exception
	 */
	public String invite() throws Exception {
		// 判断用户是否登录
		if (!userService.checkedUser(model)) {
			return "login";
		}
		userService.invite(model);
		return SUCCESS;
	}

	public UserData getModel() {
		return model;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
