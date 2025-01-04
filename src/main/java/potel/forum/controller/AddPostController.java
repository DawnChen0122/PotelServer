package potel.forum.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.ls.LSOutput;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import potel.forum.service.ForumService;
import potel.forum.service.impl.ForumServiceImpl;
import potel.forum.vo.Forum;
import potel.forum.vo.Post;

@WebServlet("/Forum/AddPost")
public class AddPostController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ForumService forumService;

	// 在初始化時創建 ForumService 實例
	@Override
	public void init() throws ServletException {
		forumService = new ForumServiceImpl(); // 初始化 ForumService
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Forum service doPost");
		Gson gson = new Gson();
		Post post = gson.fromJson(req.getReader(), Post.class);
		forumService.AddPost(post);
	}
}
