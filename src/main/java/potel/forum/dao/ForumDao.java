package potel.forum.dao;

import java.util.List;

import potel.forum.vo.Forum;

public interface ForumDao {
	
	List<Forum> selectAll();
}
