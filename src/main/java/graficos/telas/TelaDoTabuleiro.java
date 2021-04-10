package graficos.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import graficos.*;
import graficos.componentes.Chat;
import graficos.componentes.Tabuleiro;

public class TelaDoTabuleiro implements Screen {

  Othello jogo;
  OrthographicCamera camera;
  Tabuleiro tabuleiro;
  Chat chat;

  public TelaDoTabuleiro(Othello jogo) {
    this.jogo = jogo;
    tabuleiro = new Tabuleiro(jogo, 30, 112);
    chat = new Chat(jogo, 692, 30);

    camera = new OrthographicCamera();
    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(CoresPadrao.PRETO.cor);

    camera.update();

    jogo.processamentoEmLoteSprites.begin();
    // Fazer algo
    jogo.processamentoEmLoteSprites.end();

    jogo.processamentoEmLoteFormas.begin(ShapeRenderer.ShapeType.Filled);
    tabuleiro.desenharTabuleiro();
    tabuleiro.desenharBotaoPassarTurno();
    tabuleiro.desenharBotaoDesistir();
    chat.desenharChat();
    jogo.processamentoEmLoteFormas.end();
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
