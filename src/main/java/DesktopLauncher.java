import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration configuracaoGeral = new Lwjgl3ApplicationConfiguration();

        configuracaoGeral.setIdleFPS(60);
        configuracaoGeral.setTitle("Othello");
        configuracaoGeral.setWindowedMode(960, 640);

        new Lwjgl3Application(new Boot(), configuracaoGeral);
    }
}
