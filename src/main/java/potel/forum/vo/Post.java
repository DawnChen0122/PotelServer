package potel.forum.vo;

public class Post {
    private int memberId; // MEMBERID
    private String title; // TITLE
    private String content; // CONTENT
    private String postImage; // POSTIMAGE

    // 無參數建構子
    public Post() {}

    // 帶參數建構子
    public Post(int memberId, String title, String content, String postImage) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.postImage = postImage;
    }

    // Getter 和 Setter 方法
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

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    @Override
    public String toString() {
        return "Post{" +
                "memberId=" + memberId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", postImage='" + postImage + '\'' +
                '}';
    }
}
