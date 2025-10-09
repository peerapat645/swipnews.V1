package gui;

import java.time.LocalDateTime;
import java.io.File;

public class NewsItem {
    private int id;
    private String userId;
    private LocalDateTime timestamp;
    private String category;
    private String topic;
    private String content;

    public NewsItem(int id, String userId, LocalDateTime timestamp, String category, String topic, String content) {
        this.id = id;
        this.userId = userId;
        this.timestamp = timestamp;
        this.category = category;
        this.topic = topic;
        this.content = content;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getCategory() {
        return category;
    }

    public String getTopic() {
        return topic;
    }

    public String getContent() {
        return content;
    }

    // ตรวจสอบว่ามีรูปหรือไม่
    public boolean hasImage() {
         return getImagePath() != null;
    }

    // ดึง path ของรูปภาพ
public String getImagePath() {
    String[] extensions = {"jpg", "png"}; // ลำดับสำคัญ: ถ้าอยากให้ jpg ถูกเช็คก่อน
    for (String ext : extensions) {
        String path = String.format("./File/accout/news/photo/%d.%s", id, ext);
        File file = new File(path);
        if (file.exists()) {
            return path; // คืนค่าที่เจอไฟล์จริง
        }
    }
    return null; // ไม่มีไฟล์รูป
}

    // String representation for debugging
    @Override
    public String toString() {
        return String.format("[%d] %s - %s (by %s at %s)", 
            id, category, topic, userId, timestamp.toString());
    }

}
