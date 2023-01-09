package BeatBox;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.PanelUI;
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
    String[] instrumentList = {"Bass Drum", "Closed Hi-hat", "Open Hi-hat", "Acoustic Snare", "Crash Symbal", "Hand Clap", "High Tom", "High Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "L ow-mid Tom", "High Agogo", "Open Hi Conga"};
    int[] instrumentIndex = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public static void main(String[] args) {
        new BeatPlayer().buildInterface();
    }

    public void buildInterface() {
        mainFrame = new JFrame("Cyber BeatBox");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout backgroundLayout = new BorderLayout();
        JPanel backgroundPanel = new JPanel(backgroundLayout);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        checkBoxList = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new StartButtonListener());
        buttonBox.add(startButton);

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new StopButtonListener);
        buttonBox.add(stopButton);

        JButton increaseTempoButton = new JButton("Tempo Up");
        increaseTempoButton.addActionListener(new IncreaseTempoButtonListener());
        buttonBox.add(increaseTempoButton);

        JButton decreaseTempoButton = new JButton("Tempo Down");
        decreaseTempoButton.addActionListener(new DecreaseTempoButtonListener());
        buttonBox.add(decreaseTempoButton);

        Box instrumentNameBox = new Box(BoxLayout.Y_AXIS);
        for (int instrumentIndex = 0; instrumentIndex < 16; instrumentIndex++) {
            instrumentNameBox.add(new Label (instrumentList[instrumentIndex]));
        }

        backgroundPanel.add(BorderLayout.EAST, buttonBox);
        backgroundPanel.add(BorderLayout.WEST, instrumentNameBox);

        mainFrame.getContentPane().add(backgroundPanel);

        GridLayout gridLayout = new GridLayout(16, 16);
        gridLayout.setVgap(1);
        gridLayout.setHgap(2);
        mainPanel = new JPanel(gridLayout);
        backgroundPanel.add(BorderLayout.CENTER, mainPanel);

        for (int loopCount = 0; loopCount < 256; loopCount++) {
            JCheckBox beatCheckBox = new JCheckBox();
            beatCheckBox.setSelected(false);
            checkBoxList.add(beatCheckBox);
            mainPanel.add(beatCheckBox);
        }

        setUpMidi();

        mainFrame.setBounds(50, 50, 300, 300);
        mainFrame.pack();
        mainFrame.setVisible();
    }

    public void setUpMidi() {
        try {
            musicSequencer = MidiSystem.getSequencer();
            musicSequencer.open();
            musicSequence = new Sequence(Sequence.PPQ, 4);
            musicTrack = musicSequence.createTrack();
            musicSequencer.setTempoInBPM(120);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
}
