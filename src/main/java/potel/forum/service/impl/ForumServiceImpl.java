package potel.forum.service.impl;

import java.util.List;

import potel.forum.dao.ForumDao;
import potel.forum.dao.impl.ForumDaoImpl;
import potel.forum.service.ForumService;
import potel.forum.vo.Comment;
import potel.forum.vo.Forum;
import potel.forum.vo.Like;
import potel.forum.vo.Post;

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

	@Override
	public List<Like> getLike() {
		System.out.println("Forum service get likes");
		 List<Like> Likes = forumDao.selectlike();
		    System.out.println("Retrieved Likes: " + Likes.size()); // 打印返回的論壇數量
		    return Likes;
	}

	
	@Override
	public List<Comment> getComment() {
		System.out.println("Forum service get Comments");
		 List<Comment> Comments = forumDao.selectComment();
		    System.out.println("Retrieved Comments: " + Comments.size()); // 打印返回的論壇數量
		    return Comments;
	}



	@Override
	public byte[] retrieveImage(int imageId) {
		return forumDao.getImageById(imageId);
	}


	@Override
	public Integer AddPost(Post post) {
		return forumDao.insertPost(post);
	}


}
