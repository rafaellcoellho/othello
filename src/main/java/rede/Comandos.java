package rede;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Comandos extends Remote {
  void receberMensagem(String mensagemRecebida) throws RemoteException;

  void anunciarConexao() throws RemoteException;

  void passarObjetoLocalParaOponente(Comandos objetoLocalOponente) throws RemoteException;
}
