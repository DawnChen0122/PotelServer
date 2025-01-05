package potel.forum.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Forum {
    // 資料庫中的欄位
    private int postId; // POSTID
    private int memberId; // MEMBERID
    private String title; // TITLE
    private String content; // CONTENT
    private Timestamp createDate; // CREATEDATE
    private Timestamp modifyDate; // MODIFYDATE
    private Integer ImageId; // POSTIMAGEID (改為 Integer，允許為 null)

}
