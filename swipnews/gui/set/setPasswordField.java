package gui.set;

import javax.swing.JPasswordField;

public class setPasswordField extends JPasswordField {

	public setPasswordField() {
		super();
	}

	public setPasswordField(int columns) {
		super(columns);
	}

	public setPasswordField(String text) {
		super(text);
	}

	public setPasswordField(String text, int columns) {
		super(text, columns);
	}

	/**
	 * ปรับฟอนต์ของข้อความใน JPasswordField
	 */
	public void setFont(String fontName, int style, int size) {
		setFont(new java.awt.Font(fontName, style, size));
	}

	/**
	 * ปรับขนาดของ JPasswordField
	 */
	public void setFieldSize(int width, int height) {
		setPreferredSize(new java.awt.Dimension(width, height));
	}

	/**
	 * สลับการแสดง/ซ่อนรหัสผ่าน
	 */
	public void showPassword(boolean show) {
		if (show) {
			setEchoChar((char)0);
		} else {
			setEchoChar('●');
		}
	}
}
