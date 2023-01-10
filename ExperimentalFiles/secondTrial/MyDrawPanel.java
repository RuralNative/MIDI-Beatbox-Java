package ExperimentalFiles.secondTrial;

import java.awt.*;
import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.*;

public class MyDrawPanel extends JPanel implements ControllerEventListener {
    boolean detectedEvent = false;

    public void controlChange(ShortMessage event) {
        detectedEvent = true;
        repaint();
    }

    public void paintComponent(Graphics g) {
        if (detectedEvent) {
            Graphics2D graphicDrawer = (Graphics2D) g;

            int redShade = (int) (Math.random() * 250);
            int greenShade = (int) (Math.random() * 250);
            int blueShade = (int) (Math.random() * 250);

            graphicDrawer.setColor(new Color(redShade, greenShade, blueShade));

            int randomHeight = (int) ((Math.random() * 120) + 10);
            int randomWidth = (int) ((Math.random() * 120) + 10);
            int xCoordinate = (int) ((Math.random() * 40) * 10);
            int yCoordinate = (int) ((Math.random() * 40) * 10);

            graphicDrawer.fillRect(xCoordinate, yCoordinate, randomHeight, randomWidth);
            detectedEvent = false;
        }
    }
}
