package graficos;

public class Chat {

    final static private float ALTURA = 714; //79
    final static private float LARGURA = 311; //79
    final private float xInicial;
    final private float yInicial;
    final private Othello jogo;

    Chat(Othello jogo, float xInicial, float yInicial) {
        this.jogo = jogo;
        this.xInicial = xInicial;
        this.yInicial = yInicial;
    }

    public void desenharChat() {
        // Obrigatoriamente estando dentro de processamento em lote
        jogo.processamentoEmLoteFormas.setColor(CoresPadrao.FUNDO_CHAT.cor);
        jogo.processamentoEmLoteFormas.rect(xInicial, yInicial, LARGURA, ALTURA);
    }
}
