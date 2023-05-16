package src.main;
import Sound;
import Window;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Poopoo 2 3!");
        Sound test = new Sound("C:/Users/Felix/Documents/Timber Trek/2023-ICS4-Final-Project/boom.wav");
        test.play();
        new Window();
    }
}