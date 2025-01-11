package potel.petsfile.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import potel.petsfile.service.PetsFileService;
import potel.petsfile.service.impl.PetsFileServiceImpl;
import potel.petsfile.vo.Dog;

@WebServlet("/PetsFile/AddDog")
public class AddDogController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PetsFileService petsfileService;

    // 在初始化时创建 PetsFileService 实例
    @Override
    public void init() throws ServletException {
        petsfileService = new PetsFileServiceImpl(); // 初始化 PetsFileService
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应的内容类型为 JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 解析 JSON 请求内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        StringBuilder jsonRequest = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonRequest.append(line);
        }

        // 使用 Gson 解析 JSON 请求
        Gson gson = new Gson();
        Dog newDog = gson.fromJson(jsonRequest.toString(), Dog.class); // 反序列化 JSON 为 Dog 对象

        // 获取 Dog 对象的各个字段
        String dogOwner = newDog.getDogOwner();
        String dogName = newDog.getDogName();
        String dogBreed = newDog.getDogBreed();
        String dogGender = newDog.getDogGender();
        int dogImages = newDog.getDogImages();  // 可为 0 或其他有效值

        // 调用 Service 层来处理添加 Dog
        boolean isAdded = petsfileService.addDog(dogOwner, dogName, dogBreed, dogGender, dogImages);

        // 构建响应
        PrintWriter out = resp.getWriter();
        if (isAdded) {
            resp.setStatus(HttpServletResponse.SC_OK); // 响应成功状态码
            out.write("{\"message\": \"Dog added successfully\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 失败状态码
            out.write("{\"message\": \"Failed to add Dog\"}");
        }
    }
}
