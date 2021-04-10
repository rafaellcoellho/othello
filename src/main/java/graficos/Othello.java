package graficos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import graficos.telas.TelaDoTabuleiro;

public class Othello extends Game {

  public BitmapFont fonte;

  @Override
  public void create() {
    fonte = new BitmapFont();
    this.setScreen(new TelaDoTabuleiro(this));
  }

  @Override
  public void render() {
    super.render();
  }

  @Override
  public void dispose() {
    fonte.dispose();
  }
}
