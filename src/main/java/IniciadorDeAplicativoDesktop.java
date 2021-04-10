import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import graficos.Constantes;
import graficos.Othello;

public class IniciadorDeAplicativoDesktop {

  public static void main(String[] args) {
    Lwjgl3ApplicationConfiguration configuracaoGeral = new Lwjgl3ApplicationConfiguration();

    configuracaoGeral.setIdleFPS(60);
    configuracaoGeral.setTitle("Othello");
    configuracaoGeral.setWindowedMode(Constantes.LARGURA_JANELA, Constantes.ALTURA_JANELA);
    configuracaoGeral.setResizable(false);

    new Lwjgl3Application(new Othello(), configuracaoGeral);
  }
}
