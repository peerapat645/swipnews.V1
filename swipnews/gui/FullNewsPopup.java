package gui;

import javax.swing.*;
import java.awt.*;

public class FullNewsPopup extends JFrame {
    private NewsItem news;
    
    public FullNewsPopup(NewsItem news) {
        this.news = news;
        setTitle(news.getTopic());
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // หัวข้อข่าว
        JLabel titleLabel = new JLabel(news.getTopic());
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // รูปภาพ (ถ้ามี)
        if (news.hasImage()) {
            try {
                ImageIcon imageIcon = new ImageIcon(news.getImagePath());
                Image image = imageIcon.getImage();
                Image resizedImage = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel(new ImageIcon(resizedImage));
                imageLabel.setHorizontalAlignment(JLabel.CENTER);
                mainPanel.add(imageLabel, BorderLayout.CENTER);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // เนื้อหาข่าว
        JTextArea contentArea = new JTextArea(news.getContent());
        contentArea.setWrapStyleWord(true);
        contentArea.setLineWrap(true);
        contentArea.setEditable(false);
        contentArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(contentArea);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        
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