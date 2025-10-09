package gui;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import gui.set.setRoundedbotton;
import java.util.Collections;
import java.util.Comparator;
import gui.set.setRoundedPanel;

public class HistoryPanal extends setRoundedPanel implements MouseListener {

    private JPanel contentPanel;
    private ArrayList<NewsItem> userNews = new ArrayList<>();
    private ArrayList<setRoundedPanel> histories = new ArrayList<>();
    private String currentUserId;
    private setRoundedbotton switchButton;
    private boolean isShowingWrittenNews = true; // true = แสดงข่าวที่เขียน, false = แสดงข่าวที่อ่าน

    public HistoryPanal(String userId) {
        super(20);
        this.currentUserId = userId;
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setBounds(0, 0, 550, 530);// กำหนดขนาด panel 
        this.addMouseListener(this);

        // สร้าง panel สำหรับใส่เนื้อหา (เช่น ประวัติข่าว)
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.white);
        contentPanel.setPreferredSize(new Dimension(480, 2500)); // กำหนดความสูงมากกว่าขนาดแสดงผลเพื่อให้ scroll ได้


        // เพิ่ม JScrollPane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBounds(10, 10, 530, 520);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);

        // โหลดข่าวที่ผู้ใช้เขียน
        loadUserNews();

        // จัดการแสดงผลข่าว
        displayNews();

        // ปุ่มสลับระหว่างข่าวที่เขียนและข่าวที่อ่าน
        //switchHistoryType();

        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Object h = e.getSource();
        int i = 0;
        for (i = 0; i < histories.size(); i++) {
            if (h == histories.get(i) && i < userNews.size()) {
                // เปิดหน้า Full News เมื่อคลิกที่ข่าว
                new FullNewsPopup(userNews.get(i));
                break;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // Not used
    }

    private void loadUserNews() {
        userNews.clear();
        if (isShowingWrittenNews) {
            // โหลดข่าวที่เขียน
            try (BufferedReader reader = new BufferedReader(new FileReader("./File/accout/news/news.csv"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6 && parts[1].equals(currentUserId)) {
                        int id = Integer.parseInt(parts[0]);
                        String userId = parts[1];
                        LocalDateTime timestamp = LocalDateTime.parse(parts[2]);
                        String category = parts[3];
                        String topic = parts[4];
                        String content = parts[5];
                        
                        NewsItem newsItem = new NewsItem(id, userId, timestamp, category, topic, content);
                        userNews.add(newsItem);
                    }
                }
                Collections.sort(userNews, Comparator.comparing(NewsItem::getTimestamp).reversed());
            } catch (IOException e) {
                System.err.println("Error loading news: " + e.getMessage());
            }
        } else {
            // โหลดข่าวที่อ่าน
            try (BufferedReader reader = new BufferedReader(new FileReader("./File/accout/news/readHistory.csv"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2 && parts[0].equals(currentUserId)) {
                        int newsId = Integer.parseInt(parts[1]);
                        // โหลดรายละเอียดข่าวจาก news.csv
                        try (BufferedReader newsReader = new BufferedReader(new FileReader("./File/accout/news/news.csv"))) {
                            String newsLine;
                            while ((newsLine = newsReader.readLine()) != null) {
                                String[] newsParts = newsLine.split(",");
                                if (newsParts.length >= 6 && Integer.parseInt(newsParts[0]) == newsId) {
                                    LocalDateTime timestamp = LocalDateTime.parse(newsParts[2]);
                                    NewsItem newsItem = new NewsItem(
                                        newsId, newsParts[1], timestamp,
                                        newsParts[3], newsParts[4], newsParts[5]
                                    );
                                    userNews.add(newsItem);
                                    break;
                                }
                            }
                        }
                    }
                }
                Collections.sort(userNews, Comparator.comparing(NewsItem::getTimestamp).reversed());
            } catch (IOException e) {
                System.err.println("Error loading read history: " + e.getMessage());
            }
        }
    }

    

    // เปลี่ยนประเภทประวัติข่าวเป็นข่าวที่เขียนหรือข่าวที่อ่าน ##ทำไม่เสร็จจ
    private void switchHistoryType() {
        isShowingWrittenNews = !isShowingWrittenNews;
        switchButton.setText(isShowingWrittenNews ? "Switch to Read History" : "Switch to Written History");
        loadUserNews();
        contentPanel.removeAll();
        displayNews();
        contentPanel.revalidate();
        contentPanel.repaint();
        //เพิ่มปุ่มสลับ
        switchButton = new setRoundedbotton(isShowingWrittenNews ? "Switch to Read History" : "Switch to Written History", 15, new Font("Tahoma", Font.PLAIN, 12));
        switchButton.setBackground(new Color(100, 149, 237));
    }

    private void confirmDeleteNews(int newsId) {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this news?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            deleteNews(newsId);
        }
    }
    // ลบข่าวและไฟล์ที่เกี่ยวข้อง
    private void deleteNews(int newsId) {
        try {
            // อ่านไฟล์ข่าวทั้งหมด
            ArrayList<String> remainingNews = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("./File/accout/news/news.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && !parts[0].equals(String.valueOf(newsId))) {
                    remainingNews.add(line);
                }
            }
            reader.close();

            // เขียนข่าวที่เหลือกลับลงไฟล์
            FileWriter writer = new FileWriter("./File/accout/news/news.csv");
            for (String news : remainingNews) {
                writer.write(news + "\n");
            }
            writer.close();

             // ลบประวัติการอ่านข่าวที่ถูกลบ
            FileWriter Del = new FileWriter("./File/accout/news/readHistory.csv");
            for (String history : remainingNews) {
                Del.write(history + "\n");
            }
            Del.close();

            // ลบไฟล์รูปภาพที่เกี่ยวข้อง
            File photoDir = new File("./File/accout/news/photo");
            if (photoDir.exists()) {
                File[] files = photoDir.listFiles((dir, name) -> name.startsWith(newsId + "."));
                if (files != null) {
                    for (File file : files) {
                        file.delete();
                    }
                }
            }

            // รีโหลดและแสดงข่าวใหม่
            loadUserNews();
            contentPanel.removeAll();
            displayNews();
            contentPanel.revalidate();
            contentPanel.repaint();

            JOptionPane.showMessageDialog(this, "News deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error deleting news: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // แสดงข่าว
    private void displayNews() {
        int gapY = 40;  // ระยะห่างแนวตั้ง
        int startY = 20; // จุดเริ่มต้นแนว Y
        int gapX = 40; // ระยะห่างแนวนอน
        int startX = 25; // จุดเริ่มต้นแนว X
        int maxColumns = 2; // จำนวนคอลัมน์สูงสุด

        for (int i = 0; i < userNews.size(); i++) {
            NewsItem news = userNews.get(i);
            int row = i / maxColumns;
            int col = i % maxColumns;

            setRoundedPanel history = new setRoundedPanel(20);
            history.setLayout(null);
            history.setBackground(new Color(240, 240, 240));
            history.setBounds(startX + col * (225 + gapX), startY + row * (225 + gapY), 225, 225);

            // โหลดและแสดงรูปภาพ
            try {
                File photoDir = new File("./File/accout/news/photo");
                if (photoDir.exists()) {
                    File[] files = photoDir.listFiles((dir, name) -> {
                        String lowercaseName = name.toLowerCase();
                        return name.startsWith(news.getId() + ".") && (
                            lowercaseName.endsWith(".jpg") || 
                            lowercaseName.endsWith(".jpeg") || 
                            lowercaseName.endsWith(".png")
                        );
                    });

                    if (files != null && files.length > 0) {
                        ImageIcon imageIcon = new ImageIcon(files[0].getAbsolutePath());
                        Image image = imageIcon.getImage();
                        // ปรับขนาดรูปให้พอดีกับพื้นที่
                        Image resizedImage = image.getScaledInstance(205, 120, Image.SCALE_SMOOTH);
                        JLabel imageLabel = new JLabel(new ImageIcon(resizedImage));
                        imageLabel.setBounds(10, 10, 205, 120);
                        history.add(imageLabel);
                    }
                }
            } catch (Exception e) {
                System.err.println("Error loading image for news ID " + news.getId() + ": " + e.getMessage());
            }

            // เพิ่มหัวข้อข่าว
            JLabel titleLabel = new JLabel("<html><div style='width:200px;'>" + news.getTopic() + "</div></html>");
            titleLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
            titleLabel.setBounds(10, 135, 205, 40);
            history.add(titleLabel);

            // เพิ่มหมวดหมู่
            JLabel categoryLabel = new JLabel(news.getCategory());
            categoryLabel.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
            categoryLabel.setForeground(new Color(100, 100, 100));
            categoryLabel.setBounds(10, 175, 205, 20);
            history.add(categoryLabel);

            // เพิ่มเวลา
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            JLabel timeLabel = new JLabel(news.getTimestamp().format(formatter));
            timeLabel.setFont(new Font("Leelawadee UI", Font.PLAIN, 12));
            timeLabel.setForeground(new Color(150, 150, 150));
            timeLabel.setBounds(10, 195, 205, 20);
            history.add(timeLabel);

            // เพิ่มปุ่มลบ
            setRoundedbotton deleteBtn = new setRoundedbotton("Delete", 15, new Font("Tahoma", Font.PLAIN, 12));
            deleteBtn.setBackground(new Color(255, 100, 100));
            deleteBtn.setForeground(Color.WHITE);
            deleteBtn.setBounds(135, 190, 80, 25);
            deleteBtn.addActionListener(e -> confirmDeleteNews(news.getId()));
            history.add(deleteBtn);
            histories.add(history);
            contentPanel.add(history);
            history.addMouseListener(this);
        }

        // ปรับขนาด contentPanel ตามจำนวนข่าว
        int rows = (userNews.size() + 1) / maxColumns;
        contentPanel.setPreferredSize(new Dimension(480, startY + rows * (225 + gapY)));
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
