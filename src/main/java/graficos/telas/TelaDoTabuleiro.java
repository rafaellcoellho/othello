package graficos.telas;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import graficos.*;
import graficos.componentes.*;
import logica.Logica;

public class TelaDoTabuleiro extends ScreenAdapter {

  Othello jogo;
  private final OrthographicCamera camera;
  private final Stage cena;
  private final Tabuleiro tabuleiro;
  private final BotaoDePassarTurno botaoDePassarTurno;
  private final BotaoDeDesistir botaoDeDesistir;
  private final IndicadorDeTurno deQuemEhOTurno;
  private final Chat chat;

  public TelaDoTabuleiro(Othello jogo, OrthographicCamera camera, Stage cena) {
    this.jogo = jogo;
    this.camera = camera;
    this.cena = cena;

    tabuleiro = new Tabuleiro(jogo);

    botaoDePassarTurno = new BotaoDePassarTurno(jogo, camera, cena);
    botaoDeDesistir = new BotaoDeDesistir(jogo, camera, cena);
    deQuemEhOTurno = new IndicadorDeTurno(jogo.estilo);
    chat = new Chat(jogo);

    jogo.estado.eventos.adicionarObservador("passarTurno", deQuemEhOTurno);
    jogo.estado.eventos.adicionarObservador("passarTurno", botaoDeDesistir);
    jogo.estado.eventos.adicionarObservador("passarTurno", botaoDePassarTurno);
    if (jogo.estado.minhaPeca.equals(Logica.Peca.BRANCO)) {
      jogo.estado.eventos.notificarObservadores("passarTurno", jogo.estado);
    }

    jogo.comunicacao.eventos.adicionarObservador("mensagemRecebida", chat);
    jogo.comunicacao.eventos.adicionarObservador("receberTurno", deQuemEhOTurno);
    jogo.comunicacao.eventos.adicionarObservador("receberTurno", botaoDeDesistir);
    jogo.comunicacao.eventos.adicionarObservador("receberTurno", botaoDePassarTurno);
    jogo.comunicacao.eventos.adicionarObservador("receberTurno", tabuleiro);
    jogo.comunicacao.eventos.adicionarObservador("clicouBotaoEsquerdoMouse", tabuleiro);
    jogo.comunicacao.eventos.adicionarObservador("clicouBotaoDireitoMouse", tabuleiro);
    jogo.comunicacao.eventos.adicionarObservador("desistencia", botaoDeDesistir);

    construirLayout();
  }

  private void construirLayout() {
    Table controles = new Table();
    controles.add(deQuemEhOTurno).fill().expand();
    controles.add(botaoDeDesistir).fill().prefWidth(100).expand();
    controles.add(botaoDePassarTurno).fill().prefWidth(100).padLeft(15).expand();

    Table tabuleiroComControles = new Table();
    tabuleiroComControles.add(tabuleiro);
    tabuleiroComControles.row();
    tabuleiroComControles.add(controles).height(67).padTop(15).fillX().expand();

    Table tela = new Table();
    tela.setFillParent(true);
    tela.pad(30);
    tela.add(tabuleiroComControles).padRight(30);
    tela.add(chat).fill().expand();

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
    jogo.estado.eventos.removerObservador("passarTurno", deQuemEhOTurno);
    jogo.estado.eventos.removerObservador("passarTurno", botaoDeDesistir);
    jogo.estado.eventos.removerObservador("passarTurno", botaoDePassarTurno);

    jogo.comunicacao.eventos.removerObservador("mensagemRecebida", chat);
    jogo.comunicacao.eventos.removerObservador("receberTurno", deQuemEhOTurno);
    jogo.comunicacao.eventos.removerObservador("receberTurno", botaoDeDesistir);
    jogo.comunicacao.eventos.removerObservador("receberTurno", botaoDePassarTurno);
    jogo.comunicacao.eventos.removerObservador("receberTurno", tabuleiro);
    jogo.comunicacao.eventos.removerObservador("clicouBotaoEsquerdoMouse", tabuleiro);
    jogo.comunicacao.eventos.removerObservador("clicouBotaoDireitoMouse", tabuleiro);
    jogo.comunicacao.eventos.removerObservador("desistencia", botaoDeDesistir);
  }
}
