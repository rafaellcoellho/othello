package rede;

import utilitarios.Eventos;

public class Comunicacao extends Thread {

  public enum Papel {
    SERVIDOR,
    CLIENTE
  }

  private final String enderecoIp;
  private final String nomeServico;
  private final Papel papel;
  public Eventos eventos;

  public Comunicacao(Papel papel, String enderecoIp, String nomeServico) {
    this.enderecoIp = enderecoIp;
    this.nomeServico = nomeServico;
    this.papel = papel;
    eventos =
        new Eventos(
            "mensagemRecebida",
            "receberTurno",
            "clicouBotaoEsquerdoMouse",
            "clicouBotaoDireitoMouse",
            "desistencia",
            "conectou");

    if (papel.equals(Papel.SERVIDOR)) {
      try {
        System.out.println("TODO: Criar servidor de nomes e registrar obj do servidor nele");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    this.start();
  }

  public void esperarConexao() {
    try {
      System.out.println("TODO: Esperar evento de conexão");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void conectar() {
    try {
      System.out.println("TODO: Recuperar obj remoto do oponente");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void enviarMensagem(String mensagem) {
    try {
      System.out.println("TODO: Chamar função de mensagem do oponente");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void encerrarConexao() {
    try {
      System.out.println("TODO: Encerrar conexão com obj remoto");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run() {
    String mensagemRecebida;
    try {
      if (papel.equals(Papel.SERVIDOR)) {
        esperarConexao();
      } else {
        conectar();
      }
      eventos.notificarObservadores("conectou", new Mensagem("CONE", ""));
      while (!Thread.currentThread().isInterrupted()) {
        mensagemRecebida = "";
        System.out.println("TODO: Receber mensagem");
        Mensagem mensagemFormatada = new Mensagem(mensagemRecebida);
        switch (mensagemFormatada.tipo) {
          case "CHAT":
            eventos.notificarObservadores("mensagemRecebida", mensagemFormatada);
            break;
          case "PROX":
            eventos.notificarObservadores("receberTurno", mensagemFormatada);
            break;
          case "JOGO":
            if (mensagemFormatada.botao.equals("E")) {
              eventos.notificarObservadores("clicouBotaoEsquerdoMouse", mensagemFormatada);
            } else if (mensagemFormatada.botao.equals("D")) {
              eventos.notificarObservadores("clicouBotaoDireitoMouse", mensagemFormatada);
            }
            break;
          case "DESI":
            eventos.notificarObservadores("desistencia", mensagemFormatada);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
