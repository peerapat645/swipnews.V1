package gui.set;


import javax.swing.JTextField;


public class setJTextField extends JTextField {

	public setJTextField() {
		super();
	}

	public setJTextField(int columns) {
		super(columns);
	}

	public setJTextField(String text) {
		super(text);
	}

	public setJTextField(String text, int columns) {
		super(text, columns);
	}

	/**
	 * ปรับฟอนต์ของข้อความใน JTextField
	 */
	public void setFont(String fontName, int style, int size) {
		setFont(new java.awt.Font(fontName, style, size));
	}

	/**
	 * ปรับขนาดของ JTextField
	 */
	public void setFieldSize(int width, int height) {
		setPreferredSize(new java.awt.Dimension(width, height));
	}
	

}
