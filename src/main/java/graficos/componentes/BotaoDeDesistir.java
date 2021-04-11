package graficos.componentes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class BotaoDeDesistir extends TextButton {

  public BotaoDeDesistir(Skin estilo) {
    super("Desistir", estilo, "perigo");
    addListener(desistir());
  }

  private ChangeListener desistir() {
    return new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        System.out.println("Desistir... tururu");
      }
    };
  }
}
