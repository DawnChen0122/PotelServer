package potel.shopping.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import potel.shopping.Service.Impl.ShopServiceImpl;
import potel.shopping.Vo.OrderRequest;
import potel.shopping.Vo.Product;


@WebServlet("/shopping/Order")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		OrderRequest orderRequest = gson.fromJson(req.getReader(), OrderRequest.class);
		
		
		
		
	}
}
	