package util;

import java.util.List;
import java.util.Map;

public class ProdView {
	private static ProdView instance=null;
	private ProdView() {}
	public static ProdView getInstance() {
		if(instance==null)instance=new ProdView();
		return instance;
	}
	
	public void displayProd(List<Map<String, Object>> list) {
		System.out.println("   ----[[ 상품  LIST ]]");
		System.out.println(" 순번   상품번호                상품명                  가격");
		System.out.println("--------------------------------");
		for(int i=0; i<list.size(); i++) {     
			System.out.printf("%2d   %-12s %-25s %8d\n",(i+1),
					          list.get(i).get("PROD_ID"),
					          ((String)list.get(i).get("PROD_NAME")).trim(),
					          Integer.parseInt(String.valueOf(list.get(i).get("PROD_PRICE"))));	
		}
	}
}
