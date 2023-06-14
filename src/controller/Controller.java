package controller;

import java.util.HashMap;
import java.util.Map;

import service.AdminService;
import service.LoginService;
import service.MainService;
import service.OrderService;
import util.ViewEnum;

public class Controller {
	public static Map<String, Object> sessionStorage=
			   new HashMap<String, Object>();

	private final MainService mainService = MainService.getInstance();
	private final LoginService loginService = LoginService.getInstance();
	private final OrderService orderService = OrderService.getInstance();
	private final AdminService adminService = AdminService.getInstance();

	public static void main(String[] args) {
		new Controller().init();
	}

	public void init() {
		int VIEW = 1;
		while (true) {
			switch (VIEW) {
				case 1:
					VIEW = mainService.home();
				case 55:
					VIEW = adminService.adminLogin();
			}
		}
	}
}


