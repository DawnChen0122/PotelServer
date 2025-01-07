package potel.forum.vo;

import java.sql.Timestamp;

public class Image {
    private int imageId;
    private byte[] imageData;
    private Timestamp createDate;
    private Timestamp modifyDate;

    // 無參數構造方法
    public Image() {}

    // 帶參數構造方法
    public Image(int imageId, byte[] imageData, Timestamp createDate, Timestamp modifyDate) {
        this.imageId = imageId;
        this.imageData = imageData;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    // Getter 和 Setter 方法
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
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
}
