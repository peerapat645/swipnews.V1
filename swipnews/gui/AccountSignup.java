package gui;
import java.util.Locale;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import gui.set.*;   

public class AccountSignup extends popup implements ActionListener, MouseListener {

    private setRoundedPanel panel2;
    private RoundedTextField user , email ;          
    private RoundedPasswordField pass , conpass;      
    private JLabel lbUser, lbPass, textsingup ,backicon, textPanel2;
    private JPanel panel ;
    private JLabel imageUser, imageLockpass, imageLockconpass,imageemail;
    private setRoundedbotton login;
    private JRadioButton showpass;
    private JLabel Logo;
    
    
    
    public AccountSignup() {
       super();
        textsingup = new JLabel("Sign up");
        textsingup.setFont(new Font("Leelawadee UI", Font.BOLD, 64));
        textsingup.setForeground(Color.darkGray); 

        setTitle("AccountSignup | SwipeNews");
        getContentPane().setBackground(Color.LIGHT_GRAY);

         // สร้าง label ข้อความต่างๆในpanel2
        //อันนี้หัวข้อ
     JLabel Text2 = new JLabel("Recommend Password Policy");
     Text2.setFont(new Font("Leelawadee UI", Font.BOLD, 14));
     Text2.setForeground(Color.BLACK);

     Logo = new JLabel(new ImageIcon("./icon/logoW.png"));//เพิ่มรูปภาพล็อก
         add(Logo);
         Logo.setBounds(-15,20,512,512);
         //ปรับขนาดภาพ
         Logo.setIcon(new ImageIcon(new ImageIcon("./icon/logoW.png").getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH))); 
     

     //เนิ้อหาย่อยๆ
     textPanel2 = new JLabel("<html>One uppercase letter (A–Z)<br>One lowercase letter (a–z)<br>One number (0–9)<br>One special character (!@#$%^&* or similar)</html>");
     textPanel2.setFont(new Font("Leelawadee UI", Font.PLAIN, 12));
     textPanel2.setForeground(Color.BLACK);
     
    
        
   

    setTitle("AccountSignUp | SwipeNews");
    getContentPane().setBackground(Color.LIGHT_GRAY);
    setLayout(null);

    
    // สร้างpanelทางซ้าย ก้คือpanel2
    panel2 = new setRoundedPanel(50);
    panel2.setLayout(null);; 
    panel2.setBackground(Color.white);
    

    
    panel = new setRoundedPanel(20);
    panel.setLayout(null);
    panel.setBackground(Color.WHITE);
    
        

        //สร้าง label สำหรับชื่อผู้ใช้กับรหัสผ่าน
        lbUser = new JLabel();
        lbUser.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));

        lbPass = new JLabel();
        lbPass.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));

      
        login = new setRoundedbotton("Sign Up", 20,new Font("Leelawadee UI", Font.BOLD, 18));//ปุ่ม
        login.setBackground(new Color(0, 153, 102)); // ปุ่มสีเขียวเข้ม
        login.setForeground(Color.WHITE);//ตัวอักษรสีขาว

        //เพิ่ม backicon
        backicon = new JLabel(new ImageIcon("./icon/back.png"));
        ImageIcon emailI = new ImageIcon("./icon/email2.png");
        Image scaledemail= emailI.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        imageemail = new JLabel(new ImageIcon(scaledemail));
        imageemail.setOpaque(false);
        
        

        email = new RoundedTextField(20); // 20 = จำนวน columns
        email.setFont(new Font("Tahoma", Font.PLAIN, 16));
        email.setBackground(Color.WHITE);
        email.setPreferredSize(new Dimension(200, 30)); // ตั้งขนาดช่อง
        email.setText("Email");
        email.setForeground(Color.gray);
        email.addFocusListener(new FocusAdapter() {
        @Override
            public void focusGained(FocusEvent e) {
                if (email.getText().equals("Email")) {
                    email.setText("");
                    email.setForeground(Color.black);
                }
            }

        @Override
            public void focusLost(FocusEvent e) {
                if (email.getText().isEmpty()) {
                    email.setText("Email");
                    email.setForeground(Color.GRAY);
                }
            }
        });

        user = new RoundedTextField(20); // 20 = จำนวน columns
        user.setFont(new Font("Tahoma", Font.PLAIN, 16));
        user.setBackground(Color.WHITE);
        user.setPreferredSize(new Dimension(200, 30)); // ตั้งขนาดช่อง
        user.addFocusListener(new FocusAdapter() {
        @Override
            public void focusGained(FocusEvent e) {
                if (user.getText().equals("Username")) {
                    user.setText("");
                    user.setForeground(Color.black);
                }
            }

        @Override
            public void focusLost(FocusEvent e) {
                if (user.getText().isEmpty()) {
                    user.setText("Username");
                    user.setForeground(Color.GRAY);
                }
            }
        });

        pass = new RoundedPasswordField(20); // 20 = จำนวน columns
        pass.setFont(new Font("Tahoma", Font.PLAIN, 16));
        pass.setBackground(Color.WHITE);
        pass.setPreferredSize(new Dimension(200, 30)); // ตั้งขนาดช่อง
        pass.setText("Password");
        pass.setForeground(Color.gray);
        pass.setEchoChar((char) 0);

        pass.addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            if (String.valueOf(pass.getPassword()).equals("Password")) {
                pass.setText("");
                pass.setForeground(Color.black);
                pass.setEchoChar('•'); 
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (String.valueOf(pass.getPassword()).isEmpty()) {
                pass.setText("Password");
                pass.setForeground(Color.GRAY);
                pass.setEchoChar((char) 0); 
            }
        }
        });

        conpass = new RoundedPasswordField(20); // 20 = จำนวน columns
        conpass.setFont(new Font("Tahoma", Font.PLAIN, 16));
        conpass.setBackground(Color.WHITE);
        conpass.setPreferredSize(new Dimension(200, 30)); // ตั้งขนาดช่อง        
        conpass.setText("Confirm Password");
        conpass.setForeground(Color.gray);
        conpass.setEchoChar((char) 0);

        conpass.addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            if (String.valueOf(conpass.getPassword()).equals("Confirm Password")) {
                conpass.setText("");
                conpass.setForeground(Color.black);
                conpass.setEchoChar('•'); // เริ่มซ่อนตอนพิมพ์
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (String.valueOf(conpass.getPassword()).isEmpty()) {
                conpass.setText("Confirm Password");
                conpass.setForeground(Color.GRAY);
                conpass.setEchoChar((char) 0); // กลับมาแสดงตัวอักษรถ้าไม่ได้พิมพ์
            }
        }
        });

        showpass = new JRadioButton("Show Password");
        showpass.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
        showpass.setBackground(Color.WHITE); 
        // การทำงานของปุ่ม Show Password
        showpass.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                pass.showPassword(showpass.isSelected());
                 conpass.showPassword(showpass.isSelected());
            }
            
        });
        
    
       
    login.addActionListener(this);

      
        ImageIcon UserIcon = new ImageIcon("./icon/account.png");
        Image scaledUser = UserIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        imageUser = new JLabel(new ImageIcon(scaledUser));
        imageUser.setOpaque(false);
        
        ImageIcon lockIcon = new ImageIcon("icon/password.png");
        Image scaledLockpass = lockIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        imageLockpass = new JLabel(new ImageIcon(scaledLockpass));
        imageLockpass.setOpaque(false);

        ImageIcon Iconpass = new ImageIcon("./icon/password.png");
        Image scalesconpass = Iconpass.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        imageLockconpass = new JLabel(new ImageIcon(scalesconpass));
        imageLockconpass.setOpaque(false);

    // ตำแหน่ง (ใน panel ขวา)
    user.setBounds(120, 150, 180, 30);
    imageUser.setBounds(70, 150, 30, 30);
    email.setBounds(120, 200, 180, 30);
    imageemail.setBounds(70,200,30,30);
    lbPass.setBounds(110, 200, 100, 22);
    pass.setBounds(120, 250, 180, 30);
    imageLockpass.setBounds(70, 250, 30, 30);
    conpass.setBounds(120, 300, 180, 30);
    imageLockconpass.setBounds(70, 300, 30, 30);
    showpass.setBounds(120, 340, 160, 24);
    login.setBounds(130, 400, 120, 40);//ปุ่มSign in
    textsingup.setBounds(80, 30, 300, 90);
    backicon.setBounds(10, 460, 26, 26);
    Text2.setBounds(30, 5, 260, 20);
    panel2.setBounds(95, 475, 300, 100);
    textPanel2.setBounds(30, 30, 240, 60);
    
    panel.setBounds(500,75, 400, 500);

    panel.add(lbUser);
    panel.add(user);
    panel.add(email);
    panel.add(lbPass);
    panel.add(pass);
    panel.add(conpass);
    panel.add(showpass);
    panel.add(login);
    panel.add(imageUser);
    panel.add(imageLockpass);
    panel.add(imageLockconpass);
    panel.add(textsingup);
    panel.add(backicon);
    panel.add(imageemail);
    
    
    add(panel2);
    add(panel);
    
    addWindowListener(new WindowAdapter() {
    @Override
    public void windowOpened(WindowEvent e) {
        panel.requestFocusInWindow();
        }
    });
    

   //ใน panel2
    textPanel2.setHorizontalAlignment(SwingConstants.LEFT); // จัดข้อความชิดซ้าย
    panel2.add(textPanel2);
    panel2.add(Text2);

    
   

    
   

    
    
        
        // เพิ่ม MouseListener ให้ปุ่ม login
        login.addMouseListener(this);
        backicon.addMouseListener(this);
    }



        private static boolean isGmail(String s) {
    if (s == null ) return false;
    String raw = s.trim();
    // ทำหน้าที่ตรวจสอบตัวพิมพ์ใหญ่พิมพ์เล็ก
    if (!raw.equals(raw.toLowerCase(Locale.ROOT))) return false;
    String e = raw.toLowerCase(Locale.ROOT);
    // ต้องลงท้าย @gmail.com, มี @ แค่ตัวเดียว และไม่มีเว้นวรรค
    return e.endsWith("@gmail.com")
        && e.indexOf('@') == e.lastIndexOf('@')
        && !e.contains(" ");
        }


    @Override
    public void actionPerformed(ActionEvent e) {
        String username = user.getText();
        String password = new String(pass.getPassword());
        String confirm = new String(conpass.getPassword());
        String emailText = email.getText();
        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty() || emailText.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Please fill in all the information completely.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(panel, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
         // บังคับ @gmail.com 
    if (!isGmail(emailText)) {
        JOptionPane.showMessageDialog(panel, "The email must be @gmail.com only. Please enter it again.", "Error", JOptionPane.ERROR_MESSAGE);
        email.requestFocus();
        return;
        }

        // ตรวจสอบความแข็งแรงของรหัสผ่าน (เรียกจาก PasswordValidatorCase)
        PasswordValidatorCase.PasswordStrength strength = PasswordValidatorCase.validate(password);
        switch (strength) {
            case INVALID:
                JOptionPane.showMessageDialog(panel, "Incorrect password: must be at least 8 characters long", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            case WEAK:
                JOptionPane.showMessageDialog(panel, "Weak password: Please use a combination of letters and numbers, or increase the length.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            case MEDIUM:
                // ยอมรับได้ แต่แจ้งเตือนให้ผู้ใช้ทราบ
                int confirmMedium = JOptionPane.showConfirmDialog(panel, "Medium-level password — Do you want to use this password?", "Confirm password", JOptionPane.YES_NO_OPTION);
                if (confirmMedium != JOptionPane.YES_OPTION) return;
                break;
            case STRONG:
                // ยอดเยี่ยม — ดำเนินการต่อ
                break;
        }
        // บันทึกข้อมูลลงไฟล์ useraccount.csv จะมี(username,password)
        try {
            // ตรวจสอบชื่อผู้ใช้ซ้ำในไฟล์ useraccount.csv
            java.io.BufferedReader checkReader = new java.io.BufferedReader(
                new java.io.FileReader("./File/accout/useraccount.csv"));
            String checkLine;
            while ((checkLine = checkReader.readLine()) != null) {
                String[] existing = checkLine.split(",");
                if (existing.length >= 1 && existing[0].equals(username)) {
                    checkReader.close();
                    JOptionPane.showMessageDialog(panel, "Username already exists. Please choose another.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            checkReader.close();

            java.io.FileWriter fw = new java.io.FileWriter("./File/accout/useraccount.csv", true);
            fw.write("\n"+username + "," + password);
            fw.close();
            // บันทึก email แยกไว้ในไฟล์ email.csv
            java.io.FileWriter emailfw = new java.io.FileWriter("./File/accout/email.csv", true);
            emailfw.write("\n"+emailText);
            emailfw.close();
            JOptionPane.showMessageDialog(panel, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "An error occurred while saving the data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //การใช้ mouseClicked เพื่อตรวจสอบการคลิกปุ่ม login
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == backicon) {
            new login().setVisible(true);
            this.dispose();
        }
        if (e.getSource() == login) {
            String inputUser = user.getText();
            String inputPass = new String(pass.getPassword());
            boolean found = false;
            try {
                java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.FileReader("./File/accout/useraccount.scr"));
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
                javax.swing.JOptionPane.showMessageDialog(this, "An error occurred while reading the user account file", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (found) {
               new App(inputPass).setVisible(true);
                this.dispose();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "The username or password is incorrect.!", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            //ปิดหน้านี้แล้วเข้าหน้า login
            new login().setVisible(true);
            this.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Not used
    }
     
   

}


