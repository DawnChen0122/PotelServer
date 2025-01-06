package potel.account.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import potel.account.service.AccountService;
import potel.account.service.impl.AccountServiceImpl;
import potel.account.vo.AccountBean;

/**
 * Servlet implementation class PlaceHolderController
 */
@WebServlet("/member/login")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		String loginid = request.getParameter("loginid");
		String password = request.getParameter("password");
		Gson gson = new Gson();
		try {
			AccountService as = new AccountServiceImpl();
			AccountBean user = as.login(loginid, password);
			response.getWriter().write(gson.toJson(user));
		} catch (NamingException e) {
			response.getWriter().write(gson.toJson(""));
		}
	}

}
