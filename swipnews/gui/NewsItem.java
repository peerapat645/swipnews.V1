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
        String imagePath = getImagePath();
        File imageFile = new File(imagePath);
        return imageFile.exists();
    }

    // ดึง path ของรูปภาพ
    public String getImagePath() {
        return String.format("./File/accout/news/images/%d.png", id);
    }

    // String representation for debugging
    @Override
    public String toString() {
        return String.format("[%d] %s - %s (by %s at %s)", 
            id, category, topic, userId, timestamp.toString());
    }
}
