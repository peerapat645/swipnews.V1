package gui;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;

import gui.set.setRoundedPanel;

public class HistoryPanal extends setRoundedPanel implements MouseListener{

    private JPanel contentPanel;
    ArrayList<setRoundedPanel> histories = new ArrayList<>();

    public HistoryPanal() {
        super(20);
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

        // เพิ่ม History หลายอันด้วยลูปและเว้นระยะเท่ากัน
        

        int count = 10; // จำนวน History ที่ต้องการแสดง x2
        int gapY = 40;  // ระยะห่างแนวตั้ง (pixel)
        int startY = 20; // จุดเริ่มต้นแนว Y
        int gapX = 40; // ระยะห่างแนวนอน (pixel)
        int startX = 25; // จุดเริ่มต้นแนว X
        float s = 0f; 
        for (int i = 0; i < count; i++) {  
            for (int j = 0; j < 2; j++) {
                setRoundedPanel history = new setRoundedPanel(20);
                // สีไล่เฉดตัวอย่าง สามารถเปลี่ยนเป็นข้อมูลจริงได้
                history.setBackground(Color.getHSBColor((float)i/count+s, 0.7f, 0.9f));
                history.setBounds(startX + j * (200 + gapX), startY + i * (200 + gapY), 225, 225);
                histories.add(history); // เก็บอ้างอิงไว้
                contentPanel.add(history);
                history.addMouseListener(this); // เพิ่ม MouseListener ให้กับแต่ละ History
                s+=0.04f; // เพิ่มค่าสำหรับไล่เฉดสี
            }
            
        }
    
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
        Object h = e.getSource();
        int i = 0;
        for (i = 0; i < histories.size(); i++) {
            int number = i+1; // นับจาก 1 แทนที่จะเริ่มจาก 0
            if (h == histories.get(i)) { // ตัวอย่างตรวจสอบคลิก History แรก) {
                System.out.println(" history : " + number);

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
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }
    
}
