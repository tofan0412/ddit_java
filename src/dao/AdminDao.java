package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class AdminDao {
    private static AdminDao instance=null;
    private AdminDao() {}
    public static AdminDao getInstance() {
        if(instance==null)instance=new AdminDao();
        return instance;
    }

    private JDBCUtil jdbc = JDBCUtil.getInstance();

    public Map<String, Object> adminLogin(List<String> params) {
        String sql = "select * from employee where e_name = ? and e_pass = ?";
        Map<String, Object> result = jdbc.getRowFromOracle(sql, params);

        return result;
    }

    public int adminNoticeWrite(List<String> params) {
        String sql = "insert into notice(no_no, no_content, e_no, no_title) values (notice_seq.nextval, ?, ?, ?)";
        int result = jdbc.upsertToOracle(sql, params);

        return result;
    }

    public List<Map<String, Object>> adminNoticeList() {
        String sql = "select * from notice";
        List<Map<String, Object>> result = jdbc.getAllRowsFromOracle(sql);

        return result;
    }

    public Map<String, Object> getNotice(List<String> params) {
        String sql = "select * from notice where no_no = ?";
        Map<String, Object> result = jdbc.getRowFromOracle(sql, params);

        return result;
    }

    public int adminNoticeModify(List<String> params) {
        String sql = "update notice set no_title = ?, no_content= ? where no_no = ?";
        int result = jdbc.upsertToOracle(sql, params);

        return result;
    }


}
