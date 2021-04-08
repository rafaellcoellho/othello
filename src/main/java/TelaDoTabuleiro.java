import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class TelaDoTabuleiro implements Screen {

    Othello jogo;
    OrthographicCamera camera;

    public TelaDoTabuleiro(Othello jogo) {
        this.jogo = jogo;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constantes.LARGURA_JANELA, Constantes.ALTURA_JANELA);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(CoresPadrao.FUNDO.cor);

        camera.update();
        jogo.processamentoEmLote.begin();
        // Fazer algo
        jogo.processamentoEmLote.end();
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
