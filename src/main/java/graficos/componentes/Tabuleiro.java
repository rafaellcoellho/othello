package graficos.componentes;

import com.badlogic.gdx.graphics.Color;
import graficos.CoresPadrao;
import graficos.Othello;

import java.util.List;

public class Tabuleiro {

  private static final float LADO_QUADRADO = 79;
  private static final float ALTURA_BOTAO = 67;
  private static final float LARGURA_BOTAO = 200;
  private final float xInicial;
  private final float yInicial;
  private final Othello jogo;

  public Tabuleiro(Othello jogo, float xInicial, float yInicial) {
    this.jogo = jogo;
    this.xInicial = xInicial;
    this.yInicial = yInicial;
  }

  static Color corDoQuadrado(Integer linha, Integer coluna) {
    List<Integer> linhasIniciandoComQuadradoEscuro = List.of(0, 2, 4, 6);
    boolean colunaEhPar = (coluna % 2) == 0;

    if (linhasIniciandoComQuadradoEscuro.contains(linha)) {
      return colunaEhPar ? CoresPadrao.VERDE.cor : CoresPadrao.VERDE_CONTRASTE.cor;
    }

    return colunaEhPar ? CoresPadrao.VERDE_CONTRASTE.cor : CoresPadrao.VERDE.cor;
  }

  public void desenharTabuleiro() {
    // Obrigatoriamente estando dentro de processamento em lote
    for (int linha = 0; linha < 8; linha++) {
      for (int coluna = 0; coluna < 8; coluna++) {
        float x = xInicial + (linha * LADO_QUADRADO);
        float y = yInicial + (coluna * LADO_QUADRADO);
        jogo.processamentoEmLoteFormas.setColor(corDoQuadrado(linha, coluna));
        jogo.processamentoEmLoteFormas.rect(x, y, LADO_QUADRADO, LADO_QUADRADO);
      }
    }
  }

  public void desenharBotaoPassarTurno() {
    float margemTopo = 15;
    float xInicialBotao = xInicial + ((LADO_QUADRADO * 8) - LARGURA_BOTAO);
    float yInicialBotao = yInicial - (margemTopo + ALTURA_BOTAO);

    // Obrigatoriamente estando dentro de processamento em lote
    jogo.processamentoEmLoteFormas.setColor(CoresPadrao.SUCESSO.cor);
    jogo.processamentoEmLoteFormas.rect(xInicialBotao, yInicialBotao, LARGURA_BOTAO, ALTURA_BOTAO);
  }

  public void desenharBotaoDesistir() {
    float margemTopo = 15;
    float margemEntreBotoes = 10;
    float xInicialBotao =
        xInicial + ((LADO_QUADRADO * 8) - (2 * LARGURA_BOTAO) - margemEntreBotoes);
    float yInicialBotao = yInicial - (margemTopo + ALTURA_BOTAO);

    // Obrigatoriamente estando dentro de processamento em lote
    jogo.processamentoEmLoteFormas.setColor(CoresPadrao.PERIGO.cor);
    jogo.processamentoEmLoteFormas.rect(xInicialBotao, yInicialBotao, LARGURA_BOTAO, ALTURA_BOTAO);
  }
}
