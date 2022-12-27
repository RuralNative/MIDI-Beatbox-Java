import javax.sound.midi.*;

public class  MiniMusicApp {
    /*
     * Method to Play Sample Sound
     * Sequencer is instantiated and opened
     * Instantiate Sequence (played by Sequencer) and Track (holds MIDI information for Sequencer)
     * Instantiates 2 MIDI Events, then add to Track
     * Give Sequence (holding Track) to Sequencer
     * Play Sequencer
    */
    public void play(){
        
        try {
            Sequencer player = MidiSystem.getSequencer();
            player.open();

            Sequence musicSequence = new Sequence(Sequence.PPQ, 4);

            Track musicTrack = musicSequence.createTrack();

            ShortMessage soundOne = new ShortMessage();
            soundOne.setMessage(192, 1, 44, 100);
            MidiEvent noteOn = new MidiEvent(soundOne, 1);
            musicTrack.add(noteOn);

            ShortMessage soundTwo = new ShortMessage();
            soundTwo.setMessage(192, 1, 44, 100);
            MidiEvent noteOff = new MidiEvent(soundTwo, 16);
            musicTrack.add(noteOff);

            player.setSequence(musicSequence);
            
            player.start();

        } catch (Exception e) {
            System.out.println("Exception handled. Please review code");
            e.printStackTrace();
        }
    }
}    