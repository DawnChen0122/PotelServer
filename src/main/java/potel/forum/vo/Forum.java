package potel.forum.vo;

import java.sql.Timestamp;

public class Forum {

	// 資料庫中的欄位
	private int postId; // POSTID
	private int memberId; // MEMBERID
	private String title; // TITLE
	private String content; // CONTENT
	private Timestamp createDate; // CREATEDATE
	private Timestamp modifyDate; // MODIFYDATE
	private int postImageId; // POSTIMAGEID

	// 無參數構造函數
	public Forum() {
	}

	// 帶參構造函數
	public Forum(int postId, int memberId, String title, String content, Timestamp createDate, Timestamp modifyDate,
			int postImageId) {
		this.postId = postId;
		this.memberId = memberId;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.postImageId = postImageId;
	}

	// Getter 和 Setter 方法

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getPostImageId() {
		return postImageId;
	}

	public void setPostImageId(int postImageId) {
		this.postImageId = postImageId;
	}

	// 可選：toString 方法，方便打印物件信息
	@Override
	public String toString() {
		return "Forum{" + "postId=" + postId + ", memberId=" + memberId + ", title='" + title + '\'' + ", content='"
				+ content + '\'' + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", postImageId="
				+ postImageId + '}';
	}
}
