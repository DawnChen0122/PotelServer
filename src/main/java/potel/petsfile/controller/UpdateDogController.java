package potel.petsfile.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import potel.petsfile.service.PetsFileService;
import potel.petsfile.service.impl.PetsFileServiceImpl;

@WebServlet("/PetsFile/updateDog")
public class UpdateDogController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private PetsFileService petsfileService;

	public void init() throws ServletException {
		petsfileService = new PetsFileServiceImpl();
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
		    Dog dog = gson.fromJson(stringBuilder.toString(), Dog.class);

		    petsfileService.updateDog(dog.getDogId(), dog.getDogname());

			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().write("dog updateed ok");
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().write("Error: " + e.getMessage());
		}
	}
}

