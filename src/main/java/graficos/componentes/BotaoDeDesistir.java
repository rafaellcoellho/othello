package graficos.componentes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import logica.Logica;
import utilitarios.Observador;

public class BotaoDeDesistir extends TextButton implements Observador {

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

        Dialog janelaDeConfirmacao =
            new Dialog("Desistir", estilo, "dialog") {
              protected void result(Object resposta) {
                boolean desistiu = (boolean) resposta;
                if (desistiu) {
                  System.out.println("Desistir... tururu");
                }
              }
            };

        janelaDeConfirmacao.text(mensagem);
        janelaDeConfirmacao.button(confirmar, true);
        janelaDeConfirmacao.button(voltar, false);
        janelaDeConfirmacao.show(cena);
      }
    };
  }

  @Override
  public void reagir(String tipoDeEvento, Logica estado) {
    if (tipoDeEvento.equals("passarTurno")) {
      setDisabled(!estado.ehMeuTurno());
    }
  }
}
