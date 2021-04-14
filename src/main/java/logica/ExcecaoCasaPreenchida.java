package logica;

public class ExcecaoCasaPreenchida extends RuntimeException {
  ExcecaoCasaPreenchida(Logica.Casa selecionada, int linha, int coluna) {
    super(constroiMensagem(selecionada, linha, coluna));
  }

  private static String constroiMensagem(Logica.Casa selecionada, int linha, int coluna) {
    String peca = selecionada.equals(Logica.Casa.BRANCO) ? "branca" : "preta";
    return String.format("Já existe uma peça %s na casa (linha)%d,(coluna)%d", peca, linha, coluna);
  }
}
