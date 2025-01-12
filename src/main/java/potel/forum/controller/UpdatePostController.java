package potel.forum.controller;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import potel.forum.service.ForumService;
import potel.forum.service.impl.ForumServiceImpl;
import potel.forum.vo.Forum;

@WebServlet("/Forum/updatePost")
public class UpdatePostController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ForumService forumService;

	// 在初始化時創建 ForumService 實例
	@Override
	public void init() throws ServletException {
		forumService = new ForumServiceImpl(); // 初始化 ForumService
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		try {
			BufferedReader reader = req.getReader();
		    StringBuilder stringBuilder = new StringBuilder();
		    String line;
		    while ((line = reader.readLine()) != null) {
		        stringBuilder.append(line);
		    }

		    // 解析 JSON 請求
		    Gson gson = new Gson();
		    Forum post = gson.fromJson(stringBuilder.toString(), Forum.class);

			forumService.updatPost(post.getPostId(), post.getTitle(), post.getContent());

			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().write("Post updateed ok");
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().write("Error: " + e.getMessage());
		}
	}
}
