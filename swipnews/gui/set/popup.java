package gui.set;

import java.awt.Image;

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
        ImageIcon backgroundImage = new ImageIcon("./icon/bg3.jpg");
        Image scaledbg = backgroundImage.getImage().getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaledbg));
        background.setOpaque(false);
        background.setBounds(0, 0, 1000, 700);

        this.setContentPane(background);
    }
}

