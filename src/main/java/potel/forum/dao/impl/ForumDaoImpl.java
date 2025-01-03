package potel.forum.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import potel.forum.dao.ForumDao;
import potel.forum.vo.Forum;
import potel.forum.vo.Like;

public class ForumDaoImpl implements ForumDao {

    private HikariDataSource ds;

    public ForumDaoImpl() {
        // 建議將數據源配置移到配置類中
        ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://114.32.203.170:3306/potel");
        ds.setUsername("root");
        ds.setPassword("TIP102_25541859101");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }

    @Override
    public List<Forum> selectAll() {

        String sql = "SELECT * FROM forum";
        List<Forum> forums = new ArrayList<>();
        
        try (Connection conn = ds.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql); 
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Database connection established.");

            while (rs.next()) {
                Forum forum = new Forum();
                forum.setPostId(rs.getInt("POSTID"));
                forum.setMemberId(rs.getInt("MEMBERID"));
                forum.setTitle(rs.getString("TITLE"));
                forum.setContent(rs.getString("CONTENT"));
                forum.setCreateDate(rs.getTimestamp("CREATEDATE"));
                forum.setModifyDate(rs.getTimestamp("MODIFYDATE"));
                forum.setPostImageId(rs.getInt("POSTIMAGEID"));
                forums.add(forum);
            }
            
            System.out.println("Fetched " + forums.size() + " forums from the database.");
        } catch (SQLException e) {
            // 使用日誌框架替換 printStackTrace()
            e.printStackTrace();
        }
        
        return forums.isEmpty() ? null : forums;
    }

	
    @Override
	public List<Like> selectlike() {
    	String sql = "SELECT * FROM likes";
        List<Like> likes = new ArrayList<>();
        try (Connection conn = ds.getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(sql); 
                ResultSet rs = pstmt.executeQuery()) {

               System.out.println("Database connection established.");
               
               while (rs.next()) {
                   Like like = new Like();
                   like.setLikeId(rs.getInt("LIKEID"));
                   like.setMemberId(rs.getInt("MEMBERID"));
                   like.setPostId(rs.getInt("POSTID"));
                   like.setCreateDate(rs.getTimestamp("CREATEDATE"));
                   likes.add(like);
               }

               System.out.println("Fetched " + likes.size() + " likes from the database.");
           } catch (SQLException e) {
               // 使用日誌框架替換 printStackTrace()
               e.printStackTrace();
           }

           return likes.isEmpty() ? null : likes;
	}

}
