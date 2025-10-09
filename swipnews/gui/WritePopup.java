package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;


import java.awt.event.*;
import java.awt.Image;

import javax.swing.*;

import gui.set.GradientPanel;
import gui.set.RoundedBorder;
import gui.set.setRoundedbotton;


public class WritePopup extends JFrame implements MouseListener, MouseMotionListener{
    private JTextArea topicArea;
    private JTextArea contentArea;
    private JComboBox<String> categoryComboBox;
    private JLabel photoPreview;
    private String selectedImagePath = null;
    private final int PREVIEW_WIDTH = 150;
    private final int PREVIEW_HEIGHT = 150;
    private String userId;
    private static int nextNewsId = 1; // เริ่มต้นที่ 1
    private setRoundedbotton confirmBtn;

    public WritePopup(String username) { 
        super("Write | SwipNews"); // ตั้งชื่อหน้าต่าง
        
        // อ่านค่า nextNewsId จาก news.csv
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
                            // ข้ามบรรทัดที่มีปัญหา
                        }
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            nextNewsId = 1; // เริ่มต้นที่ 1 ถ้ามีปัญหา
        }

        // เก็บ username ที่รับมาจากหน้า login
        this.userId = username;
         
        this.setSize(800, 800);

        // ปรับตำแหน่ง popup ให้อยู่ด้านขวาของหน้าจอ
        this.setLocationRelativeTo(null); 
        this.setResizable(false);

         // ===== พื้นหลัง Gradient =====
        GradientPanel backgroundPanel = new GradientPanel(
                new Color(0, 168, 104),
                new Color(200, 255, 220));
        //backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.setLayout(new BorderLayout(0, 0));


        // ตั้งค่าหลัก
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        mainPanel.setBackground(new Color(200, 255, 220));
        mainPanel.setPreferredSize(new Dimension(800, 500));
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 168, 104), 20, true));
        backgroundPanel.add(mainPanel, BorderLayout.CENTER);

        backgroundPanel.add(mainPanel);
        //add(backgroundPanel);
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        this.getContentPane().add(backgroundPanel, BorderLayout.CENTER);

        // ---------------- Header ----------------
        JLabel title = new JLabel("WRITE");
        title.setFont(new Font("Tahoma", Font.BOLD, 32));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(title, BorderLayout.NORTH);

        // ---------------- Center Content ----------------
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        // หัวข้อ
        JPanel topicPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,25,20));
        topicPanel.setOpaque(false);

        // เพิ่ม ComboBox สำหรับเลือกหมวดหมู่
        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,25,20));
        categoryPanel.setOpaque(false);
        JLabel categoryLabel = new JLabel("Category :");
        categoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        categoryComboBox = new JComboBox<>(gui.set.Category.categories);
        categoryComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
        categoryPanel.add(categoryLabel);
        categoryPanel.add(categoryComboBox);

        JLabel titleLabel = new JLabel("Topic :");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));  
        topicPanel.add(titleLabel);

         // TextArea สำหรับหัวข้อ
        topicArea = new JTextArea(1, 5);
        topicArea.setLineWrap(true);
        topicArea.setWrapStyleWord(true);
        topicArea.setFont(new Font("Tahoma", Font.PLAIN, 20));
        topicArea.setBackground(Color.WHITE);
        topicArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // ScrollPane ครอบ TextArea
        JScrollPane topicscroll = new JScrollPane(topicArea,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        topicscroll.setPreferredSize(new Dimension(500, 40));
        topicscroll.setBorder(new RoundedBorder(20, new Color(0, 168, 104), 3));

        topicPanel.add(topicscroll);

        // Add photo (ปุ่มใหญ่แบบสี่เหลี่ยม)
        setRoundedbotton addPhotoBtn = new setRoundedbotton("<html><center>＋<br/>Add photo</center></html>", 20, // มุมโค้ง 30px
        new Font("Tahoma", Font.BOLD, 16));
        addPhotoBtn.setPreferredSize(new Dimension(100, 100));
        addPhotoBtn.setAlignmentX(Component.LEFT_ALIGNMENT); // ดันไปซ้าย
        addPhotoBtn.setBackground(Color.WHITE); // สีพื้นหลังเขียวเข้ม
        addPhotoBtn.setForeground(Color.DARK_GRAY);// สีข้อความ/เส้นขอบ


        // สร้าง Label สำหรับแสดงตัวอย่างรูป
        photoPreview = new JLabel();
        photoPreview.setPreferredSize(new Dimension(PREVIEW_WIDTH, PREVIEW_HEIGHT));
        photoPreview.setBorder(BorderFactory.createLineBorder(null));
        photoPreview.setHorizontalAlignment(JLabel.CENTER);

        JPanel photoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        photoPanel.setOpaque(false);
        photoPanel.add(addPhotoBtn);
        photoPanel.add(photoPreview);

        // เพิ่มการทำงานของปุ่ม
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
                    // โหลดรูปและปรับขนาด
                    ImageIcon imageIcon = new ImageIcon(selectedImagePath);
                    Image image = imageIcon.getImage();
                    Image resizedImage = image.getScaledInstance(PREVIEW_WIDTH, PREVIEW_HEIGHT, Image.SCALE_SMOOTH);
                    photoPreview.setIcon(new ImageIcon(resizedImage));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error loading image: " + ex.getMessage());
                }
            }
        });

        // เนื้อหา
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 35)); 
    contentPanel.setOpaque(false); 
    JLabel contentLabel = new JLabel("Content :");
    contentLabel.setFont(new Font("Tahoma", Font.PLAIN, 20)); 
    contentLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
    contentPanel.add(contentLabel); 

        contentArea = new JTextArea(5, 20); 
    contentArea.setLineWrap(true); 
    contentArea.setWrapStyleWord(true); 
    contentArea.setFont(new Font("Tahoma", Font.PLAIN, 16)); 
    contentArea.setBackground(Color.WHITE); 
    contentArea.setBorder(BorderFactory.createEmptyBorder(100, 10, 0, 10)); 
        
    
    
        // ScrollPane ครอบ TextArea
        JScrollPane scroll = new JScrollPane(contentArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
     JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
     scroll.setPreferredSize(new Dimension(550, 125)); 
     scroll.setBorder(new RoundedBorder(20, new Color(0, 168, 104), 3)); 
    contentPanel.add(scroll);


        // Confirm button
        confirmBtn = new setRoundedbotton("Confirm", 20, new Font("Tahoma", Font.BOLD, 18));
        confirmBtn.setBackground(new Color(0, 168, 104));
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.setFocusPainted(false);
        confirmBtn.setPreferredSize(new Dimension(120, 50));// กำหนดขนาดปุ่ม

        JPanel confirmPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,20,20));
        confirmPanel.setOpaque(false);
        confirmPanel.add(confirmBtn);

        // ใส่ทั้งหมดใน center
        centerPanel.add(topicPanel);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(categoryPanel);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(photoPanel);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(contentPanel);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(confirmPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // ใส่ panel หลักลง frame
        add(mainPanel);
        setVisible(true);

        confirmBtn.addMouseListener(this);
    }

    
        

    @Override
    public void mouseDragged(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object c = e.getSource();
       if (c == confirmBtn) {
            saveNews();
        }
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Not used
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // Not used
    }

    private void saveNews() {
        String topic = topicArea.getText().trim();
        String content = contentArea.getText().trim();
        String category = (String) categoryComboBox.getSelectedItem();
        
        if (topic.isEmpty() || content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both topic and content", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // รูปแบบการบันทึก: newsId,userId,timestamp,category,topic,content
        // และบันทึกรูปภาพโดยใช้ newsId เป็นชื่อไฟล์
        try {
            java.io.FileWriter fw = new java.io.FileWriter("./File/accout/news/news.csv", true);
            java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
            
            // แทนที่ commas และการขึ้นบรรทัดใหม่ด้วย semicolons ในข้อความเพื่อป้องกันปัญหากับ CSV
            topic = topic.replace(",", ";").replace("\n", " ").replace("\r", "");
            content = content.replace(",", ";").replace("\n", " ").replace("\r", "");
            
            // สร้าง timestamp
            String timestamp = java.time.LocalDateTime.now().toString();
            
            // เขียนข้อมูลลงไฟล์
            // ใช้ nextNewsId ที่หาได้จากไฟล์
            String newsLine = String.format("%d,%s,%s,%s,%s,%s\n", 
                nextNewsId, userId, timestamp, category, topic, content);
            bw.write(newsLine);
            bw.close();
            
            // บันทึกรูปภาพ (ถ้ามี)
            if (selectedImagePath != null) {
                String fileExtension = selectedImagePath.substring(selectedImagePath.lastIndexOf("."));
                String destPath = "./File/accout/news/photo/" + nextNewsId + fileExtension;
                try {
                    java.nio.file.Files.copy(
                        java.nio.file.Paths.get(selectedImagePath),
                        java.nio.file.Paths.get(destPath),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                    );
                } catch (Exception ex) {
                    System.err.println("Error saving image: " + ex.getMessage());
                }
            }

            nextNewsId++; // เพิ่มค่าสำหรับข่าวต่อไป
            JOptionPane.showMessageDialog(this, "News saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // ปิดหน้าต่างหลังจากบันทึกสำเร็จ
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving news: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
