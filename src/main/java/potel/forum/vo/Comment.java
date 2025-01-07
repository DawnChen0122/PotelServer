package potel.forum.vo;

import java.sql.Timestamp;

public class Comment {
    private int commentId; // COMMENTID
    private int postId;    // POSTID
    private int memberId;  // MEMBERID
    private String content; // CONTENT
    private Timestamp createDate; // CREATEDATE
    private Timestamp modifyDate; // MODIFYDATE 

    // Default constructor
    public Comment() {
    }

    // Constructor with parameters
    public Comment(int commentId, int postId, int memberId, String content, Timestamp createDate, Timestamp modifyDate) {
        this.commentId = commentId;
        this.postId = postId;
        this.memberId = memberId;
        this.content = content;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    // Getters and Setters
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

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

    // toString method for debugging
    @Override
    public String toString() {
        return "Comments{" +
                "commentId=" + commentId +
                ", postId=" + postId +
                ", memberId=" + memberId +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
