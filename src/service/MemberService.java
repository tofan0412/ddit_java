package service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dao.MemberDAO;

public class MemberService {
	private static MemberService instance;

	private MemberService() {
	}

	public static MemberService getInstance() {
		if (instance == null)
			instance = new MemberService();
		return instance;
	}
	
	Scanner sc=new Scanner(System.in);
	MemberDAO dao=MemberDAO.getInstance();
	
	public List<Map<String, Object>> searchAll(){
		return dao.searchAll();
	}
	
	public Map<String, Object> searchOne(){
		System.out.print("회원 아이디 : ");
		String mid = sc.next();
		return dao.searchOne(mid);
	}
	
	public int update() {
		int res=0;
		String mid ="";
		String flag ="";
		String pw="";
		String jumin="";
		int mileage =0;
		String updateSql="UPDATE tbl_member SET ";
		while (true) {
			System.out.print("회원 아이디 : ");
			mid = sc.next();
			LoginService loginService = LoginService.getInstance();
			Map<String, Object>map = loginService.isDuplicate(mid);
			if (map == null) {
				System.out.println("회원정보가 없습니다");
			}else {
				break;
			}
		}

		System.out.print("비밀번호를 변경하겠습니까?(Y/N) : ");
		flag = sc.next();
		if(flag.equalsIgnoreCase("y")) {
			System.out.print("비밀번호 : ");
			pw = sc.next();
			updateSql+=" MEM_PASS = '"+pw+"' ,";
		}

		System.out.print("주민번호를 변경하겠습니까?(Y/N) : ");
		flag = sc.next();
		if(flag.equalsIgnoreCase("y")) {
			System.out.print("주민등록번호 : ");
			jumin = sc.next();
			updateSql+=" MEM_JUMIN = '"+jumin+"' ,";
		}
		
		System.out.print("마일리지를 변경하겠습니까?(Y/N) : ");
		flag = sc.next();
		if(flag.equalsIgnoreCase("y")) {
			System.out.print("마일리지 : ");
			mileage = sc.nextInt();
			updateSql+=" MEM_MILEAGE = "+mileage+" ,";
		}		
		int len=updateSql.length();
		updateSql=updateSql.substring(0,len-2);
		updateSql=updateSql+" WHERE MEM_ID = '"+mid+"'";
		return dao.update(updateSql);
	}
}
