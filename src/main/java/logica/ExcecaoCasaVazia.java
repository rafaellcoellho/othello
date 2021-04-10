package logica;

public class ExcecaoCasaVazia extends RuntimeException {
  ExcecaoCasaVazia(int linha, int coluna) {
    super(constroiMensagem(linha, coluna));
  }

  private static String constroiMensagem(int linha, int coluna) {
    return String.format("Não existe peça na casa (linha)%d,(coluna)%d", linha, coluna);
  }
}
