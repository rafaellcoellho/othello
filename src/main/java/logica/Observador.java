package logica;

public interface Observador {

  void reagir(String tipoDeEvento, Logica estado);
}
