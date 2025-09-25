
package gui;

import java.awt.event.*;
import javax.swing.*;
import gui.set.setRoundedPanel;


import java.awt.Graphics;
import java.awt.Graphics2D;

public class NewsFeedPanal extends setRoundedPanel implements MouseListener, MouseMotionListener {

    private int startX = 0;
    private int startY = 0;
    private int offsetX = 0; // ระยะเลื่อนแนวนอน
    private JMenuItem menuItemCategory;

public NewsFeedPanal() {
    super(20);
    this.setLayout(null);
    this.setBackground(java.awt.Color.gray);
    this.setBounds(20, 20, 510, 510);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);

    // สร้างเมนู
    JMenu menu = new JMenu("Options");
    menuItemCategory = new JMenuItem("Menu Item 1");
    menu.add(menuItemCategory);
    // เพิ่ม ActionListener ให้กับเมนูไอเท็ม
    menuItemCategory.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Menu Item 1 selected");
            // เพิ่มโค้ดที่ต้องการให้ทำงานเมื่อเลือกเมนูไอเท็มนี้
        }
    });

    // สร้าง JMenuBar และเพิ่มเมนูลงไป
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(menu);
    menuBar.setBounds(400, 10, 52, 30); // กำหนดตำแหน่งและขนาดของเมนูบาร์
    this.add(menuBar);




    
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
    @Override  //ขยับ panel ตามการเลื่อนเมาส์
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        // เลื่อน panel ตาม offsetX
        g2.translate(offsetX, 0);
        super.paintComponent(g2);
        g2.dispose(); 
    }

    private void onSwipeLeft() {
        System.out.println("Swiped Left");
        // TODO: เพิ่มโค้ดเมื่อปัดซ้าย
    }

    private void onSwipeRight() {
        System.out.println("Swiped Right");
        // TODO: เพิ่มโค้ดเมื่อปัดขวา
    }



    }


    
    

