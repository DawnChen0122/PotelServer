package potel.forum.vo;

import java.util.Date;

public class Like {
    private int likeId;
    private int memberId;
    private int postId;
    private Date createDate;

    // 無參數建構子
    public Like() {}

    // 帶參數建構子
    public Like(int likeId, int memberId, int postId, Date createDate) {
        this.likeId = likeId;
        this.memberId = memberId;
        this.postId = postId;
        this.createDate = createDate;
    }

    // Getter 和 Setter 方法
    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    // 重寫 toString 方法（可選）
    @Override
    public String toString() {
        return "Like{" +
                "likeId=" + likeId +
                ", memberId=" + memberId +
                ", postId=" + postId +
                ", createDate=" + createDate +
                '}';
    }
}
