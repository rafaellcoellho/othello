package rede;

import utilitarios.Eventos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Comunicacao extends Thread {

  public enum Papel {
    SERVIDOR,
    CLIENTE
  }

  private ServerSocket socketDeServidor;
  private Socket socket;
  private DataOutputStream streamDeSaida;
  private DataInputStream streamDeEntrada;
  private final String enderecoIp;
  private final Integer porta;
  private final Papel papel;
  public Eventos eventos;

  public Comunicacao(Papel papel, String enderecoIp, Integer porta) {
    this.enderecoIp = enderecoIp;
    this.porta = porta;
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
        socketDeServidor = new ServerSocket(porta);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    this.start();
  }

  public void esperarConexao() {
    try {
      socket = socketDeServidor.accept();
      streamDeEntrada = new DataInputStream(socket.getInputStream());
      streamDeSaida = new DataOutputStream(socket.getOutputStream());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void conectar() {
    try {
      socket = new Socket(enderecoIp, porta);
      streamDeEntrada = new DataInputStream(socket.getInputStream());
      streamDeSaida = new DataOutputStream(socket.getOutputStream());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void enviarMensagem(String mensagem) {
    try {
      streamDeSaida.writeUTF(mensagem);
      streamDeSaida.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void encerrarConexao() {
    try {
      socket.close();
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
        mensagemRecebida = streamDeEntrada.readUTF();
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
