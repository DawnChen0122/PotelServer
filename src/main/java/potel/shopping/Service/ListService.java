package potel.shopping.Service;

import java.util.List;
import potel.shopping.Vo.Product;

public interface ListService {

	List<Product> selectAll(String prdtype);
	
	Product getProductInfomation(int product_id);
	
	Boolean insertOrder();
	
} 