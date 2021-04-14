package graficos.componentes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import graficos.Othello;
import logica.Logica;
import rede.Mensagem;
import utilitarios.Observador;

public class BotaoDeDesistir extends TextButton implements Observador {

  private final Skin estilo;
  private final Stage cena;
  private final Othello jogo;

  public BotaoDeDesistir(Othello jogo, Stage cena) {
    super("Desistir", jogo.estilo, "perigo");
    this.estilo = jogo.estilo;
    this.cena = cena;
    this.jogo = jogo;
    addListener(desistir());
  }

  private ChangeListener desistir() {
    return new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        String mensagemConfirmacao = "Tem certeza que deseja desistir?";
        TextButton confirmar = new TextButton("Sim", estilo, "perigo");
        TextButton voltar = new TextButton("Voltar", estilo, "default");

        Dialog janelaDeConfirmacao =
            new Dialog("Desistir", estilo, "dialog") {
              protected void result(Object resposta) {
                boolean desistiu = (boolean) resposta;
                if (desistiu) {
                  System.out.println("Desistir... tururu");

                  Mensagem mensagem = new Mensagem("DESI", "");
                  jogo.comunicacao.enviarMensagem(mensagem.mensagemBruta);
                }
              }
            };

        janelaDeConfirmacao.text(mensagemConfirmacao);
        janelaDeConfirmacao.button(confirmar, true);
        janelaDeConfirmacao.button(voltar, false);
        janelaDeConfirmacao.show(cena);
      }
    };
  }

  @Override
  public void reagir(String tipoDeEvento, Logica estado) {
    if (tipoDeEvento.equals("passarTurno")) {
      setDisabled(true);
    }
  }

  @Override
  public void reagir(String tipoDeEvento, Mensagem mensagem) {
    if (tipoDeEvento.equals("receberTurno")) {
      setDisabled(false);
    } else if (tipoDeEvento.equals("desistencia")) {
      System.out.println("Ganhou!... uhu");
    }
  }
}
