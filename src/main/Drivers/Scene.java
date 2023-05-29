package src.main.Drivers;

/**
 * This is an interface implemented by all scenes
 * - Victor
 */
public interface Scene {
    public static final int sceneId = -1; // Scene id for the change method

    /**
     * Returns what to change the scene to
     * Options are given by a Scene object's sceneId, shoudl only be valid scene ids
     * from the various scenes
     * 
     * @return What to change the scene to
     */
    public int nextScene();
}
