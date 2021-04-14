package utilitarios;

import logica.Logica;

public interface Observador {

  void reagir(String tipoDeEvento, Logica estado);
}
