package potel.shopping.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import potel.shopping.Service.Impl.ListServiceImpl;
import potel.shopping.Vo.Product;


@WebServlet("/shopping/List")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet");
        try {
   
        	 ListServiceImpl service = new ListServiceImpl();
             List<Product> products = service.selectAll();

            // 將產品列表轉換為 JSON
            
            
            
//            String json = gson.toJson(products);

            // 將 JSON 回傳給前端
//            resp.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
         
        }
    }
}