package graficos.componentes;

import graficos.CoresPadrao;
import graficos.Othello;

public class Chat {

  private static final float ALTURA = 714; // 79
  private static final float LARGURA = 311; // 79
  private final float xInicial;
  private final float yInicial;
  private final Othello jogo;

  public Chat(Othello jogo, float xInicial, float yInicial) {
    this.jogo = jogo;
    this.xInicial = xInicial;
    this.yInicial = yInicial;
  }

  public void desenharChat() {
    // Obrigatoriamente estando dentro de processamento em lote
    jogo.processamentoEmLoteFormas.setColor(CoresPadrao.CINZA.cor);
    jogo.processamentoEmLoteFormas.rect(xInicial, yInicial, LARGURA, ALTURA);
  }
}
