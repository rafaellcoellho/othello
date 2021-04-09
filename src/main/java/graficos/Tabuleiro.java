package graficos;

public class Tabuleiro {

    final static private float LADO_QUADRADO = 632; //79
    final static private float ALTURA_BOTAO = 67;
    final static private float LARGURA_BOTAO = 200;
    final private float xInicial;
    final private float yInicial;
    final private Othello jogo;

    Tabuleiro(Othello jogo, float xInicial, float yInicial) {
        this.jogo = jogo;
        this.xInicial = xInicial;
        this.yInicial = yInicial;
    }

    public void desenharTabuleiro() {
        // Obrigatoriamente estando dentro de processamento em lote
        jogo.processamentoEmLoteFormas.setColor(CoresPadrao.QUADRADO.cor);
        jogo.processamentoEmLoteFormas.rect(xInicial, yInicial, LADO_QUADRADO, LADO_QUADRADO);
    }

    public void desenharBotaoPassarTurno() {
        float margemTopo = 15;
        float xInicialBotao = xInicial + (LADO_QUADRADO - LARGURA_BOTAO);
        float yInicialBotao = yInicial - (margemTopo + ALTURA_BOTAO);
        // Obrigatoriamente estando dentro de processamento em lote
        jogo.processamentoEmLoteFormas.setColor(CoresPadrao.BOTAO_PASSAR_TURNO.cor);
        jogo.processamentoEmLoteFormas.rect(xInicialBotao, yInicialBotao, LARGURA_BOTAO, ALTURA_BOTAO);
    }

    public void desenharBotaoDesistir() {
        float margemTopo = 15;
        float margemEntreBotoes = 10;
        float xInicialBotao = xInicial + (LADO_QUADRADO - (2*LARGURA_BOTAO) - margemEntreBotoes);
        float yInicialBotao = yInicial - (margemTopo + ALTURA_BOTAO);
        // Obrigatoriamente estando dentro de processamento em lote
        jogo.processamentoEmLoteFormas.setColor(CoresPadrao.BOTAO_DESISTIR.cor);
        jogo.processamentoEmLoteFormas.rect(xInicialBotao, yInicialBotao, LARGURA_BOTAO, ALTURA_BOTAO);
    }
}
