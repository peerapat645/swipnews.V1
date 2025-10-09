
package gui;

import java.awt.event.*;
import javax.swing.*;
import gui.set.setRoundedPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Image;
import java.awt.FontMetrics;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class NewsFeedPanal extends setRoundedPanel implements MouseListener, MouseMotionListener {

    private int startX = 0;
    private int startY = 0;
    private int offsetX = 0; // ระยะเลื่อนแนวนอน
    private JPopupMenu categoryPopup;
    private JLabel categoryLabel;
    private String selectedCategory = "All";
    
    private List<NewsItem> allNews = new ArrayList<>();
    private List<NewsItem> filteredNews = new ArrayList<>();
    private List<Integer> readNewsIds = new ArrayList<>();
    private int currentIndex = 0;
    private String currentUserId;

public NewsFeedPanal(String userId) {
    super(20);
    this.currentUserId = userId;
    this.setLayout(null);
    this.setBackground(java.awt.Color.gray);
    this.setBounds(20, 20, 510, 510);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);

    // สร้างเมนู Popup สำหรับหมวดหมู่
    categoryPopup = new JPopupMenu();
    
    // เพิ่มตัวเลือก "All" เป็นตัวแรก
    JMenuItem allItem = new JMenuItem("All");
    allItem.addActionListener(e -> selectCategory("All"));
    categoryPopup.add(allItem);
    categoryPopup.addSeparator();
    
    // เพิ่มหมวดหมู่จาก Category.java
    for (String category : gui.set.Category.categories) {
        JMenuItem item = new JMenuItem(category);
        item.addActionListener(e -> selectCategory(category));
        categoryPopup.add(item);
    }

    // สร้าง Label สำหรับแสดงหมวดหมู่ที่เลือก
    categoryLabel = new JLabel("Category: " + selectedCategory);
    categoryLabel.setFont(new java.awt.Font("Leelawadee UI", java.awt.Font.BOLD, 14));
    categoryLabel.setBounds(380, 10, 150, 30);
    categoryLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    categoryLabel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            categoryPopup.show(categoryLabel, 0, categoryLabel.getHeight());
        }
    });
    this.add(categoryLabel);

    // สร้าง Label สำหรับแสดงหมวดหมู่ที่เลือก

    // โหลดข่าวทั้งหมด
    loadNews();
    updateFilteredNews();
}
// โหลดข่าวจากไฟล์ CSV
private void loadNews() {
    allNews.clear();
    try (BufferedReader reader = new BufferedReader(new FileReader("./File/accout/news/news.csv"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 6) {
                int id = Integer.parseInt(parts[0]);
                String userId = parts[1];
                LocalDateTime timestamp = LocalDateTime.parse(parts[2]);
                String category = parts[3];
                String topic = parts[4];
                String content = parts[5];
                
                NewsItem newsItem = new NewsItem(id, userId, timestamp, category, topic, content);
                allNews.add(newsItem);
            }
        }
    } catch (IOException e) {
        System.err.println("Error loading news: " + e.getMessage());
    }
}
// อัปเดตรายการข่าวที่กรองตามหมวดหมู่ที่เลือก
private void updateFilteredNews() {
    loadReadHistory(); // โหลดประวัติการอ่านล่าสุด
    filteredNews.clear();
    for (NewsItem news : allNews) {
        if ((selectedCategory.equals("All") || news.getCategory().equals(selectedCategory)) 
            && !readNewsIds.contains(news.getId())) {
            filteredNews.add(news);
        }
    }
    currentIndex = 0;
    updateCurrentNews();
}
// อัปเดตรูปภาพและหัวข้อข่าวที่แสดง
private Image currentImage = null;
private String currentTitle = "";
private boolean noMoreNews = false;

// โหลดรูปภาพจากไฟล์ตาม newsId
private void updateCurrentNews() {
    if (!filteredNews.isEmpty() && currentIndex >= 0 && currentIndex < filteredNews.size()) {
        NewsItem current = filteredNews.get(currentIndex);
        loadImage(current.getId());
        currentTitle = current.getTopic();
        noMoreNews = false;
    } else {
        currentImage = null;
        currentTitle = "";
        noMoreNews = filteredNews.isEmpty();
    }
    repaint();
}
// โหลดรูปภาพจากไฟล์ตาม newsId
private void loadImage(int newsId) {
    currentImage = null;
    if (newsId >= 0) {
        // ตรวจสอบโฟลเดอร์รูปภาพ
        File photoDir = new File("./File/accout/news/photo");
        if (!photoDir.exists()) {
            System.err.println("Photo directory does not exist");
            return;
        }

        // ค้นหาไฟล์ที่มี prefix เป็น newsId
        File[] files = photoDir.listFiles((dir, name) -> {
            String lowercaseName = name.toLowerCase();
            // ตรวจสอบว่าชื่อไฟล์ขึ้นต้นด้วย newsId และเป็นไฟล์รูปภาพที่รองรับ (jpg, jpeg, png)
            return name.startsWith(newsId + ".") && (
                lowercaseName.endsWith(".jpg") || 
                lowercaseName.endsWith(".jpeg") || 
                lowercaseName.endsWith(".png")
            );
        });

        // ถ้าพบไฟล์รูปภาพ
        if (files != null && files.length > 0) {
            try {
                // ใช้ไฟล์แรกที่พบ
                currentImage = ImageIO.read(files[0]);
                if (currentImage == null) {
                    System.err.println("Could not load image: " + files[0].getName());
                }
            } catch (Exception e) {
                System.err.println("Error loading image for news ID " + newsId + ": " + e.getMessage());
            }
        }
    }
}

 









    @Override
    public void mouseDragged(MouseEvent e) {
        int dx = e.getX() - startX;
        offsetX = dx;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
      //  throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
      //  throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
      //  throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override //รับตำแหน่งเริ่มต้นเมื่อกดเมาส์
    public void mousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
    }

    @Override //ตรวจสอบการปัดเมื่อปล่อยเมาส์
    public void mouseReleased(MouseEvent e) {
        int endX = e.getX();
        int endY = e.getY();
        int deltaX = endX - startX;
        int deltaY = endY - startY;
        if (Math.abs(deltaX) > Math.abs(deltaY) && Math.abs(deltaX) > 200) { // ตรวจสอบว่าปัดในแนวนอนและเกินระยะที่กำหนด
            if (deltaX > 0) {
                onSwipeRight();
            } else {
                onSwipeLeft();
            }
        }
        // กลับ offsetX เป็น 0 (reset)
        offsetX = 0;
        repaint();
    }
    //------------โค้ดเลื่อน------------
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.translate(offsetX, 0);
        super.paintComponent(g2);

        if (noMoreNews) {
            // แสดงข้อความเมื่อไม่มีข่าว
            g2.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
            String message = "ไม่มีข่าวใหม่แล้ว";
            FontMetrics fm = g2.getFontMetrics();
            int messageWidth = fm.stringWidth(message);
            int x = (getWidth() - messageWidth) / 2;
            int y = getHeight() / 2;
            g2.drawString(message, x, y);
        } else if (!filteredNews.isEmpty()) {
            int panelWidth = getWidth();
            int y = 50; // เริ่มจากด้านบนที่ตำแหน่ง y = 50
            int textY = y; // ตำแหน่งเริ่มต้นของข้อความ

            // ถ้ามีรูปภาพ แสดงรูปภาพก่อน
            if (currentImage != null) {
                int maxImgWidth = 400;
                int maxImgHeight = 400;
                
                // คำนวณอัตราส่วนของรูปภาพ
                double imgRatio = (double) currentImage.getWidth(null) / currentImage.getHeight(null);
                int imgWidth = maxImgWidth;
                int imgHeight = (int) (imgWidth / imgRatio);

                // ปรับขนาดถ้าความสูงเกิน
                if (imgHeight > maxImgHeight) {
                    imgHeight = maxImgHeight;
                    imgWidth = (int) (imgHeight * imgRatio);
                }

                // คำนวณตำแหน่งกึ่งกลาง
                int x = (panelWidth - imgWidth) / 2;

                g2.drawImage(currentImage, x, y, imgWidth, imgHeight, null);
                textY = y + imgHeight + 30; // ข้อความจะอยู่ใต้รูป
            }

            // แสดงหัวข้อข่าวเสมอ
            if (currentTitle != null && !currentTitle.isEmpty()) {
                g2.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(currentTitle);
                int textX = (panelWidth - textWidth) / 2;

                g2.drawString(currentTitle, textX, textY);
            }
        }

        g2.dispose();
    }

    private void onSwipeLeft() {
        if (!filteredNews.isEmpty() && currentIndex >= 0 && currentIndex < filteredNews.size()) {
            // บันทึกว่าอ่านข่าวนี้แล้ว
            NewsItem current = filteredNews.get(currentIndex);
            markNewsAsRead(current.getId());
            
            // อัพเดตรายการข่าวและข้ามไปข่าวถัดไป
            updateFilteredNews();
        }
    }

    private void onSwipeRight() {
        if (!filteredNews.isEmpty() && currentIndex >= 0 && currentIndex < filteredNews.size()) {
            // เปิดหน้า Full News
            NewsItem currentNews = filteredNews.get(currentIndex);
            FullNewsPopup popup = new FullNewsPopup(currentNews);
            popup.setVisible(true);
            NewsItem current = filteredNews.get(currentIndex);
            markNewsAsRead(current.getId());
            
            // อัพเดตรายการข่าวและข้ามไปข่าวถัดไป
            updateFilteredNews();
        }
    }
// เลือกหมวดหมู่และอัปเดตรายการข่าว
    private void selectCategory(String category) {
        this.selectedCategory = category;
        categoryLabel.setText("Category: " + category);
        updateFilteredNews();
    }
// โหลดประวัติการอ่านข่าวจากไฟล์
    private void loadReadHistory() {
        readNewsIds.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("./File/accout/news/readHistory.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(currentUserId)) {
                    try {
                        int newsId = Integer.parseInt(parts[1]);
                        readNewsIds.add(newsId);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid news ID format in read history: " + parts[1]);
                        continue;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading read history: " + e.getMessage());
        }
    }
// บันทึกข่าวที่อ่านแล้วลงไฟล์
    private void markNewsAsRead(int newsId) {
        if (!readNewsIds.contains(newsId)) {
            readNewsIds.add(newsId);
            try (FileWriter writer = new FileWriter("./File/accout/news/readHistory.csv", true)) {
                writer.write(String.format("%s,%d\n", currentUserId, newsId));
            } catch (IOException e) {
                System.err.println("Error saving read history: " + e.getMessage());
            }
        }
    }


    }


    
    

