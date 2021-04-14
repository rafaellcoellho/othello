package graficos.componentes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import graficos.Othello;

public class BotaoDePassarTurno extends TextButton {

  private final Othello jogo;

  public BotaoDePassarTurno(Othello jogo) {
    super("Passar", jogo.estilo, "sucesso");
    this.jogo = jogo;
    setHeight(67);
    addListener(passarTurno());
  }

  private ChangeListener passarTurno() {
    return new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        if (jogo.estado.ehMeuTurno()) {
          jogo.estado.passarTurno();
          colocarEmEstadoDeEspera();
        }
      }
    };
  }

  public void colocarEmEstadoDeEspera() {
    setText("Aguarde...");
    setDisabled(true);
  }

  public void colocarEmEstadoNormal() {
    setText("Passar");
    setDisabled(false);
  }
}
