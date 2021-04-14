package graficos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import graficos.telas.TelaDoTabuleiro;
import logica.Logica;
import rede.Comunicacao;

public class Othello extends Game {

  public BitmapFont fonte;
  public Skin estilo;
  public ShapeRenderer shapeRenderer;
  public Logica estado;
  public Comunicacao comunicacao;

  public Othello(String arg) {
    Comunicacao.Papel papel = arg.equals("servidor") ? Comunicacao.Papel.SERVIDOR : Comunicacao.Papel.CLIENTE;
    comunicacao = new Comunicacao(papel, "localhost", 9090);

    if (papel.equals(Comunicacao.Papel.SERVIDOR)) {
      System.out.println("Esperando conexão...");
      comunicacao.esperarConexao();
      System.out.println("Sucesso...");
    } else {
      System.out.println("Tentando conexão...");
      comunicacao.conectar();
      System.out.println("Sucesso...");
    }
  }

  @Override
  public void create() {
    fonte = new BitmapFont();
    estilo = new Skin(Gdx.files.internal("assets/uiskin.json"));
    shapeRenderer = new ShapeRenderer();
    estado = new Logica(Logica.Peca.PRETO);
    this.setScreen(new TelaDoTabuleiro(this));
  }

  @Override
  public void render() {
    super.render();
  }

  @Override
  public void dispose() {
    fonte.dispose();
    estilo.dispose();
    shapeRenderer.dispose();
  }
}
