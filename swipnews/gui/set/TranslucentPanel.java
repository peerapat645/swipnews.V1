package gui.set;

import java.awt.*;
import javax.swing.*;

public class TranslucentPanel extends JPanel {

    private int arc;          // ความโค้งของมุม
    private Color fillColor;  // สีพื้นหลัง
    private Color borderColor;// สีขอบ
    private float borderWidth;

    public TranslucentPanel() {
        this(40, new Color(255, 255, 255, 80), new Color(255, 255, 255, 120), 2f);
    }

    public TranslucentPanel(int arc, Color fillColor, Color borderColor, float borderWidth) {
        this.arc = arc;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // วาดพื้นหลังโปร่งแสง
        g2.setColor(fillColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        // วาดขอบโค้ง
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderWidth));
        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, arc, arc);
    }

    // ✅ setter เผื่ออยากเปลี่ยนค่าทีหลัง
    public void setArc(int arc) { this.arc = arc; repaint(); }
    public void setFillColor(Color fillColor) { this.fillColor = fillColor; repaint(); }
    public void setBorderColor(Color borderColor) { this.borderColor = borderColor; repaint(); }
    public void setBorderWidth(float borderWidth) { this.borderWidth = borderWidth; repaint(); }
}
