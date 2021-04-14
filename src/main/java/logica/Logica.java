package logica;

import utilitarios.Eventos;

import java.util.ArrayList;

public class Logica {

  public enum Peca {
    BRANCO,
    PRETO
  }

  public enum Casa {
    VAZIA,
    BRANCO,
    PRETO
  }

  public ArrayList<ArrayList<Casa>> tabuleiro;
  private Peca turno;
  public final Peca minhaPeca;
  public Eventos eventos;

  public Logica(Peca minhaPeca) {
    turno = Peca.PRETO; // Preto sempre começa
    this.minhaPeca = minhaPeca;
    eventos = new Eventos("passarTurno");

    tabuleiro = new ArrayList<>(8);
    for (int linha = 0; linha < 8; linha++) {
      tabuleiro.add(new ArrayList<>(8));
      for (int coluna = 0; coluna < 8; coluna++) {
        boolean ehPecaInicialPreta = (linha == 3 && coluna == 3) || (linha == 4 && coluna == 4);
        boolean ehPecaInicialBranca = (linha == 3 && coluna == 4) || (linha == 4 && coluna == 3);
        if (ehPecaInicialPreta) {
          tabuleiro.get(linha).add(Casa.PRETO);
        } else if (ehPecaInicialBranca) {
          tabuleiro.get(linha).add(Casa.BRANCO);
        } else {
          tabuleiro.get(linha).add(Casa.VAZIA);
        }
      }
    }
  }

  public void passarTurno() throws ExcecaoNaoPodePassarTurno {
    if (!ehMeuTurno()) {
      throw new ExcecaoNaoPodePassarTurno(minhaPeca);
    }
    turno = turno.equals(Peca.BRANCO) ? Peca.PRETO : Peca.BRANCO;
    eventos.notificarObservadores("passarTurno", this);
  }

  public void receberTurno() {
    turno = turno.equals(Peca.BRANCO) ? Peca.PRETO : Peca.BRANCO;
  }

  public boolean ehMeuTurno() {
    return minhaPeca.equals(turno);
  }

  public void colocarPeca(int linha, int coluna) {
    if (!tabuleiro.get(linha).get(coluna).equals(Casa.VAZIA)) {
      throw new ExcecaoCasaPreenchida(tabuleiro.get(linha).get(coluna), linha, coluna);
    }
    tabuleiro.get(linha).set(coluna, turno.equals(Peca.BRANCO) ? Casa.BRANCO : Casa.PRETO);
  }

  public void retirarPeca(int linha, int coluna) {
    if (tabuleiro.get(linha).get(coluna).equals(Casa.VAZIA)) {
      throw new ExcecaoCasaVazia(linha, coluna);
    }
    tabuleiro.get(linha).set(coluna, Casa.VAZIA);
  }

  public void virarPeca(int linha, int coluna) {
    if (tabuleiro.get(linha).get(coluna).equals(Casa.VAZIA)) {
      throw new ExcecaoCasaVazia(linha, coluna);
    }
    Casa peca = tabuleiro.get(linha).get(coluna).equals(Casa.BRANCO) ? Casa.PRETO : Casa.BRANCO;
    tabuleiro.get(linha).set(coluna, peca);
  }

  public void clicouBotaoEsquerdo(int linha, int coluna) {
    Casa casa = tabuleiro.get(linha).get(coluna);

    if (casa.equals(Casa.VAZIA)) {
      colocarPeca(linha, coluna);
    } else {
      virarPeca(linha, coluna);
    }
  }

  public void clicouBotaoDireito(int linha, int coluna) {
    Casa casa = tabuleiro.get(linha).get(coluna);

    if (!casa.equals(Casa.VAZIA)) {
      retirarPeca(linha, coluna);
    }
  }

  public boolean houveVencedor() {
    return false;
  }

  public Peca vencedor() {
    return minhaPeca;
  }
}
