package logica;

public class ExcecaoCasaVazia extends Exception {
    ExcecaoCasaVazia(int linha, int coluna) {
        super(constroiMensagem(linha, coluna));
    }

    static private String constroiMensagem(int linha, int coluna) {
        return String.format("Não existe peça na casa (linha)%d,(coluna)%d", linha, coluna);
    }
}
