import javax.sound.midi.*;

public class MusicTestOne {
    //Method to Instantiate Sequencer object and print out String upon success
    public void play() {
        //Exception Handling for MidiUnavailableException
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
        } catch (MidiUnavailableException exception) {
            System.out.println("Exception handled");
            exception.printStackTrace();
        }
    }
}    