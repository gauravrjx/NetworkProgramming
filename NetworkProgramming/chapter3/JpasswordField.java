package chapter3;

import java.awt.*;
import javax.swing.*;

public class JpasswordField extends JApplet {
    public void init() {
        // Create a JPanel with a GridLayout
        JPanel panel = new JPanel(new GridLayout(2, 1));

        // Add a JLabel for the password field
        panel.add(new JLabel("Enter Password:"));

        // Create a JPasswordField
        JPasswordField passwordField = new JPasswordField();

        // Add the password field to the panel
        panel.add(passwordField);

        // Add the panel to the applet
        add(panel);
    }
}

