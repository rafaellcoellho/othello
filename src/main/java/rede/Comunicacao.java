package rede;

import utilitarios.Eventos;
import utilitarios.Observador;

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
  public Eventos eventos;

  public Comunicacao(Papel papel, String enderecoIp, Integer porta) {
    this.enderecoIp = enderecoIp;
    this.porta = porta;
    eventos = new Eventos("mensagemRecebida", "receberTurno");

    if (papel.equals(Papel.SERVIDOR)) {
      try {
        socketDeServidor = new ServerSocket(porta);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void esperarConexao() {
    try {
      socket = socketDeServidor.accept();
      streamDeEntrada = new DataInputStream(socket.getInputStream());
      streamDeSaida = new DataOutputStream(socket.getOutputStream());
      this.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void conectar() {
    try {
      socket = new Socket(enderecoIp, porta);
      streamDeEntrada = new DataInputStream(socket.getInputStream());
      streamDeSaida = new DataOutputStream(socket.getOutputStream());
      this.start();
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
      while (!Thread.currentThread().isInterrupted()) {
        mensagemRecebida = streamDeEntrada.readUTF();
        Mensagem mensagemFormatada = new Mensagem(mensagemRecebida);
        if (mensagemFormatada.tipo.equals("CHAT")) {
          eventos.notificarObservadores("mensagemRecebida", mensagemFormatada);
        } else if (mensagemFormatada.tipo.equals("PROX")) {
          eventos.notificarObservadores("receberTurno", mensagemFormatada);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
