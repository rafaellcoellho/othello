import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        config.setIdleFPS(60);
        config.setTitle("Othello");
        config.setWindowedMode(960, 640);

        new Lwjgl3Application(new Boot(), config);
    }
}
