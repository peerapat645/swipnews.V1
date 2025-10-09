package gui;

import javax.swing.*;

import gui.set.popup;
import gui.set.setRoundedPanel;

import java.awt.*;

public class FullNewsPopup extends popup {
    private NewsItem news;
    
    public FullNewsPopup(NewsItem news) {
        this.news = news;
        setSize(1000, 1000);
        setTitle(news.getTopic());
        setLocationRelativeTo(null);
        
        setRoundedPanel mainPanel = new setRoundedPanel(20);
        mainPanel.setLayout(null);
        mainPanel.setBounds(40, 20, 900, 900);
        
        // หัวข้อข่าว
        JLabel titleLabel = new JLabel(news.getTopic());
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setBounds(420, 10, 760, 30);
        mainPanel.add(titleLabel);

        // รูปภาพ (ถ้ามี)
        if (news.hasImage()) {
    try {
        ImageIcon imageIcon = new ImageIcon(news.getImagePath());
        Image image = imageIcon.getImage();

        // ขนาดสูงสุดที่อยากให้ภาพแสดง
        int maxWidth = 700;
        int maxHeight = 400;

        // คำนวณอัตราส่วน
        int imgWidth = image.getWidth(null);
        int imgHeight = image.getHeight(null);
        double widthRatio = (double) maxWidth / imgWidth;
        double heightRatio = (double) maxHeight / imgHeight;
        double scale = Math.min(widthRatio, heightRatio); // เลือกตัวที่ทำให้พอดีทั้งสองด้าน

        int newWidth = (int) (imgWidth * scale);
        int newHeight = (int) (imgHeight * scale);

        Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(resizedImage));
        imageLabel.setBounds(100, 50, 700, 400);

        // ใช้ Layout manager แทน setBounds จะง่ายกว่า
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(imageLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

    } catch (Exception e) {
        e.printStackTrace();
    }
}
        
        // เนื้อหาข่าว
        JTextArea contentArea = new JTextArea(news.getContent());
        contentArea.setWrapStyleWord(true);
        contentArea.setLineWrap(true);
        contentArea.setEditable(false);
        contentArea.setFont(new Font("Tahoma", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(contentArea);
        scrollPane.setBounds(30, 490, 850, 350);
        mainPanel.add(scrollPane);

        // ข้อมูลเพิ่มเติม
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.add(new JLabel("Category: " + news.getCategory()));
        infoPanel.add(new JLabel(" | "));
        infoPanel.add(new JLabel("By: " + news.getUserId()));
        infoPanel.add(new JLabel(" | "));
        infoPanel.add(new JLabel("Date: " + news.getTimestamp().toLocalDate()));
        mainPanel.add(infoPanel, BorderLayout.PAGE_END);
        
        add(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}