import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import graficos.Othello;

public class IniciadorDeAplicativoDesktop {
  public static final int ALTURA_JANELA = 774;
  public static final int LARGURA_JANELA = 1033;

  public static void main(String[] args) {
    Lwjgl3ApplicationConfiguration configuracaoGeral = new Lwjgl3ApplicationConfiguration();

    configuracaoGeral.setIdleFPS(60);
    configuracaoGeral.setTitle("Othello");
    configuracaoGeral.setWindowedMode(LARGURA_JANELA, ALTURA_JANELA);
    configuracaoGeral.setResizable(false);

    new Lwjgl3Application(new Othello(), configuracaoGeral);
  }
}
