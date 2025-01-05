package potel.shopping.Dao;

import java.util.List;

import potel.shopping.Vo.Product;

public interface ListDao {

//	Product selectByUserName(String username);

	int insert(Product product);
	
	int deletebyId(Integer id);
	
	int update(Product product);

	Product selectByUsernameAndPassword(Product product);
	
	List<Product> selectAll();
}