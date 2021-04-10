package graficos.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import graficos.*;

public class TelaDoTabuleiro implements Screen {

  Othello jogo;
  OrthographicCamera camera;

  public TelaDoTabuleiro(Othello jogo) {
    this.jogo = jogo;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(PaletaDeCores.PRETO.cor);
    camera.update();
  }

  @Override
  public void resize(int largura, int altura) {}

  @Override
  public void show() {}

  @Override
  public void hide() {}

  @Override
  public void pause() {}

  @Override
  public void resume() {}

  @Override
  public void dispose() {}
}
