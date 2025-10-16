package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;
import gui.set.*;

public class WritePopup extends JFrame {
    private JTextField topicArea;

    private JTextArea contentArea;
    private JComboBox<String> categoryComboBox;
    private JLabel photoPreview;
    private String selectedImagePath = null;
    private final int PREVIEW_WIDTH = 150;
    private final int PREVIEW_HEIGHT = 150;
    private String userId;
    private static int nextNewsId = 1;
    private setRoundedbotton confirmBtn;

    public WritePopup(String username) {
        super("Write | SwipNews");

        // อ่านค่า nextNewsId จากไฟล์
        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.FileReader("./File/accout/news/news.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length > 0) {
                        try {
                            int id = Integer.parseInt(parts[0]);
                            nextNewsId = Math.max(nextNewsId, id + 1);
                        } catch (NumberFormatException e) {
                            // ข้ามบรรทัดที่ผิด
                        }
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            nextNewsId = 1;
        }

        this.userId = username;

        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // ===== พื้นหลังรูปภาพ
        ImageIcon bgIcon = new ImageIcon("./icon/bg3.jpg");
        Image bgImage = bgIcon.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        JLabel backgroundPanel = new JLabel(new ImageIcon(bgImage));
        backgroundPanel.setLayout(new BorderLayout(0, 0));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.setPreferredSize(new Dimension(600, 400));
       

        this.setContentPane(backgroundPanel);

        // ---------------- Header ----------------
        
        ImageIcon logoIcon = new ImageIcon("./icon/logoW.png");

        // ปรับขนาดโลโก้ให้เหมาะสม (เช่น 50x50 px)
        Image logoImage = logoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedLogo = new ImageIcon(logoImage);

        // สร้าง JLabel ที่มีทั้งไอคอนและข้อความ
        JLabel title = new JLabel("WRITE", resizedLogo, JLabel.LEFT);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Tahoma", Font.BOLD, 32));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ปรับระยะห่างระหว่างรูปกับตัวอักษร
        title.setIconTextGap(15); // ช่องว่างระหว่างโลโก้กับคำว่า WRITE

        // จัดแนวให้ภาพอยู่ซ้ายและข้อความอยู่ตรงกลางแนวดิ่ง
        title.setHorizontalTextPosition(JLabel.RIGHT);
        title.setVerticalTextPosition(JLabel.CENTER);

        mainPanel.add(title, BorderLayout.NORTH);


        // ---------------- Center ----------------
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        
        // ✅ สร้างกรอบสีขาวขอบโค้ง (เหมือนกล่องล็อกอิน)
        RoundedPanel whiteBox = new RoundedPanel(30);
        whiteBox.setBackgroundColor(new Color(255, 255, 255, 230)); // สีขาวโปร่งนิด ๆ
        whiteBox.setPreferredSize(new Dimension(730, 670));
        whiteBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // ขอบใน (padding)
        whiteBox.add(centerPanel, BorderLayout.CENTER);

        // ✅ ใช้ GridBagLayout เพื่อจัด whiteBox ให้อยู่กลางจอ
        JPanel centerContainer = new JPanel(new GridBagLayout());
        centerContainer.setOpaque(false);
        centerContainer.add(whiteBox);

        mainPanel.add(centerContainer, BorderLayout.CENTER);


        // ---------------- Topic ----------------
        JPanel topicPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 20));
        topicPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Topic :");
        titleLabel.setForeground(Color.darkGray);
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        topicPanel.add(titleLabel);

        RoundedTextField topicField = new RoundedTextField(40);
        topicField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        topicField.setBackground(Color.WHITE);
        topicField.setPreferredSize(new Dimension(500, 40));
        topicField.setArc(20);
        topicPanel.add(topicField);
        topicArea = topicField; // ให้ topicArea ใช้งานข้อมูลจากช่องนี้

        // ---------------- Category ----------------
        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 20));
        categoryPanel.setOpaque(false);
        JLabel categoryLabel = new JLabel("Category :");
        categoryLabel.setForeground(Color.darkGray);
        categoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        categoryComboBox = new JComboBox<>(gui.set.Category.categories);
        categoryComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
        categoryPanel.add(categoryLabel);
        categoryPanel.add(categoryComboBox);

        // ---------------- Photo ----------------
        setRoundedbotton addPhotoBtn = new setRoundedbotton("<html><center>＋<br/>Add photo</center></html>", 20,
                new Font("Tahoma", Font.BOLD, 16));
        addPhotoBtn.setPreferredSize(new Dimension(100, 100));
        addPhotoBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        addPhotoBtn.setBackground(Color.WHITE);
        addPhotoBtn.setForeground(Color.DARK_GRAY);

        photoPreview = new JLabel();
        photoPreview.setPreferredSize(new Dimension(PREVIEW_WIDTH, PREVIEW_HEIGHT));
        photoPreview.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2)); // ปรับ border
        photoPreview.setHorizontalAlignment(JLabel.CENTER);
        photoPreview.setVerticalAlignment(JLabel.CENTER);

        JPanel photoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        photoPanel.setOpaque(false);
        photoPanel.setPreferredSize(new Dimension(400, 160)); // ปรับ layout
        photoPanel.add(addPhotoBtn);
        photoPanel.add(photoPreview);

        // ✅ Event: Add Photo
        addPhotoBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                public boolean accept(java.io.File f) {
                    String name = f.getName().toLowerCase();
                    return f.isDirectory() || name.endsWith(".jpg") ||
                           name.endsWith(".jpeg") || name.endsWith(".png");
                }
                public String getDescription() {
                    return "Image files (*.jpg, *.jpeg, *.png)";
                }
            });

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedImagePath = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    ImageIcon imageIcon = new ImageIcon(selectedImagePath);
                    Image image = imageIcon.getImage();
                    Image resizedImage = image.getScaledInstance(PREVIEW_WIDTH, PREVIEW_HEIGHT, Image.SCALE_SMOOTH);
                    photoPreview.setIcon(new ImageIcon(resizedImage));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error loading image: " + ex.getMessage());
                }
            }
        });

        // ---------------- Content ----------------
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 35));
        contentPanel.setOpaque(false);
        JLabel contentLabel = new JLabel("Content :");
        contentLabel.setForeground(Color.darkGray);
        contentLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPanel.add(contentLabel);

        RoundedTextAreaWithScroll roundedBox = new RoundedTextAreaWithScroll(6, 40);
        
        contentArea = roundedBox.getTextArea(); // ให้ contentArea อ้างถึง TextArea ด้านใน
        contentPanel.add(roundedBox);

        // ---------------- Confirm Button ----------------
        confirmBtn = new setRoundedbotton("Confirm", 20, new Font("Tahoma", Font.BOLD, 18));
        confirmBtn.setBackground(new Color(0, 168, 104));
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.setFocusPainted(false);
        confirmBtn.setPreferredSize(new Dimension(120, 50));

        JPanel confirmPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        confirmPanel.setOpaque(false);
        confirmPanel.add(confirmBtn);

        confirmBtn.addActionListener(e -> saveNews()); // ใช้ ActionListener แทน MouseListener

        // รวมทั้งหมดใน Center Panel
        centerPanel.add(topicPanel);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(categoryPanel);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(photoPanel);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(contentPanel);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(confirmPanel);

        
        add(mainPanel);
        setVisible(true);
    }

    // ---------------- Save Function ----------------
    private void saveNews() {
        String topic = topicArea.getText().trim();
        String content = contentArea.getText().trim();
        String category = (String) categoryComboBox.getSelectedItem();

        if (topic.isEmpty() || content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both topic and content", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            java.io.FileWriter fw = new java.io.FileWriter("./File/accout/news/news.csv", true);
            java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);

            topic = topic.replace(",", ";").replace("\n", " ").replace("\r", "");
            content = content.replace(",", ";").replace("\n", " ").replace("\r", "");

            String timestamp = java.time.LocalDateTime.now().toString();
            String newsLine = String.format("%d,%s,%s,%s,%s,%s\n",
                    nextNewsId, userId, timestamp, category, topic, content);
            bw.write(newsLine);
            bw.close();

            // ตรวจสอบโฟลเดอร์ก่อนบันทึกรูป
            java.io.File photoDir = new java.io.File("./File/accout/news/photo/");
            if (!photoDir.exists()) photoDir.mkdirs();

            // บันทึกรูป (ถ้ามี)
            if (selectedImagePath != null) {
                String fileExtension = selectedImagePath.substring(selectedImagePath.lastIndexOf("."));
                String destPath = "./File/accout/news/photo/" + nextNewsId + fileExtension;
                java.nio.file.Files.copy(
                    java.nio.file.Paths.get(selectedImagePath),
                    java.nio.file.Paths.get(destPath),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
                );
            }

            nextNewsId++;
            JOptionPane.showMessageDialog(this, "News saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // ล้างข้อมูลหลังบันทึก
            topicArea.setText("");
            contentArea.setText("");
            photoPreview.setIcon(null);
            selectedImagePath = null;

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving news: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
