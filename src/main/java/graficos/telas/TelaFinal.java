package graficos.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import graficos.Othello;
import graficos.PaletaDeCores;
import logica.Logica;

public class TelaFinal extends ScreenAdapter {

  Othello jogo;
  private final OrthographicCamera camera;
  private final Stage cena;
  private final TextButton botaoDeEncerrarJogo;
  private final Label mensagemFinal;

  public TelaFinal(
      Othello jogo, OrthographicCamera camera, Stage cena, String vencedor, boolean desistencia) {
    this.jogo = jogo;
    this.camera = camera;
    this.cena = cena;

    botaoDeEncerrarJogo = new TextButton("Encerrar", jogo.estilo, "sucesso");
    botaoDeEncerrarJogo.addListener(fecharJogo());

    if (!vencedor.equals("")) {
      if (vencedor.equals("Branco") && jogo.estado.minhaPeca.equals(Logica.Peca.BRANCO)
          || vencedor.equals("Preto") && jogo.estado.minhaPeca.equals(Logica.Peca.PRETO)) {
        mensagemFinal = new Label("Parabens. Otima partida!", jogo.estilo, "azul");
      } else if (vencedor.equals("Empate")) {
        mensagemFinal = new Label("Empate. Otima partida!", jogo.estilo, "azul");
      } else {
        mensagemFinal = new Label("Voce perdeu. Ate a proxima partida!", jogo.estilo, "vermelho");
      }
    } else {
      if (desistencia) {
        mensagemFinal = new Label("Parabens. Otima partida!", jogo.estilo, "azul");
      } else {
        mensagemFinal = new Label("Voce perdeu. Ate a proxima partida!", jogo.estilo, "vermelho");
      }
    }

    cena.clear();
    construirLayout();
  }

  private void construirLayout() {
    Table tela = new Table();
    tela.setFillParent(true);
    tela.pad(30);
    tela.add(mensagemFinal).expand();
    tela.row();
    tela.add(botaoDeEncerrarJogo).expandY().height(67).width(200).top();

    cena.addActor(tela);
  }

  private ChangeListener fecharJogo() {
    return new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        Gdx.app.exit();
        System.exit(0);
        jogo.comunicacao.encerrarConexao();
      }
    };
  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(PaletaDeCores.PRETO.cor);
    camera.update();
    cena.act(delta);
    cena.draw();
  }

  @Override
  public void dispose() {
    cena.dispose();
  }
}
