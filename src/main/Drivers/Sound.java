package src.main.Drivers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * <h1>Sound Class</h1>
 * Time spent: 4.5 hours
 * 
 * @version 1.2
 * @version 6/8/2023
 * @author Felix Zhao, Victor Sarca (bugfixes)
 */
public class Sound {
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private DataLine.Info info;
    private SourceDataLine sourceDataLine;
    private static final int BUFFER_SIZE = 12288;

    /**
     * Felix Zhao - makes a sound object with the file path
     * 
     * @param filePath file path of the sound file
     */
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

    /*
     * public void play(int volume) {
     * Thread soundThread = new Thread(() -> {
     * try {
     * sourceDataLine.open(audioFormat);
     * sourceDataLine.start();
     * 
     * byte[] buffer = new byte[BUFFER_SIZE];
     * int bytesRead;
     * 
     * while ((bytesRead = audioStream.read(buffer, 0, buffer.length)) != -1) {
     * sourceDataLine.write(buffer, 0, bytesRead);
     * }
     * 
     * sourceDataLine.drain();
     * sourceDataLine.stop();
     * sourceDataLine.close();
     * audioStream.close();
     * } catch (IOException | LineUnavailableException e) {
     * e.printStackTrace();
     * }
     * });
     * soundThread.start();
     * }
     */

    /**
     * Felix Zhao - plays the sound once
     * 
     * @param volume specified volume
     */
    public void play(float volume) {
        loop(1, volume);
    }

    /**
     * Felix Zhao - loops the sound a specified number of times
     * 
     * @param times  number of times
     * @param volume specified volume
     */
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
                    // audioStream.reset();
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
