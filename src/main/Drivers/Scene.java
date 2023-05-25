package src.main.Drivers;

/**
 * This is an interface implemented by all scenes
 *      - Victor
 */
public interface Scene {
    /**
     * Returns what to change the scene to
     * Options are:
     *  0 - Nothing (keep the same)
     *  1 - MainMenu
     *  2 - Options
     *  X3 - Lesson X
     *  X4 - Maze X
     *  X5 - ActionLevel X
     * @return what to change the scene to
     */
    public int change();
}
