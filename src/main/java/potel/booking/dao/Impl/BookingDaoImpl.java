package potel.booking.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import potel.booking.vo.Order;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import potel.booking.dao.BookingDao;
import potel.booking.vo.RoomType;

public class BookingDaoImpl implements BookingDao{
	private HikariDataSource ds;
	public BookingDaoImpl() {
		// 建議將數據源配置移到配置類中
		ds = new HikariDataSource();
		ds.setJdbcUrl("jdbc:mysql://114.32.203.170:3306/potel");
		ds.setUsername("root");
		ds.setPassword("TIP102_25541859101");
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
	}
	
	@Override
	public List<RoomType> findAllRoomType() {
		String sql = "select * from roomtype";
		List<RoomType> list = new ArrayList<>();
		try (
			//ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/example2");
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()
			
			){
			
			while (rs.next()) {
			    RoomType roomtype = new RoomType();
			    
			    roomtype.setRoomtypeid(rs.getInt("ROOMTYPEID"));
			    roomtype.setDescpt(rs.getString("DESCPT"));
			    roomtype.setImageid(rs.getInt("IMAGEID"));
			    roomtype.setPrice(rs.getInt("PRICE"));
			    roomtype.setPettype(rs.getString("PETTYPE").charAt(0));  // 修改部分
			    roomtype.setWeightl(rs.getInt("WEIGHTL"));
			    roomtype.setWeighth(rs.getInt("WEIGHTH"));
			    roomtype.setStatus(rs.getString("STATUS").charAt(0));     // 確認 STATUS 是否為單字符類型
			    roomtype.setCreatedate(rs.getTimestamp("CREATEDATE"));
			    roomtype.setModifydate(rs.getTimestamp("MODIFYDATE"));
			    list.add(roomtype);
			}
//			System.out.println("finish");
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RoomType> findRoomTypeById(int roomTypeId) {
		
		String sql = "select * from roomtype where ROOMTYPEID = ?"; //根據 roomTypeId 查詢
		List<RoomType> list = new ArrayList<>();
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			){
			pstmt.setInt(1, roomTypeId); // 設置查詢參數
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
			    RoomType roomtype = new RoomType();
			    
			    roomtype.setRoomtypeid(rs.getInt("ROOMTYPEID"));
			    roomtype.setDescpt(rs.getString("DESCPT"));
			    roomtype.setImageid(rs.getInt("IMAGEID"));
			    roomtype.setPrice(rs.getInt("PRICE"));
			    roomtype.setPettype(rs.getString("PETTYPE").charAt(0));  // 修改部分
			    roomtype.setWeightl(rs.getInt("WEIGHTL"));
			    roomtype.setWeighth(rs.getInt("WEIGHTH"));
			    roomtype.setStatus(rs.getString("STATUS").charAt(0));     // 確認 STATUS 是否為單字符類型
			    roomtype.setCreatedate(rs.getTimestamp("CREATEDATE"));
			    roomtype.setModifydate(rs.getTimestamp("MODIFYDATE"));
			    list.add(roomtype);
			}
//			System.out.println("selectIDfinish");
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] findImageDataById(int id) {
		String sql = "SELECT IMAGEDATA FROM IMAGES WHERE IMAGEID = ?";
	    byte[] imageData = null;
	    try (Connection conn = ds.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	         
	        pstmt.setInt(1, id); // 設置查詢參數
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            imageData = rs.getBytes("IMAGEDATA"); // 獲取圖片數據
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return imageData;
	}

	@Override
	public void addOrder(Order order) {
String sql = "INSERT INTO orders (MEMBERID, ROOMTYPEID, ROOMID, EXPDATES, EXPDATEE, DATES, DATEE, AMOUNT, PETID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ds.getConnection();
    			PreparedStatement pstmt = conn.prepareStatement(sql);) {
             
            pstmt.setInt(1, order.getMemberid());
            pstmt.setInt(2, order.getRoomtypeid());
            pstmt.setInt(3, order.getRoomid());
            pstmt.setDate(4, new java.sql.Date(order.getExpdates().getTime()));
            pstmt.setDate(5, new java.sql.Date(order.getExpdatee().getTime()));
            pstmt.setDate(6, new java.sql.Date(order.getDates().getTime()));
            pstmt.setDate(7, new java.sql.Date(order.getDatee().getTime()));
            pstmt.setInt(8, order.getAmount());
//            pstmt.setInt(9, order.getRefundamount());
            pstmt.setInt(9, order.getPetid());
//            pstmt.setString(11, String.valueOf(order.getOrderstate()));
//            pstmt.setString(12, String.valueOf(order.getPaymentstate()));
//            pstmt.setString(13, String.valueOf(order.getRefundstate()));
//            pstmt.setInt(14, order.getScore());
//            pstmt.setString(15, order.getComment());
           

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	

}
