package graficos.componentes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class BotaoDeDesistir extends TextButton {

  private final Skin estilo;
  private final Stage cena;

  public BotaoDeDesistir(Skin estilo, Stage cena) {
    super("Desistir", estilo, "perigo");
    this.estilo = estilo;
    this.cena = cena;
    addListener(desistir());
  }

  private ChangeListener desistir() {
    return new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        String mensagem = "Tem certeza que deseja desistir?";
        TextButton confirmar = new TextButton("Sim", estilo, "perigo");
        TextButton voltar = new TextButton("Voltar", estilo, "default");
;
        new Dialog("Desistir", estilo, "dialog") {
          protected void result(Object resposta) {
            boolean desistiu = (boolean) resposta;
            if (desistiu) {
              System.out.println("Desistir... tururu");
            }
          }
        }.text(mensagem).button(confirmar, true).button(voltar, false).show(cena);

      }
    };
  }
}
