package graficos.componentes;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import graficos.PaletaDeCores;
import logica.Logica;
import rede.Mensagem;
import utilitarios.Observador;

public class IndicadorDeTurno extends Table implements Observador {

  private final Label setaJogador;
  private final Label setaOponente;
  private final Label jogador;
  private final Label oponente;

  public IndicadorDeTurno(Skin estilo) {
    setaJogador = new Label(">>", estilo, "indicador-de-turno");
    setaOponente = new Label(">>", estilo, "indicador-de-turno");
    jogador = new Label("Voce", estilo);
    oponente = new Label("Adversario", estilo);

    add(setaJogador);
    add(jogador).expand();
    row();
    add(setaOponente);
    add(oponente).expand();

    selecionaTurnoDoJogador();
  }

  public void selecionaTurnoDoJogador() {
    setaJogador.setVisible(true);
    jogador.setColor(PaletaDeCores.AZUL.cor);
    setaOponente.setVisible(false);
    oponente.setColor(PaletaDeCores.BRANCO.cor);
  }

  public void selecionaTurnoDoOponente() {
    setaJogador.setVisible(false);
    jogador.setColor(PaletaDeCores.BRANCO.cor);
    setaOponente.setVisible(true);
    oponente.setColor(PaletaDeCores.AZUL.cor);
  }

  @Override
  public void reagir(String tipoDeEvento, Logica estado) {
    if (tipoDeEvento.equals("passarTurno")) {
      selecionaTurnoDoOponente();
    }
  }

  @Override
  public void reagir(String tipoDeEvento, Mensagem mensagem) {
    if (tipoDeEvento.equals("receberTurno")) {
      selecionaTurnoDoJogador();
    }
  }
}
