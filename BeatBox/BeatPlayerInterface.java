package BeatBox;

import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;

public class BeatPlayerInterface {

    JFrame mainFrame;
    JPanel mainPanel;
    Sequencer musicSequencer;
    Sequence musicSequence;
    Track musicTrack;
    ArrayList<JCheckBox> checkBoxList;
    String[] instrumentList = {"Bass Drum", "Closed Hi-hat", "Open Hi-hat", "Acoustic Snare", "Crash Symbal", "Hand Clap", "High Tom", "High Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "L ow-mid Tom", "High Agogo", "Open Hi Conga"};
    int[] instrumentIndex = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};
    
    //Method to create GUI 
    public void buildInterface() {
        //Creates Frame of which all GUI components shall be built
        mainFrame = new JFrame("Cyber BeatBox");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creates Panel (backgroundPanel) with BorderLayOut and set Properties where components shall be built on
        BorderLayout backgroundLayout = new BorderLayout();
        JPanel backgroundPanel = new JPanel(backgroundLayout);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        checkBoxList = new ArrayList<JCheckBox>();

        //Creates Box to Add Buttons On
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        //Creates Buttons then Add to Box
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new StartButtonListener());
        buttonBox.add(startButton);
        //
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new StopButtonListener());
        buttonBox.add(stopButton);
        //
        JButton increaseTempoButton = new JButton("Tempo Up");
        increaseTempoButton.addActionListener(new IncreaseTempoButtonListener());
        buttonBox.add(increaseTempoButton);
        //
        JButton decreaseTempoButton = new JButton("Tempo Down");
        decreaseTempoButton.addActionListener(new DecreaseTempoButtonListener());
        buttonBox.add(decreaseTempoButton);

        //Creates Box to Put Instrument Names On
        Box instrumentNameBox = new Box(BoxLayout.Y_AXIS);
        for (int instrumentIndex = 0; instrumentIndex < 16; instrumentIndex++) {
            instrumentNameBox.add(new Label (instrumentList[instrumentIndex]));
        }

        //Add the Two Boxes to Panel EAST and WEST regions
        backgroundPanel.add(BorderLayout.EAST, buttonBox);
        backgroundPanel.add(BorderLayout.WEST, instrumentNameBox);

        //Add Panel (backgroundPanel) to Frame
        mainFrame.getContentPane().add(backgroundPanel);

        //Build Panel (mainPanel) on Panel (backgroundPanel)
        GridLayout gridLayout = new GridLayout(16, 16);
        gridLayout.setVgap(1);
        gridLayout.setHgap(2);
        mainPanel = new JPanel(gridLayout);
        backgroundPanel.add(BorderLayout.CENTER, mainPanel);

        //Build Checkboxes for Panel (mainPanel)
        for (int loopCount = 0; loopCount < 256; loopCount++) {
            JCheckBox beatCheckBox = new JCheckBox();
            beatCheckBox.setSelected(false);
            checkBoxList.add(beatCheckBox);
            mainPanel.add(beatCheckBox);
        }


        //Set Up Midi Functions (NOT GUI)
        setUpMidi();

        //Set Properties of Frame
        mainFrame.setBounds(50, 50, 300, 300);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}

private void buildFrame(String frameTitle) {
    //Creates Frame of which all GUI components shall be built
    mainFrame = new JFrame(frameTitle);
    //Set Properties of Frame
    mainFrame.setBounds(50, 50, 300, 300);
    mainFrame.pack();
    mainFrame.setVisible(true);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

