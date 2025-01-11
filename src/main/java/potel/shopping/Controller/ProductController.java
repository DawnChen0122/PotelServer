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
import potel.shopping.Vo.Product;


@WebServlet("/shopping/Product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet");
		
		String prdId = req.getParameter("prdId");
		System.out.println("ProductController prdId=" + prdId);
		try {
        	 ShopServiceImpl service = new ShopServiceImpl();
             Product product = service.getProduct(Integer.parseInt(prdId));

            // 將產品列表轉換為 JSON
             
//            String json = gson.toJson(products);
             
            // 將 JSON 回傳給前端
             
            resp.getWriter().write(new Gson().toJson(product, Product.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
