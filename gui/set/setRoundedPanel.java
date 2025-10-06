package gui.set;

import java.awt.Graphics;
import java.awt.*;
import javax.swing.JPanel;

// JPanel ที่มีขอบมน
    public class setRoundedPanel extends JPanel {
        protected int cornerRadius;
        public setRoundedPanel(int radius) {
            super();
            this.cornerRadius = radius;
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            g2.dispose();
        }
    }