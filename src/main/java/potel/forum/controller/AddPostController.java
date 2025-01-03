package potel.forum.controller;

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

@WebServlet("/Forum/addPost")
public class AddPostController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ForumService forumService;

	// 在初始化時創建 ForumService 實例
    @Override
    public void init() throws ServletException {
        forumService = new ForumServiceImpl();  // 初始化 ForumService
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Gson gson = new Gson();
        Forum post = gson.fromJson(req.getReader(), Forum.class);
        
        try {
            Integer newPostId = forumService.AddPost(post);

            if (newPostId != -1) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("{\"postId\": " + newPostId + "}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\": \"Unable to create post. Please try again later.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();  // 可以根据需要记录日志
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Server error occurred while creating post.\"}");
        }
    }
}
