package potel.forum.dao;

import java.util.List;

import potel.forum.vo.Comment;
import potel.forum.vo.Forum;
import potel.forum.vo.Like;
import potel.forum.vo.Post;

public interface ForumDao {
	
	List<Forum> selectAll() ;
	
	List<Like> selectlike();

	List<Comment> selectComment();

	Integer insertPost(Post post);

	byte[] getImageById(int imageId);
}
