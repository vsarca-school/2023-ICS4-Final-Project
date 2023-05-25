package src.main.Drivers;

/**
 * This is an interface implemented by all scenes
 *      - Victor
 */
public interface Scene {
    /**
     * Returns what to change the scene to
     * Options are:
     *  N - Nothing (keep the same)
     *  M - MainMenu
     *  O - Options
     *  L - Lesson
     *  Z - Maze
     *  A - ActionLevel
     * @return what to change the scene to
     */
    public char change();
}
