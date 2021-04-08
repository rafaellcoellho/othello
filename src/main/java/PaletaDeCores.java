import com.badlogic.gdx.graphics.Color;

public enum PaletaDeCores{

    FUNDO(0.192f, 0.180f, 0.168f), //#312e2b
    QUADRADO(0.462f, 0.588f, 0.337f), //#769656
    BOTAO(0.498f, 0.650f, 0.313f); //#7fa650

    public Color cor;

    PaletaDeCores(float r, float g, float b) {
        cor = new Color(r, g, b, 1);
    }
}
