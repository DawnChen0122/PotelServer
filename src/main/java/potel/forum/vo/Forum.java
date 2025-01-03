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
    private Integer postImageId; // POSTIMAGEID (改為 Integer，允許為 null)

    // 無參數構造函數
    public Forum() {
    }

    // 帶參構造函數
    public Forum(int postId, int memberId, String title, String content, Timestamp createDate, Timestamp modifyDate,
                 Integer postImageId) {
        this.postId = postId;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.postImageId = postImageId;
    }

    // Getter and Setter methods
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

    public Integer getPostImageId() {
        return postImageId;
    }

    public void setPostImageId(Integer postImageId) {
        this.postImageId = postImageId;
    }
}

