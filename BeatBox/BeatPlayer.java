package BeatBox;

import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;

public class BeatPlayer {
    JFrame mainFrame;
    JPanel mainPanel;
    Sequencer musicSequencer;
    Sequence musicSequence;
    Track musicTrack;
    ArrayList<JCheckBox> checkBoxList;
    String[] instrumentList = {"Bass Drum", "Closed Hi-hat", "Open Hi-hat", "Acoustic Snare", "Crash Symbal", "Hand Clap", "High Tom", "High Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};
    int[] instrumentIndex = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public static void main(String[] args) {
        new BeatPlayer().buildInterface();
    }

    public void buildInterface() {
        mainFrame = new JFrame("Cyber BeatBox");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout backgroundLayout = new BorderLayout();
        JPanel background = new JPanel(backgroundLayout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    }
     
}
