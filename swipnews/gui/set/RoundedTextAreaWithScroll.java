package gui.set;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedTextAreaWithScroll extends JPanel {
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private int arc = 30;

    public RoundedTextAreaWithScroll(int rows, int cols) {
        setOpaque(false);
        setLayout(new BorderLayout());

        textArea = new JTextArea(rows, cols);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textArea.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        textArea.setOpaque(false);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setViewportBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // ทำให้ scrollbar ไม่กินพื้นที่เกินไป
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getHorizontalScrollBar().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);

        add(scrollPane, BorderLayout.CENTER);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    @Override
protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // 🔹 เงาจาง ๆ ด้านหลัง
    g2.setColor(new Color(0, 0, 0, 25));
    g2.fill(new RoundRectangle2D.Double(2, 2, getWidth() - 4, getHeight() - 4, arc, arc));

    // 🔹 พื้นหลังกล่อง (สีขาว)
    g2.setColor(Color.WHITE);
    g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arc, arc));

    // ✅ เส้นขอบ (เพิ่มบรรทัดนี้)
    g2.setStroke(new BasicStroke(1)); // ความหนาเส้นขอบ
    g2.setColor(Color.DARK_GRAY); // สีเทาอ่อน
    g2.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, arc, arc));

    g2.dispose();
    super.paintComponent(g);
}

}
