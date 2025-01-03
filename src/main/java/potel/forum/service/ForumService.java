package potel.forum.service;

import java.util.List;

import potel.forum.vo.Forum;
import potel.forum.vo.Like;

public interface ForumService {
	List<Forum> getForum();
	
	List<Like> getLike();
}
