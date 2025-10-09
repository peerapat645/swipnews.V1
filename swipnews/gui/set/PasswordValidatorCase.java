package gui.set;

public class PasswordValidatorCase {

    public static enum PasswordStrength {
        INVALID,
        WEAK,
        MEDIUM,
        STRONG
    }

    public static PasswordStrength validate(String pass) { 
        // ความยาวขั้นต่ำที่ต้องการ
        int minLength = 8; // กำหนดความยาวขั้นต่ำ
        if (pass == null || pass.length() < minLength) {
            return PasswordStrength.INVALID; // สั้นเกินไป == ไม่ถูกต้อง
        }

        // ตัวแปรสำหรับตรวจประเภทตัวอักษร
        boolean hasLower = false;   // มีตัวอักษรพิมเล็ก
        boolean hasUpper = false;   // มีตัวอักษรพิมใหญ่
        boolean hasDigit = false;   // มีตัวเลข
        boolean hasSpecial = false; // มีอักขระพิเศษ

        // วนตรวจแต่ละตัวอักษรในรหัสผ่าน
        for (char c : pass.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLower = true; // พบตัวพิมพ์เล็ก
            } else if (Character.isUpperCase(c)) {
                hasUpper = true; // พบตัวพิมพ์ใหญ่
            } else if (Character.isDigit(c)) {
                hasDigit = true; // พบตัวเลข
            } else {
                hasSpecial = true; // ถ้าไม่เข้าเงื่อนไขข้างบน ให้เป็นอักขระพิเศษ
            }
        }

        // STRONG: ยาว >=10 และมี lower + upper + digit + special
        if (hasLower && hasUpper && hasDigit && hasSpecial && pass.length() >= 10) {
            return PasswordStrength.STRONG;
        }

        // MEDIUM: ยาว >=8 และมีตัวอักษรอย่างน้อยกับตัวเลขหรืออักขระพิเศษ
        if ((hasLower || hasUpper) && (hasDigit || hasSpecial)) {
            return PasswordStrength.MEDIUM;
        }

        // WEAK: ยาวพอแต่ยังขาดอะไรอยู่ (เช่น มีแต่ตัวอักษร หรือมีแต่ตัวเลข)
        return PasswordStrength.WEAK;
    }
}


