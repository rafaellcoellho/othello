package graficos.componentes;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import graficos.PaletaDeCores;

public class Chat extends Table {

  private final ScrollPane areaDeMensagens;
  private final TextArea textoDoUsuario;
  private final TextButton botaoDeEnviar;
  private final Table linhas;

  public Chat(Skin estilo) {
    linhas = new Table();
    areaDeMensagens = new ScrollPane(linhas, estilo);
    textoDoUsuario = new TextArea("", estilo, "chat");
    botaoDeEnviar = new TextButton("Enviar", estilo, "sucesso");

    Table textoComBotaoDeEnviar = new Table();
    textoComBotaoDeEnviar.add(textoDoUsuario).expand().fill();
    textoComBotaoDeEnviar.add(botaoDeEnviar).prefWidth(70).fillY();

    add(areaDeMensagens).fill().expand();
    row();
    add(textoComBotaoDeEnviar).fillX().height(67).padTop(15);
  }
}
