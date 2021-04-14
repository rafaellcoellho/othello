package graficos.componentes;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import graficos.Othello;
import graficos.PaletaDeCores;
import logica.Logica;
import rede.Mensagem;
import utilitarios.Observador;

public class Tabuleiro extends Actor implements Observador {

  private final int LADO_QUADRADO = 79;
  private final ShapeRenderer renderer;
  private final Othello jogo;

  public Tabuleiro(Othello jogo) {
    this.jogo = jogo;
    renderer = jogo.shapeRenderer;
    setWidth(LADO_QUADRADO * 8);
    setHeight(LADO_QUADRADO * 8);
    addListener(clicouNoTabuleiroBotaoEsquerdo());
    addListener(clicouNoTabuleiroBotaoDireito());
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

        if (!jogo.estado.tabuleiro.get(linha).get(coluna).equals(Logica.Casa.VAZIA)) {
          float xCentroQuadrado = x + (float) (LADO_QUADRADO / 2);
          float yCentroQuadrado = y + (float) (LADO_QUADRADO / 2);
          desenharCirculo(xCentroQuadrado, yCentroQuadrado, pegarCorDaPeca(linha, coluna));
        }
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

  private Color pegarCorDaPeca(int linha, int coluna) {
    Logica.Casa peca = jogo.estado.tabuleiro.get(linha).get(coluna);
    return peca.equals(Logica.Casa.PRETO) ? PaletaDeCores.PRETO.cor : PaletaDeCores.BRANCO.cor;
  }

  private void desenharQuadrado(float x, float y, Color cor) {
    renderer.setColor(cor);
    renderer.rect(x, y, LADO_QUADRADO, LADO_QUADRADO);
  }

  private void desenharCirculo(float x, float y, Color cor) {
    renderer.setColor(cor);
    int RAIO_CIRCULO = 30;
    renderer.circle(x, y, RAIO_CIRCULO);
  }

  private ClickListener clicouNoTabuleiroBotaoEsquerdo() {
    return new ClickListener(Input.Buttons.LEFT) {
      public void clicked(InputEvent event, float x, float y) {
        if (jogo.estado.ehMeuTurno()) {
          int linha = (int) Math.ceil(x / 79);
          int coluna = (int) Math.ceil(y / 79);
          jogo.estado.clicouBotaoEsquerdo(linha - 1, coluna - 1);

          Mensagem mensagem = new Mensagem(linha - 1, coluna - 1, "E");
          jogo.comunicacao.enviarMensagem(mensagem.mensagemBruta);
        }
      }
    };
  }

  private ClickListener clicouNoTabuleiroBotaoDireito() {
    return new ClickListener(Input.Buttons.RIGHT) {
      public void clicked(InputEvent event, float x, float y) {
        if (jogo.estado.ehMeuTurno()) {
          int linha = (int) Math.ceil(x / 79);
          int coluna = (int) Math.ceil(y / 79);
          jogo.estado.clicouBotaoDireito(linha - 1, coluna - 1);
        }
      }
    };
  }

  @Override
  public void reagir(String tipoDeEvento, Logica estado) {}

  @Override
  public void reagir(String tipoDeEvento, Mensagem mensagem) {
    if (tipoDeEvento.equals("receberTurno")) {
      jogo.estado.receberTurno();
    } else if (tipoDeEvento.equals("clicouBotaoEsquerdoMouse")) {
      jogo.estado.clicouBotaoEsquerdo(mensagem.linha, mensagem.coluna);
    }
  }
}
