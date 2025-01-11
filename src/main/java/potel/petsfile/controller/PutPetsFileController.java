package potel.petsfile.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import potel.petsfile.service.PetsFileService;
import potel.petsfile.service.impl.PetsFileServiceImpl;
import potel.petsfile.vo.PetsFile;

@WebServlet("/potel/petsfile")
public class PutPetsFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 寵物檔案服務類，用於處理所有與寵物檔案相關的業務邏輯
	private PetsFileService petsFileService;

	// 在構造函數中初始化 PetFileService
	public PutPetsFileController() {
		this.petsFileService = new PetsFileServiceImpl(); // 使用 PetFileServiceImpl 實現類
	}
	// 處理 PUT 請求，更新指定的寵物資料
		@Override
		protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// 設定回應的內容類型為 JSON
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			// 從請求中獲取寵物資料（假設是 JSON 格式）
			Gson gson = new Gson();
			PetsFile updatedPet = gson.fromJson(req.getReader(), PetsFile.class); // 將請求的 JSON 轉換為 Pet 物件

			try {
				PetsFileService.updatePet(updatedPet); // 調用 PetFileService 的 updatePet() 方法來更新寵物資料
			} catch (Exception e) {
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 設置錯誤狀態碼
				resp.getWriter().write("{\"error\": \"Unable to update petsFile\"}");
				return;
			}

			// 返回成功訊息
			resp.getWriter().write("{\"message\": \"PetsFile updated successfully\"}");
		}
	}