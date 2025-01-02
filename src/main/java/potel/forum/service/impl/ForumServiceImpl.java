package potel.forum.service.impl;

import java.util.List;

import potel.forum.dao.ForumDao;
import potel.forum.dao.impl.ForumDaoImpl;
import potel.forum.service.ForumService;
import potel.forum.vo.Forum;

public class ForumServiceImpl implements ForumService {
	
	private ForumDao forumDao;
	
	public ForumServiceImpl() {
		this.forumDao = new ForumDaoImpl();
	}

	@Override
	public List<Forum> getForum() {
		System.out.println("Forum service get forum");
		 List<Forum> forums = forumDao.selectAll();
		    System.out.println("Retrieved forums: " + forums.size()); // 打印返回的論壇數量
		    return forums;
	}
	
}