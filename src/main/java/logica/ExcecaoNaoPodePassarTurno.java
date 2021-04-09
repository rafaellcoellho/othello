package logica;

public class ExcecaoNaoPodePassarTurno extends Exception {
    ExcecaoNaoPodePassarTurno(Othello.Peca jogador) {
        super(constroiMensagem(jogador));
    }

    static private String constroiMensagem(Othello.Peca jogador) {
        String peca = jogador.equals(Othello.Peca.BRANCO)
                ? "brancas"
                : "pretas";
        return String.format("O jogador das peças %s tentou passar o turno quando não era sua vez", peca);
    }
}
