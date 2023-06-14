package dao;

import java.util.Map;
import util.JDBCUtil;

public class AdminDao {
    private static AdminDao instance=null;
    private AdminDao() {}
    public static AdminDao getInstance() {
        if(instance==null)instance=new AdminDao();
        return instance;
    }

    JDBCUtil jdbc=JDBCUtil.getInstance();

    public int adminLogin(Map<String, String> userInfo) {
        String sql = "SELECT * FROM MEMBER WHERE MEM_ID = "
                + userInfo.get("userId") + "AND MEM_PW = "
                + userInfo.get("userPw");

        Map<String, Object> result = jdbc.selectOne(sql);

    }


}
