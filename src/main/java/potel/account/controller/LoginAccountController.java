package potel.account.controller;

import java.io.IOException;
import java.nio.channels.Pipe.SourceChannel;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import potel.account.service.AccountService;
import potel.account.service.impl.AccountServiceImpl;
import potel.account.vo.Member;

@WebServlet("/member/login")
public class LoginAccountController extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private AccountService accountService;

		@Override
		public void init() throws ServletException {
			try {
				accountService = new AccountServiceImpl();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			String input = req.getParameter("INPUT");
			String passwd = req.getParameter("PASSWORD");
			System.out.println("input(" + input + "," + passwd + ")");
			Member result = accountService.login(input,passwd);
//			JsonObject respBody = new JsonObject();
////			respBody.addProperty("success", result);
////			respBody.addProperty("message", result ? "正確" : "不正確");
//			respBody.
//			String res = respBody.toString();
//			System.out.println("input(" + input + "," + passwd + ")==>" + res);
			resp.getWriter().write(new Gson().toJson(result, Member.class));
		}

}
