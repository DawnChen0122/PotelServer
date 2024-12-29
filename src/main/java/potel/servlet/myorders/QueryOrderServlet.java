package potel.servlet.myorders;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.zaxxer.hikari.HikariDataSource;

import potel.utils.JDBCConstants;
import static potel.utils.Defines.*;

@WebServlet(description = "查詢預約訂房訂單明細", urlPatterns = { "/api/order" })
public class QueryOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String orderid = req.getParameter("orderid");
		System.out.println("[" + sdf.format(new Date()) + "] orderid=" + orderid);
		
		HikariDataSource ds = JDBCConstants.getDataSource();
		
		try (
		     Connection conn = ds.getConnection();
		     PreparedStatement pstmt = conn.prepareStatement("select o.ROOMID,o.EXPDATES,o.EXPDATEE"
		                                                     + ",o.DATES,o.DATEE,o.AMOUNT,o.REFUNDAMOUNT,o.PETID"
		                                                     + ",o.PAYMENTSTATE,o.REFUNDSTATE,o.SCORE,o.COMMENT,o.PAYDATETIME"
		                                                     + ",o.REFUNDDATETIME,o.CREATEDATE"
		                                                     + ",rt.DESCPT,rt.PETTYPE,rt.IMAGEID RTIMG,rt.ROOMTYPEID"
		                                                     + ",p.NICKNAME,p.IMAGEID PIMG"
		                                                     + ",m.NAME"
		                                                     + " from ORDERS o"
		                                                     + " inner join ROOMTYPE rt on o.ROOMTYPEID=rt.ROOMTYPEID"
		                                                     + " inner join IMAGES im on rt.IMAGEID=im.IMAGEID"
		                                                     + " inner join PETS p on o.PETID=p.PETID"
		                                                     + " inner join MEMBERS m on m.MEMBERID=o.MEMBERID"
		                                                     + " where o.ORDERID=?");		) {
			Object temp = null;
			int pindex = 1;
			pstmt.setInt(pindex++, Integer.valueOf(orderid));
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					JsonObject jorder = new JsonObject();
					jorder.addProperty("orderid", Integer.valueOf(orderid));
					jorder.addProperty("roomid", rs.getInt("ROOMID"));
					jorder.addProperty("expdates", sdfd.format(rs.getDate("EXPDATES")));
					jorder.addProperty("expdatee", sdfd.format(rs.getDate("EXPDATEE")));
					
					jorder.addProperty("dates", (temp=rs.getDate("DATES"))==null?null:sdfd.format(temp));
					jorder.addProperty("datee", (temp=rs.getDate("DATEE"))==null?null:sdfd.format(temp));
					jorder.addProperty("amount", rs.getInt("AMOUNT"));
					jorder.addProperty("refundamount", rs.getInt("REFUNDAMOUNT"));
					jorder.addProperty("petid", rs.getInt("PETID"));
					
					JsonObject jroomtype = new JsonObject();
					jroomtype.addProperty("roomtypeid", rs.getInt("ROOMTYPEID"));
					jroomtype.addProperty("descpt", rs.getString("DESCPT"));
					jroomtype.addProperty("pettype", rs.getString("PETTYPE"));
					jroomtype.addProperty("imageid", rs.getInt("RTIMG"));
					jorder.add("roomtype", jroomtype);
					
					JsonObject jpet = new JsonObject();
					jpet.addProperty("nickname", rs.getString("NICKNAME"));
					jpet.addProperty("imageid", rs.getInt("PIMG"));
					jorder.add("pet", jpet);
					
					JsonObject jmember = new JsonObject();
					jmember.addProperty("name", rs.getString("NAME"));
					jorder.add("member", jmember);

					Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss")
										.create();
					resp.getWriter().write(gson.toJson(jorder));
				}else {
					System.out.println("沒有資料");
					resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}
