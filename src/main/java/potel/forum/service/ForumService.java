package potel.forum.service;

import java.io.InputStream;
import java.util.List;
import potel.forum.vo.Comment;
import potel.forum.vo.Forum;
import potel.forum.vo.Like;

public interface ForumService {
	List<Forum> getForum();
	
	List<Like> getLike();

	List<Comment> getComment();

	int saveImageToDatabase(InputStream imageStream);

	void addPost(int memberId, String title, String content, int imageId);

	boolean addComment(Comment newComment);

	boolean deletePost(int postId);
	
	boolean deleteComment(int commentId);

	void updatPostWithImage(int postId, String title, String content, Integer imageId);

	void updatPostWithoutImage(int postId, String title, String content);

	void updatPost(int postId, String title, String content);

	void updateComment(int commentId, String content);

	boolean likePost(int postId, int memberId);

	boolean unlikePost(int postId, int memberId);

}
