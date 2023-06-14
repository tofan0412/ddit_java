package service;

import controller.Controller;
import dao.AdminDao;

import java.util.*;

import util.ViewEnum;

public class AdminService {
    private static Scanner sc = new Scanner(System.in);
    private static AdminDao adminDao = AdminDao.getInstance();
    private static AdminService instance;

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
        System.out.println("아이디를 입력해 주세요. (이전 화면으로 돌아가시려면 0번을 입력해 주세요.)");
        String eName = sc.nextLine();
        System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");

        if ("0".equals(eName)) {
            return ViewEnum.HOME_MAIN;
        }

        if ("".equals(eName)) {
            System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
            System.out.println("유효하지 않은 아이디입니다. 다시 입력해 주세요.");
            System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");
            return ViewEnum.ADMIN_LOGIN;
        }

        System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
        System.out.println("관리자 로그인 화면입니다.");
        System.out.println("비밀번호를 입력해 주세요:");
        String ePass = sc.nextLine();
        System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");

        if ("".equals(ePass)) {
            System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
            System.out.println("유효하지 않은 비밀번호입니다. 다시 입력해 주세요.");
            System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");
            return ViewEnum.ADMIN_LOGIN;
        }

        // 사용자가 입력한 관리자의 아이디와 비밀번호를 순서를 지키기 위해 리스트에 저장
        List<String> params = new ArrayList<>();
        params.add(eName);
        params.add(ePass);

        Map<String, Object> result = adminDao.adminLogin(params);

        // 조회 데이터가 없는 경우
        if (result.get("E_NAME") == null) {
            System.out.println("!!!!사용자 정보가 존재하지 않습니다. 다시 시도해 주세요.!!!!");
            return ViewEnum.ADMIN_LOGIN;
        } else {
            // 로그인한 정보를 Controller 클래스 변수에 저장합니다.
            Controller.sessionStorage = result;
            return ViewEnum.ADMIN_HOME;
        }
    }

    public int adminHome() {
        System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
        System.out.println(Controller.sessionStorage.get("E_NAME") + "님, 환영합니다.");
        System.out.println("1. 공지사항 작성하기 2. 공지사항 목록 확인하기 3. 로그아웃");
        System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");

        try {
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    return ViewEnum.ADMIN_NOTICE_WRITE;
                case 2:
                    return ViewEnum.ADMIN_NOTICE_LIST;
                case 3:
                    // sessionStorage를 초기화합니다.
                    System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
                    System.out.println(" 관리자 계정 로그아웃 되었습니다. 감사합니다. ");
                    System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");
                    Controller.sessionStorage = new HashMap<>();

                    return ViewEnum.HOME_MAIN;
                default:
                    return ViewEnum.ADMIN_HOME;
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해 주세요.");
            sc = new Scanner(System.in);
            return ViewEnum.ADMIN_HOME;
        }
    }

    public int adminNoticeWrite() {
        System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
        System.out.println(" 먼저, 공지사항의 제목을 입력해 주세요.");
        System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");
        sc.nextLine(); // 버퍼 비우기용..
        String noTitle = sc.nextLine();

        System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
        System.out.println(" 먼저, 공지사항 내용을 입력해 주세요.");
        System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");
        String noContent = sc.nextLine();

        if ("".equals(noTitle.trim()) || "".equals(noContent.trim())) {
            System.out.println("제목 또는 내용은 최소 한 글자를 입력해 주셔야 합니다.");
            return ViewEnum.ADMIN_HOME;
        }

        System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
        System.out.println(" 제목 : " + noTitle);
        System.out.println(" 내용 : " + noContent);
        System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");

        System.out.println(" 값을 저장합니다...");

        List<String> params = new ArrayList<>();
        params.add(noContent);
        params.add((String) Controller.sessionStorage.get("E_NO"));
        params.add(noTitle);
        int r = adminDao.adminNoticeWrite(params);

        if (r == 0) {
            System.out.println("글 작성에 실패하였습니다. 다시 시도해 주세요.");
            return ViewEnum.ADMIN_NOTICE_WRITE;
        } else {
            System.out.println("글 작성에 성공하였습니다.");
            return ViewEnum.ADMIN_NOTICE_LIST;
        }
    }

    public int adminNoticeList() {
        List<Map<String, Object>> list = adminDao.adminNoticeList();

        // 작성된 게시글이 하나도 없는 경우..
        if (list.size() == 0) {
            System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
            System.out.println("작성된 글이 아직 없네요. 새로운 글을 작성해 보세요!");
            System.out.println("(글을 작성하시려면 1번, 관리자 메인 페이지로 넘어가려면 2번을 눌러 주세요.)");
            System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");

            try {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        return ViewEnum.ADMIN_NOTICE_WRITE;
                    case 2:
                        return ViewEnum.ADMIN_HOME;
                    default:
                        System.out.println("잘못 누르셨습니다. 다시 한번 눌러 주세요.");
                        return ViewEnum.ADMIN_NOTICE_LIST;
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해 주세요.");
                sc = new Scanner(System.in);
                return ViewEnum.ADMIN_NOTICE_LIST;
            }
        }

        // 작성된 게시글이 하나라도 있는 경우
        System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
        for (Map<String, Object> article : list) {
            System.out.println("글 번호 : " + article.get("NO_NO"));
            System.out.println("<" + article.get("NO_TITLE") + ">");
        }
        System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");
        System.out.println("게시글의 내용을 확인하시려면 게시글 번호를 입력해 주세요. (0번 입력 시 관리자 메인 페이지)");

        try {
            int noNo = sc.nextInt();

            if (noNo == 0) {
                return ViewEnum.ADMIN_HOME;
            }

            List<String> params = new ArrayList<>();
            String temp = String.valueOf(noNo);
            params.add(temp);
            Map<String, Object> article = adminDao.getNotice(params);

            if (article.get("NO_TITLE") == null && article.get("NO_CONTENT") == null) {
                System.out.println("게시글 번호를 잘못 입력하셨습니다. 다시 입력해 주세요!");
                return ViewEnum.ADMIN_NOTICE_LIST;
            }

            System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
            System.out.println(" 제목 : " + article.get("NO_TITLE"));
            System.out.println(" 내용 : " + article.get("NO_CONTENT"));
            System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");

            System.out.println("게시글을 수정하려면 0번을 눌러주세요.");
            System.out.println("게시글을 삭제하려면 1번을 눌러주세요.");
            System.out.println("다시 목록으로 돌아가시려면 2번을 눌러주세요.");

            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    return adminNoticeModify(
                            (String) article.get("NO_NO"),
                            (String) article.get("NO_TITLE"),
                            (String) article.get("NO_CONTENT"));
                case 1:
                    return ViewEnum.ADMIN_NOTICE_DELETE;
                case 2:
                    return ViewEnum.ADMIN_NOTICE_LIST;
                default:
                    System.out.println("관리자 메인 페이지로 돌아갑니다..");
                    return ViewEnum.ADMIN_HOME;
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해 주세요.");
            sc = new Scanner(System.in);
            return ViewEnum.ADMIN_NOTICE_LIST;
        }
    }

    public int adminNoticeModify(String noNo, String noTitle, String noContent) {
        try {
            List<String> params = new ArrayList<>();
            params.add(noNo);

            sc.nextLine();
            System.out.println("수정할 글 제목을 입력해 주세요. (수정하기 싫으면 그냥 엔터)");
            String modifiedTitle = sc.nextLine();
            System.out.println("수정할 글 내용을 입력해 주세요. (수정하기 싫으면 그냥 엔터)");
            String modifiedContent = sc.nextLine();

            if (modifiedTitle.length() == 0) {
                modifiedTitle = noTitle;
            }
            if (modifiedContent.length() == 0) {
                modifiedContent = noContent;
            }

            List<String> modified = new ArrayList<>();
            modified.add(modifiedTitle);
            modified.add(modifiedContent);
            modified.add(String.valueOf(noNo));

            int result = adminDao.adminNoticeModify(modified);

            if (result == 0) {
                System.out.println("수정에 실패했습니다. 다시 시도해 주세요.");
                return adminNoticeModify(noNo, noTitle, noContent);
            }

            System.out.println("게시글을 수정했습니다.");
            Map<String, Object> modifiedArticle = adminDao.getNotice(params);
            System.out.println("⌜⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⌝");
            System.out.println(" 제목 : " + modifiedArticle.get("NO_TITLE"));
            System.out.println(" 내용 : " + modifiedArticle.get("NO_CONTENT"));
            System.out.println("⌞⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⌟");

            System.out.println("목록으로 돌아가려면 아무 키나 눌러주세요.");

            sc.nextLine();
            return ViewEnum.ADMIN_NOTICE_LIST;

        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해 주세요.");
            return ViewEnum.ADMIN_HOME;
        }
    }

//    public int adminNoticeDelete() {
//
//    }
}
