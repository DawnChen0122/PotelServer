package potel.booking.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RoomType {
	private int roomTypeId;         // 房型 ID (主鍵)
    private String descpt;          // 描述
    private int imageId;            // 圖片 ID
    private int price;              // 價格
    private char petType;           // 寵物類型 (單一字元)
    private int weightL;            // 最低重量
    private int weightH;            // 最高重量
    private char status;            // 狀態 (單一字元)
    private Timestamp createDate;   // 創建時間
    private Timestamp modifyDate;   // 修改時間
}
