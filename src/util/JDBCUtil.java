package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Select : 3개 (getAllRowsFromOracle, getRowFromOracle, getRowsFromOracle) 메서드로 모두 대응 가능
 * Insert, Update, Delete : UpsertToOracle 메서드로 대응가능
 * 안되면 연락 주세요 ^^ (tofan@naver.com, 010-9950-8978, woonghyuncho@lguplus.co.kr)
 * 아래 메서드로 커버가 안되는 건.. 아마 없을 겁니다.
 */
public class JDBCUtil {

    /**
     * Oracle Connection에 필요한 정보들.
     * static : JDBCUtil.x 와 같이 접근할 수 있습니다. (클래스 변수)
     * final : 변경할 수 없는 값입니다. (IDE를 통한 변경만 가능. 서버 동작 중엔 변경 불가능합니다.)
     */
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "testuser";
    private static final String PASSWORD = "testuser";
    private static JDBCUtil instance = null;

    private Connection conn;
    private PreparedStatement pstmt = null;
    //    private Statement stmt = null;
    private ResultSet rs = null;

    private JDBCUtil() {
    }

    public static JDBCUtil getInstance() {
        if (instance == null) instance = new JDBCUtil();
        return instance;
    }

    /**
     * Finally에 포함되어 있던 코드를 메서드화
     *
     * @param rs    Resultset
     * @param pstmt PreparedStatement
     * @param conn  Connection
     */
    void closeConnection(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 모든 메서드에서 공통으로 사용되는, DB 객체를 List<Map>으로 변환하는 로직을
     * 메서드로 분할.
     * @param list 복수의 rows를 담기 위한 리스트.
     * @param rs Oracle에서 쿼리문을 수행한 결과를 담은 객체.
     * @return List<Map>
     */
    List<Map<String, Object>> saveListData(List list, ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();

                for (int i = 0; i < columnCount; i++) {
                    String key = rsmd.getColumnLabel(i + 1);
                    Object value = rs.getObject(i + 1);
                    row.put(key, value);
                }
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @param row Oracle Row.
     * @param rs Oracle에서 쿼리문을 수행한 결과를 담은 객체.
     * @return
     */
    Map<String, Object> saveRowData(Map<String, Object> row, ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    String key = rsmd.getColumnLabel(i + 1);
                    //or	String key=rsmd.getColumnName(i);
                    Object value = rs.getObject(i + 1);
                    row.put(key, value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }


    /**
     * 테이블에 존재하는 "모든 데이터"를 조회할 때 사용합니다.
     * Java Application -> DB로 어떤 인자값도 전달하지 않습니다.
     * 즉, select * from member; 와 같이 고정된 sql문에서만 활용합니다.
     *
     * @param sql 쿼리
     * @return
     */
    public List<Map<String, Object>> getAllRowsFromOracle(String sql) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            list = saveListData(list, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * Oracle DB에서 어떤 조건을 통해 "특정 데이터 1개"를 조회할 때 사용합니다.
     * @param sql    쿼리
     * @param params Oracle DB에게 전달하고자 하는 인자값.
     *               ex) 사용자 ID & PW로 유저 정보 조회, 특정 게시글 번호(PK)로 게시글 조회
     * @return 해당하는 데이터가 있는 경우 column 정보가 담긴 Map 객체를, 없는 경우 Null을 반환합니다.
     */
    public Map<String, Object> getRowFromOracle(String sql, List<String> params) {
        Map<String, Object> row = new HashMap<>();
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            rs = pstmt.executeQuery();

            row = saveRowData(row, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(rs, pstmt, conn);
        }
        return row;
    }

    /**
     * Oracle DB에서 어떤 조건을 통해 여러 데이터(전체 데이터가 아닐 수도 있다..)를 조회하고자 할 때 사용합니다.
     * ex) 신범종이라는 작성자가 쓴 게시글 조회. (2개 이상 조회할 때 씀)
     * @param sql
     * @param params
     * @return
     */
    public List<Map<String, Object>> getRowsFromOracle(String sql, List<String> params) {
        List<Map<String, Object>> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            pstmt = conn.prepareStatement(sql);

            // SQL문에서 ?에 해당하는 값 설정해주기.
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            rs = pstmt.executeQuery();

            list = saveListData(list, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * INSERT, UPDATE, DELETE문을 수행하는 메서드.
     * UPSERT : 검색한 데이터가 없으면 INSERT를 수행하고,
     * 해당하는 데이터가 있으면 UPDATE를 수행합니다.
     *
     * @param sql
     * @return
     */
    public int upsertToOracle(String sql, List<String> params) {
        int r = 0;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            pstmt = conn.prepareStatement(sql);

            // SQL문에서 ?에 해당하는 값 설정해주기.
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            r = pstmt.executeUpdate(); // insert, delete, update
        } catch (SQLException e) {
            System.out.println("SQL 실행에 실패하였습니다. 자세한 사항은 e.printstacktrace를 참고해 주세요.");
            e.printStackTrace(); // 주석처리할 수 있음.
        } finally {
            closeConnection(rs, pstmt, conn);
        }
        return r;
    }
}