package potel.forum.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import potel.forum.dao.ForumDao;
import potel.forum.vo.Forum;

public class ForumDaoImpl implements ForumDao{
	
	private HikariDataSource ds;
	
	public ForumDaoImpl() {
	    try {
	    	ds = new HikariDataSource();
	    	ds.setJdbcUrl("jdbc:mysql://114.32.203.170:3306/potel");
	    	ds.setUsername("root");
	    	ds.setPassword("TIP102_25541859101");
	        try (Connection conn = ds.getConnection()) {
	            System.out.println("Successfully connected to the database!");
	        }
	    } catch (Exception e) {
	        System.out.println("Failed to connect to the database.");
	        e.printStackTrace();
	    }
	}
	
	@Override
	public List<Forum> selectAll() {
//	    System.out.println("Start querying forums from database.");
//	    String sql = "SELECT * FROM forum";
//	    try (
//	        Connection conn = ds.getConnection();
//	        PreparedStatement pstmt = conn.prepareStatement(sql);
//	        ResultSet rs = pstmt.executeQuery();
//	    ) {
//	    	System.out.println("Connection successful!");
//	        List<Forum> forums = new ArrayList<>();
//	        System.out.println("Database connection established.");
//	        while (rs.next()) {
//	            System.out.println("Fetching row...");
//	            Forum forum = new Forum();
//	            forum.setPostId(rs.getInt("POSTID"));
//	            forum.setMemberId(rs.getInt("MEMBERID"));
//	            forum.setTitle(rs.getString("TITLE"));
//	            forum.setContent(rs.getString("CONTENT"));
//	            forum.setCreateDate(rs.getTimestamp("CREATEDATE"));
//	            forum.setModifyDate(rs.getTimestamp("MODIFYDATE"));
//	            forum.setPostImageId(rs.getInt("POSTIMAGEID"));
//	            forums.add(forum);
//	            System.out.println("Fetched forum: " + forum.getTitle());
//	        }
//	        System.out.println("Number of forums fetched: " + forums.size());
//	        return forums;
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
	    return null;
	}

}
