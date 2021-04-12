package graficos.componentes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Chat extends Table {

  public enum Origem {
    MINHA,
    ADVERSARIO
  }

  private final ScrollPane areaDeMensagens;
  private final TextArea textoDoUsuario;
  private final VerticalGroup linhas;
  private final Skin estilo;

  public Chat(Skin estilo) {
    this.estilo = estilo;

    linhas = new VerticalGroup();
    linhas.grow();

    areaDeMensagens = new ScrollPane(linhas, estilo);
    areaDeMensagens.setFadeScrollBars(false);
    areaDeMensagens.setOverscroll(false, false);

    textoDoUsuario = new TextArea("", estilo, "chat");
    TextButton botaoDeEnviar = new TextButton("Enviar", estilo, "sucesso");

    botaoDeEnviar.addListener(mandarMensagem());

    Table textoComBotaoDeEnviar = new Table();
    textoComBotaoDeEnviar.add(textoDoUsuario).grow();
    textoComBotaoDeEnviar.add(botaoDeEnviar).fillY().prefWidth(70);

    add(areaDeMensagens).growX().height(635).width(311);
    row();
    add(textoComBotaoDeEnviar).fillX().height(67).padTop(15);
  }

  public void adicionarMensagem(Origem origem, String mensagem) {
    Label labelMensagem = new Label(mensagem, estilo);
    labelMensagem.setWrap(true);

    Label labelOrigem =
        origem == Origem.MINHA
            ? new Label("Voce:", estilo, "azul")
            : new Label("Adversario:", estilo, "vermelho");

    Table linha = new Table();

    linha.add(labelOrigem).top();
    linha.add(labelMensagem).growX();

    linhas.addActor(linha);
    areaDeMensagens.scrollTo(0, 0, 0, 0);
  }

  private ChangeListener mandarMensagem() {
    return new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        String mensagemParaEnviar = textoDoUsuario.getText();
        if (!mensagemParaEnviar.equals("")) {
          adicionarMensagem(Origem.MINHA, mensagemParaEnviar);
          textoDoUsuario.setText("");
        }
      }
    };
  }
}
