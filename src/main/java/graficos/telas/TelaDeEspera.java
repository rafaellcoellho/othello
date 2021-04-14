package graficos.telas;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import graficos.Othello;
import graficos.PaletaDeCores;
import logica.Logica;
import rede.Mensagem;
import utilitarios.Observador;

public class TelaDeEspera extends ScreenAdapter implements Observador {

  Othello jogo;
  private final OrthographicCamera camera;
  private final Stage cena;
  private final Label mensagemDeEspera;

  public TelaDeEspera(Othello jogo, OrthographicCamera camera, Stage cena) {
    this.jogo = jogo;
    this.camera = camera;
    this.cena = cena;

    mensagemDeEspera = new Label("Aguarde enquanto a partida comeca...", jogo.estilo, "azul");
    jogo.comunicacao.eventos.adicionarObservador("conectou", this);

    construirLayout();
  }

  private void comecarJogo() {
    cena.clear();
    jogo.setScreen(new TelaDoTabuleiro(jogo, camera, cena));
  }

  private void construirLayout() {
    Table tela = new Table();
    tela.setFillParent(true);
    tela.pad(30);
    tela.add(mensagemDeEspera).expand();

    cena.addActor(tela);
  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(PaletaDeCores.PRETO.cor);
    camera.update();
    cena.act(delta);
    cena.draw();
  }

  @Override
  public void dispose() {
    jogo.comunicacao.eventos.removerObservador("conectou", this);
  }

  @Override
  public void reagir(String tipoDeEvento, Logica estado) {}

  @Override
  public void reagir(String tipoDeEvento, Mensagem mensagem) {
    if (tipoDeEvento.equals("conectou")) {
      comecarJogo();
    }
  }
}
