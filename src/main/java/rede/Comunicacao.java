package rede;

import utilitarios.Eventos;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Comunicacao {

  public enum Papel {
    SERVIDOR,
    CLIENTE
  }

  private Comandos objetoRemotoOponente;
  public Eventos eventos;

  public Comunicacao(Papel papel, String enderecoIp, String nomeServico) {
    eventos =
        new Eventos(
            "mensagemRecebida",
            "receberTurno",
            "clicouBotaoEsquerdoMouse",
            "clicouBotaoDireitoMouse",
            "desistencia",
            "conectou");

    try {
      Jogo objetoLocalJogador = new Jogo(this);
      if (papel.equals(Papel.SERVIDOR)) {
        Registry servidorDeNomes = LocateRegistry.createRegistry(1099);
        servidorDeNomes.bind(nomeServico, objetoLocalJogador);
      } else {
        objetoRemotoOponente = (Comandos) Naming.lookup(String.format("//%s/%s", enderecoIp, nomeServico));
        objetoRemotoOponente.passarObjetoLocalParaOponente(objetoLocalJogador);
        objetoRemotoOponente.anunciarConexao();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void enviarMensagem(String mensagem) {
    try {
      objetoRemotoOponente.receberMensagem(mensagem);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void registrarObjetoDoOponente(Comandos objetoOponente) {
    objetoRemotoOponente = objetoOponente;
  }
}
