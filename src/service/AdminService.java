package service;

import dao.AdminDao;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import util.ViewEnum;

public class AdminService {

    private static AdminService instance;
    private static AdminDao adminDao;
    private static Scanner sc=new Scanner(System.in);

    private AdminService() {
    }

    public static AdminService getInstance() {
        if (instance == null)
            instance = new AdminService();
        return instance;
    }

    public int adminLogin() {
        System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
        System.out.println("관리자 로그인 화면입니다.");
        System.out.println("아이디를 입력해 주세요:");
        String userId = sc.nextLine();
        System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");

        if ("".equals(userId)) {
            System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
            System.out.println("유효하지 않은 아이디입니다. 다시 입력해 주세요.");
            System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");
            return ViewEnum.ADMIN_LOGIN;
        }

        System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
        System.out.println("관리자 로그인 화면입니다.");
        System.out.println("비밀번호를 입력해 주세요:");
        String userPw = sc.nextLine();
        System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");

        if ("".equals(userPw)) {
            System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
            System.out.println("유효하지 않은 비밀번호입니다. 다시 입력해 주세요.");
            System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");
            return ViewEnum.ADMIN_LOGIN;
        }

        // 사용자가 입력한 관리자의 아이디와 비밀번호를 Map객체에 저장
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userId", userId);
        userInfo.put("userPw", userPw);

        int result = adminDao.adminLogin(userInfo);



        return 1;
    }
}
