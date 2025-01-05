package potel.shopping.Service;

import java.util.List;
import potel.shopping.Vo.Product;

public interface ListService {

	List<Product> selectAll();
	
	Product getProductInfomation(int product_id);
	
	Boolean insertOrder();
	
}