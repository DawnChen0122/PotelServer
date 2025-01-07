package potel.shopping.Dao;

import java.util.List;

import potel.shopping.Vo.Product;

public interface ListDao {


	int insert(Product product);
	
	int deletebyId(Integer id);
	
	int update(Product product);
	
	List<Product> selectAll(String prdtype);
}