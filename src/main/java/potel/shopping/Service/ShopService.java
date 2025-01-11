package potel.shopping.Service;

import java.util.List;

import javax.naming.NamingException;

import potel.shopping.Vo.OrderRequest;
import potel.shopping.Vo.Product;

public interface ShopService {

	List<Product> selectAll(String prdtype) throws NamingException;
	
	Product getProduct(int prdId) throws NamingException;
	
	int insertOrder(OrderRequest orderRequest);
	
} 