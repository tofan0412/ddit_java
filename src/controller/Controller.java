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
			VIEW = switch (VIEW) {
				case ViewEnum.HOME_MAIN -> mainService.home();
				case ViewEnum.ADMIN_LOGIN -> adminService.adminLogin();
				case ViewEnum.ADMIN_HOME -> adminService.adminHome();
				case ViewEnum.ADMIN_NOTICE_WRITE -> adminService.adminNoticeWrite();
				case ViewEnum.ADMIN_NOTICE_LIST -> adminService.adminNoticeList();
				default -> ViewEnum.HOME_MAIN;
			};
		}
	}
}


