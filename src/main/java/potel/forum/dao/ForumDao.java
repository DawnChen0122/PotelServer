package potel.forum.dao;

import java.util.List;

import potel.forum.vo.Comment;
import potel.forum.vo.Forum;
import potel.forum.vo.Like;

public interface ForumDao {
	
	List<Forum> selectAll() ;
	
	List<Like> selectlike();

	List<Comment> selectComment();

	Integer insertPost(Forum post);
}
