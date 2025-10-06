package gui.set;

import javax.swing.*;


public abstract class popup extends JFrame{
    public popup(){
        // กำหนดค่าพื้นฐานของ JFrame
        super("");
        this.setSize(600,700); //ยาวxกว้าง
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}


