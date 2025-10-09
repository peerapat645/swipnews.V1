package gui;
import java.awt.*;
import java.awt.event.*;

import gui.set.*;
import javax.swing.*;
public class AdminDashboardUser extends JFrame {

    public AdminDashboardUser() {
        setTitle("Admin Dashboard User");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== พื้นหลัง Gradient =====
        GradientPanel backgroundPanel = new GradientPanel(
                new Color(0, 168, 104),
                new Color(200, 255, 220));
        //backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.setLayout(new BorderLayout(0, 0));

        // กล่องด้านใน (เขียวอ่อน)
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(new Color(200, 255, 220));
        mainPanel.setPreferredSize(new Dimension(800, 500));
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 168, 104), 30, true));
        backgroundPanel.add(mainPanel, BorderLayout.CENTER);

        backgroundPanel.add(mainPanel);
        //add(backgroundPanel);
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        this.getContentPane().add(backgroundPanel, BorderLayout.CENTER);


        // หัวข้อ
        JLabel title = new JLabel("Admin Dashboard User");
        title.setFont(new Font("Arial", Font.BOLD, 18));

        // ช่องค้นหา
        JTextField search = new JTextField("search");
        search.setFont(new Font("Arial", Font.PLAIN, 14));
        search.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(search, BorderLayout.CENTER);

        // รายชื่อ user
        JPanel userPanel = new JPanel();
        userPanel.setOpaque(false);
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < 5; i++) {
            JPanel row = new JPanel(new BorderLayout());
            row.setOpaque(false);
            row.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

            JLabel username = new JLabel("username");
            JButton delete = new JButton("🚫");
            delete.setBackground(Color.WHITE);
            delete.setFocusPainted(false);

            row.add(username, BorderLayout.WEST);
            row.add(delete, BorderLayout.EAST);
            userPanel.add(row);
        }

        // เมนูด้านล่าง
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        bottom.setOpaque(false);
        bottom.add(new JButton("👤"));
        bottom.add(new JButton("📋"));
        bottom.add(new JButton("✏️"));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(userPanel, BorderLayout.CENTER);
        mainPanel.add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AdminDashboardUser();
    }
}