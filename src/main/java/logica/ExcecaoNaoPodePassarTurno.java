package logica;

public class ExcecaoNaoPodePassarTurno extends RuntimeException {
  ExcecaoNaoPodePassarTurno(Logica.Peca jogador) {
    super(constroiMensagem(jogador));
  }

  private static String constroiMensagem(Logica.Peca jogador) {
    String peca = jogador.equals(Logica.Peca.BRANCO) ? "brancas" : "pretas";
    return String.format(
        "O jogador das peças %s tentou passar o turno quando não era sua vez", peca);
  }
}
