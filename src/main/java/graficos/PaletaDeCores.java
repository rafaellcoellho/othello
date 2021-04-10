package graficos;

import com.badlogic.gdx.graphics.Color;

public enum PaletaDeCores {
  PRETO(0.192f, 0.180f, 0.168f), // #312e2b
  VERDE(0.462f, 0.588f, 0.337f), // #769656
  VERDE_CONTRASTE(0.933f, 0.933f, 0.823f), // #eeeed2
  CINZA(0.694f, 0.694f, 0.690f), // #b1b1b0
  SUCESSO(0.498f, 0.650f, 0.313f), // #7fa650
  PERIGO(0.701f, 0.203f, 0.188f); // #b33430

  public Color cor;

  PaletaDeCores(float r, float g, float b) {
    cor = new Color(r, g, b, 1);
  }
}
