package potel.shopping.Dao;

import java.util.List;

import javax.naming.NamingException;

import potel.shopping.Vo.OrderRequest;
import potel.shopping.Vo.Product;

public interface ShopDao {


	int insert(Product product);
	
	int deletebyId(Integer prdId);
	
	int update(Product product);
	
	List<Product> selectAll(String prdtype) throws NamingException;
	
	Product select(int prdId) throws NamingException;
	
	int insertOrder(OrderRequest orderRequest);
	
	int insertOrderItem(OrderRequest orderRequest);
	
}