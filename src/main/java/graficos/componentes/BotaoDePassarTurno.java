package graficos.componentes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import graficos.Othello;
import logica.Logica;
import rede.Mensagem;
import utilitarios.Observador;

public class BotaoDePassarTurno extends TextButton implements Observador {

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

          Mensagem mensagem = new Mensagem("PROX", "");
          jogo.comunicacao.enviarMensagem(mensagem.mensagemBruta);
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

  @Override
  public void reagir(String tipoDeEvento, Logica estado) {
    if (tipoDeEvento.equals("passarTurno")) {
      colocarEmEstadoDeEspera();
    }
  }

  @Override
  public void reagir(String tipoDeEvento, Mensagem mensagem) {
    if (tipoDeEvento.equals("receberTurno")) {
      colocarEmEstadoNormal();
    }
  }
}
