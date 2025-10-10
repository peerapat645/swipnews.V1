package gui.set;

import java.awt.*;
import javax.swing.*;

public class GradientPanel extends JPanel {
    private Color color1;
    private Color color2;

    // constructor รับ 2 สี
    public GradientPanel(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // วาด gradient แนวตั้ง
        GradientPaint gp = new GradientPaint(
            0, 0, color1,            // ด้านบน
            0, getHeight(), color2   // ด้านล่าง
        );

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
