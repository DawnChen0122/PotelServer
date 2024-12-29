package potel.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.zaxxer.hikari.HikariDataSource;

public class ReadImage {

	public static void main(String[] args) {
		Path path = Paths.get("C:\\27-JohnnyWu\\Work\\Project\\pics\\cat.jpg");
		try {
			byte[] imgdata = Files.readAllBytes(path);
			HikariDataSource ds = JDBCConstants.getDataSource();
			try(Connection conn = ds.getConnection();
								PreparedStatement pstmt = conn.prepareStatement("update images set imagedata=? where imageid=?");){
				pstmt.setBytes(1, imgdata);
				pstmt.setInt(2, 4);
				int effect = pstmt.executeUpdate();
				System.out.println("take effect=" + effect);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
