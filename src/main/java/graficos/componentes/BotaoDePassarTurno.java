package graficos.componentes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import graficos.Othello;
import graficos.telas.TelaFinal;
import logica.Logica;
import rede.Mensagem;
import utilitarios.Observador;

public class BotaoDePassarTurno extends TextButton implements Observador {

  private final Othello jogo;
  private final Stage cena;
  private final OrthographicCamera camera;

  public BotaoDePassarTurno(Othello jogo, OrthographicCamera camera, Stage cena) {
    super("Passar", jogo.estilo, "sucesso");
    this.jogo = jogo;
    this.cena = cena;
    this.camera = camera;
    setHeight(67);
    addListener(passarTurno());
  }

  private ChangeListener passarTurno() {
    return new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        if (jogo.estado.ehMeuTurno()) {
          jogo.estado.passarTurno();

          if (jogo.estado.jogoAcabou()) {
            String vencedor = jogo.estado.vencedor();

            jogo.setScreen(new TelaFinal(jogo, camera, cena, vencedor, false));
          }

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
      if (jogo.estado.jogoAcabou()) {
        String vencedor = jogo.estado.vencedor();

        jogo.setScreen(new TelaFinal(jogo, camera, cena, vencedor, false));
      }
    }
  }
}
