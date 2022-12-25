import javax.sound.midi.*;

public class MusicTestOne {
    //Method to Instantiate Sequencer object and print out String upon success
    public void play() {
        //Exception Handling for MidiUnavailableException
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            System.out.println("We got a sequencer");
        } catch (MidiUnavailableException exception) {
            System.out.println("MidiUnavailableException catched.");
        }
    }
}    