package rede;

import logica.Logica;

public class Mensagem {

    public String mensagemBruta;
    public String tipo;
    public String corpo;
    public Integer linha;
    public Integer coluna;
    public String botao;

    public Mensagem(String mensagemBruta) {
        this.mensagemBruta = mensagemBruta;

        tipo = mensagemBruta.substring(0, 4);
        corpo = mensagemBruta.substring(6);

        if (tipo.equals("JOGO")) {
            linha = Integer.parseInt(mensagemBruta.substring(8, 9));
            coluna = Integer.parseInt(mensagemBruta.substring(12, 13));

            botao = mensagemBruta.substring(14, 15);
        }
    }

    public Mensagem(String tipo, String corpo) {
        this.tipo = tipo;
        this.corpo = corpo;

        mensagemBruta = String.format("%s: %s", tipo, corpo);
    }

    public Mensagem(Integer linha, Integer coluna, String botao) {
        this.linha = linha;
        this.coluna = coluna;
        this.botao = botao;

        mensagemBruta = String.format("JOGO: L(%d)C(%d)%s", linha, coluna, botao);
    }
}
