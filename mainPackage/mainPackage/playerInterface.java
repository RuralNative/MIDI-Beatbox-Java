package mainPackage;

import javax.swing.*;
import java.awt.*;

public class playerInterface {
    public playerInterface() {
        JFrame frame = new JFrame();
        JButton button = new JButton("Click Me");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(button);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
    

    

        
    
}
