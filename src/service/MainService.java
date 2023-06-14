package service;

import java.util.InputMismatchException;
import java.util.Scanner;
import util.ViewEnum;

public class MainService {

    private static MainService instance;

    private static Scanner sc=new Scanner(System.in);

    private MainService() {
    }

    public static MainService getInstance() {
        if (instance == null)
            instance = new MainService();
        return instance;
    }

    public int home() {
        System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
        System.out.println("안녕하세요, OOOO 서비스입니다.");
        System.out.println("원하시는 메뉴를 선택해 주세요.");
        System.out.println("1. 회원 로그인 2. 관리자 로그인 3. 회원가입");
        System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");

        try {
            int choice = sc.nextInt();

            switch (choice) {
                // 회원 로그인에 해당하는 int 반환
                case 1:
                    return ViewEnum.ADMIN_LOGIN;
                case 2:
                    return ViewEnum.ADMIN_LOGIN;
                default:
                    return ViewEnum.HOME_MAIN;
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해 주세요.");
            sc = new Scanner(System.in);
            return ViewEnum.HOME_MAIN;
        }

    }
}
