package be.ugent.objprog.ugentopoly.ui.sound;

import be.ugent.objprog.ugentopoly.Ugentopoly;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

public class SoundManager {
    private Clip coinSound;
    private Clip backgroundMusicClip;
    private Clip diceRollSoundClip;
    private Clip pionMoveSoundClip;

    public SoundManager() {
        loadSounds();
    }

    private void loadSounds() {
        try {
            coinSound = AudioSystem.getClip();
            AudioInputStream coinInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(Ugentopoly.class.getResource("sound/coin.wav")));
            assert coinSound != null;
            coinSound.open(coinInputStream);

            diceRollSoundClip = AudioSystem.getClip();
            AudioInputStream diceRollInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(Ugentopoly.class.getResource("sound/diceRoll.wav")));
            assert diceRollSoundClip != null;
            diceRollSoundClip.open(diceRollInputStream);

            pionMoveSoundClip = AudioSystem.getClip();
            AudioInputStream pionMoveInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(Ugentopoly.class.getResource("sound/woosh.wav")));
            assert pionMoveSoundClip != null;
            pionMoveSoundClip.open(pionMoveInputStream);

            backgroundMusicClip = AudioSystem.getClip();
            AudioInputStream backgroundMusicInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(Ugentopoly.class.getResource("sound/background.wav")));
            assert backgroundMusicClip != null;
            backgroundMusicClip.open(backgroundMusicInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playCoinSound() {
        coinSound.setFramePosition(0);
        FloatControl gainControl = (FloatControl) coinSound.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        coinSound.start();
    }

    public void playDiceRollSound() {
        diceRollSoundClip.setFramePosition(0);
        FloatControl gainControl = (FloatControl) diceRollSoundClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-20.0f);
        diceRollSoundClip.start();
    }

    public void playPionMoveSound() {
        pionMoveSoundClip.setFramePosition(0);
        FloatControl gainControl = (FloatControl) pionMoveSoundClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-20.0f);
        pionMoveSoundClip.start();
    }

    public void playBackgroundMusic() {
        backgroundMusicClip.setFramePosition(0);
        FloatControl gainControl = (FloatControl) backgroundMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-26.0f);
        backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}