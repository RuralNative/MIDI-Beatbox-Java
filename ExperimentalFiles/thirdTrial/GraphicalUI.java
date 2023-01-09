package practiceForGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicalUI {
    JFrame userFrame;
    JLabel label;


    public void go() {
        userFrame = new JFrame();
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton changeColorButton = new JButton("Change Color");
        changeColorButton.addActionListener(new ColorListener());

        JButton changeLabelButton = new JButton("ChangeLabel");
        changeLabelButton.addActionListener(new LabelListener());

        label = new JLabel("I am a Label");

        MyDrawPanel drawPanel = new MyDrawPanel();

        userFrame.getContentPane().add(BorderLayout.SOUTH, changeColorButton);
        userFrame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        userFrame.getContentPane().add(BorderLayout.WEST, label);
        userFrame.getContentPane().add(BorderLayout.EAST, changeLabelButton);
        userFrame.setSize(300, 300);
        userFrame.setVisible(true);   
    }

    class ColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        userFrame.repaint();
        }
    }

    class LabelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        label.setText("Ouch");
        }
    }
}
