package potel.forum.dao.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import potel.forum.dao.ForumDao;
import potel.forum.vo.Comment;
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

		String sql = "SELECT * FROM forum"; // 可考慮只選擇必要的欄位來提高效能
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
				forum.setImageId(rs.getInt("IMAGEID"));
				forums.add(forum);
			}

			System.out.println("Fetched " + forums.size() + " likes from the database.");
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
	public Integer insertImage(InputStream imageStream) {
		int imageId = 0;
		try (Connection conn = ds.getConnection();) { // 替換為你自己的資料庫連線方法
			String sql = "INSERT INTO images (IMAGEDATA, CREATEDATE) VALUES (?, ?)";
			try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				stmt.setBlob(1, imageStream);
				stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
				stmt.executeUpdate();

				// 獲取自動生成的 IMAGEID
				try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						imageId = generatedKeys.getInt(1);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageId;
	}

	@Override
	public void addPost(int memberId, String title, String content, int imageId) {
		try (Connection conn = ds.getConnection()) {
			String sql = "INSERT INTO forum (MEMBERID, TITLE, CONTENT, CREATEDATE, IMAGEID) VALUES (?, ?, ?, ?, ?)";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, memberId);
				stmt.setString(2, title);
				stmt.setString(3, content);
				stmt.setTimestamp(4, Timestamp.valueOf(java.time.LocalDateTime.now()));
				if (imageId > 0) {
					stmt.setInt(5, imageId);
				} else {
					stmt.setNull(5, java.sql.Types.INTEGER);
				}
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public boolean insertComment(Comment comment) {
String sql = "INSERT INTO comments (POSTID, MEMBERID, CONTENT) VALUES (?, ?, ?)";
        
        try (Connection conn = ds.getConnection(); // 使用資料源獲取連線
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // 設置參數
            stmt.setInt(1, comment.getPostId());   // POSTID
            stmt.setInt(2, comment.getMemberId()); // MEMBERID
            stmt.setString(3, comment.getContent()); // CONTENT
            
            // 執行插入操作
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // 如果影響行數大於 0，表示插入成功
            
        } catch (SQLException e) {
            e.printStackTrace(); // 如果有錯誤則打印出錯誤信息
            return false; // 發生錯誤時返回 false
        }
	}

}
