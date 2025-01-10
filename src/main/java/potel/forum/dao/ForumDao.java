package potel.forum.dao;

import java.io.InputStream;
import java.util.List;
import potel.forum.vo.Comment;
import potel.forum.vo.Forum;
import potel.forum.vo.Like;

public interface ForumDao {
	
	List<Forum> selectAll() ;
	
	List<Like> selectlike();

	List<Comment> selectComment();

	Integer insertImage(InputStream imageStream);

	void addPost(int memberId, String title, String content, int imageId);

	boolean insertComment(Comment comment);

	boolean deletePostAndComments(int postId);
	
	boolean deleteComment(int commentId);

	void updatPostWithImage(int postId, String title, String content, int imageId);

	void updatePostWithoutImage(int postId, String title, String content);

	void updatePost(int postId, String title, String content);

	void updateComment(int commentId, String content);
}
