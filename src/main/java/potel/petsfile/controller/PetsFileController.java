package potel.petsfile.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import potel.petsfile.service.PetsFileService;
import potel.petsfile.service.impl.PetsFileServiceImpl;
import potel.petsfile.vo.PetsFile;

@WebServlet("/PetsFile")
public class PetsFileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 寵物檔案服務類，用於處理所有與寵物檔案相關的業務邏輯
    private PetsFileService petsFileService;

    // 在構造函數中初始化 PetFileService
    public PetsFileController() {
        this.petsFileService = new PetsFileServiceImpl(); // 使用 PetFileServiceImpl 實現類
    }

    // 處理 GET 請求，獲取所有寵物資料
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 設定回應的內容類型為 JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if ("getAll".equals(action)) {
            // 獲取所有寵物資料
            List<PetsFile> pets;
            try {
                pets = petsFileService.getPetsFile(); // 調用 PetFileService 的 getAllPets() 方法來獲取所有寵物資料
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 設置錯誤狀態碼
                resp.getWriter().write("{\"error\": \"Unable to fetch pets\"}");
                return;
            }

            // 使用 Gson 來將 List<PetsFile> 轉換為 JSON 格式
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(pets);

            // 返回 JSON 數據
            resp.getWriter().write(jsonResponse);
        }
        // 根據 ID 查詢特定的寵物資料
        else if ("getById".equals(action)) {
            String idParam = req.getParameter("id");
            if (idParam != null) {
                int id = Integer.parseInt(idParam);
                try {
                    PetsFile petFile = petsFileService.selectFileById(id);
                    if (petFile != null) {
                        // 使用 Gson 來將 PetsFile 轉換為 JSON 格式
                        Gson gson = new Gson();
                        String jsonResponse = gson.toJson(petFile);
                        resp.getWriter().write(jsonResponse);
                    } else {
                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND); // 查無資料，設定 404 錯誤
                        resp.getWriter().write("{\"error\": \"Pet file not found\"}");
                    }
                } catch (Exception e) {
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    resp.getWriter().write("{\"error\": \"Unable to fetch pet file\"}");
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 缺少 id 參數
                resp.getWriter().write("{\"error\": \"Missing pet file ID\"}");
            }
        }
    }

    // 處理 POST 請求，用於新增、更新和刪除操作
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 設定回應的內容類型為 JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        // 新增寵物資料
        if ("insert".equals(action)) {
            try {
                // 從請求中獲取 PetFile 資料
                BufferedReader reader = req.getReader();
                Gson gson = new Gson();
                PetsFile petFile = gson.fromJson(reader, PetsFile.class); // 假設請求體是 PetsFile 的 JSON 格式

                boolean isSuccess = petsFileService.insertFile(petFile);
                if (isSuccess) {
                    resp.getWriter().write("{\"message\": \"Pet file inserted successfully\"}");
                } else {
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    resp.getWriter().write("{\"error\": \"Failed to insert pet file\"}");
                }
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\": \"Error inserting pet file\"}");
            }
        }
        // 更新寵物資料
        else if ("update".equals(action)) {
            try {
                // 從請求中獲取 PetFile 資料
                BufferedReader reader = req.getReader();
                Gson gson = new Gson();
                PetsFile petFile = gson.fromJson(reader, PetsFile.class); // 假設請求體是 PetsFile 的 JSON 格式

                boolean isSuccess = petsFileService.updateFile(petFile);
                if (isSuccess) {
                    resp.getWriter().write("{\"message\": \"Pet file updated successfully\"}");
                } else {
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    resp.getWriter().write("{\"error\": \"Failed to update pet file\"}");
                }
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\": \"Error updating pet file\"}");
            }
        }
        // 刪除寵物資料
        else if ("delete".equals(action)) {
            String idParam = req.getParameter("id");
            if (idParam != null) {
                int id = Integer.parseInt(idParam);
                boolean isSuccess = petsFileService.deleteFile(id);
                if (isSuccess) {
                    resp.getWriter().write("{\"message\": \"Pet file deleted successfully\"}");
                } else {
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    resp.getWriter().write("{\"error\": \"Failed to delete pet file\"}");
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 缺少 id 參數
                resp.getWriter().write("{\"error\": \"Missing pet file ID\"}");
            }
        }
    }
}
