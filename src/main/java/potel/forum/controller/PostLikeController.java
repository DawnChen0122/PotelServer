package potel.forum.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import potel.forum.service.ForumService;
import potel.forum.service.impl.ForumServiceImpl;

@WebServlet("/posts/*")
public class PostLikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ForumService forumService;

	// 在初始化時創建 ForumService 實例
	@Override
	public void init() throws ServletException {
		forumService = new ForumServiceImpl(); // 初始化 ForumService
	}
	
}
