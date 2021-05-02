package rede;

import utilitarios.Eventos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Jogo extends UnicastRemoteObject implements Comandos {

  public Eventos eventos;
  public Comunicacao comunicacao;

  public Jogo(Comunicacao comunicacao) throws RemoteException {
    super();
    this.comunicacao = comunicacao;
    this.eventos = comunicacao.eventos;
  }

  public void receberMensagem(String mensagemRecebida) throws RemoteException {
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

  public void anunciarConexao() throws RemoteException {
    eventos.notificarObservadores("conectou", new Mensagem("CONE", ""));
  }

  public void passarObjetoLocalParaOponente(Comandos objetoLocalOponente) throws RemoteException {
    comunicacao.registrarObjetoDoOponente(objetoLocalOponente);
  }
}
