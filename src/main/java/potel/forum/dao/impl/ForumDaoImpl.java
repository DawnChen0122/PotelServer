package potel.forum.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import potel.forum.dao.ForumDao;
import potel.forum.vo.Comment;
import potel.forum.vo.Forum;
import potel.forum.vo.Like;
import potel.forum.vo.Post;

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
				forum.setPostImage(rs.getString("POSTIMAGE"));
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

	@Override
	public List<Comment> selectComment() {
		String sql = "SELECT * FROM comments";
		List<Comment> comments = new ArrayList<>();

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			System.out.println("Database connection established.");

			while (rs.next()) {
				Comment comment = new Comment();
				comment.setCommentId(rs.getInt("COMMENTID"));
				comment.setPostId(rs.getInt("POSTID"));
				comment.setMemberId(rs.getInt("MEMBERID"));
				comment.setContent(rs.getString("CONTENT"));
				comment.setCreateDate(rs.getTimestamp("CREATEDATE"));
				comment.setModifyDate(rs.getTimestamp("MODIFYDATE"));
				comments.add(comment);
			}

			System.out.println("Fetched " + comments.size() + " comments from the database.");
		} catch (SQLException e) {
			// 使用日誌框架替換 printStackTrace()
			e.printStackTrace();
		}

		return comments.isEmpty() ? null : comments;
	}

	@Override
	public Integer insertPost(Post post) {
		System.out.println("insert post dao");
		System.out.println("MEMBERID: " + post.getMemberId());
		System.out.println("TITLE: " + post.getTitle());
		System.out.println("CONTENT: " + post.getContent());
		System.out.println("POSTIMAGE: " + post.getPostImage());
		LocalDateTime now = LocalDateTime.now();
	    Timestamp timestamp = Timestamp.valueOf(now);
	    String sql = "INSERT INTO Forum (MEMBERID, TITLE, CONTENT, CREATEDATE,MODIFYDATE, POSTIMAGE) VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection conn = ds.getConnection(); 
	         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        pstmt.setInt(1, post.getMemberId());
	        pstmt.setString(2, post.getTitle());
	        pstmt.setString(3, post.getContent()); // 修正插入 CONTENT 欄位
	        pstmt.setTimestamp(4, timestamp);
	        pstmt.setTimestamp(5, null);
	        if(post.getPostImage()!=null) {
	        	pstmt.setString(6, post.getPostImage());
	        }else {
	        	pstmt.setString(6, null);
	        }

	        int affectedRows = pstmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating post failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                return generatedKeys.getInt(1);  
	            } else {
	                throw new SQLException("Creating post failed, no ID obtained.");
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();  
	        return -1;  
	    }
	}
}
