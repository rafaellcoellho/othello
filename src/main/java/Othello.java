import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Othello extends Game {

    public SpriteBatch processamentoEmLote;
    public BitmapFont fonte;

    @Override
    public void create() {
        fonte = new BitmapFont();
        processamentoEmLote = new SpriteBatch();
        this.setScreen(new TelaDoTabuleiro(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose () {
        fonte.dispose();
        processamentoEmLote.dispose();
    }
}
