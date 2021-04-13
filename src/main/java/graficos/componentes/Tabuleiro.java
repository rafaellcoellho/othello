package graficos.componentes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import graficos.Othello;
import graficos.PaletaDeCores;

public class Tabuleiro extends Actor {

  private final int LADO_QUADRADO = 79;
  private final int RAIO_CIRCULO = 25;
  private final ShapeRenderer renderer;

  public Tabuleiro(Othello jogo) {
    renderer = jogo.shapeRenderer;
    setWidth(LADO_QUADRADO * 8);
    setHeight(LADO_QUADRADO * 8);
    addListener(clicouNoTabuleiro());
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    batch.end();

    renderer.setProjectionMatrix(batch.getProjectionMatrix());
    renderer.setTransformMatrix(batch.getTransformMatrix());
    renderer.translate(getX(), getY(), 0);

    renderer.begin(ShapeRenderer.ShapeType.Filled);
    for (int linha = 0; linha < 8; linha++) {
      for (int coluna = 0; coluna < 8; coluna++) {
        float x = linha * LADO_QUADRADO;
        float y = coluna * LADO_QUADRADO;
        desenharQuadrado(x, y, pegarCorDoQuadrado(linha, coluna));
      }
    }
    renderer.end();

    batch.begin();
  }

  private Color pegarCorDoQuadrado(int linha, int coluna) {
    boolean linhaEhPar = (linha % 2) == 0;
    boolean colunaEhPar = (coluna % 2) == 0;

    if (linhaEhPar) {
      return colunaEhPar ? PaletaDeCores.VERDE.cor : PaletaDeCores.VERDE_CONTRASTE.cor;
    }

    return colunaEhPar ? PaletaDeCores.VERDE_CONTRASTE.cor : PaletaDeCores.VERDE.cor;
  }

  private void desenharQuadrado(float x, float y, Color cor) {
    renderer.setColor(cor);
    renderer.rect(x, y, LADO_QUADRADO, LADO_QUADRADO);
  }

  private void desenharCirculo(float x, float y, Color cor) {
    renderer.setColor(cor);
    renderer.circle(x, y, RAIO_CIRCULO);
  }

  private ClickListener clicouNoTabuleiro() {
    return new ClickListener() {
      public void clicked(InputEvent event, float x, float y) {
        int linha = (int) Math.ceil(y/79);
        int coluna = (int) Math.ceil(x/79);
        System.out.printf("X: %f | Y: %f | Linha: %d | Coluna: %d\n", x, y, linha, coluna);
      }
    };
  }
}
