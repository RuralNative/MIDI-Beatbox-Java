package BeatBox;

import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;

public class BeatPlayerInterface {

    JFrame interfaceFrame;
    JPanel mainPanel;
    JPanel instrumentCheckBoxPanel;
    Box buttonBox;
    Box instrumentNameBox;
    Sequencer musicSequencer;
    Sequence musicSequence;
    Track musicTrack;
    ArrayList<JCheckBox> checkBoxList;
    String[] instrumentList = {"Bass Drum", "Closed Hi-hat", "Open Hi-hat", "Acoustic Snare", "Crash Symbal", "Hand Clap", "High Tom", "High Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "L ow-mid Tom", "High Agogo", "Open Hi Conga"};
    int[] instrumentIndex = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};
    

    public void buildPlayerInterface() {
        buildFrame("Cyber BeatBox");
        buildPanel();
        buildBoxWithButtons();
        buildBoxForInstrumentNames();
        insertBoxesToEastAndWestRegions();
        buildInstrumentCheckBoxPanelonMainPanel();
    }


    private void buildFrame(String frameTitle) {
        //Creates Frame of which all GUI components shall be built
        interfaceFrame = new JFrame(frameTitle);
        //Set Properties of Frame
        interfaceFrame.setBounds(50, 50, 300, 300);
        interfaceFrame.pack();
        interfaceFrame.setVisible(true);
        interfaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void buildPanel() {
        //Creates Panel (backgroundPanel) with BorderLayOut and set Properties where components shall be built on
        BorderLayout backgroundLayout = new BorderLayout();
        mainPanel = new JPanel(backgroundLayout);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }


    private void buildBoxWithButtons() {
        buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new StartButtonListener());
        buttonBox.add(startButton);
        
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new StopButtonListener());
        buttonBox.add(stopButton);
        
        JButton increaseTempoButton = new JButton("Tempo Up");
        increaseTempoButton.addActionListener(new IncreaseTempoButtonListener());
        buttonBox.add(increaseTempoButton);
        
        JButton decreaseTempoButton = new JButton("Tempo Down");
        decreaseTempoButton.addActionListener(new DecreaseTempoButtonListener());
        buttonBox.add(decreaseTempoButton);
    }


    private void buildBoxForInstrumentNames() {
        instrumentNameBox = new Box(BoxLayout.Y_AXIS);
            for (int instrumentIndex = 0; instrumentIndex < 16; instrumentIndex++) {
                instrumentNameBox.add(new Label (instrumentList[instrumentIndex]));
            }
    }


    private void insertBoxesToEastAndWestRegions() {
        mainPanel.add(BorderLayout.EAST, buttonBox);
        mainPanel.add(BorderLayout.WEST, instrumentNameBox);
    }


    private void buildInstrumentCheckBoxPanelonMainPanel() {
        GridLayout gridLayout = new GridLayout(16, 16);
        gridLayout.setVgap(1);
        gridLayout.setHgap(2);
        mainPanel = new JPanel(gridLayout);
        mainPanel.add(BorderLayout.CENTER, instrumentCheckBoxPanel);

        checkBoxList = new ArrayList<JCheckBox>();
        for (int loopCount = 0; loopCount < 256; loopCount++) {
            JCheckBox beatCheckBox = new JCheckBox();
            beatCheckBox.setSelected(false);
            checkBoxList.add(beatCheckBox);
            instrumentCheckBoxPanel.add(beatCheckBox);
        }
    }
    

    //INNER CLASSES FOR BUTTON ACTION LISTENERS
    public class StartButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            System.out.println("Start Button Clicked");
            MusicPlayer player = new MusicPlayer();
            player.buildTrackAndStart();
        }
    }
    
    public class StopButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            musicSequencer.stop();
        }
    }
    
    public class IncreaseTempoButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            float tempoFactor = musicSequencer.getTempoFactor();
            musicSequencer.setTempoFactor((float)(tempoFactor * 1.03));
        }
    }
   
    public class DecreaseTempoButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            float tempoFactor = musicSequencer.getTempoFactor();
            musicSequencer.setTempoFactor((float)(tempoFactor * 0.97));
        }
    }
}