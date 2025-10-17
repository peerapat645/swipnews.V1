package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import gui.set.*;
import java.util.ArrayList;
import java.util.List;

public class admindashboard extends popup implements MouseListener {

    private setRoundedPanel mainPanel;
    private RoundedTextField searchField;
    private JLabel titleLabel;
    private JLabel iconUser, iconnews;
    private List<JLabel> newsLabels = new ArrayList<>();
    private List<JLabel> deleteIcons = new ArrayList<>();
    private List<String> news = new ArrayList<>();
    private static final String NEWS_FILE = "./File/accout/news/news.txt";

    public admindashboard() {
        super();
        setTitle("AdminDashboard | SwipeNews");
        getContentPane().setBackground(new Color(190, 255, 210)); 
        setLayout(new GridBagLayout());

        // ===== โหลดข่าวจากไฟล์ =====
        loadNewsFromCSV(NEWS_FILE);

        // ===== Main Content Panel =====
        mainPanel = new setRoundedPanel(25);
        mainPanel.setBackground(new Color(255, 255, 255, 230));
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(380, 560));

        // ===== Title =====
        titleLabel = new JLabel("Admin Dashboard | News");
        titleLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 22));
        titleLabel.setBounds(30, 25, 300, 30);
        mainPanel.add(titleLabel);

        // ===== Search Field =====
        searchField = new RoundedTextField(20);
        searchField.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
        searchField.setText("search");
        searchField.setForeground(Color.GRAY);
        searchField.setBounds(30, 60, 120, 30);
        // กด Enter แล้วไฮไลท์ข่าวที่ตรงกับคำค้น
        searchField.addActionListener(e -> {
            String keyword = searchField.getText().trim().toLowerCase();

        // รีเซ็ตสีทั้งหมดก่อน
            for (JLabel label : newsLabels) {
                label.setBackground(Color.WHITE);
            }

            // ถ้าไม่ได้พิมพ์อะไรเลย
            if (keyword.isEmpty() || keyword.equals("search")) {
                JOptionPane.showMessageDialog(this, "Please enter a search term", "Notification", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            boolean found = false;
            for (int i = 0; i < newsLabels.size(); i++) {
                JLabel label = newsLabels.get(i);
                String text = label.getText().toLowerCase();
                if (text.contains(keyword)) {
                //  ไฮไลท์
                    label.setBackground(new Color(255, 255, 150));
                    found = true;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "No news found with the word \"" + keyword + "\"", "Search results", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("search")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("search");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        mainPanel.add(searchField);

        // ====== สร้างรายชื่อข่าว ======
        JPanel newsListPanel = new JPanel(null);
        newsListPanel.setOpaque(false);

        int y = 10;
        for (int i = 0; i < news.size(); i++) {
            String newss = news.get(i);

            JLabel nameLabel = new JLabel(newss);
            nameLabel.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
            nameLabel.setOpaque(true);
            nameLabel.setBackground(Color.WHITE);
            nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            nameLabel.setBounds(40, y, 220, 30);
            newsListPanel.add(nameLabel);
            newsLabels.add(nameLabel);

            // ถังขยะ
            ImageIcon deleteIcon = new ImageIcon("./icon/delete.png");
            Image scaledDelete = deleteIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            JLabel delLabel = new JLabel(new ImageIcon(scaledDelete));
            delLabel.setBounds(270, y + 3, 24, 24);
            delLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            delLabel.addMouseListener(this);
            newsListPanel.add(delLabel);
            deleteIcons.add(delLabel);

            y += 45;
        }

        newsListPanel.setPreferredSize(new Dimension(340, y + 10));

        JScrollPane scrollPane = new JScrollPane(newsListPanel);
        scrollPane.setBounds(10, 110, 350, 400);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//ปิดแนวนอน
        mainPanel.add(scrollPane);

        // ===== แถบล่าง =====
        setRoundedPanel adminbar = new setRoundedPanel(30);
        //adminbar.setBackgroundColor(new Color(255, 255, 255, 230));
        adminbar.setPreferredSize(new Dimension(170, 50));
        adminbar.setLayout(null);

        ImageIcon newsiIcon = new ImageIcon("icon/news.png");
        Image newicon = newsiIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        iconnews = new JLabel(new ImageIcon(newicon));
        iconnews.setBounds(30, 10, 30, 30);
        iconnews.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ImageIcon usericon = new ImageIcon("icon/ban-user.png");
        Image user = usericon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        iconUser = new JLabel(new ImageIcon(user));
        iconUser.setBounds(110, 10, 30, 30);
        iconUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        adminbar.add(iconnews);
        adminbar.add(iconUser);

        JPanel bottomBar = new JPanel(new GridBagLayout());
        bottomBar.setOpaque(false);
        bottomBar.add(adminbar);
        bottomBar.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);
        container.add(mainPanel, BorderLayout.CENTER);
        container.add(bottomBar, BorderLayout.SOUTH);
        add(container);

        // ปิด focus 
        addWindowListener(new WindowAdapter() {
        @Override
            public void windowOpened(WindowEvent e) {
                container.requestFocusInWindow();
                }
            });

        iconnews.addMouseListener(this);
        iconUser.addMouseListener(this);
    }

    // โหลดข่าวจากไฟล์ CSV
    private void loadNewsFromCSV(String filePath) {
        news.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] data = line.split(",");
                    news.add(data[4].trim());
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "News file not found: " + filePath, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ลบข่าวจากไฟล์ CSV
    private void removeNewsFromCSV(String newsToRemove) {
        File inputFile = new File(NEWS_FILE);
        File tempFile = new File("./File/accout/news/news_temp.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(",");
                if (parts.length > 0) {
                    String currentNews = parts[4].trim();
                    if (!currentNews.equals(newsToRemove)) {
                        writer.write(currentLine);
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "An error occurred while deleting the news.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // แทนที่ไฟล์เก่า
        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            JOptionPane.showMessageDialog(this, "Cannot update the news file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // โหลดใหม่หลังลบ
    private void reloadNews() {
        this.dispose();
        new admindashboard().setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < deleteIcons.size(); i++) {
            if (e.getSource() == deleteIcons.get(i)) {
                String delTitle = news.get(i);
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Want to delete the news \"" + delTitle + "\" or not?",
                        "Confirm deletion",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    removeNewsFromCSV(delTitle);
                    reloadNews();
                }
            }
        }
        if (e.getSource() == iconUser) {
            new AdminDashboardUser().setVisible(true);
            this.dispose();
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
