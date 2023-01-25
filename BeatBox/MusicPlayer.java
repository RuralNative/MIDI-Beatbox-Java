package BeatBox;

import java.util.ArrayList;
import javax.sound.midi.*;
import javax.swing.JCheckBox; 

public class MusicPlayer {
    Sequencer musicSequencer;
    Sequence musicSequence;
    Track musicTrack;
    ArrayList<JCheckBox> checkBoxList = BeatPlayerInterface.checkBoxList;
    int[] instrumentIndex = BeatPlayerInterface.instrumentIndex;
    JCheckBox instrumentIndexCheckBox;

    //Build Sequencer and SequencE
    MusicPlayer() {
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
        System.out.println("Method Successfully Called");
        int[] trackList;
    
        musicSequence.deleteTrack(musicTrack);
        System.out.println("Track Deleted");
        musicTrack = musicSequence.createTrack();
        System.out.println("Created Track in Sequence");

        //LOOP
        for (int outerLoopCount = 0; outerLoopCount < 16; outerLoopCount++) {
            trackList = new int[16];
           int key = instrumentIndex[outerLoopCount];

            for (int innerLoopCount = 0; innerLoopCount < 16; innerLoopCount++) {
                instrumentIndexCheckBox = checkBoxList.get(innerLoopCount + (16 + outerLoopCount));
                
                if (instrumentIndexCheckBox.isSelected()) {
                    trackList[innerLoopCount] = key;
                } else {
                    trackList[innerLoopCount] = 0;
                } 
            }
            makeTracks(trackList);
            musicTrack.add(makeEvent(176, 1, 127, 0, 16));
        }
        //LOOP

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


    
    //Makes Track to be added to Sequence in buildTrackAndStart()
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
            note.setMessage(command, channel, one, two);
            event = new MidiEvent(note, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }
}

