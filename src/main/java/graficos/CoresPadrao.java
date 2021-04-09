package graficos;

import com.badlogic.gdx.graphics.Color;

public enum CoresPadrao {

    FUNDO(0.192f, 0.180f, 0.168f), //#312e2b
    QUADRADO(0.462f, 0.588f, 0.337f), //#769656
    BOTAO_PASSAR_TURNO(0.498f, 0.650f, 0.313f), //#7fa650
    FUNDO_CHAT(0.694f, 0.694f, 0.690f), //#b1b1b0
    BOTAO_DESISTIR(0.701f, 0.203f, 0.188f); //#b33430

    public Color cor;

    CoresPadrao(float r, float g, float b) {
        cor = new Color(r, g, b, 1);
    }
}
