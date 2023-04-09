package chapter3;
import javax.swing.*;

public class JpasswordFieldExample {

	public static void main (String[]args) {
		JPasswordField passwordField = new JPasswordField();
		JLabel passwordLabel = new JLabel("Password: ");

		JFrame f = new JFrame("Password Field Example");
		passwordLabel.setBounds(20, 100, 80, 30);
		passwordField.setBounds(100, 100, 100, 30);
		f.add(passwordField);
		f.add(passwordLabel);
		f.setLayout(null);
		f.setSize(300, 300);
		f.setVisible(true);

	}

}