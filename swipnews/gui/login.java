package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;


import gui.set.*;

public class login extends popup implements MouseListener {

    private setRoundedPanel panel;
    private setRoundedbotton login;
    private setJTextField username;
    private setPasswordField password;
    private JLabel label_wallcome;
    private JLabel plsuser_password;
    private JLabel imageLabelLock;
    private JLabel imageLabelUser;
    //private JLabel backicon ;
    private JRadioButton showePassword;
    private JLabel botton_admin;
    private JLabel botton_singup;
    

    public login() {
        super();
        setTitle("Login | SwipNews"); // ตั้งชื่อหน้าต่าง
        getContentPane().setBackground(Color.LIGHT_GRAY); // เปลี่ยนสีพื้นหลังของ login panel หลัก
        setLayout(new GridBagLayout()); // ให้อยู่กลางหน้าต่าง
        panel = new setRoundedPanel(20); // 20 คือความโค้งของมุม
        panel.setLayout(null); // ใช้ null layout เพื่อกำหนดตำแหน่งเอง
        panel.setBackground(Color.WHITE); // เปลี่ยนสีพื้นหลังของ panel
        panel.setPreferredSize(new Dimension(300, 400)); // กำหนดขนาด panel

        //เพิ่มตัวอักษร Walcome 
        label_wallcome = new JLabel("      Welcome ");
        label_wallcome.setFont(new Font("Leelawadee UI", Font.BOLD, 28)); // เปลี่ยนฟอนต์ไทยและขนาด
        plsuser_password = new JLabel("Please enter the user password");
        plsuser_password.setFont(new Font("Leelawadee UI", Font.PLAIN, 16)); // เปลี่ยนฟอนต์ไทยและขนาด
        plsuser_password.setForeground(Color.gray); // เปลี่ยนสีข้อความเป็นสีเทา

        //เพิ่มปุ่ม login ลงใน panel
        login = new setRoundedbotton("Login", 20,new Font("Leelawadee UI", Font.BOLD, 18));//ชื่อปุ่ม, ความโค้ง, ฟอนต์
        //เพิ่มขีด label_adminและ singup
        botton_admin = new JLabel("<html><u>Login as admin</u></html>");
        botton_singup = new JLabel("<html><u>Singup</u></html>");

        //เพิ่ม JTextField สำหรับชื่อผู้ใช้
        username = new setJTextField(); // สร้าง JTextField สำหรับชื่อผู้ใช้
        username.setFont("Tahoma", Font.PLAIN, 18); // เปลี่ยนฟอนต์ไทยและขนาด
        username.setFieldSize(200, 30); // กำหนดขนาดของ JTextField
        

        //เพิ่ม JpasswordField สำหรับรหัสผ่าน
        password = new setPasswordField(); // สร้าง JTextField สำหรับรหัสผ่าน
        password.setFont("Tahoma", Font.PLAIN, 18); // เปลี่ยนฟอนต์ไทยและขนาด
        password.setFieldSize(180, 28);// กำหนดขนาดของ JTextField
        password.showPassword(false); // เริ่มต้นซ่อนรหัสผ่าน
        
        //เพิ่ม ปุ่ม showePassword และการทำงาน
        showePassword = new JRadioButton("Show Password"); 
        showePassword.setFont(new Font("Leelawadee UI", Font.PLAIN, 14)); // เปลี่ยนฟอนต์ไทยและขนาด
        showePassword.setBackground(Color.WHITE); // เปลี่ยนสีพื้นหลังของปุ่มให้เข้ากับ panel

        //กด showePassword
        showePassword.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                password.showPassword(showePassword.isSelected());
            }
        });

        //เพิ่ม รูป
        //backicon = new JLabel(new ImageIcon("./icon/back.png"));//เพิ่มรูปภาพล็อก
        imageLabelLock = new JLabel(new ImageIcon("./icon/unlock.png"));//เพิ่มรูปภาพล็อก
        imageLabelUser = new JLabel(new ImageIcon("./icon/user.png"));//เพิ่มรูปภาพล็อก

        //ปรับตำแหน่งปุ่มและขนาด
        label_wallcome.setBounds(35, 35, 300, 40); // x, y, width, height 
        plsuser_password.setBounds(40, 70, 300, 40); // x, y, width, height
        username.setBounds(80, 140, 170, 30); // x, y, width, height
        password.setBounds(80, 180, 170, 30); // x, y, width, height
        login.setBounds(100, 250, 100, 40); // x, y, width, height
        showePassword.setBounds(70, 210, 120, 30); // x, y, width, height
        imageLabelUser.setBounds(40, 140, 26, 26); // x, y, width, height
        imageLabelLock.setBounds(40, 180, 26, 26); // x, y, width, height
        //backicon.setBounds(10, 360, 26, 26); // x, y, width, height
        botton_admin.setBounds(180, 360, 150, 30); // x, y, width, height
         botton_admin.setFont(new Font("Leelawadee UI", Font.PLAIN, 14)); // เปลี่ยนฟอนต์ไทยและขนาด
        botton_singup.setBounds(205, 210, 100, 30); // x, y, width, height
         botton_singup.setFont(new Font("Leelawadee UI", Font.PLAIN, 14)); // เปลี่ยนฟอนต์ไทยและขนาด
        
        

        //เพิ่มปุ่มลงใน panel
        panel.add(label_wallcome);
        panel.add(login);
        panel.add(username);
        panel.add(password);
        panel.add(showePassword);
        panel.add(imageLabelUser);
        panel.add(imageLabelLock);
        panel.add(plsuser_password);  
        //panel.add(backicon);
        panel.add(botton_admin);
        panel.add(botton_singup);
        

        add(panel); // เพิ่ม panel เข้าไปใน login panel หลัก

        // เพิ่ม MouseListener ให้ปุ่ม
        login.addMouseListener(this);
        //backicon.addMouseListener(this);
        botton_admin.addMouseListener(this);
        botton_singup.addMouseListener(this);
    }

    //การใช้ mouseClicked เพื่อตรวจสอบการคลิกปุ่ม login
    // การตรวจสอบชื่อผู้ใช้และรหัสผ่าน
    @Override
    public void mouseClicked(MouseEvent e) {
        Object l = e.getSource();  
        Object a = e.getSource();
        Object s = e.getSource();
        if (s == botton_singup ) {
            new AccountSingup().setVisible(true);
            this.dispose();
            
        }
        if (a == botton_admin ) {
            new adminLogin().setVisible(true);
            this.dispose();
            
        }  
        if (l == login ) {
            String inputUser = username.getText();
            String inputPass = new String(password.getPassword());
            boolean found = false;
            try {
                java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.FileReader("./File/accout/useraccount.scr")); // ใช้ path ตรงกับ root project
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2 && inputUser.equals(parts[0]) && inputPass.equals(parts[1])) {
                        found = true;
                        break;
                    }
                }
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "An error occurred while reading the author's account file.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (found) {
                new App().setVisible(true);
                this.dispose();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "The username or password is incorrect.!", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }
    
}
