package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import gui.set.*;

public class AdminDashboardUser extends popup implements MouseListener {

    private setRoundedPanel mainPanel;
    private RoundedTextField searchField;
    private JLabel titleLabel;
    private JLabel iconUser, iconNews;
    private List<JLabel> userLabels = new ArrayList<>();
    private List<JLabel> banIcons = new ArrayList<>();
    private List<String> usernames = new ArrayList<>();

    // 🔹 path ไฟล์ข้อมูลผู้ใช้
    private static final String USER_FILE = "./File/accout/useraccount.csv";

    public AdminDashboardUser() {
        super();
        setTitle("AdminDashboard | User");
        getContentPane().setBackground(new Color(120, 255, 180));
        setLayout(new GridBagLayout());

        // 🔹 โหลดรายชื่อจากไฟล์ CSV
        loadUsernamesFromCSV(USER_FILE);

        // ====== Main Panel (ขาวโปร่ง) ======
        mainPanel = new setRoundedPanel(40);
        mainPanel.setBackground(new Color(255, 255, 255, 230));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(380, 560));

        // ====== Header ======
        JPanel headerPanel = new JPanel(null);
        headerPanel.setOpaque(false);
        headerPanel.setPreferredSize(new Dimension(380, 100));

        titleLabel = new JLabel("Admin Dashboard | User");
        titleLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
        titleLabel.setBounds(30, 25, 300, 30);
        titleLabel.setForeground(Color.DARK_GRAY);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        headerPanel.add(titleLabel);

        searchField = new RoundedTextField(20);
        searchField.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
        searchField.setText("search");
        searchField.setForeground(Color.GRAY);
        searchField.setBounds(30, 60, 120, 30);
        
        headerPanel.add(searchField);

        // กด Enter แล้วไฮไลท์ข่าวที่ตรงกับคำค้น
        searchField.addActionListener(e -> {
            String keyword = searchField.getText().trim().toLowerCase();

        // รีเซ็ตสีทั้งหมดก่อน
            for (JLabel label : userLabels) {
                label.setBackground(Color.WHITE);
            }

            // ถ้าไม่ได้พิมพ์อะไรเลย
            if (keyword.isEmpty() || keyword.equals("search")) {
                JOptionPane.showMessageDialog(this, "กรุณาพิมพ์คำค้นหา", "แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            boolean found = false;
            for (int i = 0; i < userLabels.size(); i++) {
                JLabel label = userLabels.get(i);
                String text = label.getText().toLowerCase();
                if (text.contains(keyword)) {
                //  ไฮไลท์
                    label.setBackground(new Color(255, 255, 150));
                    found = true;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "ไม่พบข่าวที่มีคำว่า \"" + keyword + "\"", "ผลการค้นหา", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // placeholder
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

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ====== User List Panel ======
        JPanel userListPanel = new JPanel(null);
        userListPanel.setOpaque(false);
        int y = 10;

        for (int i = 0; i < usernames.size(); i++) {
            String username = usernames.get(i);

            JLabel nameLabel = new JLabel(username);
            nameLabel.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
            nameLabel.setOpaque(true);
            nameLabel.setBackground(Color.WHITE);
            nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            nameLabel.setBounds(40, y, 220, 30);
            userListPanel.add(nameLabel);
            userLabels.add(nameLabel);

            // ไอคอน ban
            ImageIcon banIcon = new ImageIcon("icon/ban.png");
            Image scaledBan = banIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            JLabel banLabel = new JLabel(new ImageIcon(scaledBan));
            banLabel.setBounds(270, y + 3, 24, 24);
            banLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            banLabel.addMouseListener(this);
            userListPanel.add(banLabel);
            banIcons.add(banLabel);

            y += 45;
        }

        userListPanel.setPreferredSize(new Dimension(350, y + 10));

        JScrollPane scrollPane = new JScrollPane(userListPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // ====== Bottom Bar ======
        RoundedPanel bottomBarContainer = new RoundedPanel(40);
        bottomBarContainer.setBackgroundColor(new Color(255, 255, 255, 230));
        bottomBarContainer.setPreferredSize(new Dimension(170, 50));
        bottomBarContainer.setLayout(null);

        // user icon
        ImageIcon userIcon = new ImageIcon("icon/ban-user.png");
        Image uImg = userIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        iconUser = new JLabel(new ImageIcon(uImg));
        iconUser.setBounds(110, 10, 30, 30);
        iconUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bottomBarContainer.add(iconUser);

        // news icon
        ImageIcon newsIcon = new ImageIcon("icon/news.png");
        Image nImg = newsIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        iconNews = new JLabel(new ImageIcon(nImg));
        iconNews.setBounds(30, 10, 30, 30);
        iconNews.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bottomBarContainer.add(iconNews);

        JPanel bottomBarWrapper = new JPanel(new GridBagLayout());
        bottomBarWrapper.setOpaque(false);
        bottomBarWrapper.add(bottomBarContainer);
        bottomBarWrapper.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        // รวมทุกส่วนเข้าด้วยกัน
        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);
        container.add(mainPanel, BorderLayout.CENTER);
        container.add(bottomBarWrapper, BorderLayout.SOUTH);

        add(container);
        iconNews.addMouseListener(this);
        iconUser.addMouseListener(this);
        // focus fix
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                mainPanel.requestFocusInWindow();
            }
        });
    }

    // ===== โหลดชื่อผู้ใช้จากไฟล์ CSV =====
    private void loadUsernamesFromCSV(String filePath) {
        usernames.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] data = line.split(",");
                    if (data.length >= 1) {
                        usernames.add(data[0].trim());
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "ไม่พบไฟล์: " + filePath, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== ลบผู้ใช้ออกจากไฟล์ CSV =====
    private void removeUserFromCSV(String usernameToRemove) {
        File inputFile = new File(USER_FILE);
        File tempFile = new File("./File/accout/useraccount_temp.csv");

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(",");
                if (parts.length > 0) {
                    String username = parts[0].trim();
                    if (!username.equals(usernameToRemove)) {
                        writer.write(currentLine);
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "เกิดข้อผิดพลาดในการลบข้อมูลผู้ใช้", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ลบไฟล์เก่าแล้วแทนที่ด้วย temp
        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            JOptionPane.showMessageDialog(this, "ไม่สามารถอัปเดตไฟล์ได้", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== รีโหลดหน้าหลังจากลบ =====
    private void reloadUsers() {
        this.dispose();
        new AdminDashboardUser().setVisible(true);
    }

    // ===== เมื่อคลิก ban =====
    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < banIcons.size(); i++) {
            if (e.getSource() == banIcons.get(i)) {
                String usernameToBan = usernames.get(i);
                int confirm = JOptionPane.showConfirmDialog(this,
                        "ต้องการแบนผู้ใช้ \"" + usernameToBan + "\" หรือไม่?",
                        "ยืนยันการแบน",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    removeUserFromCSV(usernameToBan);
                    reloadUsers(); // โหลดรายชื่อใหม่
                }
            }
        }
        if (e.getSource() == iconNews) {
            new admindashboard().setVisible(true);
            this.dispose();
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
