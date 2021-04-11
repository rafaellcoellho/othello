package graficos.componentes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class BotaoDePassarTurno extends TextButton {

  public BotaoDePassarTurno(Skin estilo) {
    super("Passar", estilo, "sucesso");
    setHeight(67);
    addListener(passarTurno());
  }

  private ChangeListener passarTurno() {
    return new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        colocarEmEstadoDeEspera();
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
