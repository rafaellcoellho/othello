package graficos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Othello extends Game {

    public SpriteBatch processamentoEmLoteSprites;
    public ShapeRenderer processamentoEmLoteFormas;
    public BitmapFont fonte;

    @Override
    public void create() {
        fonte = new BitmapFont();
        processamentoEmLoteSprites = new SpriteBatch();
        processamentoEmLoteFormas = new ShapeRenderer();
        this.setScreen(new TelaDoTabuleiro(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose () {
        fonte.dispose();
        processamentoEmLoteSprites.dispose();
        processamentoEmLoteFormas.dispose();
    }
}
