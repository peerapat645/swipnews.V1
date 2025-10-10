package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;


import gui.set.*;

public class login extends popup implements MouseListener{

    private setRoundedPanel panel;
    private setRoundedbotton login;
    private RoundedTextField username;
    private RoundedPasswordField password;
    private JLabel labelSwipeNew;
    private JLabel label_signin;
    private JLabel label_wallcome;
    private JLabel plsuser_password;
    private JLabel imageLabelLock;
    private JLabel imageLabelUser;
    private JLabel dout_have_accout;
    private JLabel Logo;
    //private JLabel backicon ;
    private JRadioButton showePassword;
    private JLabel botton_admin;
    private JLabel botton_signup;
    
    

    public login() {
        super();
        setTitle("Login | SwipNews"); // ตั้งชื่อหน้าต่าง
        setLayout (null);
        panel = new setRoundedPanel(20); // 20 คือความโค้งของมุม
        panel.setLayout(null); // ใช้ null layout เพื่อกำหนดตำแหน่งเอง
        panel.setBounds(490, 80, 400, 500);//กำหนดตำแหน่ง panel
        panel.setBackground(Color.WHITE); // เปลี่ยนสีพื้นหลังของ panel
        panel.setPreferredSize(new Dimension(400, 500)); // กำหนดขนาด panel

        //เพิ่มตัวอักษร 
        labelSwipeNew = new JLabel("SwipeNews");
        labelSwipeNew.setFont(new Font("Leelawadee UI", Font.BOLD, 48));
        labelSwipeNew.setForeground(Color.darkGray);

        label_signin = new JLabel("Sign In");
        label_signin.setFont(new Font("Leelawadee UI", Font.BOLD, 60)); // เปลี่ยนฟอนต์ไทยและขนาด

        plsuser_password = new JLabel("Please enter the User and Password");
        plsuser_password.setFont(new Font("Leelawadee UI", Font.PLAIN, 14)); // เปลี่ยนฟอนต์ไทยและขนาด
        plsuser_password.setForeground(Color.darkGray); // เปลี่ยนสีข้อความเป็นสีเทา

        label_wallcome = new JLabel("SwipeNews");
        label_wallcome.setFont(new Font("SansSerif", Font.BOLD, 60)); 
        label_wallcome.setForeground(Color.WHITE);

        //เพิ่มตัวอักษร dout have accout
        dout_have_accout = new JLabel("don't have an accout? ");
        dout_have_accout.setFont(new Font("Leelawadee UI", Font.PLAIN, 14)); // เปลี่ยนฟอนต์ไทยและขนาด

        //เพิ่มปุ่ม login ลงใน panel
        login = new setRoundedbotton("Sign in", 20,new Font("Leelawadee UI", Font.BOLD, 18));//ชื่อปุ่ม, ความโค้ง, ฟอนต์
        login.setBackground(new Color(0, 153, 102)); // ปุ่มสีเขียวเข้ม
        login.setForeground(Color.WHITE);//ตัวอักษรสีขาว  
        //เพิ่มขีด label_adminและ signup
        botton_admin = new JLabel("<html><u>Login In as admin</u></html>");
        botton_signup = new JLabel("<html><u>Sign Up</u></html>");
        

        //เพิ่ม JTextField สำหรับชื่อผู้ใช้
        username = new RoundedTextField(20); // 20 = จำนวน columns
        username.setFont(new Font("Tahoma", Font.PLAIN, 18));
        username.setBackground(Color.WHITE);
        username.setPreferredSize(new Dimension(200, 30)); // ตั้งขนาดช่อง

        
        //เพิ่ม JpasswordField สำหรับรหัสผ่าน
        password = new RoundedPasswordField(20); // 20 = จำนวน columns
        password.setFont(new Font("Tahoma", Font.PLAIN, 18));
        password.setBackground(Color.WHITE);
        password.setPreferredSize(new Dimension(200, 30)); // ตั้งขนาดช่อง

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
        //PasswordIcon
        ImageIcon lockIcon = new ImageIcon("icon/password.png");
        Image scaledLock = lockIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        imageLabelLock = new JLabel(new ImageIcon(scaledLock));
        imageLabelLock.setOpaque(false);
        //UserIcon
        ImageIcon userIcon = new ImageIcon("icon/account.png");
        Image scaledUser = userIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        imageLabelUser = new JLabel(new ImageIcon(scaledUser));
        imageLabelUser.setOpaque(false);    
        
        Logo = new JLabel(new ImageIcon("./icon/logoW.png"));//เพิ่มรูปภาพlogo
         add(Logo);
         
         //ปรับขนาดภาพ
         Logo.setIcon(new ImageIcon(new ImageIcon("./icon/logoW.png").getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH)));
        


        //ปรับตำแหน่งปุ่มและขนาด
        labelSwipeNew.setBounds(70, 35, 300, 60);
        label_signin.setBounds(100, 35, 300, 80); // x, y, width, height 
        plsuser_password.setBounds(90, 140, 300, 40); // x, y, width, height
        username.setBounds(100, 200, 210, 30); // x, y, width, height
        password.setBounds(100, 260, 210, 30); // x, y, width, height
        login.setBounds(140, 350, 100, 40); // x, y, width, height
        showePassword.setBounds(100, 290, 200, 30); // x, y, width, height
        imageLabelUser.setBounds(60, 200, 30, 30); // x, y, width, height
        imageLabelLock.setBounds(60, 260, 30, 30); // x, y, width, height
        //backicon.setBounds(10, 360, 26, 26); // x, y, width, height
        botton_admin.setBounds(10, 450, 150, 30); // x, y, width, height
         botton_admin.setFont(new Font("Leelawadee UI", Font.PLAIN, 14)); // เปลี่ยนฟอนต์ไทยและขนาด
        botton_signup.setBounds(155, 420, 100, 30); // x, y, width, height
         botton_signup.setFont(new Font("Leelawadee UI", Font.PLAIN, 14)); // เปลี่ยนฟอนต์ไทยและขนาด
        dout_have_accout.setBounds(10, 420, 300, 30); // x, y, width, height 
        label_wallcome.setBounds(75, 90, 500, 80); // x, y, width, height
        Logo.setBounds(-10,130,512,512);
        

        //เพิ่มปุ่มลงใน panel
        //panel.add(labelSwipeNew);
        panel.add(label_signin);
        panel.add(login);
        panel.add(username);
        panel.add(password);
        panel.add(showePassword);
        panel.add(imageLabelUser);
        panel.add(imageLabelLock);
        panel.add(plsuser_password);
        panel.add(dout_have_accout);  
        //panel.add(backicon);
        panel.add(botton_admin);
        panel.add(botton_signup);
        
        

        add(panel); // เพิ่ม panel เข้าไปใน login panel หลัก
        add(label_wallcome);
        // เพิ่ม MouseListener ให้ปุ่ม
        login.addMouseListener(this);
        //backicon.addMouseListener(this);
        botton_admin.addMouseListener(this);
        botton_signup.addMouseListener(this);
    }

    //การใช้ mouseClicked เพื่อตรวจสอบการคลิกปุ่ม login
    // การตรวจสอบชื่อผู้ใช้และรหัสผ่าน
    @Override
    public void mouseClicked(MouseEvent e) {
        Object l = e.getSource();  
        Object a = e.getSource();
        Object s = e.getSource();
        if (s == botton_signup ) {
            new AccountSignup().setVisible(true);
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
                    new java.io.FileReader("./File/accout/useraccount.csv")); // ใช้ path ตรงกับ root project
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
                new App(inputUser).setVisible(true);
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
    

