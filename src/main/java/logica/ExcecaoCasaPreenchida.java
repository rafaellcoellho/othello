package logica;

public class ExcecaoCasaPreenchida extends Exception {
    ExcecaoCasaPreenchida(Othello.Casa selecionada, int linha, int coluna) {
        super(constroiMensagem(selecionada, linha, coluna));
    }

    static private String constroiMensagem(Othello.Casa selecionada, int linha, int coluna) {
        String peca = selecionada.equals(Othello.Casa.BRANCO)
                ? "branca"
                : "preta";
        return String.format("Já existe uma peça %s na casa (linha)%d,(coluna)%d", peca, linha, coluna);
    }
}
