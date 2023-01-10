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
        stopButton.addActionListener(new StopButtonListener());
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
        mainFrame.setVisible(true);
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
    
    public void buildTrackAndStart() {
        int[] trackList = null;

        musicSequence.deleteTrack(musicTrack);
        musicTrack = musicSequence.createTrack();

        for (int outerLoopCount = 0; outerLoopCount < 16; outerLoopCount++) {
            trackList = new int[16];
            int key = instrumentIndex[outerLoopCount];
            
            for (int innerLoopCount = 0; innerLoopCount < 16; innerLoopCount++) {
                JCheckBox instrumentIndexCheckBox = (JCheckBox) checkBoxList.get(innerLoopCount + (16+ outerLoopCount));
                
                if (instrumentIndexCheckBox.isSelected()) {
                    trackList[innerLoopCount] = key;
                } else {
                    trackList[innerLoopCount] = 0;
                }
            }
            makeTracks(trackList);
            musicTrack.add(makeEvent(192, 9, 1, 0, 15));
        }

        musicTrack.add(makeEvent(192, 9, 1, 0, 15));
        try {
            musicSequencer.setSequence(musicSequence);
            musicSequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            musicSequencer.start();
            musicSequencer.setTempoInBPM(120);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class StartButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            buildTrackAndStart();
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

    public void makeTracks(int[] list) {
        for (int loopCount = 0; loopCount < 16; loopCount++) {
            int key = list[loopCount];

            if (key != 0) {
                musicTrack.add(makeEvent(144, 9, key, 100, loopCount));
                musicTrack.add(makeEvent(128, 9, key, 100, loopCount+1));
            }
        }
    }

    public MidiEvent makeEvent(int command, int channel, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage note = new ShortMessage();
            note.setMessage(command, channel, two, tick);
            event = new MidiEvent(note, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }
}


