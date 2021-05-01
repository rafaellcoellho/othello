package graficos.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import graficos.Othello;
import graficos.PaletaDeCores;
import logica.Logica;
import rede.Comunicacao;

public class TelaInicial extends ScreenAdapter {

  Othello jogo;
  private final OrthographicCamera camera;
  private final Stage cena;
  private final TextButton botaoDeGerenciarPartida;
  private final TextButton botaoDeConectarEmPartida;
  private final Image logoDoJogo;
  private final Label enderecoIpLabel;
  private final TextField enderecoIp;
  private final Label portaLabel;
  private final TextField porta;

  public TelaInicial(Othello jogo) {
    this.jogo = jogo;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    cena = new Stage(new ScreenViewport());
    Gdx.input.setInputProcessor(cena);

    botaoDeGerenciarPartida = new TextButton("Criar partida", jogo.estilo, "sucesso");
    botaoDeGerenciarPartida.addListener(irParaTelaDeEspera());

    botaoDeConectarEmPartida = new TextButton("Conectar", jogo.estilo, "perigo");
    botaoDeConectarEmPartida.addListener(irParaTelaDeInput());

    Texture imagem = new Texture(Gdx.files.internal("assets/logo.png"));
    logoDoJogo = new Image(imagem);

    enderecoIpLabel = new Label("Endereco IP:", jogo.estilo);
    enderecoIp = new TextField("localhost", jogo.estilo);
    portaLabel = new Label("Nome Servico:", jogo.estilo);
    porta = new TextField("othello", jogo.estilo);

    construirLayout();
  }

  private void construirLayout() {
    Table entradas = new Table();
    entradas.add(enderecoIpLabel);
    entradas.add(enderecoIp).padLeft(15);
    entradas.row();
    entradas.add(portaLabel).right();
    entradas.add(porta).padLeft(15);

    Table tela = new Table();
    tela.setFillParent(true);
    tela.pad(30);
    tela.add(logoDoJogo).expand().colspan(2);
    tela.row();
    tela.add(entradas).expand().colspan(2);
    tela.row();
    tela.add(botaoDeGerenciarPartida).expandY().height(67).width(200).right().padRight(5);
    tela.add(botaoDeConectarEmPartida).expandY().height(67).width(200).left();

    cena.addActor(tela);
  }

  private ChangeListener irParaTelaDeEspera() {
    return new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        cena.clear();
        jogo.comunicacao =
            new Comunicacao(Comunicacao.Papel.SERVIDOR, enderecoIp.getText(), porta.getText());
        jogo.estado = new Logica(Logica.Peca.PRETO);

        jogo.setScreen(new TelaDeEspera(jogo, camera, cena));
      }
    };
  }

  private ChangeListener irParaTelaDeInput() {
    return new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        cena.clear();
        jogo.comunicacao =
            new Comunicacao(Comunicacao.Papel.CLIENTE, enderecoIp.getText(), porta.getText());
        jogo.estado = new Logica(Logica.Peca.BRANCO);
        jogo.setScreen(new TelaDoTabuleiro(jogo, camera, cena));
      }
    };
  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(PaletaDeCores.PRETO.cor);
    camera.update();
    cena.act(delta);
    cena.draw();
  }

  @Override
  public void dispose() {}
}
