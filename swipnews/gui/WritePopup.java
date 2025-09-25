package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;


import java.awt.event.*;

import javax.swing.*;

import gui.set.setRoundedbotton;


public class WritePopup extends JFrame implements MouseListener, MouseMotionListener{

    public WritePopup() {
          super("Write | SwipNews"); // ตั้งชื่อหน้าต่าง
        this.setSize(700, 600);

        // ปรับตำแหน่ง popup ให้อยู่ด้านขวาของหน้าจอ
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width - this.getWidth();
        int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);
        this.setResizable(false);

        // ตั้งค่าหลัก
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(200, 255, 220)); // สีพื้นหลังอ่อน ๆ

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

        JLabel titleLabel = new JLabel("Topic :");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));  
        topicPanel.add(titleLabel);

         // TextArea สำหรับหัวข้อ
        JTextArea topicArea = new JTextArea(1, 5);
        topicArea.setLineWrap(true);          // ตัดบรรทัดอัตโนมัติ
        topicArea.setWrapStyleWord(true);     // ตัดตามคำ
        topicArea.setFont(new Font("Tahoma", Font.PLAIN, 18));

        // ScrollPane ครอบ TextArea
        JScrollPane topicscroll = new JScrollPane(topicArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // ไม่ให้มีแถบเลื่อนแนวนอน
        topicscroll.setPreferredSize(new Dimension(350, 25));

        topicPanel.add(topicscroll);

        // Add photo (ปุ่มใหญ่แบบสี่เหลี่ยม)
        setRoundedbotton addPhotoBtn = new setRoundedbotton("<html><center>＋<br/>Add photo</center></html>", 20, // มุมโค้ง 30px
        new Font("Tahoma", Font.BOLD, 16));
        addPhotoBtn.setPreferredSize(new Dimension(100, 100));
        addPhotoBtn.setAlignmentX(Component.LEFT_ALIGNMENT); // ดันไปซ้าย
        addPhotoBtn.setBackground(new Color(0, 168, 104)); // สีพื้นหลังเขียวเข้ม
        addPhotoBtn.setForeground(Color.white);          // สีข้อความ/เส้นขอบ
        JPanel photoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,120,5));
        photoPanel.setOpaque(false);
        photoPanel.add(addPhotoBtn);

        // เนื้อหา
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        contentPanel.setOpaque(false);

        // Label "Content :"
        JLabel contentLabel = new JLabel("Content :");
        contentLabel.setFont(new Font("Tahoma", Font.PLAIN, 20)); // ขยายขนาดฟอนต์
        contentPanel.add(contentLabel);

        // TextArea สำหรับเนื้อหา
        JTextArea contentArea = new JTextArea(10, 45);
        contentArea.setLineWrap(true);          // ตัดบรรทัดอัตโนมัติ
        contentArea.setWrapStyleWord(true);     // ตัดตามคำ
        contentArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
        

        // ScrollPane ครอบ TextArea
        JScrollPane scroll = new JScrollPane(contentArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // ไม่ให้มีแถบเลื่อนแนวนอน
        scroll.setPreferredSize(new Dimension(500, 100));

        contentPanel.add(scroll);


        // Confirm button
        setRoundedbotton confirmBtn = new setRoundedbotton("Confirm",20, new Font("Tahoma", Font.BOLD, 16));
        confirmBtn.setBackground(new Color(0, 168, 104));
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.setFocusPainted(false);
        confirmBtn.setPreferredSize(new Dimension(100, 40));

        JPanel confirmPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,20,20));
        confirmPanel.setOpaque(false);
        confirmPanel.add(confirmBtn);

        // ใส่ทั้งหมดใน center
        centerPanel.add(topicPanel);
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
    }

    
        

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }
    
}
