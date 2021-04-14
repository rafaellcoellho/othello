package rede;

import logica.Logica;

public class Mensagem {

    public String mensagemBruta;
    public String tipo;
    public String corpo;
    public Integer linha;
    public Integer coluna;
    public Logica.Casa casa;

    public Mensagem(String mensagemBruta) {
        this.mensagemBruta = mensagemBruta;

        tipo = mensagemBruta.substring(0, 4);
        corpo = mensagemBruta.substring(6);

        if (tipo.equals("JOGO")) {
            linha = Integer.parseInt(mensagemBruta.substring(8, 9));
            coluna = Integer.parseInt(mensagemBruta.substring(12, 13));

            String peca = mensagemBruta.substring(14, 15);
            switch (peca) {
                case "V":
                    casa = Logica.Casa.VAZIA;
                    break;
                case "P":
                    casa = Logica.Casa.PRETO;
                    break;
                case "B":
                    casa = Logica.Casa.BRANCO;
                    break;
            }
        }
    }

    public Mensagem(String tipo, String corpo) {
        this.tipo = tipo;
        this.corpo = corpo;

        mensagemBruta = String.format("%s: %s", tipo, corpo);
    }

    public Mensagem(Integer linha, Integer coluna, Logica.Casa casa) {
        this.linha = linha;
        this.coluna = coluna;
        this.casa = casa;

        String casaFormatada;
        if (casa.equals(Logica.Casa.VAZIA)) {
            casaFormatada = "V";
        } else if (casa.equals(Logica.Casa.PRETO)) {
            casaFormatada = "P";
        } else {
            casaFormatada = "B";
        }

        mensagemBruta = String.format("JOGO: L(%d)C(%d)%s", linha, coluna, casaFormatada);
    }
}
