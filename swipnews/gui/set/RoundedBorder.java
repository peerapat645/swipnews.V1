package gui.set;

// คลาสสำหรับสร้างเส้นขอบโค้งมน (ใช้กับ JTextArea, JPanel, JScrollPane ฯลฯ)
import java.awt.*;


public class RoundedBorder implements javax.swing.border.Border {
    private int radius;
    private Color borderColor;
    private int thickness;

    RoundedBorder(int radius) {
        this(radius, new Color(180, 220, 200), 2); // ขอบเขียวอ่อน
    }

    public RoundedBorder(int radius, Color color, int thickness) {
        this.radius = radius;
        this.borderColor = color;
        this.thickness = thickness;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius/2, this.radius/2, this.radius/2, this.radius/2);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // วาดพื้นหลังขาวมล
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(x, y, width-1, height-1, radius, radius);

        // วาดขอบโค้ง
        g2.setStroke(new BasicStroke(thickness));
        g2.setColor(borderColor);
        g2.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}