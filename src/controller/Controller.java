package controller;

import java.util.HashMap;
import java.util.Map;

import service.AdminService;
import service.MainService;
import util.ViewEnum;

public class Controller {
	public static Map<String, Object> sessionStorage=
			   new HashMap<>();

	/**
	 * private : 이 클래스에서만 쓸 수 있음
	 * final : 변하지 않음.
	 */
	private MainService mainService = MainService.getInstance();
	private AdminService adminService = AdminService.getInstance();

	public static void main(String[] args) {
		new Controller().init();
	}

	public void init() {
		int VIEW = 1;
		while (true) {
			switch (VIEW) {
				case ViewEnum.HOME_MAIN:
					VIEW = mainService.home();
					break;
				case ViewEnum.ADMIN_LOGIN:
					VIEW = adminService.adminLogin();
					break;
				case ViewEnum.ADMIN_HOME:
					VIEW = adminService.adminHome();
					break;
				case ViewEnum.ADMIN_NOTICE_WRITE:
					VIEW = adminService.adminNoticeWrite();
					break;
				case ViewEnum.ADMIN_NOTICE_LIST:
					VIEW = adminService.adminNoticeList();
					break;
				default:
					VIEW = ViewEnum.HOME_MAIN;
			}
		}
	}
}


