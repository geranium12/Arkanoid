package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class MusicView extends BaseObjectView{
    private AudioInputStream ais;

    public MusicView() {
        try {
            ais = AudioSystem.getAudioInputStream(new File(DATA_LOCATION + "Arkanoid.wav"));
        } catch (UnsupportedAudioFileException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public AudioInputStream getAis() {
        return ais;
    }
}
