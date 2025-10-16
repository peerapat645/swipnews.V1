package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import gui.set.popup;
import gui.set.setRoundedPanel;

public class App extends popup  implements MouseListener {
    private String username;
    private setRoundedPanel panel, menu_panel ;
    private JLabel imageFeednews, imgewrite, imagehistory; 
    private JLabel text_history, text_Feednews, text_write;
    private JMenu menu_category; // เมนูที่จะแสดงเมื่อกดปุ่มเมนู
    

    public App(String username) {
        super();
        this.username = username;
        setTitle("App-Feednews | SwipNews"); // ตั้งชื่อหน้าต่าง
        getContentPane().setBackground(Color.LIGHT_GRAY); // เปลี่ยนสีพื้นหลังของ login panel หลัก
        setLayout(new GridBagLayout()); // ให้อยู่กลางหน้าต่าง
        panel = new setRoundedPanel(20); // 20 คือความโค้งของมุม
        panel.setLayout(null); // ใช้ null layout เพื่อกำหนดตำแหน่งเอง
        panel.setBackground(new Color(255, 255, 255, 230)); // เปลี่ยนสีพื้นหลังของ panel
        panel.setPreferredSize(new Dimension(550, 600)); // กำหนดขนาด panel

        menu_panel = new setRoundedPanel(20); // 20 คือความโค้งของมุม
        menu_panel.setLayout(null); // ใช้ null layout เพื่อกำหนดตำแหน
        menu_panel.setBackground(Color.white); // เปลี่ยนสีพื้นหลังของ panel
        menu_panel.setBounds(145, 535, 240, 60); // x, y, width, height

        //เพิ่มข้อความ
        text_history = new JLabel("History");
        text_Feednews = new JLabel("Feednews");
        text_write = new JLabel("Write");
        text_history.setFont(new Font("Leelawadee UI", Font.BOLD, 12));
        text_Feednews.setFont(new Font("Leelawadee UI", Font.BOLD, 12));
        text_write.setFont(new Font("Leelawadee UI", Font.BOLD, 12));

        //เพิ่มรูป
        ImageIcon feedIc = new ImageIcon("./icon/web-content.png");//เพิ่มรูปภาพ newsfeed
        Image scaledfeed = feedIc.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        imageFeednews = new JLabel(new ImageIcon(scaledfeed));
        imageFeednews.setOpaque(false);

        ImageIcon writeIC = new ImageIcon("./icon/write2.png");
        Image scaledwrite = writeIC.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        imgewrite = new JLabel(new ImageIcon(scaledwrite));//เพิ่มรูปภาพ write
        imgewrite.setOpaque(false);

        ImageIcon hisIC = new ImageIcon("./icon/history2.png");
        Image scaledhis = hisIC.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        imagehistory = new JLabel(new ImageIcon(scaledhis));
        imagehistory.setOpaque(false);
        
            

        // ตั้งค่าให้เมาส์เป็นรูปมือเมื่อชี้ที่ปุ่มเมนู
        imageFeednews.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        imgewrite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        imagehistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        

        //----กำหนดตำแหน่ง----
        imagehistory.setBounds(25, 5, 40, 40); // x, y, width, height
        imageFeednews.setBounds(100, 5, 40, 40); // x, y, width, height
        imgewrite.setBounds(175, 5, 40, 40); // x, y, width, height
        //ตำแหน่งข้อความ
        text_history.setBounds(23, 35, 60, 20); // x, y, width, height
        text_Feednews.setBounds( 93, 35, 60, 20); // x, y, width, height
        text_write.setBounds(180, 35, 60, 20); // x, y, width, height
            
        
        //เพิ่มpanel
        add(panel); // เพิ่ม panel หลักเข้าไปในหน้าต่าง
        panel.add(menu_panel); // เพิ่ม panel เมนูเข้าไปใน panel หลัก    

        panel.add(new NewsFeedPanal(username)); // เพิ่ม NewsFeed เข้าไปใน mainPanel

        //เพิ่มปุ่ม ตรงเมนู
        menu_panel.add(imagehistory); // เพิ่มรูปภาพเข้าไปใน panel เมนู
        menu_panel.add(imageFeednews); // เพิ่มรูปภาพเข้าไปใน panel เมนู
        menu_panel.add(imgewrite); // เพิ่มรูปภาพเข้าไปใน panel เมนู
        menu_panel.add(text_history); // เพิ่มข้อความเข้าไปใน panel เมนู
        menu_panel.add(text_Feednews); // เพิ่มข้อความเข้าไปใน panel เมนู
        menu_panel.add(text_write); // เพิ่มข้อความเข้าไปใน panel เมนู

        // เพิ่ม MouseListener ให้กับปุ่มเมนู
        imageFeednews.addMouseListener(this);
        imgewrite.addMouseListener(this);
        imagehistory.addMouseListener(this);
       

    }





    @Override
    public void mouseClicked(MouseEvent e) {
        Object f = e.getSource(); 
        Object h = e.getSource();
        Object w = e.getSource();
        Object m = e.getSource();
         if (m ==  menu_category ) {

        

        }
        if (w == imgewrite ) {
             new WritePopup(username).setVisible(true);
    
        }
        if (h == imagehistory ) {
            setTitle("App-history | SwipNews"); // ตั้งชื่อหน้าต่าง
            panel.removeAll(); // ลบเนื้อหาทั้งหมดใน panel
            panel.add(new HistoryPanal(username)); // เพิ่ม History เข้าไปใน mainPanel โดยส่ง username
            panel.add(menu_panel); // เพิ่ม panel เมนูเข้าไปใน panel หลัก    
            panel.revalidate(); // รีเฟรช panel
            panel.repaint(); // วาด panel ใหม่
        }
        if (f == imageFeednews ) {
            setTitle("App-Feednews | SwipNews"); // ตั้งชื่อหน้าต่าง
            panel.removeAll(); // ลบเนื้อหาทั้งหมดใน panel
            panel.add(new NewsFeedPanal(username)); // เพิ่ม NewsFeed เข้าไปใน mainPanel
            panel.add(menu_panel); // เพิ่ม panel เมนูเข้าไปใน panel หลัก    
            panel.revalidate(); // รีเฟรช panel
            panel.repaint(); // วาด panel ใหม่
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    
}
