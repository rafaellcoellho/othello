package logica;

public class Othello {

  public enum Peca {
    BRANCO,
    PRETO
  }

  public enum Casa {
    VAZIA,
    BRANCO,
    PRETO
  }

  public Casa[][] tabuleiro;
  private Peca turno;
  private final Peca minhaPeca;

  Othello(Peca minhaPeca) {
    turno = Peca.PRETO; // Preto sempre começa
    this.minhaPeca = minhaPeca;
    tabuleiro =
        new Casa[][] {
          {
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA
          },
          {
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA
          },
          {
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA
          },
          {
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.BRANCO,
            Casa.PRETO,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA
          },
          {
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.PRETO,
            Casa.BRANCO,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA
          },
          {
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA
          },
          {
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA
          },
          {
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA,
            Casa.VAZIA
          }
        };
  }

  public void passarTurno() throws ExcecaoNaoPodePassarTurno {
    if (!ehMeuTurno()) {
      throw new ExcecaoNaoPodePassarTurno(minhaPeca);
    }
    turno = turno.equals(Peca.BRANCO) ? Peca.PRETO : Peca.BRANCO;
  }

  public boolean ehMeuTurno() {
    return minhaPeca.equals(turno);
  }

  public void colocarPeca(int linha, int coluna) throws ExcecaoCasaPreenchida {
    if (!tabuleiro[linha][coluna].equals(Casa.VAZIA)) {
      throw new ExcecaoCasaPreenchida(tabuleiro[linha][coluna], linha, coluna);
    }
    tabuleiro[linha][coluna] = minhaPeca.equals(Peca.BRANCO) ? Casa.BRANCO : Casa.PRETO;
  }

  public void retirarPeca(int linha, int coluna) throws ExcecaoCasaVazia {
    if (tabuleiro[linha][coluna].equals(Casa.VAZIA)) {
      throw new ExcecaoCasaVazia(linha, coluna);
    }
    tabuleiro[linha][coluna] = minhaPeca.equals(Peca.BRANCO) ? Casa.BRANCO : Casa.PRETO;
  }

  public void virarPeca(int linha, int coluna) throws ExcecaoCasaVazia {
    if (tabuleiro[linha][coluna].equals(Casa.VAZIA)) {
      throw new ExcecaoCasaVazia(linha, coluna);
    }
    tabuleiro[linha][coluna] =
        tabuleiro[linha][coluna].equals(Casa.BRANCO) ? Casa.PRETO : Casa.BRANCO;
  }

  public boolean houveVencedor() {
    return false;
  }

  public Peca vencedor() {
    return minhaPeca;
  }
}
