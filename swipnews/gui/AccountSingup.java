package gui;
import java.util.Locale;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import gui.set.*;   
import gui.Pass.PasswordValidatorCase;

public class AccountSingup extends popup implements ActionListener, MouseListener {

    private setJTextField user , email ;          
    private setPasswordField pass , conpass;       
    private JLabel lbUser, lbPass, textsingup ,backicon ;
    private JPanel panel;
    private JLabel imageUser, imageLockpass, imageLockconpass,imageemail;
    private setRoundedbotton login;
    private JRadioButton showpass;
    
    
    
    public AccountSingup() {
        super();
        textsingup = new JLabel("Sign up");
        textsingup.setFont(new Font("Leelawadee UI", Font.BOLD, 22));
        textsingup.setForeground(Color.BLACK); 
   

        setTitle("AccountSingup | SwipeNews");
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(new GridBagLayout());         // จัด panel ให้อยู่กลาง

        panel = new setRoundedPanel(20); //ทำให้มุมโค้ง เพื่อ????
        panel.setLayout(null);                  
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(320, 420));
        

        //สร้าง label สำหรับชื่อผู้ใช้กับรหัสผ่าน
        lbUser = new JLabel();
        lbUser.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));

        lbPass = new JLabel();
        lbPass.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));

      
        login = new setRoundedbotton("Login", 20,new Font("Leelawadee UI", Font.BOLD, 18));//ชื่อปุ่ม, ความโค้ง, ฟอนต์
       
        //เพิ่ม backicon
        backicon = new JLabel(new ImageIcon("./icon/back.png"));
        imageemail = new JLabel(new ImageIcon("./icon/email.png"));
        

        email = new setJTextField();
        email.setFont("Tahoma", Font.PLAIN, 16);
        email.setFieldSize(180, 28);

        user = new setJTextField();
        user.setFont("Tahoma", Font.PLAIN, 16);
        user.setFieldSize(180, 28);

        pass = new setPasswordField();          
        pass.setFont("Tahoma", Font.PLAIN, 16);
        pass.setFieldSize(180, 28);
        pass.showPassword(false);  // เริ่มซ่อน 

        conpass = new setPasswordField();
        conpass.setFont("Tahoma", Font.PLAIN, 16);
        conpass.setFieldSize(180, 28);
        conpass.showPassword(false);  // เริ่มซ่อน           

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

      
       imageUser = new JLabel(new ImageIcon("./icon/user.png"));
       imageLockpass = new JLabel(new ImageIcon("./icon/unlock.png"));
       imageLockconpass = new JLabel(new ImageIcon("./icon/unlock.png"));

    // ตำแหน่ง
    user.setBounds(70, 100, 180, 30);
    imageUser.setBounds(40, 100, 26, 26);
    email.setBounds(70, 140, 180, 30);
    imageemail.setBounds(40,140,30,30);
    lbPass.setBounds(110, 170, 100, 22);
    pass.setBounds(70, 180, 180, 30);
    imageLockpass.setBounds(40, 180, 26, 26);
    conpass.setBounds(70, 220, 180, 30);
    imageLockconpass.setBounds(40, 220, 26, 26);
    showpass.setBounds(150, 260, 160, 24);
    login.setBounds(100, 310, 120, 40);
    textsingup.setBounds(110, 35, 300, 40);
    backicon.setBounds(10, 360, 26, 26);


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

    add(panel);
        
        // เพิ่ม MouseListener ให้ปุ่ม login
        login.addMouseListener(this);
        backicon.addMouseListener(this);
    }



        private static boolean isGmail(String s) {
    if (s == null) return false;
    String e = s.trim().toLowerCase(Locale.ROOT);
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
            JOptionPane.showMessageDialog(panel, "กรุณากรอกข้อมูลให้ครบถ้วน", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(panel, "รหัสผ่านไม่ตรงกัน", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
         // บังคับ @gmail.com 
    if (!isGmail(emailText)) {
        JOptionPane.showMessageDialog(panel, "อีเมลต้องเป็น @gmail.com เท่านั้น ไปใส่มาใหม่", "Error", JOptionPane.ERROR_MESSAGE);
        email.requestFocus();
        return;
        }

        // ตรวจสอบความแข็งแรงของรหัสผ่าน (เรียกจาก PasswordValidatorCase)
        PasswordValidatorCase.PasswordStrength strength = PasswordValidatorCase.validate(password);
        switch (strength) {
            case INVALID:
                JOptionPane.showMessageDialog(panel, "รหัสผ่านไม่ถูกต้อง: ต้องมีอย่างน้อย 8 ตัวอักษร", "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
                return;
            case WEAK:
                JOptionPane.showMessageDialog(panel, "รหัสผ่านอ่อน: กรุณาใช้ตัวอักษรผสมตัวเลข หรือเพิ่มความยาวให้มากขึ้น", "คำเตือน", JOptionPane.WARNING_MESSAGE);
                return;
            case MEDIUM:
                // ยอมรับได้ แต่แจ้งเตือนให้ผู้ใช้ทราบ
                int confirmMedium = JOptionPane.showConfirmDialog(panel, "รหัสผ่านระดับกลาง — ต้องการใช้รหัสผ่านนี้หรือไม่?", "ยืนยันรหัสผ่าน", JOptionPane.YES_NO_OPTION);
                if (confirmMedium != JOptionPane.YES_OPTION) return;
                break;
            case STRONG:
                // ยอดเยี่ยม — ดำเนินการต่อ
                break;
        }
        // บันทึกข้อมูลลงไฟล์ useraccount.scr จะมี(username,password)
        try {
            java.io.FileWriter fw = new java.io.FileWriter("./File/accout/useraccount.scr", true);
            fw.write("\n"+username + "," + password);
            fw.close();
            // บันทึก email แยกไว้ในไฟล์ email.scr
            java.io.FileWriter emailfw = new java.io.FileWriter("./File/accout/email.scr", true);
            emailfw.write("\n"+emailText);
            emailfw.close();
            JOptionPane.showMessageDialog(panel, "สมัครสมาชิกสำเร็จ!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "เกิดข้อผิดพลาดในการบันทึกข้อมูล", "Error", JOptionPane.ERROR_MESSAGE);
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
                javax.swing.JOptionPane.showMessageDialog(this, "เกิดข้อผิดพลาดในการอ่านไฟล์บัญชีผู้ใช้", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (found) {
                new App().setVisible(true);
                this.dispose();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง!", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
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



