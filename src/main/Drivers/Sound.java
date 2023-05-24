package src.main.Drivers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Changelog: creation by Felix
 * 
 * Bugfix in looping, removed reset()
 *      - Victor
 * 
 * Removed play method in favor of calling loop(1), added volume control
 *      - Victor
 */

public class Sound {
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private DataLine.Info info;
    private SourceDataLine sourceDataLine;
    private static final int BUFFER_SIZE = 12288;

    public Sound(String filePath) {
        try {
            soundFile = new File(filePath);
            audioStream = AudioSystem.getAudioInputStream(soundFile);
            audioFormat = audioStream.getFormat();
            info = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public void play(int volume) {
        Thread soundThread = new Thread(() -> {
            try {
                sourceDataLine.open(audioFormat);
                sourceDataLine.start();

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;

                while ((bytesRead = audioStream.read(buffer, 0, buffer.length)) != -1) {
                    sourceDataLine.write(buffer, 0, bytesRead);
                }

                sourceDataLine.drain();
                sourceDataLine.stop();
                sourceDataLine.close();
                audioStream.close();
            } catch (IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        });
        soundThread.start();
    }*/

    public void play(float volume)
    {
        loop(1, volume);
    }

    public void loop(int times, float volume) {
        Thread soundThread = new Thread(() -> {
            try {
                sourceDataLine.open(audioFormat);

                // Adjust volume on audio line
                if (sourceDataLine.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                    FloatControl vol = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
                    vol.setValue(volume);
                }

                sourceDataLine.start();
    
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;
    
                for (int i = 0; i < times; i++) {
                    audioStream = AudioSystem.getAudioInputStream(soundFile);
    
                    while ((bytesRead = audioStream.read(buffer, 0, buffer.length)) != -1) {
                        sourceDataLine.write(buffer, 0, bytesRead);
                    }
    
                    // Rewind the audio stream to the beginning for the next loop
                    //audioStream.reset();
                }
    
                sourceDataLine.drain();
                sourceDataLine.stop();
                sourceDataLine.close();
                audioStream.close();
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        });
    
        soundThread.start();
    }

}
