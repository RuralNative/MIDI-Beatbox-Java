package practiceForGUI;

import javax.swing.*;
import java.awt.*;

public class Swing {
    public static void main(String[] args) {
        createFrame();
    }

    public static void createFrame() {
        JFrame windowFrame = new JFrame();
        JPanel windowPanel = new JPanel();
        JButton createButton = new JButton("Create Button");
        JButton deleteButton = new JButton("Delete Button");

        windowPanel.setBackground(Color.blue);
        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.Y_AXIS));
        windowPanel.add(createButton);
        windowPanel.add(deleteButton);
        windowFrame.getContentPane().add(BorderLayout.EAST, windowPanel);
        windowFrame.setSize(300,300);
        windowFrame.setVisible(true);
        
    }
}
