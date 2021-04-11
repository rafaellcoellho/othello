package graficos.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import graficos.*;
import graficos.componentes.Tabuleiro;

public class TelaDoTabuleiro extends ScreenAdapter {

  Othello jogo;
  private final OrthographicCamera camera;
  private final Stage cena;

  public TelaDoTabuleiro(Othello jogo) {
    this.jogo = jogo;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    cena = new Stage(new ScreenViewport());
    Gdx.input.setInputProcessor(cena);

    Tabuleiro tabuleiro = new Tabuleiro(jogo);

    TextButton botaoDePassarTurno = new TextButton("Passar", jogo.estilo, "sucesso");
    TextButton botaoDeDesistir = new TextButton("Desistir", jogo.estilo, "perigo");
    Label deQuemEhOTurno = new Label("Eh o seu turno", jogo.estilo);

    Table controles = new Table();
    controles.add(deQuemEhOTurno).fill().expand();
    controles.add(botaoDeDesistir).fill().expand();
    controles.add(botaoDePassarTurno).fill().padLeft(15).expand();

    Table tabuleiroComControles = new Table();
    tabuleiroComControles.add(tabuleiro);
    tabuleiroComControles.row();
    tabuleiroComControles.add(controles).height(67).padTop(15).fillX().expand();

    Table tela = new Table();
    tela.setFillParent(true);
    tela.pad(30);
    tela.add(tabuleiroComControles).left().top().expand();

    cena.addActor(tela);
  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(PaletaDeCores.PRETO.cor);
    camera.update();
    cena.act(delta);
    cena.draw();
  }

  @Override
  public void dispose () {
    cena.dispose();
  }
}
