package gui.set;

import javax.swing.*;

public abstract class popup extends JFrame {

    public popup() {
        super("");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);

        // ใส่ภาพพื้นหลัง
        ImageIcon backgroundImage = new ImageIcon("./icon/BG2.png");
        JLabel background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 1000, 700);

        this.setContentPane(background);
    }
}

