package utilitarios;

import logica.Logica;
import rede.Mensagem;

public interface Observador {

  void reagir(String tipoDeEvento, Logica estado);
  void reagir(String tipoDeEvento, Mensagem mensagem);
}
