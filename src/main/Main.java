package src.main;
import src.main.Sound;
import src.main.Window;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Poopoo 2 3!");
        Sound test = new Sound("src/main/Sounds/boom.wav");
        Sound amogus = new Sound("src/main/Sounds/amongus.wav");
        Sound duck = new Sound("src/main/Sounds/duck.wav");
        duck.play();
        amogus.loop(5);
        new Window();
    }
}