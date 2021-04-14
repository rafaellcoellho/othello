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

public class TelaFinal extends ScreenAdapter {

  Othello jogo;
  private final OrthographicCamera camera;
  private final Stage cena;
  private final TextButton botaoDeEncerrarJogo;
  private final Label mensagemFinal;

  public TelaFinal(Othello jogo, OrthographicCamera camera, Stage cena, boolean vencedor) {
    this.jogo = jogo;
    this.camera = camera;
    this.cena = cena;

    botaoDeEncerrarJogo = new TextButton("Encerrar", jogo.estilo, "sucesso");
    botaoDeEncerrarJogo.addListener(fecharJogo());

    if (vencedor) {
      mensagemFinal = new Label("Parabens. Otima partida!", jogo.estilo, "vermelho");
    } else {
      mensagemFinal = new Label("Voce perdeu. Ate a proxima partida!", jogo.estilo, "azul");
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
