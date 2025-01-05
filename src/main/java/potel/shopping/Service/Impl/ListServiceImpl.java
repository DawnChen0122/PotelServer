package potel.shopping.Service.Impl;

import java.util.List;

import javax.naming.NamingException;

import potel.shopping.Dao.ListDao;
import potel.shopping.Dao.Impl.ListDaoImpl;
import potel.shopping.Service.ListService;
import potel.shopping.Vo.Product;

public class ListServiceImpl implements ListService {

	private ListDao listDao;

	public ListServiceImpl() throws NamingException{
		listDao = new ListDaoImpl();
	}

	@Override
	public List<Product> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductInfomation(int product_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean insertOrder() {
		// TODO Auto-generated method stub
		return null;
	}
}