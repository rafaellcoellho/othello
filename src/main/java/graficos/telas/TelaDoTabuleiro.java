package graficos.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import graficos.*;

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
