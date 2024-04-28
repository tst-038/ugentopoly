package be.ugent.objprog.ugentopoly.ui.sound;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Objects;

public class SoundManager {
    private Media coinSound;
    private MediaPlayer diceRollSoundPlayer;
    private MediaPlayer pionMoveSoundPlayer;

    public SoundManager() {
        loadSounds();
    }

    private void loadSounds() {
        coinSound = new Media(Objects.requireNonNull(Ugentopoly.class.getResource("sound/coin.mp3")).toString());

        Media diceRollSound = new Media(Objects.requireNonNull(Ugentopoly.class.getResource("sound/diceRoll.mp3")).toString());
        diceRollSoundPlayer = new MediaPlayer(diceRollSound);

        Media pionMoveSound = new Media(Objects.requireNonNull(Ugentopoly.class.getResource("sound/woosh.mp3")).toString());
        pionMoveSoundPlayer = new MediaPlayer(pionMoveSound);
    }

    public void playCoinSound() {
        MediaPlayer coinSoundPlayer = new MediaPlayer(coinSound);
        coinSoundPlayer.play();
    }

    public void playDiceRollSound() {
        diceRollSoundPlayer.stop();
        diceRollSoundPlayer.play();
    }

    public void playPionMoveSound() {
        pionMoveSoundPlayer.stop();
        pionMoveSoundPlayer.play();
    }
}