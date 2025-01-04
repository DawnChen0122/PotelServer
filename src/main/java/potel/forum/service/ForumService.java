package potel.forum.service;

import java.util.List;

import potel.forum.vo.Comment;
import potel.forum.vo.Forum;
import potel.forum.vo.Like;
import potel.forum.vo.Post;

public interface ForumService {
	List<Forum> getForum();
	
	List<Like> getLike();

	List<Comment> getComment();

	Integer AddPost(Post post);

	byte[] retrieveImage(int imageId);
}