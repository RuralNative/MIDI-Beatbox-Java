package BeatBox;

public class BeatPlayer {
    public static void main(String[] args) {
        BeatPlayerInterface userGUI = new BeatPlayerInterface();
        userGUI.buildPlayerInterface();
        MusicPlayer player = new MusicPlayer();
        player.setUpMidi();
        System.out.println("Successful Built");
    }
}
    


