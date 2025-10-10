package gui.set;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedPasswordField extends JPasswordField {
    private int arcWidth = 20;  // ความโค้งแนวนอน
    private int arcHeight = 20; // ความโค้งแนวตั้ง

    public RoundedPasswordField(int columns) {
        super(columns);
        setOpaque(false); // เพื่อให้พื้นหลังโปร่งใส (เราจะวาดเอง)
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // เว้นระยะขอบใน
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // วาดพื้นหลังโค้งมน
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth()-1, getHeight()-1, arcWidth, arcHeight));

        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // วาดเส้นขอบมน
        if (hasFocus()) {
            g2.setColor(new Color(66, 133, 244)); // ขอบน้ำเงินเมื่อโฟกัส
        } else {
            g2.setColor(Color.GRAY);
        }
        g2.draw(new RoundRectangle2D.Double(0, 0, getWidth()-1, getHeight()-1, arcWidth, arcHeight));
        g2.dispose();
    }

    // เมธอดเพิ่มเพื่อให้ตั้งค่าความโค้งได้
    public void setArc(int arc) {
        this.arcWidth = arc;
        this.arcHeight = arc;
        repaint();
    }

    // ✅ เมธอดที่จำเป็นสำหรับปุ่ม "Show Password"
    public void showPassword(boolean show) {
        setEchoChar(show ? (char) 0 : '\u2022'); // แสดง/ซ่อนรหัสผ่าน
    }
}

