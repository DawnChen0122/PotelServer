package potel.forum.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import potel.forum.service.ForumService;
import potel.forum.service.impl.ForumServiceImpl;

@WebServlet("/api/image/*")
public class GetImage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ForumService imageService = new ForumServiceImpl(); // 使用 ImageService 來處理業務邏輯

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 String imageidParam = req.getParameter("imageid");

         try {
             // 解析 imageid 參數
             int imageId = Integer.parseInt(imageidParam);

             // 透過 Service 層獲取圖片資料
             byte[] imageData = imageService.retrieveImage(imageId);  

             if (imageData != null) {
                 // 設置圖片的 Content-Type，這裡假設是 JPG 格式
                 resp.setContentType("image/jpg");
                 resp.getOutputStream().write(imageData);  // 直接輸出圖片資料
             } else {
                 resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found");
             }

         } catch (NumberFormatException e) {
             resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid imageid format");
         } catch (IOException e) {
             resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving image");
         }
     }
}