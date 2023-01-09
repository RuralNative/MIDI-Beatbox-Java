package BeatBox;

import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;

public class BeatBox {
    JPanel mainPanel;
    ArrayList<JCheckBox> checkboxList;
    Sequencer sequencer;
    Sequence sequence;
    Track track;
    JFrame theFrame;

String[] instrumentNames = {“Bass Drum”, “Closed Hi-Hat”,
“Open Hi-Hat”,”Acoustic Snare”, “Crash Cymbal”, “Hand Clap”,
“High Tom”, “Hi Bongo”, “Maracas”, “Whistle”, “Low Conga”,
“Cowbell”, “Vibraslap”, “Low-mid Tom”, “High Agogo”,
“Open Hi Conga”};
int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
These represent the actual drum ‘keys’.
public static void main (String[] args) {
The drum channel is like a piano, except
new BeatBox2().buildGUI();
each ‘key’ on the piano is a different drum.
}
So the number ‘35’ is the key for the Bass
drum, 42 is Closed Hi-Hat, etc.
public void buildGUI() {
theFrame = new JFrame(“Cyber BeatBox”);
theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
BorderLayout layout = new BorderLayout();
JPanel background = new JPanel(layout);
background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
checkboxList = new ArrayList<JCheckBox>();
Box buttonBox = new Box(BoxLayout.Y_AXIS);
JButton start = new JButton(“Start”);
start.addActionListener(new MyStartListener());
buttonBox.add(start);
JButton stop = new JButton(“Stop”);
stop.addActionListener(new MyStopListener());
buttonBox.add(stop);
JButton upTempo = new JButton(“Tempo Up”);
upTempo.addActionListener(new MyUpTempoListener());
buttonBox.add(upTempo);
JButton downTempo = new JButton(“Tempo Down”);
420   chapter 13

downTempo.addActionListener(new MyDownTempoListener());
buttonBox.add(downTempo);
Box nameBox = new Box(BoxLayout.Y_AXIS);
for (int i = 0; i < 16; i++) {
nameBox.add(new Label(instrumentNames[i]));
}
background.add(BorderLayout.EAST, buttonBox);
background.add(BorderLayout.WEST, nameBox);
theFrame.getContentPane().add(background);
GridLayout grid = new GridLayout(16,16);
grid.setVgap(1);
grid.setHgap(2);
mainPanel = new JPanel(grid);
background.add(BorderLayout.CENTER, mainPanel);
for (int i = 0; i < 256; i++) {
JCheckBox c = new JCheckBox();
c.setSelected(false);
checkboxList.add(c);
mainPanel.add(c);
} // end loop
setUpMidi();
theFrame.setBounds(50,50,300,300);
theFrame.pack();
theFrame.setVisible(true);
} // close method
public void setUpMidi() {
try {
sequencer = MidiSystem.getSequencer();
sequencer.open();
sequence = new Sequence(Sequence.PPQ,4);
track = sequence.createTrack();
sequencer.setTempoInBPM(120);
} catch(Exception e) {e.printStackTrace();}
} // close method

BeatBox code
public void buildTrackAndStart() {
int[] trackList = null;
sequence.deleteTrack(track);
track = sequence.createTrack();
for (int i = 0; i < 16; i++) {
trackList = new int[16];
int key = instruments[i];
for (int j = 0; j < 16; j++ ) {
JCheckBox jc = (JCheckBox) checkboxList.get(j + (16*i));
if ( jc.isSelected()) {
trackList[j] = key;
} else {                                                   Is the checkbox at this beat selected? If yes, put
trackList[j] = 0;                                          the key value in this slot in the array (the slot that
}                                                          represents this beat). Otherwise, the instrument is
} // close inner loop                                      NOT supposed to play at this beat, so set it to zero.
makeTracks(trackList);
track.add(makeEvent(176,1,127,0,16));
} // close outer
We always want to make sure that there IS an event at
track.add(makeEvent(192,9,1,0,15));
beat 16 (it goes 0 to 15). Otherwise, the BeatBox might
try {
not go the full 16 beats before it starts over.
sequencer.setSequence(sequence);
sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
Let’s you specify the number
sequencer.start();
of loop iterations, or in this
sequencer.setTempoInBPM(120);
case, continuous looping.
} catch(Exception e) {e.printStackTrace();}
} // close buildTrackAndStart method
public class MyStartListener implements ActionListener {
public void actionPerformed(ActionEvent a) {
buildTrackAndStart();
}
} // close inner class
422   chapter 13

