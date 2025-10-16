package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Dimension;
import java.io.File;
import javax.imageio.ImageIO;
import gui.set.setRoundedPanel;
 
public class Photol extends setRoundedPanel {
    private Image currentImage = null;
    private int newsId = -1;

    public Photol(int cornerRadius) {
        super(cornerRadius);
        this.setBackground(java.awt.Color.WHITE);
    }

    public void setNewsId(int id) {
        if (this.newsId != id) {
            this.newsId = id;
            loadImage();
            repaint();
        }
    }

    private void loadImage() {
        currentImage = null;
        if (newsId >= 0) {
            try {
                File imageFile = new File(String.format("./File/accout/news/photo/%d.jpg", newsId));
                if (imageFile.exists()) {
                    currentImage = ImageIO.read(imageFile);
                }
            } catch (Exception e) {
                System.err.println("Error loading image for news ID " + newsId + ": " + e.getMessage());
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        if (currentImage != null) {
            // คำนวณขนาดและตำแหน่งเพื่อให้รูปอยู่กึ่งกลางและคงสัดส่วน
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            
            // คำนวณอัตราส่วนของรูปภาพ
            double imgRatio = (double) currentImage.getWidth(null) / currentImage.getHeight(null);
            int imgWidth = panelWidth - 20; // เว้นขอบ 10px ทั้งสองด้าน
            int imgHeight = (int) (imgWidth / imgRatio);

            // ถ้าความสูงเกินพื้นที่ panel ให้ปรับขนาดตามความสูง
            if (imgHeight > panelHeight - 20) {
                imgHeight = panelHeight - 20;
                imgWidth = (int) (imgHeight * imgRatio);
            }

            // คำนวณตำแหน่งกึ่งกลาง
            int x = (panelWidth - imgWidth) / 2;
            int y = (panelHeight - imgHeight) / 2;

            g2.drawImage(currentImage, x, y, imgWidth, imgHeight, null);
        }

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400); // ขนาดเริ่มต้นของ panel
    }
}