package potel.forum.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import potel.forum.service.ForumService;
import potel.forum.service.impl.ForumServiceImpl;
import potel.forum.vo.Forum;

@WebServlet("/GetForums")
public class GetForumsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ForumService forumService;

    // 在構造函數中初始化 ForumService
    public GetForumsController() {
        this.forumService = new ForumServiceImpl();  // 使用 ForumServiceImpl 實現類
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 // 初始化 HttpServletResponse 的 writer
    	System.out.println("doGet");
        var writer = resp.getWriter();

        // 設定回應的內容類型為 JSON
        resp.setContentType("application/json");

        // 1. 調用 ForumService 的 getForum() 方法來獲取所有論壇貼文資料
        List<Forum> forums = forumService.getForum();

        // 若 forums 為空，則回傳一個空的 JSON 數組
        if (forums == null || forums.isEmpty()) {
            writer.write("[]");
            return;
        }

        // 2. 構建 JSON 字串手動轉換 List<Forum> 為 JSON
        StringBuilder jsonResponse = new StringBuilder();
        jsonResponse.append("[");

        for (int i = 0; i < forums.size(); i++) {
            Forum forum = forums.get(i);
            jsonResponse.append("{")
                        .append("\"POSTID\":").append(forum.getPostId()).append(",")
                        .append("\"MEMBERID\":").append(forum.getMemberId()).append(",")
                        .append("\"TITLE\":\"").append(forum.getTitle()).append("\",")
                        .append("\"CONTENT\":\"").append(forum.getContent()).append("\",")
                        .append("\"CREATEDATE\":\"").append(forum.getCreateDate()).append("\",")
                        .append("\"MODIFYDATE\":").append(forum.getModifyDate() == null ? "null" : "\"" + forum.getModifyDate() + "\"").append(",")
                        .append("\"POSTIMAGEID\":").append(forum.getPostImageId())
                        .append("}");

            if (i < forums.size() - 1) {
                jsonResponse.append(",");
            }
        }

        jsonResponse.append("]");

        // 3. 將手動構建的 JSON 字串寫入回應流
        writer.write(jsonResponse.toString());

    }
}
