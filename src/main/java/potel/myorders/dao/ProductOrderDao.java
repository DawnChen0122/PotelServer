package potel.myorders.dao;

import javax.naming.NamingException;

import potel.myorders.vo.PrdOrder;
import potel.myorders.vo.ResponseObject;

public interface ProductOrderDao {
	public ResponseObject queryPrdOrders(String memberid, String status) throws NamingException;
	public ResponseObject queryPrdOrder(String prdorderid) throws NamingException;
	public ResponseObject updatePrdOrder(PrdOrder prdorder) throws NamingException;
	
}
