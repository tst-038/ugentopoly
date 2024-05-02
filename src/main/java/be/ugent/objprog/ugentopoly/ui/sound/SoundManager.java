package be.ugent.objprog.ugentopoly.ui.sound;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.exception.UgentopolyException;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

public class SoundManager {
    // I am aware that this is not the best way to handle the sound,
    // As far as I am aware, this is the only way in the standard Java library to play multiple sounds at the same time
    private static final int COIN_SOUND_POOL_SIZE = 40;
    private Clip[] coinSoundPool;
    private int coinSoundIndex;
    private Clip backgroundMusicClip;
    private Clip diceRollSoundClip;
    private Clip pionMoveSoundClip;

    public SoundManager() {
        loadSounds();
    }

    private void loadSounds() {
        try {
            coinSoundPool = new Clip[COIN_SOUND_POOL_SIZE];
            for (int i = 0; i < COIN_SOUND_POOL_SIZE; i++) {
                coinSoundPool[i] = AudioSystem.getClip();
                AudioInputStream coinInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(Ugentopoly.class.getResource("sound/coin.wav")));
                coinSoundPool[i].open(coinInputStream);
            }
            coinSoundIndex = 0;

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
            throw new UgentopolyException("Could not load sound", e);
        }
    }

    public void playCoinSound() {
        Clip coinSoundClip = coinSoundPool[coinSoundIndex];
        coinSoundClip.setFramePosition(0);
        FloatControl gainControl = (FloatControl) coinSoundClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        coinSoundClip.start();

        coinSoundIndex = (coinSoundIndex + 1) % COIN_SOUND_POOL_SIZE;
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