package src.main;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private DataLine.Info info;
    private SourceDataLine sourceDataLine;
    private static final int BUFFER_SIZE = 4096;

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

    public void play() {
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
    }
}
