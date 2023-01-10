package ExperimentalFiles.secondTrial;

import javax.sound.midi.*;
import javax.swing.JFrame;

public class MusicPlayer {
    static JFrame windowFrame = new JFrame("Music Player");
    static MyDrawPanel windowDrawPanel;
    
    public static void main(String[] args) {
        MusicPlayer mini = new MusicPlayer(); 
        mini.go();
    }
    
    public void setUpGraphicalInterface() {
        windowDrawPanel = new MyDrawPanel();
        windowFrame.setContentPane(windowDrawPanel);
        windowFrame.setBounds(30, 30, 300, 300);
        windowFrame.setVisible(true);
    }
    
    public void go() {
        setUpGraphicalInterface();

        try {
            Sequencer musicSequencer = MidiSystem.getSequencer();
            musicSequencer.open();

            int[] controlEvent = {127};
            musicSequencer.addControllerEventListener(windowDrawPanel, controlEvent);

            Sequence musicSequence = new Sequence(Sequence.PPQ, 4);
            Track musicTrack = musicSequence.createTrack();

            int randomInteger = 0;
            for (int randomNum = 0; randomNum < 60; randomNum += 4 ) {
                randomInteger = (int) ((Math.random() * 50) + 1);
                Thread.sleep(50);
                musicTrack.add(makeEvent(144, 1, randomNum, 100, randomNum));
                musicTrack.add(makeEvent(176, 1, 127, 0, randomNum));
                musicTrack.add(makeEvent(128, 1, randomNum, 100, randomNum + 2));
            }

            musicSequencer.setSequence(musicSequence);
            musicSequencer.setTempoInBPM(220);
            musicSequencer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

    public static MidiEvent makeEvent(int command, int channel, int one, int two, int tick) {
        MidiEvent musicNote =  null;
        try {
            ShortMessage note = new ShortMessage();
            note.setMessage(command, channel, one, two);
            musicNote = new MidiEvent(note, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return musicNote;
    }
}
