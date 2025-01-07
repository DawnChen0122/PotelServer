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

	

}
