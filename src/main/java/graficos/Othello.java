package graficos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import graficos.telas.TelaDoTabuleiro;

public class Othello extends Game {

  public BitmapFont fonte;
  public Skin estilo;

  @Override
  public void create() {
    fonte = new BitmapFont();
    estilo = new Skin(Gdx.files.internal("assets/uiskin.json"));
    this.setScreen(new TelaDoTabuleiro(this));
  }

  @Override
  public void render() {
    super.render();
  }

  @Override
  public void dispose() {
    fonte.dispose();
    estilo.dispose();
  }
}
