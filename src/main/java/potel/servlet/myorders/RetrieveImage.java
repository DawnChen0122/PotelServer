package potel.servlet.myorders;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zaxxer.hikari.HikariDataSource;

import potel.utils.JDBCConstants;

@WebServlet("/api/image")
public class RetrieveImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String imageid = req.getParameter("imageid");
		
		HikariDataSource ds = JDBCConstants.getDataSource();
		try (Connection conn = ds.getConnection();
		     PreparedStatement pstmt = conn.prepareStatement("select IMAGEDATA from IMAGES where IMAGEID=?");) {
			pstmt.setInt(1, Integer.valueOf(imageid));
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					byte[] imagedata = rs.getBytes("IMAGEDATA");
					resp.setContentType("image/jpg");
					resp.getOutputStream().write(imagedata);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
