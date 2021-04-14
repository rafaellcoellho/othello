package graficos.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import graficos.Othello;
import graficos.PaletaDeCores;

public class TelaInicial extends ScreenAdapter {

  Othello jogo;
  private final OrthographicCamera camera;
  private final Stage cena;
  private final TextButton botaoDeGerenciarPartida;
  private final TextButton botaoDeConectarEmPartida;
  private final Image logoDoJogo;

  public TelaInicial(Othello jogo) {
    this.jogo = jogo;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    cena = new Stage(new ScreenViewport());
    Gdx.input.setInputProcessor(cena);

    botaoDeGerenciarPartida = new TextButton("Criar partida", jogo.estilo, "sucesso");
    botaoDeGerenciarPartida.setHeight(67);
    botaoDeGerenciarPartida.addListener(irParaTelaDeEspera());

    botaoDeConectarEmPartida = new TextButton("Conectar", jogo.estilo, "perigo");
    botaoDeConectarEmPartida.setHeight(67);
    botaoDeConectarEmPartida.addListener(irParaTelaDeInput());

    Texture imagem = new Texture(Gdx.files.internal("assets/logo.png"));
    logoDoJogo = new Image(imagem);

    construirLayout();
  }

  private void construirLayout() {
    Table tela = new Table();
    tela.setFillParent(true);
    tela.pad(30);
    tela.add(logoDoJogo).expand();
    tela.row();
    tela.add(botaoDeGerenciarPartida).expandX().height(67).width(200).bottom().padBottom(5);
    tela.row();
    tela.add(botaoDeConectarEmPartida).expand().height(67).width(200).top();

    cena.addActor(tela);
//    cena.setDebugAll(true);
  }

  private ChangeListener irParaTelaDeEspera() {
    return new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
//        jogo.setScreen();
      }
    };
  }

  private ChangeListener irParaTelaDeInput() {
    return new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
//        jogo.setScreen();
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
