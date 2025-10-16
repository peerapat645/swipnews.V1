package gui.set;

import javax.swing.*;
import java.awt.*;

public class setRoundedbotton extends JButton {
    public static final Component POPUP_LAYER = null;
    private int cornerRadius;

    public setRoundedbotton(String text, int radius) {
        super(text);
        this.cornerRadius = radius;
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    public setRoundedbotton(String text, int radius, Font font) {
        this(text, radius);
        setFont(font);
    }

    public void setButtonFont(Font font) {
        setFont(font);
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isArmed()) {
            g2.setColor(getBackground().darker());
        } else {
            g2.setColor(getBackground());
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        super.paintComponent(g);
        g2.dispose();
    }
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, cornerRadius, cornerRadius);
        g2.dispose();
    }
    @Override
    public boolean contains(int x, int y) {
        return new java.awt.geom.RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius).contains(x, y);
    }
}
