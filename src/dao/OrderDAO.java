package dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class OrderDAO {
	private static OrderDAO instance=null;
	private OrderDAO() {}
	public static OrderDAO getInstance() {
		if (instance==null)instance=new OrderDAO();
		return instance;
	}
	
	JDBCUtil jdbc=JDBCUtil.getInstance();
	
	public List<Map<String, Object>> selectList(){
		String sql="SELECT * FROM TBL_PROD ";
		return jdbc.selectList(sql);
	}
	
	public int insertOrder(List<Object> param) {
		Date now=new Date();
		SimpleDateFormat formatter=
				new SimpleDateFormat("yyyyMMdd");
		String today=formatter.format(now);
				
		String sql="INSERT INTO TBL_ORDER( ORDER_ID, "
				+ " MEM_ID, PROD_ID, ORDER_DATE, QTY ) "
				+" VALUES ( FN_ORDERID("+today+"), ?, ?, "+ today+", ? )";
		return jdbc.update(sql, param);
	}
}
