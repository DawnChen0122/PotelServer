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
	public List<Product> selectAll(String prdtype) {
		// TODO Auto-generated method stub
		if(prdtype==null) {
			System.out.println("未傳入參數(" + prdtype + ")");
			return null;
		}else if(!prdtype.equalsIgnoreCase("c") && !prdtype.equalsIgnoreCase("d")) {
			System.out.println("傳入參數錯誤(" + prdtype + ")");
			return null;
		}
		return listDao.selectAll(prdtype.toUpperCase());
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